package org.objser.Objser;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Objser {

	private final ObjectMapper mapper;
	
	private final ObjectReflectionUtilities instantiationUtilities;

	private final ObjserSettings settings;

	public Objser(ObjserSettings settings) {
		this.settings = settings;
		this.mapper = new ObjectMapper();
		this.instantiationUtilities = new ObjectReflectionUtilities(settings);
	}
	
	public Objser() {
		this(new ObjserSettings());
	}

	public <T> String serialize(T object) {
		ObjectNode node = newNode();
		try {
			serialize(object, node);
		} catch(Exception e) {
			e.printStackTrace();
		}
		if (settings.isFormattedPrint()) {
			return node.toPrettyString();
		}else {
			return node.toString();
		}
	}
	
	private void serialize(Object object, ObjectNode node) throws Exception {
		Class clazz = object.getClass();
		
		Optional<ObjserLeaf> optionalLeaf = settings.getLeaf(clazz);
		if (optionalLeaf.isPresent()) {
			 ObjserLeaf leaf = optionalLeaf.get();
			 node.put(ObjserConstants.CLASS_NAME_KEYNAME, leaf.getLeafTypeName());
			 leaf.serialize(object, node);
		}
		else{
			node.put(ObjserConstants.CLASS_NAME_KEYNAME, object.getClass().getCanonicalName());
			if (isEnum(clazz)) {
				serializeEnum((Enum) object, node);
			}else if (isCollection(clazz)) {
				Collection collection = (Collection) object;
				serializeCollection(collection, node);
			}else if (isArray(clazz)) {
				Object[] array = (Object[]) object;
				serializeArray(array, node);
			}else if (isMap(clazz)) {
				Map<Object, Object> map = (Map<Object, Object>) object;
				serializeMap(map, node);
			}else {
				try {
					serializeObject(object, node);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public <T> T deserialize(String serialized) {
		try {
			JsonNode node = mapper.readTree(serialized);
			return (T)deserialize(node);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Object deserialize(JsonNode node) throws Exception {
		String objectClass = node.get(ObjserConstants.CLASS_NAME_KEYNAME).asText();
		Optional<ObjserLeaf> optionalLeaf = settings.getLeaf(objectClass);
		if (optionalLeaf.isPresent()) {
			ObjserLeaf leaf = optionalLeaf.get();
			return leaf.deserialize(node);
		}else {
			String objectClassWithoutSquareBrackets = objectClass.replace("[]", "");
			Class clazz = Class.forName(objectClassWithoutSquareBrackets);
			
			if (isEnum(clazz)) {
				return deserializeEnum(clazz, node);
			}else if (isCollection(clazz)) {
				return deserializeCollection(clazz, node);
			}else if (isArray(objectClass)) {
				return deserializeArray(clazz, node);
			}else if (isMap(clazz)) {
				return deserializeMap(clazz, node);
			}else {
				return deserializeObject(clazz, node);
			}
		}
	}
	
	////////////////////////////////
	// Object
	////////////////////////////////
	
	private void serializeObject(Object object, ObjectNode node) throws Exception {
		Collection<Field> instanceFields = instantiationUtilities.getAllFields(object.getClass()).values();
		for (Field instanceField : instanceFields) {
			boolean fieldExcluded = settings.getFieldExclusionStrategies().parallelStream().anyMatch(strategy -> strategy.isExcluded(instanceField));
			if(!fieldExcluded) {
				instanceField.setAccessible(true);
				Object childObject = instanceField.get(object);
				if (childObject != null) {
					ObjectNode childNode = newNode();
					serialize(childObject, childNode);
					node.set(instanceField.getName(), childNode);
				}
			}
		}
	}
	
	private Object deserializeObject(Class clazz, JsonNode node) throws Exception {
		Object instance = instantiationUtilities.getObjectInstance(clazz);
		Iterator<Entry<String, JsonNode>> nodes = node.fields();
		Map<String, Field> fields = instantiationUtilities.getAllFields(clazz);
		while(nodes.hasNext()) {
			Entry<String, JsonNode> entry = nodes.next();
			if(entry.getKey().equals(ObjserConstants.CLASS_NAME_KEYNAME) == false) {
				Object fieldName = entry.getKey();
				Object value = deserialize(entry.getValue());
				Field field = fields.get(fieldName);
				field.setAccessible(true);
				field.set(instance, value);
			}
		}
		return instance;
	}
	
	////////////////////////////////
	// Map
	////////////////////////////////
	
	private boolean isMap(Class clazz) {
		return Map.class.isAssignableFrom(clazz);
	}
	
	private void serializeMap(Map<Object, Object> map, ObjectNode node) throws Exception {
		ArrayNode arrayNode = newArray();
		for(Entry entry : map.entrySet()) {
			ObjectNode containerNode = newNode();
			
			ObjectNode keyNode = newNode();
			serialize(entry.getKey(), keyNode);
			containerNode.set(ObjserConstants.KEY_KEYNAME, keyNode);
			
			ObjectNode valueNode = newNode();
			serialize(entry.getValue(), valueNode);
			containerNode.set(ObjserConstants.VALUE_KEYNAME, valueNode);
			
			arrayNode.add(containerNode);
		}
		node.set(ObjserConstants.VALUES_KEYNAME, arrayNode);
	}
	
	public Map deserializeMap(Class clazz, JsonNode node) throws Exception{
		Map map = (Map)instantiationUtilities.getObjectInstance(clazz);
		ArrayNode entries = (ArrayNode)node.get(ObjserConstants.VALUES_KEYNAME);
		for(JsonNode entry : entries) {
			Object key = deserialize(entry.get(ObjserConstants.KEY_KEYNAME));
			Object value = deserialize(entry.get(ObjserConstants.VALUE_KEYNAME));
			map.put(key, value);
		}
		
		return map;
	}
	
	////////////////////////////////
	// Enum
	////////////////////////////////

	private boolean isEnum(Class clazz) {
		return clazz.isEnum() || clazz.equals(Enum.class);
	}
	
	private void serializeEnum(Enum enumeration, ObjectNode node){
		 node.put("_value", enumeration.name());
	}
	
	public Enum deserializeEnum(Class clazz, JsonNode node) {
		String value = node.get(ObjserConstants.VALUE_KEYNAME).asText();
		return Enum.valueOf(clazz, value);
	}
	
	////////////////////////////////
	// Collection
	////////////////////////////////
	
	private boolean isCollection(Class clazz) {
		return Collection.class.isAssignableFrom(clazz);
	}

	private void serializeCollection(Collection collection, ObjectNode node) throws Exception {
		ArrayNode arrayNode = newArray();
		for(Object childObject : collection) {
			ObjectNode childNode = newNode();
			serialize(childObject, childNode);
			arrayNode.add(childNode);
		}
		node.set(ObjserConstants.VALUES_KEYNAME, arrayNode);
	}
	
	private Collection deserializeCollection(Class clazz, JsonNode node) throws Exception {
		Collection collection = (Collection) instantiationUtilities.getObjectInstance(clazz);
		ArrayNode values = (ArrayNode)node.get(ObjserConstants.VALUES_KEYNAME);
		for(JsonNode value : values) {
			collection.add(deserialize(value));
		}
		return collection;
	}
	
	////////////////////////////////
	// Array
	////////////////////////////////
	
	private boolean isArray(Class clazz) {
		return clazz.isArray();
	}
	
	private boolean isArray(String className) {
		return className.endsWith("[]");
	}
	
	private void serializeArray(Object[] array, ObjectNode node) throws Exception {
		ArrayNode arrayNode = newArray();
		for(Object childObject : array) {
			ObjectNode childNode = newNode();
			serialize(childObject, childNode);
			arrayNode.add(childNode);
		}
		node.set(ObjserConstants.VALUES_KEYNAME, arrayNode);
	}
	
	private Object[] deserializeArray(Class clazz, JsonNode node) throws Exception {
		ArrayNode values = (ArrayNode)node.get(ObjserConstants.VALUES_KEYNAME);
		Object[] array = (Object[]) Array.newInstance(clazz, values.size());
		int arrayIndex = 0;
		for(JsonNode value : values) {
			array[arrayIndex] = deserialize(value);
			arrayIndex++;
		}
		return array;
	}

	////////////////////////////////
	// Utilities
	////////////////////////////////


	private ObjectNode newNode() {
		return mapper.createObjectNode();
	}
	
	private ArrayNode newArray() {
		return mapper.createArrayNode();
	}

}

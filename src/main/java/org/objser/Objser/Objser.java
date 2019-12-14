package org.objser.Objser;

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

	private ObjectMapper mapper;

	private ObjserSettings settings;

	public Objser(ObjserSettings settings) {
		this.mapper = new ObjectMapper();
		this.settings = settings;
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
		String objectClass = node.get(settings.getClassKey()).asText();
		Optional<ObjserLeaf> optionalLeaf = settings.getLeaf(objectClass);
		if (optionalLeaf.isPresent()) {
			ObjserLeaf leaf = optionalLeaf.get();
			return leaf.deserialize(node);
		}else {
			Class clazz = Class.forName(objectClass);
			
			if (isEnum(clazz)) {
				return deserializeEnum(clazz, node);
			}else if (isCollection(clazz)) {
				return deserializeCollection(clazz, node);
			}else if (isArray(clazz)) {
				return deserializeArray(clazz, node);
			}else if (isMap(clazz)) {
				return deserializeMap(clazz, node);
			}else {
				return deserializeObject(clazz, node);
			}
		}
	}
	
	private Object deserializeObject(Class clazz, JsonNode node) throws Exception {
		Object instance = getObjectInstance(clazz);
		Iterator<Entry<String, JsonNode>> nodes = node.fields();
		Map<String, Field> fields = getAllFields(clazz);
		while(nodes.hasNext()) {
			Entry<String, JsonNode> entry = nodes.next();
			if(entry.getKey().equals(settings.getClassKey()) == false) {
				Object fieldName = entry.getKey();
				Object value = deserialize(entry.getValue());
				Field field = fields.get(fieldName);
				field.setAccessible(true);
				field.set(instance, value);
			}
		}
		return instance;
	}
	
	public Map deserializeMap(Class clazz, JsonNode node) throws Exception{
		Map map = (Map)getObjectInstance(clazz);
		ArrayNode entries = (ArrayNode)node.get("_values");
		for(JsonNode entry : entries) {
			Object key = deserialize(entry.get("_key"));
			Object value = deserialize(entry.get("_value"));
			map.put(key, value);
		}
		
		return map;
	}
	
	public Enum deserializeEnum(Class clazz, JsonNode node) {
		String value = node.get("_value").asText();
		return Enum.valueOf(clazz, value);
	}
	
	public Collection deserializeCollection(Class clazz, JsonNode node) throws Exception {
		Collection collection = (Collection) getObjectInstance(clazz);
		ArrayNode values = (ArrayNode)node.get("_values");
		for(JsonNode value : values) {
			collection.add(deserialize(value));
		}
		return collection;
	}
	
	public Object[] deserializeArray(Class clazz, JsonNode node) throws Exception {
		ArrayNode values = (ArrayNode)node.get("_values");
		Object[] array = new Object[node.size()];
		int arrayIndex = 0;
		for(JsonNode value : values) {
			array[arrayIndex] = deserialize(value);
			arrayIndex++;
		}
		return array;
	}

	
	
	private Object getObjectInstance(Class clazz) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		
		Constructor constructors[] = clazz.getDeclaredConstructors();
		Object obj = null;

		Constructor selectedConstructor = null;
		Class[] selectedConstructorParams = new Class[] {};

		for (Constructor cons : constructors) {
			Class[] params = cons.getParameterTypes();
			if (params.length == 0) {
				// No-args constructor, priorizing it.
				selectedConstructorParams = params;
				selectedConstructor = cons;
				break;
			} else if (params.length > selectedConstructorParams.length) {
				// Taking the constructor with the most arguments.
				selectedConstructorParams = params;
				selectedConstructor = cons;
			}
		}
		Object[] defaults = getDefaults(selectedConstructorParams);
		obj = selectedConstructor.newInstance(defaults);

		return obj;
	}
	
	private Object[] getDefaults(Class[] classArray) {
		Object[] defaults = new Object[classArray.length];
		for(int i = 0; i < classArray.length; i++) {
			boolean isLeaf = settings.isLeaf(classArray[i]);
			if (isLeaf) {
				defaults[i] = settings.getDefaultValue(classArray[i]);
 			}else {
				defaults[i] = null;
			}
		}
		return defaults;
	}

	private void serialize(Object object, ObjectNode node) throws Exception {
		Class clazz = object.getClass();
		
		Optional<ObjserLeaf> optionalLeaf = settings.getLeaf(clazz);
		if (optionalLeaf.isPresent()) {
			 ObjserLeaf leaf = optionalLeaf.get();
			 node.put(settings.getClassKey(), leaf.getLeafTypeName());
			 leaf.serialize(object, node);
		}
		else{
			node.put(settings.getClassKey(), object.getClass().getCanonicalName());
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
	
	private void serializeEnum(Enum enumeration, ObjectNode node){
		 node.put("_value", enumeration.name());
	}

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
		node.set("_values", arrayNode);
	}

	private boolean isArray(Class clazz) {
		return clazz.isArray();
	}
	
	private void serializeArray(Object[] array, ObjectNode node) throws Exception {
		ArrayNode arrayNode = newArray();
		for(Object childObject : array) {
			ObjectNode childNode = newNode();
			serialize(childObject, childNode);
			arrayNode.add(childNode);
		}
		node.set("_values", arrayNode);
	}
	
	private boolean isMap(Class clazz) {
		return Map.class.isAssignableFrom(clazz);
	}
	
	private void serializeMap(Map<Object, Object> map, ObjectNode node) throws Exception {
		ArrayNode arrayNode = newArray();
		for(Entry entry : map.entrySet()) {
			ObjectNode containerNode = newNode();
			
			ObjectNode keyNode = newNode();
			serialize(entry.getKey(), keyNode);
			containerNode.set("_key", keyNode);
			
			ObjectNode valueNode = newNode();
			serialize(entry.getValue(), valueNode);
			containerNode.set("_value", valueNode);
			
			arrayNode.add(containerNode);
		}
		node.set("_values", arrayNode);
	}

	private boolean isEnum(Class clazz) {
		return clazz.isEnum() || clazz.equals(Enum.class);
	}
	
	private Map<String, Field> getAllFields(Class clazz) {
		Map<String, Field> fields = new HashMap<String, Field>();
		for(Field field : clazz.getDeclaredFields()) {
			fields.put(field.getName(), field);
		}
        
        Class superclass = clazz.getSuperclass();
        while(superclass != null && !superclass.equals(Object.class)) {
        	 fields.putAll(getAllFields(clazz.getSuperclass()));
        	 superclass = superclass.getSuperclass();
        }
        return fields;
    }
	
	private void serializeObject(Object object, ObjectNode node) throws Exception {
		Collection<Field> instanceFields = getAllFields(object.getClass()).values();
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

	private ObjectNode newNode() {
		return mapper.createObjectNode();
	}
	
	private ArrayNode newArray() {
		return mapper.createArrayNode();
	}

}

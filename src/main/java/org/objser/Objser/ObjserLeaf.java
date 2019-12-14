package org.objser.Objser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class ObjserLeaf {
	
	private final String leafTypeName;
	private final Object defaultValue;
	
	public ObjserLeaf( String leafTypeName, Object defaultValue) {
		this.leafTypeName = leafTypeName;
		this.defaultValue = defaultValue;
	}

	protected String getLeafTypeName() {
		return leafTypeName;
	}

	protected Object getDefaultValue() {
		return defaultValue;
	}
	
	public abstract void serialize(Object object, ObjectNode node) throws Exception;
	
	public abstract Object deserialize(JsonNode node) throws Exception;
	
	public abstract boolean isLeaf(Class clazz);
}

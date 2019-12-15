package org.objser.Objser;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

class ObjserDefaultLeafs {

	private final List<ObjserLeaf> leafs = new ArrayList<>();

	protected ObjserDefaultLeafs() {
		
		///////////////
		// Byte
		///////////////

		leafs.add(new ObjserLeaf("byte", (byte) 0) {
			@Override
			public Object deserialize(JsonNode node) {
				return Byte.parseByte(node.get("_value").asText());
			}

			@Override
			public void serialize(Object object, ObjectNode node) {
				node.put("_value", ((Byte) object).toString());
			}

			@Override
			public boolean isLeaf(Class clazz) {
				return clazz.equals(byte.class);
			}
		});
		
		leafs.add(new ObjserLeaf("Byte", null) {
			@Override
			public Object deserialize(JsonNode node) {
				return Byte.parseByte(node.get("_value").asText());
			}

			@Override
			public void serialize(Object object, ObjectNode node) {
				node.put("_value", ((Byte) object).toString());
			}

			@Override
			public boolean isLeaf(Class clazz) {
				return clazz.equals(Byte.class);
			}
		});
		
		///////////////
		// Char
		///////////////
		
		leafs.add(new ObjserLeaf("char", '\u0000') {
			@Override
			public Object deserialize(JsonNode node) {
				return node.get("_value").asText().charAt(0);
			}

			@Override
			public void serialize(Object object, ObjectNode node) {
				node.put("_value", ((Character) object).toString());
			}

			@Override
			public boolean isLeaf(Class clazz) {
				return clazz.equals(char.class);
			}
		});
		
		leafs.add(new ObjserLeaf("Char", null) {
			@Override
			public Object deserialize(JsonNode node) {
				return node.get("_value").asText().charAt(0);
			}

			@Override
			public void serialize(Object object, ObjectNode node) {
				node.put("_value", ((Character) object).toString());
			}

			@Override
			public boolean isLeaf(Class clazz) {
				return clazz.equals(Character.class);
			}
		});

		///////////////
		// Short
		///////////////
		
		leafs.add(new ObjserLeaf("short", (short) 0) {
			@Override
			public Object deserialize(JsonNode node) {
				return Short.parseShort(node.get("_value").asText());
			}

			@Override
			public void serialize(Object object, ObjectNode node) {
				node.put("_value", ((Short) object).toString());
			}

			@Override
			public boolean isLeaf(Class clazz) {
				return clazz.equals(short.class);
			}
		});
		
		leafs.add(new ObjserLeaf("Short", null) {
			@Override
			public Object deserialize(JsonNode node) {
				return Short.parseShort(node.get("_value").asText());
			}

			@Override
			public void serialize(Object object, ObjectNode node) {
				node.put("_value", ((Short) object).toString());
			}

			@Override
			public boolean isLeaf(Class clazz) {
				return clazz.equals(Short.class);
			}
		});
		
		///////////////
		// Int
		///////////////

		leafs.add(new ObjserLeaf("int", 0) {
			@Override
			public Object deserialize(JsonNode node) {
				return node.get("_value").asInt();
			}

			@Override
			public void serialize(Object object, ObjectNode node) {
				node.put("_value", ((Integer) object).toString());
			}

			@Override
			public boolean isLeaf(Class clazz) {
				return clazz.equals(int.class);
			}
		});
		
		leafs.add(new ObjserLeaf("Int", null) {
			@Override
			public Object deserialize(JsonNode node) {
				return node.get("_value").asInt();
			}

			@Override
			public void serialize(Object object, ObjectNode node) {
				node.put("_value", ((Integer) object).toString());
			}

			@Override
			public boolean isLeaf(Class clazz) {
				return clazz.equals(Integer.class) ;
			}
		});
		
		///////////////
		// Long
		///////////////

		leafs.add(new ObjserLeaf("long", 0l) {
			@Override
			public Object deserialize(JsonNode node) {
				return node.get("_value").asLong();
			}

			@Override
			public void serialize(Object object, ObjectNode node) {
				node.put("_value", ((Long) object).toString());
			}

			@Override
			public boolean isLeaf(Class clazz) {
				return clazz.equals(long.class);
			}
		});
		
		leafs.add(new ObjserLeaf("Long", null) {
			@Override
			public Object deserialize(JsonNode node) {
				return node.get("_value").asLong();
			}

			@Override
			public void serialize(Object object, ObjectNode node) {
				node.put("_value", ((Long) object).toString());
			}

			@Override
			public boolean isLeaf(Class clazz) {
				return clazz.equals(Long.class);
			}
		});
		
		///////////////
		// Float
		///////////////

		leafs.add(new ObjserLeaf("float", 0f) {
			@Override
			public Object deserialize(JsonNode node) {
				return Float.parseFloat(node.get("_value").asText());
			}

			@Override
			public void serialize(Object object, ObjectNode node) {
				node.put("_value", ((Float) object).toString());
			}

			@Override
			public boolean isLeaf(Class clazz) {
				return clazz.equals(float.class);
			}
		});
		
		leafs.add(new ObjserLeaf("Float", null) {
			@Override
			public Object deserialize(JsonNode node) {
				return Float.parseFloat(node.get("_value").asText());
			}

			@Override
			public void serialize(Object object, ObjectNode node) {
				node.put("_value", ((Float) object).toString());
			}

			@Override
			public boolean isLeaf(Class clazz) {
				return clazz.equals(Float.class);
			}
		});
		
		///////////////
		// Double
		///////////////

		leafs.add(new ObjserLeaf("double", 0d) {
			@Override
			public Object deserialize(JsonNode node) {
				return node.get("_value").asDouble();
			}

			@Override
			public void serialize(Object object, ObjectNode node) {
				node.put("_value", ((Double) object).toString());
			}

			@Override
			public boolean isLeaf(Class clazz) {
				return clazz.equals(double.class);
			}
		});
		
		leafs.add(new ObjserLeaf("Double", null) {
			@Override
			public Object deserialize(JsonNode node) {
				return node.get("_value").asDouble();
			}

			@Override
			public void serialize(Object object, ObjectNode node) {
				node.put("_value", ((Double) object).toString());
			}

			@Override
			public boolean isLeaf(Class clazz) {
				return clazz.equals(Double.class);
			}
		});
		
		///////////////
		// Bool
		///////////////

		leafs.add(new ObjserLeaf("bool", false) {
			@Override
			public Object deserialize(JsonNode node) {
				return node.get("_value").asBoolean();
			}

			@Override
			public void serialize(Object object, ObjectNode node) {
				node.put("_value", ((Boolean) object).toString());
			}

			@Override
			public boolean isLeaf(Class clazz) {
				return clazz.equals(boolean.class);
			}
		});
		
		leafs.add(new ObjserLeaf("Bool", null) {
			@Override
			public Object deserialize(JsonNode node) {
				return node.get("_value").asBoolean();
			}

			@Override
			public void serialize(Object object, ObjectNode node) {
				node.put("_value", ((Boolean) object).toString());
			}

			@Override
			public boolean isLeaf(Class clazz) {
				return clazz.equals(Boolean.class);
			}
		});
		
		///////////////
		// String
		///////////////

		leafs.add(new ObjserLeaf("String", null) {
			@Override
			public Object deserialize(JsonNode node) {
				return node.get("_value").asText();
			}

			@Override
			public void serialize(Object object, ObjectNode node) {
				node.put("_value", (String) object);
			}

			@Override
			public boolean isLeaf(Class clazz) {
				return clazz.equals(String.class);
			}
		});
		
		///////////////
		// Class
		///////////////

		leafs.add(new ObjserLeaf("Class", null) {
			@Override
			public Object deserialize(JsonNode node) throws ClassNotFoundException {
				return Class.forName(node.get("_value").asText());
			}

			@Override
			public void serialize(Object object, ObjectNode node) {
				node.put("_value", ((Class) object).getCanonicalName());
			}

			@Override
			public boolean isLeaf(Class clazz) {
				return clazz.equals(Class.class);
			}
		});
	}

	protected List<ObjserLeaf> getLeafs() {
		return leafs;
	}

}

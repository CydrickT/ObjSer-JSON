package org.objser.Objser.testjavabeans;

public class TestPrimitiveBean implements IBean {
	
	private final Byte objectByte;
	private final byte primitiveByte;
	
	private final Character objectChar;
	private final char primitiveChar;
	
	private final Short objectShort;
	private final short primitiveShort;
	
	private final Integer objectInteger;
	private final int primitiveInteger;
	
	private final Long objectLong;
	private final long primitiveLong;
	
	private final Float objectFloat;
	private final float primitiveFloat;
	
	private final Double objectDouble;
	private final double primitiveDouble;
	
	private final Boolean objectBoolean;
	private final boolean primitiveBoolean;

	private final String objectString;

	public TestPrimitiveBean(Byte objectByte, byte primitiveByte, Character objectChar, char primitiveChar,
			Short objectShort, short primitiveShort, Integer objectInteger, int primitiveInteger, Long objectLong,
			long primitiveLong, Float objectFloat, float primitiveFloat, Double objectDouble, double primitiveDouble,
			Boolean objectBoolean, boolean primitiveBoolean, String objectString) {
		this.objectByte = objectByte;
		this.primitiveByte = primitiveByte;
		this.objectChar = objectChar;
		this.primitiveChar = primitiveChar;
		this.objectShort = objectShort;
		this.primitiveShort = primitiveShort;
		this.objectInteger = objectInteger;
		this.primitiveInteger = primitiveInteger;
		this.objectLong = objectLong;
		this.primitiveLong = primitiveLong;
		this.objectFloat = objectFloat;
		this.primitiveFloat = primitiveFloat;
		this.objectDouble = objectDouble;
		this.primitiveDouble = primitiveDouble;
		this.objectBoolean = objectBoolean;
		this.primitiveBoolean = primitiveBoolean;
		this.objectString = objectString;
	}

	public Byte getObjectByte() {
		return objectByte;
	}

	public byte getPrimitiveByte() {
		return primitiveByte;
	}

	public Character getObjectChar() {
		return objectChar;
	}

	public char getPrimitiveChar() {
		return primitiveChar;
	}

	public Short getObjectShort() {
		return objectShort;
	}

	public short getPrimitiveShort() {
		return primitiveShort;
	}

	public Integer getObjectInteger() {
		return objectInteger;
	}

	public int getPrimitiveInteger() {
		return primitiveInteger;
	}

	public Long getObjectLong() {
		return objectLong;
	}

	public long getPrimitiveLong() {
		return primitiveLong;
	}

	public Float getObjectFloat() {
		return objectFloat;
	}

	public float getPrimitiveFloat() {
		return primitiveFloat;
	}

	public Double getObjectDouble() {
		return objectDouble;
	}

	public double getPrimitiveDouble() {
		return primitiveDouble;
	}

	public Boolean getObjectBoolean() {
		return objectBoolean;
	}

	public boolean isPrimitiveBoolean() {
		return primitiveBoolean;
	}

	public String getObjectString() {
		return objectString;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((objectBoolean == null) ? 0 : objectBoolean.hashCode());
		result = prime * result + ((objectByte == null) ? 0 : objectByte.hashCode());
		result = prime * result + ((objectChar == null) ? 0 : objectChar.hashCode());
		result = prime * result + ((objectDouble == null) ? 0 : objectDouble.hashCode());
		result = prime * result + ((objectFloat == null) ? 0 : objectFloat.hashCode());
		result = prime * result + ((objectInteger == null) ? 0 : objectInteger.hashCode());
		result = prime * result + ((objectLong == null) ? 0 : objectLong.hashCode());
		result = prime * result + ((objectShort == null) ? 0 : objectShort.hashCode());
		result = prime * result + ((objectString == null) ? 0 : objectString.hashCode());
		result = prime * result + (primitiveBoolean ? 1231 : 1237);
		result = prime * result + primitiveByte;
		result = prime * result + primitiveChar;
		long temp;
		temp = Double.doubleToLongBits(primitiveDouble);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Float.floatToIntBits(primitiveFloat);
		result = prime * result + primitiveInteger;
		result = prime * result + (int) (primitiveLong ^ (primitiveLong >>> 32));
		result = prime * result + primitiveShort;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestPrimitiveBean other = (TestPrimitiveBean) obj;
		if (objectBoolean == null) {
			if (other.objectBoolean != null)
				return false;
		} else if (!objectBoolean.equals(other.objectBoolean))
			return false;
		if (objectByte == null) {
			if (other.objectByte != null)
				return false;
		} else if (!objectByte.equals(other.objectByte))
			return false;
		if (objectChar == null) {
			if (other.objectChar != null)
				return false;
		} else if (!objectChar.equals(other.objectChar))
			return false;
		if (objectDouble == null) {
			if (other.objectDouble != null)
				return false;
		} else if (!objectDouble.equals(other.objectDouble))
			return false;
		if (objectFloat == null) {
			if (other.objectFloat != null)
				return false;
		} else if (!objectFloat.equals(other.objectFloat))
			return false;
		if (objectInteger == null) {
			if (other.objectInteger != null)
				return false;
		} else if (!objectInteger.equals(other.objectInteger))
			return false;
		if (objectLong == null) {
			if (other.objectLong != null)
				return false;
		} else if (!objectLong.equals(other.objectLong))
			return false;
		if (objectShort == null) {
			if (other.objectShort != null)
				return false;
		} else if (!objectShort.equals(other.objectShort))
			return false;
		if (objectString == null) {
			if (other.objectString != null)
				return false;
		} else if (!objectString.equals(other.objectString))
			return false;
		if (primitiveBoolean != other.primitiveBoolean)
			return false;
		if (primitiveByte != other.primitiveByte)
			return false;
		if (primitiveChar != other.primitiveChar)
			return false;
		if (Double.doubleToLongBits(primitiveDouble) != Double.doubleToLongBits(other.primitiveDouble))
			return false;
		if (Float.floatToIntBits(primitiveFloat) != Float.floatToIntBits(other.primitiveFloat))
			return false;
		if (primitiveInteger != other.primitiveInteger)
			return false;
		if (primitiveLong != other.primitiveLong)
			return false;
		if (primitiveShort != other.primitiveShort)
			return false;
		return true;
	}
}

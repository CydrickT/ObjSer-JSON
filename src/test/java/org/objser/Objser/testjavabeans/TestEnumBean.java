package org.objser.Objser.testjavabeans;

public class TestEnumBean implements IBean{
	
	private final TestEnum testEnum;
	private final Enum testObjectEnum;
	
	public TestEnumBean(TestEnum testEnum, Enum testObjectEnum) {
		this.testEnum = testEnum;
		this.testObjectEnum = testObjectEnum;
	}

	protected TestEnum getTestEnum() {
		return testEnum;
	}

	protected Enum getTestObjectEnum() {
		return testObjectEnum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((testEnum == null) ? 0 : testEnum.hashCode());
		result = prime * result + ((testObjectEnum == null) ? 0 : testObjectEnum.hashCode());
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
		TestEnumBean other = (TestEnumBean) obj;
		if (testEnum != other.testEnum)
			return false;
		if (testObjectEnum == null) {
			if (other.testObjectEnum != null)
				return false;
		} else if (!testObjectEnum.equals(other.testObjectEnum))
			return false;
		return true;
	}
}

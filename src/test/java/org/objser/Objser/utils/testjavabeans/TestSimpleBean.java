package org.objser.Objser.utils.testjavabeans;

public class TestSimpleBean implements IBean{
	
	private final String theString;

	public TestSimpleBean(String theString) {
		this.theString = theString;
	}

	protected String getTheString() {
		return theString;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((theString == null) ? 0 : theString.hashCode());
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
		TestSimpleBean other = (TestSimpleBean) obj;
		if (theString == null) {
			if (other.theString != null)
				return false;
		} else if (!theString.equals(other.theString))
			return false;
		return true;
	}
}

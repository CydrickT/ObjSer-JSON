package org.objser.Objser.testjavabeans;

public abstract class TestAbstractBean implements IBean {

	private final String stringInAbstractBean;

	public TestAbstractBean(String stringInAbstractBean) {
		this.stringInAbstractBean = stringInAbstractBean;
	}

	protected String getStringInAbstractBean() {
		return stringInAbstractBean;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stringInAbstractBean == null) ? 0 : stringInAbstractBean.hashCode());
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
		TestAbstractBean other = (TestAbstractBean) obj;
		if (stringInAbstractBean == null) {
			if (other.stringInAbstractBean != null)
				return false;
		} else if (!stringInAbstractBean.equals(other.stringInAbstractBean))
			return false;
		return true;
	}
	
}

package org.objser.Objser.testjavabeans;

public class TestInterfaceBean implements IBean {
	
	private final IBean iBean1;
	private final IBean iBean2;
	
	public TestInterfaceBean(IBean iBean1, IBean iBean2) {
		this.iBean1 = iBean1;
		this.iBean2 = iBean2;
	}

	protected IBean getiBean1() {
		return iBean1;
	}
	
	protected IBean getiBean2() {
		return iBean2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((iBean1 == null) ? 0 : iBean1.hashCode());
		result = prime * result + ((iBean2 == null) ? 0 : iBean2.hashCode());
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
		TestInterfaceBean other = (TestInterfaceBean) obj;
		if (iBean1 == null) {
			if (other.iBean1 != null)
				return false;
		} else if (!iBean1.equals(other.iBean1))
			return false;
		if (iBean2 == null) {
			if (other.iBean2 != null)
				return false;
		} else if (!iBean2.equals(other.iBean2))
			return false;
		return true;
	}
	
	
}

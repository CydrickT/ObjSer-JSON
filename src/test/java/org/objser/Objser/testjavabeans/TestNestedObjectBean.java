package org.objser.Objser.testjavabeans;

public class TestNestedObjectBean implements IBean {
	
	private final TestSimpleBean bean1;
	private final Object bean2;
	
	public TestNestedObjectBean(TestSimpleBean bean1, Object bean2) {
		this.bean1 = bean1;
		this.bean2 = bean2;
	}

	public TestSimpleBean getBean1() {
		return bean1;
	}

	public Object getBean2() {
		return bean2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bean1 == null) ? 0 : bean1.hashCode());
		result = prime * result + ((bean2 == null) ? 0 : bean2.hashCode());
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
		TestNestedObjectBean other = (TestNestedObjectBean) obj;
		if (bean1 == null) {
			if (other.bean1 != null)
				return false;
		} else if (!bean1.equals(other.bean1))
			return false;
		if (bean2 == null) {
			if (other.bean2 != null)
				return false;
		} else if (!bean2.equals(other.bean2))
			return false;
		return true;
	}
}

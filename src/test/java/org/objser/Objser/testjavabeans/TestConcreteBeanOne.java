package org.objser.Objser.testjavabeans;

public class TestConcreteBeanOne extends TestAbstractBean{
	
	private final String stringInConcreteBean1;

	public TestConcreteBeanOne(String stringInAbstractBean, String stringInConcreteBean1) {
		super(stringInAbstractBean);
		this.stringInConcreteBean1 = stringInConcreteBean1;
	}

	protected String getStringInConcreteBean1() {
		return stringInConcreteBean1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((stringInConcreteBean1 == null) ? 0 : stringInConcreteBean1.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestConcreteBeanOne other = (TestConcreteBeanOne) obj;
		if (stringInConcreteBean1 == null) {
			if (other.stringInConcreteBean1 != null)
				return false;
		} else if (!stringInConcreteBean1.equals(other.stringInConcreteBean1))
			return false;
		return true;
	}
}

package org.objser.Objser.testjavabeans;

public class TestConcreteBeanTwo extends TestAbstractBean{
	
	private final String stringInConcreteBean2;

	public TestConcreteBeanTwo(String stringInAbstractBean, String stringInConcreteBean2) {
		super(stringInAbstractBean);
		this.stringInConcreteBean2 = stringInConcreteBean2;
	}

	protected String getStringInConcreteBean2() {
		return stringInConcreteBean2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((stringInConcreteBean2 == null) ? 0 : stringInConcreteBean2.hashCode());
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
		TestConcreteBeanTwo other = (TestConcreteBeanTwo) obj;
		if (stringInConcreteBean2 == null) {
			if (other.stringInConcreteBean2 != null)
				return false;
		} else if (!stringInConcreteBean2.equals(other.stringInConcreteBean2))
			return false;
		return true;
	}
}

package org.objser.Objser.testjavabeans;

public class TestTypeParameterBean<A, B> implements IBean{

	private final A refA;
	private final B refB;
	
	public TestTypeParameterBean(A refA, B refB) {
		this.refA = refA;
		this.refB = refB;
	}

	public A getRefA() {
		return refA;
	}

	public B getRefB() {
		return refB;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((refA == null) ? 0 : refA.hashCode());
		result = prime * result + ((refB == null) ? 0 : refB.hashCode());
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
		TestTypeParameterBean other = (TestTypeParameterBean) obj;
		if (refA == null) {
			if (other.refA != null)
				return false;
		} else if (!refA.equals(other.refA))
			return false;
		if (refB == null) {
			if (other.refB != null)
				return false;
		} else if (!refB.equals(other.refB))
			return false;
		return true;
	}
}

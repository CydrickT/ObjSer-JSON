package org.objser.Objser.utils.testjavabeans;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class TestCollectionBean implements IBean {

	private final Object[] objectArray;
	private final Collection objectCollection;
	private final Map objectMap;
	
	public TestCollectionBean(Object[] objectArray, Collection objectCollection, Map objectMap) {
		this.objectArray = objectArray;
		this.objectCollection = objectCollection;
		this.objectMap = objectMap;
	}

	protected Object[] getObjectArray() {
		return objectArray;
	}

	protected Collection getObjectCollection() {
		return objectCollection;
	}

	protected Map getObjectMap() {
		return objectMap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(objectArray);
		result = prime * result + ((objectCollection == null) ? 0 : objectCollection.hashCode());
		result = prime * result + ((objectMap == null) ? 0 : objectMap.hashCode());
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
		TestCollectionBean other = (TestCollectionBean) obj;
		if (!Arrays.deepEquals(objectArray, other.objectArray))
			return false;
		if (objectCollection == null) {
			if (other.objectCollection != null)
				return false;
		} else if (!objectCollection.equals(other.objectCollection))
			return false;
		if (objectMap == null) {
			if (other.objectMap != null)
				return false;
		} else if (!objectMap.equals(other.objectMap))
			return false;
		return true;
	}
}

package org.objser.Objser;

import java.lang.reflect.Field;

@FunctionalInterface
public interface FieldExclusionStrategy {
	public boolean isExcluded(Field field);
}

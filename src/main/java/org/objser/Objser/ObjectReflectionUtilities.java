package org.objser.Objser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

class ObjectReflectionUtilities {
	
	private ObjserSettings settings;
	
	protected ObjectReflectionUtilities(ObjserSettings settings) {
		this.settings = settings;
	}
	
	protected Object getObjectInstance(Class clazz) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		
		Constructor constructors[] = clazz.getDeclaredConstructors();
		Object obj = null;

		Constructor selectedConstructor = null;
		Class[] selectedConstructorParams = new Class[] {};

		for (Constructor cons : constructors) {
			Class[] params = cons.getParameterTypes();
			if (params.length == 0) {
				// No-args constructor, priorizing it.
				selectedConstructorParams = params;
				selectedConstructor = cons;
				break;
			} else if (params.length > selectedConstructorParams.length) {
				// Taking the constructor with the most arguments.
				selectedConstructorParams = params;
				selectedConstructor = cons;
			}
		}
		Object[] defaults = getDefaults(selectedConstructorParams);
		obj = selectedConstructor.newInstance(defaults);

		return obj;
	}
	
	protected Object[] getDefaults(Class[] classArray) {
		Object[] defaults = new Object[classArray.length];
		for(int i = 0; i < classArray.length; i++) {
			boolean isLeaf = settings.isLeaf(classArray[i]);
			if (isLeaf) {
				defaults[i] = settings.getDefaultValue(classArray[i]);
 			}else {
				defaults[i] = null;
			}
		}
		return defaults;
	}
	
	protected Map<String, Field> getAllFields(Class clazz) {
		Map<String, Field> fields = new HashMap<String, Field>();
		for(Field field : clazz.getDeclaredFields()) {
			fields.put(field.getName(), field);
		}
        
        Class superclass = clazz.getSuperclass();
        while(superclass != null && !superclass.equals(Object.class)) {
        	 fields.putAll(getAllFields(clazz.getSuperclass()));
        	 superclass = superclass.getSuperclass();
        }
        return fields;
    }
}

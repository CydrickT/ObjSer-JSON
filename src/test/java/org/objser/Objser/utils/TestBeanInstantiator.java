package org.objser.Objser.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.objser.Objser.utils.testjavabeans.IBean;
import org.objser.Objser.utils.testjavabeans.TestCollectionBean;
import org.objser.Objser.utils.testjavabeans.TestConcreteBeanOne;
import org.objser.Objser.utils.testjavabeans.TestConcreteBeanTwo;
import org.objser.Objser.utils.testjavabeans.TestEnum;
import org.objser.Objser.utils.testjavabeans.TestEnumBean;
import org.objser.Objser.utils.testjavabeans.TestIgnoredField;
import org.objser.Objser.utils.testjavabeans.TestInheritanceBean;
import org.objser.Objser.utils.testjavabeans.TestInterfaceBean;
import org.objser.Objser.utils.testjavabeans.TestNestedObjectBean;
import org.objser.Objser.utils.testjavabeans.TestPrimitiveBean;
import org.objser.Objser.utils.testjavabeans.TestSimpleBean;
import org.objser.Objser.utils.testjavabeans.TestTypeParameterBean;

public class TestBeanInstantiator {
	
	public static TestSimpleBean getSimpleBean() {
		return new TestSimpleBean("Hello World from Simple Bean");
	}

	public static TestPrimitiveBean getTestPrimitiveBean() {
		Byte objectByte = 12;
		byte primitiveByte = -34;

		Character objectCharacter = 'a';
		char primitiveCharacter = 'z';

		Short objectShort = -1234;
		short primitiveShort = -5678;

		Integer objectInteger = 123456789;
		int primitiveInteger = -123456789;

		Long objectLong = 123456789123456789l;
		long primitiveLong = -123456789123456789l;

		Float objectFloat = 123456789.12345689f;
		float primitiveFloat = -123456789.12345689f;

		Double objectDouble = 123456789.12345689;
		double primitiveDouble = -123456789.12345689;

		Boolean objectBoolean = true;
		boolean primitiveBoolean = false;

		String objectString = "Hello World from Primitive Bean";
		Class objectClass = String.class;

		return new TestPrimitiveBean(objectByte, primitiveByte, objectCharacter, primitiveCharacter, objectShort,
				primitiveShort, objectInteger, primitiveInteger, objectLong, primitiveLong, objectFloat, primitiveFloat,
				objectDouble, primitiveDouble, objectBoolean, primitiveBoolean, objectString, objectClass);
	}
	
	public static TestPrimitiveBean getNullTestPrimitiveBean() {
		Byte objectByte = null;
		byte primitiveByte = 0;

		Character objectCharacter = null;
		char primitiveCharacter = 'a';

		Short objectShort = null;
		short primitiveShort = 0;

		Integer objectInteger = null;
		int primitiveInteger = 0;

		Long objectLong = null;
		long primitiveLong = 0;

		Float objectFloat = null;
		float primitiveFloat = 0;

		Double objectDouble = null;
		double primitiveDouble = 0;

		Boolean objectBoolean = null;
		boolean primitiveBoolean = false;

		String objectString = null;
		
		Class objectClass = null;
		
		return new TestPrimitiveBean(objectByte, primitiveByte, objectCharacter, primitiveCharacter, objectShort,
				primitiveShort, objectInteger, primitiveInteger, objectLong, primitiveLong, objectFloat, primitiveFloat,
				objectDouble, primitiveDouble, objectBoolean, primitiveBoolean, objectString, objectClass);
	}

	public static TestNestedObjectBean getTestNestedObjectBean() {
		return new TestNestedObjectBean(getSimpleBean(), getSimpleBean());
	}

	public static Object getNullTestNestedObjectBean() {
		return new TestNestedObjectBean(null, getSimpleBean());
	}

	public static TestEnumBean getTestEnumBean() {
		return new TestEnumBean(TestEnum.VAL1, TestEnum.VAL2);
	}

	public static TestTypeParameterBean getTestTypeParameterObjectBean() {
		return new TestTypeParameterBean(getSimpleBean(), getTestEnumBean());
	}
	
	public static TestTypeParameterBean getTestTypeParameterPrimitiveBean() {
		return new TestTypeParameterBean(new Double(123), 123.456d);
	}
	
	public static TestInheritanceBean getInheritanceBean() {
		return new TestInheritanceBean(getConcreteBeanOne(), getConcreteBeanTwo());
	}
	
	public static TestConcreteBeanOne getConcreteBeanOne() {
		return new TestConcreteBeanOne("String in parent class One", "String in child class One");
	}
	
	public static TestConcreteBeanTwo getConcreteBeanTwo() {
		return new TestConcreteBeanTwo("String in parent class Two", "String in child class Two");
	}
	
	public static TestInterfaceBean getInterfaceBean() {
		return new TestInterfaceBean(getSimpleBean(), getTestEnumBean()); 
	}
	
	public static TestIgnoredField getTestIgnoreFieldBean() {
		return new TestIgnoredField("This should be ignored", "This should NOT be ignored");
	}
	
	public static TestCollectionBean getTestCollectionBean() {
		Map<TestEnum, IBean> map = new HashMap<>();
		map.put(TestEnum.VAL1, getSimpleBean());
		map.put(TestEnum.VAL2, getTestTypeParameterObjectBean());
		
		List<IBean> ibeanList = new ArrayList<>();
		ibeanList.add(getConcreteBeanOne());
		ibeanList.add(getConcreteBeanTwo());
		
		Object[] array = new Object[] {"Hello World", 1, getSimpleBean()};
		return new TestCollectionBean(array, ibeanList, map);
		
	}
}

package org.objser.Objser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.objser.Objser.testjavabeans.IBean;
import org.objser.Objser.testjavabeans.TestCollectionBean;
import org.objser.Objser.testjavabeans.TestConcreteBeanOne;
import org.objser.Objser.testjavabeans.TestConcreteBeanTwo;
import org.objser.Objser.testjavabeans.TestEnum;
import org.objser.Objser.testjavabeans.TestEnumBean;
import org.objser.Objser.testjavabeans.TestIgnoredField;
import org.objser.Objser.testjavabeans.TestTypeParameterBean;
import org.objser.Objser.testjavabeans.TestInheritanceBean;
import org.objser.Objser.testjavabeans.TestInterfaceBean;
import org.objser.Objser.testjavabeans.TestNestedObjectBean;
import org.objser.Objser.testjavabeans.TestPrimitiveBean;
import org.objser.Objser.testjavabeans.TestSimpleBean;

class TestBeanInstantiator {
	
	protected static TestSimpleBean getSimpleBean() {
		return new TestSimpleBean("Hello World from Simple Bean");
	}

	protected static TestPrimitiveBean getTestPrimitiveBean() {
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

		return new TestPrimitiveBean(objectByte, primitiveByte, objectCharacter, primitiveCharacter, objectShort,
				primitiveShort, objectInteger, primitiveInteger, objectLong, primitiveLong, objectFloat, primitiveFloat,
				objectDouble, primitiveDouble, objectBoolean, primitiveBoolean, objectString);
	}

	protected static TestNestedObjectBean getTestNestedObjectBean() {
		return new TestNestedObjectBean(getSimpleBean(), getSimpleBean());
	}

	protected static TestEnumBean getTestEnumBean() {
		return new TestEnumBean(TestEnum.VAL1, TestEnum.VAL2);
	}

	protected static TestTypeParameterBean getTestTypeParameterObjectBean() {
		return new TestTypeParameterBean(getSimpleBean(), getTestEnumBean());
	}
	
	protected static TestTypeParameterBean getTestTypeParameterPrimitiveBean() {
		return new TestTypeParameterBean(new Double(123), 123.456d);
	}
	
	protected static TestInheritanceBean getInheritanceBean() {
		return new TestInheritanceBean(getConcreteBeanOne(), getConcreteBeanTwo());
	}
	
	protected static TestConcreteBeanOne getConcreteBeanOne() {
		return new TestConcreteBeanOne("String in parent class One", "String in child class One");
	}
	
	protected static TestConcreteBeanTwo getConcreteBeanTwo() {
		return new TestConcreteBeanTwo("String in parent class Two", "String in child class Two");
	}
	
	protected static TestInterfaceBean getInterfaceBean() {
		return new TestInterfaceBean(getSimpleBean(), getTestEnumBean()); 
	}
	
	protected static TestIgnoredField getTestIgnoreFieldBean() {
		return new TestIgnoredField("This should be ignored", "This should NOT be ignored");
	}
	
	protected static TestCollectionBean getTestCollectionBean() {
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

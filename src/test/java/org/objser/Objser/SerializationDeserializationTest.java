package org.objser.Objser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.objser.Objser.utils.TestBeanInstantiator;
import org.objser.Objser.utils.testjavabeans.TestIgnoredField;

public class SerializationDeserializationTest 
{
    @Test
    public void primitiveBeanTest() {
    	testSerialization(TestBeanInstantiator.getTestPrimitiveBean());
    }
    
    @Test
    public void nullPrimitiveBeanTest() {
    	testSerialization(TestBeanInstantiator.getNullTestPrimitiveBean());
    }
    
    @Test
    public void nestedBeanTest() {
    	testSerialization(TestBeanInstantiator.getTestNestedObjectBean());
    }
    
    @Test
    public void nullNestedBeanTest() {
    	testSerialization(TestBeanInstantiator.getNullTestNestedObjectBean());
    }
    
    @Test
    public void enumTest() {
    	testSerialization(TestBeanInstantiator.getTestEnumBean());
    }
    
    @Test
    public void inheritanceTest() {
    	testSerialization(TestBeanInstantiator.getInheritanceBean());
    }
    
    @Test
    public void genericObjectTest() {
    	testSerialization(TestBeanInstantiator.getTestTypeParameterPrimitiveBean());
    }
    
    @Test
    public void genericPrimitiveTest() {
    	testSerialization(TestBeanInstantiator.getTestTypeParameterObjectBean());
    }
   
    @Test
    public void interfaceTest() {
    	testSerialization(TestBeanInstantiator.getInterfaceBean());
    }
    
    @Test
    public void collectionTest() {
    	testSerialization(TestBeanInstantiator.getTestCollectionBean());
    }
    
    @Test
    public void ignoredFieldTest() {
    	Objser objser = new Objser(new ObjserSettings().addFieldExclusionStrategy(field -> field.getName().equals("shouldBeIgnored")));
    	TestIgnoredField original = TestBeanInstantiator.getTestIgnoreFieldBean();
    	String serialized = objser.serialize(original);
    	assertFalse(serialized.contains("shouldBeIgnored"));
    	TestIgnoredField deserialized = objser.deserialize(serialized);
    	assertEquals(original.getShouldNotBeIgnored(), deserialized.getShouldNotBeIgnored());
    	assertNull(deserialized.getShouldBeIgnored());
    }
    
    private void testSerialization(Object original) {
    	System.out.println("------------------- Testing serialization & Deserialization of " + original.getClass().getCanonicalName() + " -------------------");
    	Objser objser = new Objser();
    	String serialized = objser.serialize(original);
    	System.out.println(serialized);
    	Object deserialized = objser.deserialize(serialized);
    	assertEquals(original, deserialized);
    }
}

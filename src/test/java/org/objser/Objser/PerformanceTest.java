package org.objser.Objser;

import java.text.DecimalFormat;

import org.junit.Test;
import org.objser.Objser.Objser;
import org.objser.Objser.utils.TestBeanInstantiator;
import org.objser.Objser.utils.testjavabeans.TestNestedObjectBean;

public class PerformanceTest {
	
	private final static DecimalFormat ELAPSED_TIME_FORMATTER = new DecimalFormat("#.###");

    @Test
    public void speedTest() {
    	int numberOfSerializationDeserialization = 1000000;
    	long startTime = System.nanoTime();
    	Objser objser = new Objser();
    	for(int i = 0; i < numberOfSerializationDeserialization; i++) {
    		TestNestedObjectBean original = TestBeanInstantiator.getTestNestedObjectBean();
        	String serialized = objser.serialize(original);
        	Object deserialized = objser.deserialize(serialized);
        	if (i % 10000 == 0) {
        		System.out.println(i);
        	}
    	}
    	long endTime = System.nanoTime();
    	double elapsedInSeconds = ((double)(endTime - startTime)) / 1_000_000_000d;
    	System.out.println("Serialized & Deserialized " + numberOfSerializationDeserialization + " nested beans in " + ELAPSED_TIME_FORMATTER.format(elapsedInSeconds) + "sec");
    }
  
	
}

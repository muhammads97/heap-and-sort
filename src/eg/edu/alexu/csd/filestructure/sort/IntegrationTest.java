package eg.edu.alexu.csd.filestructure.sort;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import eg.edu.alexu.csd.filestructure.sort.IHeap;
import eg.edu.alexu.csd.filestructure.sort.ReflectionHelper;

public class IntegrationTest {
    
    private final Class<?> heapInterfaceToTest = IHeap.class;
    	
    private final Class<?> sortInterfaceToTest = IHeap.class;

    
    @Test
    public void testCreationHeap() {	
    	List<Class<?>> candidateClasses = ReflectionHelper.findClassesImplementing(heapInterfaceToTest, heapInterfaceToTest.getPackage());
    	Assert.assertNotNull("Failed to create instance using interface '" + heapInterfaceToTest.getName() + "' !", candidateClasses);
    	Assert.assertEquals("You have more than one public implementation of the interface", 1, candidateClasses.size());
    }
    
    @Test
    public void testCreationSort() {	
    	List<Class<?>> candidateClasses = ReflectionHelper.findClassesImplementing(sortInterfaceToTest, sortInterfaceToTest.getPackage());
    	Assert.assertNotNull("Failed to create instance using interface '" + sortInterfaceToTest.getName() + "' !", candidateClasses);
    	Assert.assertEquals("You have more than one public implementation of the interface", 1, candidateClasses.size());
    }

}

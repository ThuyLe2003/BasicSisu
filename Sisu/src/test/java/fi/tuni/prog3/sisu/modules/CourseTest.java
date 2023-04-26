package fi.tuni.prog3.sisu.modules;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Course class.
 */
public class CourseTest {
    
    /**
     * Test of constructor and getters.
     */
    @Test
    public void testGetters() {
        System.out.println("constructorAndGetters");
        Course instance = new Course("Differential and Integral Calculus",
                                     "otm-1dc4fc64-39fd-4575-aef6-280199870f71",
                                     "tut-cu-g-35899",
                                     5, "MATH.APP.160");
        assertEquals("Differential and Integral Calculus", 
                instance.getName());
        assertEquals("otm-1dc4fc64-39fd-4575-aef6-280199870f71", 
                instance.getId());
        assertEquals("tut-cu-g-35899", instance.getGroupId());
        assertEquals(5, instance.getMinCredits());
        assertEquals("MATH.APP.160", instance.getCode());
    }

    /**
     * Test of addItem method of class Course to throw 
     * UnsupportedOperationException.
     */
    @Test
    public void testAddItem() {
        System.out.println("addItem");
        Course instance = new Course("Differential and Integral Calculus",
                                     "otm-1dc4fc64-39fd-4575-aef6-280199870f71",
                                     "tut-cu-g-35899",
                                     5, "MATH.APP.160");
        Course instance2 = new Course("Differential and Integral Calculus",
                                     "otm-1e8f1023-1977-4f98-9386-110ef623327b",
                                     "otm-1e8f1023-1977-4f98-9386-110ef623327b",
                                     5, "MATH.MA.210");
        assertThrows(UnsupportedOperationException.class, () -> {
            instance.addItem(instance2);
        });
    }
    
    /**
     * Test of toString method, of class Course.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Course instance = new Course("Differential and Integral Calculus",
                                     "otm-1dc4fc64-39fd-4575-aef6-280199870f71",
                                     "tut-cu-g-35899",
                                     5, "MATH.APP.160");
        assertEquals("Differential and Integral Calculus (5 credits)", 
                instance.toString());
    }
}

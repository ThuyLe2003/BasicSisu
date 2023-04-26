package fi.tuni.prog3.sisu.modules;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for GroupingModule class.
 */
public class GroupingModuleTest {
    
    /**
     * Test of constructor and getters.
     */
    @Test
    public void testGetters() {
        System.out.println("constructorAndGetters");
        
        GroupingModule instance = new GroupingModule(
                "Free Choice Study Modules",
                "otm-6c36cb36-1507-44ff-baab-a30ac76ca786",
                "otm-6c36cb36-1507-44ff-baab-a30ac76ca786",
                0
        );
        
        assertEquals("Free Choice Study Modules", 
                instance.getName());
        assertEquals("otm-6c36cb36-1507-44ff-baab-a30ac76ca786",
                instance.getId());
        assertEquals("otm-6c36cb36-1507-44ff-baab-a30ac76ca786", 
                instance.getGroupId());
        assertEquals(0, instance.getMinCredits());
    }
    
    /**
     * Test of getStudyModules and addItem method, of class GroupingModule.
     */
    @Test
    public void testGetStudyModulesAndAddItem() {
        System.out.println("getStudyModules");
        
        GroupingModule instance = new GroupingModule(
                "Free Choice Study Modules",
                "otm-6c36cb36-1507-44ff-baab-a30ac76ca786",
                "otm-6c36cb36-1507-44ff-baab-a30ac76ca786",
                0
        );

        StudyModule module1 = new StudyModule(
                "General Studies in Computer Sciences",
                "otm-3858f1d8-4bf9-4769-b419-3fee1260d7ff",
                "otm-3858f1d8-4bf9-4769-b419-3fee1260d7ff",
                35
        );
        instance.addItem(module1);
        
        StudyModule module2 = new StudyModule(
                "Free Choice Course Units",
                "otm-35d5a7e1-71c1-456a-8783-9cf8c34262f5",
                "otm-35d5a7e1-71c1-456a-8783-9cf8c34262f5",
                0
        );
        instance.addItem(module2);
        
        ArrayList<StudyModule> result = instance.getStudyModules();
        assertEquals(2, result.size());
        assertEquals(module1, result.get(0));
        assertEquals(module2, result.get(1));
    }
    
    /**
     * Test of getCourses and addItem method, of class GroupingModule.
     */
    @Test
    public void testGetCourses() {
        System.out.println("getCoursesAndAddItem");
        
        GroupingModule instance = new GroupingModule(
                "Free Choice Study Modules",
                "otm-6c36cb36-1507-44ff-baab-a30ac76ca786",
                "otm-6c36cb36-1507-44ff-baab-a30ac76ca786",
                0
        );
        
        Course course = new Course("Differential and Integral Calculus",
                                     "otm-1dc4fc64-39fd-4575-aef6-280199870f71",
                                     "tut-cu-g-35899",
                                     5, "MATH.APP.160");
        instance.addItem(course);
        ArrayList<Course> result = instance.getCourses();
        assertEquals(1, result.size());
        assertEquals(course, result.get(0));
    }

    /**
     * Test of toString method, of class GroupingModule.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        
        GroupingModule instance = new GroupingModule(
                "Free Choice Study Modules",
                "otm-6c36cb36-1507-44ff-baab-a30ac76ca786",
                "otm-6c36cb36-1507-44ff-baab-a30ac76ca786",
                0
        );
        
        assertEquals("Free Choice Study Modules", 
                instance.toString());
    }
    
}

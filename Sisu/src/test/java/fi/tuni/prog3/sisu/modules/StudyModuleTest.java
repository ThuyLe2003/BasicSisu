package fi.tuni.prog3.sisu.modules;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Admin
 */
public class StudyModuleTest {
    
   /**
     * Test of constructor and getters.
     */
    @Test
    public void testGetters() {
        System.out.println("constructorAndGetters");
        
        StudyModule instance = new StudyModule(
                "General Studies in Computer Sciences",
                "otm-3858f1d8-4bf9-4769-b419-3fee1260d7ff",
                "otm-3858f1d8-4bf9-4769-b419-3fee1260d7ff",
                35
        );
        
        assertEquals("General Studies in Computer Sciences", 
                instance.getName());
        assertEquals("otm-3858f1d8-4bf9-4769-b419-3fee1260d7ff",
                instance.getId());
        assertEquals("otm-3858f1d8-4bf9-4769-b419-3fee1260d7ff", 
                instance.getGroupId());
        assertEquals(35, instance.getMinCredits());
    }
    
    /**
     * Test of getSubModules and addItem method, of class StudyModule.
     */
    @Test
    public void testGetSubModulesAndAddItem() {
        System.out.println("getSubModulesAndAddItem");
        
        StudyModule instance = new StudyModule(
                "General Studies in Computer Sciences",
                "otm-3858f1d8-4bf9-4769-b419-3fee1260d7ff",
                "otm-3858f1d8-4bf9-4769-b419-3fee1260d7ff",
                35
        );
        
        StudyModule module1 = new StudyModule(
                "General Studies in Computer Sciences Copy",
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
        
        ArrayList<StudyModule> result = instance.getSubModules();
        assertEquals(2, result.size());
        assertEquals(module1, result.get(0));
        assertEquals(module2, result.get(1));
    }

    /**
     * Test of getGroupingModules and addItem method, of class StudyModule.
     */
    @Test
    public void testGetGroupingModulesAndAddItem() {
        System.out.println("getGroupingModulesAndAddItem");
        
        StudyModule instance = new StudyModule(
                "General Studies in Computer Sciences",
                "otm-3858f1d8-4bf9-4769-b419-3fee1260d7ff",
                "otm-3858f1d8-4bf9-4769-b419-3fee1260d7ff",
                35
        );
        
        GroupingModule module = new GroupingModule(
                "Free Choice Study Modules",
                "otm-6c36cb36-1507-44ff-baab-a30ac76ca786",
                "otm-6c36cb36-1507-44ff-baab-a30ac76ca786",
                0
        );
        instance.addItem(module);
        
        ArrayList<GroupingModule> result = instance.getGroupingModules();
        assertEquals(1, result.size());
        assertEquals(module, result.get(0));
    }

    /**
     * Test of getCourses and addItem method, of class StudyModule.
     */
    @Test
    public void testGetCoursesAndAddItem() {
        System.out.println("getCoursesAndAddItem");
        
        StudyModule instance = new StudyModule(
                "General Studies in Computer Sciences",
                "otm-3858f1d8-4bf9-4769-b419-3fee1260d7ff",
                "otm-3858f1d8-4bf9-4769-b419-3fee1260d7ff",
                35
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
}

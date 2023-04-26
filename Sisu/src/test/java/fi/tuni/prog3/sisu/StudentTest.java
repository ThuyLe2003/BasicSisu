package fi.tuni.prog3.sisu;

import java.util.TreeSet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Admin
 */
public class StudentTest {
    
    /**
     * Test of constructor and getters.
     */
    @Test
    public void testConstructorAndGetters() {
        System.out.println("getters");
        Student student1 = new Student("Thuy", "Le",
                "150533634", 2021, 2024);
        assertTrue(student1 instanceof Student);
        assertEquals("Thuy", student1.getFirstName());
        assertEquals("Le", student1.getLastName());
        assertEquals("150533634", student1.getStudentNumber());
        assertEquals(2021, student1.getStartYear());
        assertEquals(2024, student1.getGradYear());
        assertEquals("", student1.getDegree());
        assertEquals(0, student1.getCompletedCourses().size());
        
        Student student2 = new Student("Thuy", "Le",
                "150533634", 2021, 2024, 
                "otm-df83fbbd-f82d-4fda-b819-78f6b2077fcb");
        assertTrue(student2 instanceof Student);
        assertEquals("otm-df83fbbd-f82d-4fda-b819-78f6b2077fcb",
                student2.getDegree());
        assertEquals(0, student2.getCompletedCourses().size());
        
        TreeSet<String> courses = new TreeSet<>();
        courses.add("course1");
        courses.add("course2");
        
        Student student3 = new Student("Thuy", "Le",
                "150533634", 2021, 2024, 
                "otm-df83fbbd-f82d-4fda-b819-78f6b2077fcb", 
                courses);
        assertTrue(student3 instanceof Student);
        assertEquals("otm-df83fbbd-f82d-4fda-b819-78f6b2077fcb",
                student3.getDegree());
        assertEquals(2, student3.getCompletedCourses().size());
    }

    /**
     * Test of setGradYear method, of class Student.
     */
    @Test
    public void testSetGradYear() {
        System.out.println("setGradYear");
        
        Student student = new Student("Thuy", "Le",
                "150533634", 2021, 2024);
        
        assertEquals(false, student.setGradYear(0));
        assertEquals(true, student.setGradYear(2023));
        assertEquals(false, student.setGradYear(2021));
    }

    /**
     * Test of addCompletedCourse method, of class Student.
     */
    @Test
    public void testAddCompletedCourse_String() {
        System.out.println("addCompletedCourse");
        String courseId = "course";
        Student student = new Student("Thuy", "Le",
                "150533634", 2021, 2024);
        student.addCompletedCourse(courseId);
        assertEquals(1, 
                student.getCompletedCourses().size());
        assertEquals(true, 
                student.getCompletedCourses().contains("course"));
    }

    /**
     * Test of addCompletedCourse method, of class Student.
     */
    @Test
    public void testAddCompletedCourse_TreeSet() {
        System.out.println("addCompletedCourse");
        TreeSet<String> courses = new TreeSet<>();
        courses.add("course1");
        courses.add("course2");
        Student student = new Student("Thuy", "Le",
                "150533634", 2021, 2024);
        student.addCompletedCourse(courses);
        assertEquals(2, 
                student.getCompletedCourses().size());
        assertEquals(true, 
                student.getCompletedCourses().contains("course1"));
        assertEquals(true, 
                student.getCompletedCourses().contains("course2"));
    }

    /**
     * Test of setDegree method, of class Student.
     */
    @Test
    public void testSetDegree() {
        System.out.println("setDegree");
        String degree = "otm-df83fbbd-f82d-4fda-b819-78f6b2077fcb";
        Student student = new Student("Thuy", "Le",
                "150533634", 2021, 2024);
        student.setDegree(degree);
        assertEquals(degree, student.getDegree());
        
        String degreeChange = "otm-bb9c3242-637c-4a7f-883f-b8850ed2dab6";
        student.setDegree(degreeChange);
        assertEquals(degreeChange, student.getDegree());
    }

    /**
     * Test of isDegreeSet method, of class Student.
     */
    @Test
    public void testIsDegreeSet() {
        System.out.println("isDegreeSet");
        Student student = new Student("Thuy", "Le",
                "150533634", 2021, 2024);
        assertEquals(false, student.isDegreeSet());
        
        String degree = "otm-bb9c3242-637c-4a7f-883f-b8850ed2dab6";
        student.setDegree(degree);
        assertEquals(true, student.isDegreeSet());
    }
    
}

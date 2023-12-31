package fi.tuni.prog3.sisu.files;

import fi.tuni.prog3.sisu.Student;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for JsonReaderWriter class.
 */
public class JsonReaderWriterTest {
    
    /**
     * Test of readFromFile method, of class JsonReaderWriter. Makes sure that 
     * the readFromFile method returns null when trying to read a wrong 
     * student number.
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testReadFromFileWithInvalidStudentNumber() throws Exception {
        System.out.println("readFromFileWithInvalidStudentNumber");
        JsonReaderWriter instance = new JsonReaderWriter();
        Student testStudent = new Student("Test", "Tester", 
                "invalidnumber", 2000, 2005);
        testStudent.setDegree("degree"); 
        testStudent.addCompletedCourse("TestCourse");

        assertNull(instance
                .readFromFile(testStudent.getStudentNumber()));
    }
    
    /**
     * Test reading a student from an existing JSON file with a valid student 
     * number. The method should return the correct student with all the 
     * information.
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testReadFromFileWithExistingStudentNumber() throws Exception {
        System.out.println("readFromFileWithExistingStudentNumber");
        JsonReaderWriter readerWriter = new JsonReaderWriter();
        String studentNumber = "12345";
        Student expectedStudent = new Student("Test", "Tester", 
                studentNumber, 2018, 2022, "degree");

        expectedStudent.addCompletedCourse("Mathematics");
        expectedStudent.addCompletedCourse("English");
        readerWriter.writeToFile(expectedStudent);
        Student actualStudent = readerWriter
                .readFromFile(studentNumber);
        assertEquals(expectedStudent.getFirstName(), 
                actualStudent.getFirstName());
        assertEquals(expectedStudent.getLastName(), 
                actualStudent.getLastName());
        assertEquals(expectedStudent.getStudentNumber(), 
                actualStudent.getStudentNumber());
        assertEquals(expectedStudent.getStartYear(), 
                actualStudent.getStartYear());
        assertEquals(expectedStudent.getGradYear(), 
                actualStudent.getGradYear());
        assertEquals(expectedStudent.getDegree(), 
                actualStudent.getDegree());
        assertEquals(expectedStudent.getCompletedCourses(), 
                actualStudent.getCompletedCourses());
    }

    /**
     * Test of writeToFile method, of class JsonReaderWriter. Makes sure the 
     * writer catches errors that are raised when the student number contains 
     * illegal characters.
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testWriteToFileWithInvalidStudentNumber() throws Exception {
        System.out.println("writeToFile");
        Student testStudent = new Student("Test", "Tester", 
                "..\\12345", 2000, 2005);
        JsonReaderWriter instance = new JsonReaderWriter();
        boolean expResult = false;
        assertEquals(expResult, 
                instance.writeToFile(testStudent));

    }
    /**
     * Tests the writeToFile() method of the JsonReaderWriter class. It checks 
     * if the method returns true when given a valid Student object.
     * @throws Exception if an error occurs while executing the test.
     */
    @Test
    public void testWriteToFileWithValidStudent() throws Exception {
        System.out.println("writeToFileWithValidStudent");
        JsonReaderWriter readerWriter = new JsonReaderWriter();
        String studentNumber = "87654321";
        Student studentToWrite = new Student("Test", "Tester", 
                studentNumber, 2020, 2024,
                "Masters in History");
        studentToWrite.addCompletedCourse("History");
        studentToWrite.addCompletedCourse("Physics");
        boolean writeSuccessful = readerWriter
                .writeToFile(studentToWrite);
        assertTrue(writeSuccessful);
    }
    
}

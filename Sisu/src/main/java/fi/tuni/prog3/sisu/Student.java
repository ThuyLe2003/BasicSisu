package fi.tuni.prog3.sisu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fi.tuni.prog3.sisu.files.iReadAndWriteToFile;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

/**
 * A class for storing information on Students.
 */
public class Student {
    private String firstName;
    private String lastName;
    private String studentNumber;
    private int startYear;
    private int gradYear;
    private String degree;
    private TreeSet<String> completedCourses = new TreeSet<>();

    /**
     * Constructs an empty Student object.
     */
    public Student() {
    }

    /**
     * Constructs a student with given information.
     * @param firstName first name of the Student.
     * @param lastName last name of the Student.
     * @param studentNumber student number.
     * @param startYear start year of the Student.
     * @param gradYear target graduation year of the Student.
     */
    public Student(String firstName, String lastName, String studentNumber, 
            int startYear, int gradYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
        this.startYear = startYear;
        this.gradYear = gradYear;
    }

    /**
     * Constructs a student with given information.
     * @param firstName first name of the Student.
     * @param lastName last name of the Student.
     * @param studentNumber student number.
     * @param startYear start year of the Student.
     * @param gradYear target graduation year of the Student.
     * @param degree the degree programme of the Student.
     */
    public Student(String firstName, String lastName, String studentNumber, 
            int startYear, int gradYear, String degree) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
        this.startYear = startYear;
        this.gradYear = gradYear;
        this.degree = degree;
    }

    /**
     * Constructs a student with given information.
     * @param firstName first name of the Student.
     * @param lastName last name of the Student.
     * @param studentNumber student number.
     * @param startYear start year of the Student.
     * @param gradYear target graduation year of the Student.
     * @param degree the degree programme of the Student.
     * @param completedCourses list of courses completed by the Student.
     */
    public Student(String firstName, String lastName, String studentNumber, 
            int startYear, int gradYear, String degree, 
            TreeSet<String> completedCourses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
        this.startYear = startYear;
        this.gradYear = gradYear;
        this.degree = degree;
        this.completedCourses = completedCourses;
    }

    /**
     * Returns the first name of the Student.
     * @return first name of the Student.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name of the Student.
     * @return last name of the Student.
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Returns the student number of the Student.
     * @return student number of the Student.
     */
    public String getStudentNumber() {
        return studentNumber;
    }

    /**
     * Returns the start year of the Student.
     * @return start year of the Student.
     */
    public int getStartYear() {
        return startYear;
    }

    /**
     * Returns the target graduation year of the Student.
     * @return the target graduation year of the Student.
     */
    public int getGradYear() {
        return gradYear;
    }

    /**
     * Returns the degree of the Student.
     * @return the degree of the Student.
     */
    public String getDegree() {
        return degree;
    }

    /**
     * Returns the sets of ids of completed courses of the Student.
     * @return the sets of ids of completed courses of the Student.
     */
    public TreeSet<String> getCompletedCourses() {
        return completedCourses;
    }
    
    /**
     * Sets the target graduation year of the Student.
     * @param year new target graduation year of the Student.
     * @return true if the new graduation year is set, false when the new target 
     * year is smaller than the start year of the Student.
     */
    public boolean setGradYear(int year) {
        if (startYear < year) {
            return false;
        }
        this.gradYear = year;
        return true;
    }
    
    /**
     * Adds new course to the curriculum of the Student.
     * @param courseId id of the new course to be added.
     */
    public void addCompletedCourse(String courseId) {
        completedCourses.add(courseId);
    }
    
    /**
     * Adds new course to the curriculum of the Student.
     * @param coursesId sets of ids of courses to be added.
     */
    public void addCompletedCourse(TreeSet<String> coursesId) {
        completedCourses = coursesId;
    }

    /**
     * Sets the degree programme for the Student.
     * @param degree the degree to be set.
     */
    public void setDegree(String degree) {
        this.degree = degree;
    }
    
    /**
     * Returns true if the student has set a degree programme, otherwise false
     * @return true if the student has set a degree programme, otherwise false
     */
    public boolean isDegreeSet() {
        return degree.length() > 0;
    }

    /**
     * Reads JSON from the given file.
     * @param fileName name of the file to read from.
     * @throws IOException if the method e.g, cannot find the file. 
     */
    public void readFromFile(String fileName) throws IOException {
        Gson gson = new Gson();
        JsonObject dataSet = gson.fromJson(new FileReader(fileName + ".json"), 
                JsonObject.class);
        firstName = dataSet.get("firstName").getAsString();
        lastName = dataSet.get("lastName").getAsString();
        studentNumber = dataSet.get("studentNumber").getAsString();
        startYear = dataSet.get("startYear").getAsInt();
        gradYear = dataSet.get("gradYear").getAsInt();
        degree = dataSet.get("degree").getAsString();
        
        JsonArray courses = dataSet.getAsJsonArray("courses");
        courses.forEach(course -> {
            completedCourses.add(course.getAsString());
        });
    }

    /**
     * Write the student progress as JSON into the given file.
     * @param fileName name of the file to write to.
     * @return true if the write was successful, otherwise false.
     * @throws IOException if the method e.g., cannot write to a file.
     */
    public boolean writeToFile(String fileName) throws IOException {
        try (FileWriter writer = new FileWriter(fileName + ".json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject jsonObj = new JsonObject();
            jsonObj.addProperty("firstName", firstName);
            jsonObj.addProperty("lastName", lastName);
            jsonObj.addProperty("studentNumber", studentNumber);
            jsonObj.addProperty("startYear", startYear);
            jsonObj.addProperty("gradYear", gradYear);
            jsonObj.addProperty("degree", degree);
            
            if (!completedCourses.isEmpty()) {
                JsonArray courses = new JsonArray();
                
                completedCourses.forEach(course -> {
                    courses.add(course);
                });
                
                jsonObj.add("courses", courses);
            } else {
                jsonObj.add("courses", null);
            }
            gson.toJson(jsonObj, writer);
            return true;
        } 
        catch (IOException e) {
            return false;
        }
    }
}

package fi.tuni.prog3.sisu;

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
}

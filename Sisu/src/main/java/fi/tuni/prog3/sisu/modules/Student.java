package fi.tuni.prog3.sisu.modules;

import java.util.ArrayList;

/**
 * A class for storing information on Students
 */
public class Student {
    private final String firstName;
    private final String lastName;
    private final String studentNumber;
    private final int startYear;
    private int gradYear;
    private String degree;
    private ArrayList<Course> curriculum;

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

    public Student(String firstName, String lastName, String studentNumber, 
            int startYear, String degree) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
        this.startYear = startYear;
        this.degree = degree;
    }

    public Student(String firstName, String lastName, String studentNumber, 
            int startYear, String degree, ArrayList<Course> curriculum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
        this.startYear = startYear;
        this.degree = degree;
        this.curriculum = curriculum;
    }

    /**
     * Returns the first name of the Student.
     * @return first name of the Student
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
     * @return start year of the Student
     */
    public int getStartYear() {
        return startYear;
    }

    /**
     * Returns the target graduation year of the Student.
     * @return 
     */
    public int getGradYear() {
        return gradYear;
    }

    /**
     * Returns the curriculum of the Student.
     * @return curriculum of the Student.
     */
    public ArrayList<Course> getCurriculum() {
        return curriculum;
    }

    public String getDegree() {
        return degree;
    }

    /**
     * Set the target graduation year of the Student.
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
     * Add new course to the curriculum of the Student.
     * @param course new course to be added.
     * @return true of new course is added, false if the course is already in
     * the curriculum.
     */
    public boolean addCourse(Course course) {
        if (curriculum.contains(course)) {
            return false;
        }
        curriculum.add(course);
        return true;
    }
    
    /**
     * Change the state of a course.
     * @param courseName the name of the course to be changed.
     */
    public void changeCourseState(String courseName) {
        for (int i = 0; i < curriculum.size(); ++i) {
            if (curriculum.get(i).getName().equals(courseName)) {
                curriculum.get(i).changeState();
                return;
            }
        }
    }

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
     * Returns completed courses.
     * @return completed courses.
     */
    public ArrayList<Course> getCompletedCourses() {
        ArrayList<Course> completedCourses = new ArrayList<>();
        for (Course course : curriculum) {
            if (course.isCompleted()) {
                completedCourses.add(course);
            }
        }
        return completedCourses;
    }
}

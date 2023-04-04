/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.sisu;

/**
 * A class for storing information on Courses
 */
public class Course extends DegreeModule {
    private boolean completedState;
    
    /**
     * Constructs a course with given information and the default incomplete 
     * state.
     * @param name name of the Course
     * @param id id of the Course
     * @param groupId group id of the Course
     * @param minCredits minimum credits of the Course.
     */
    public Course(String name, String id, String groupId, int minCredits) {
        super(name, id, groupId, minCredits);
        this.completedState = false;
    }

    /**
     * Return the progress of the course.
     * @return true if the course is finished, false otherwise.
     */
    public boolean isCompleted() {
        return completedState;
    }

    /**
     * Change the state of the course
     */
    public void changeState() {
        completedState = !completedState;
    }

    /**
     * Return a string on the state of the course.
     * @return a string in format "[name]: [state].".
     */
    @Override
    public String toString() {
        if (completedState) {
            return this.getName() + ": completed.";
        } else {
            return this.getName() + ": on-going.";
        }
    }
}

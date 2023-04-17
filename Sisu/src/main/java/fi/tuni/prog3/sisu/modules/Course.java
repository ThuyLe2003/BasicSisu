package fi.tuni.prog3.sisu.modules;

/**
 * A class for storing information on Courses.
 */
public class Course extends DegreeModule {
    private boolean completedState;
    private String code;
    
    /**
     * Constructs a course with given information and the default incomplete 
     * state.
     * @param name name of the Course.
     * @param id id of the Course.
     * @param groupId group id of the Course.
     * @param minCredits minimum credits of the Course.
     * @param code code of the Course.
     */
    public Course(String name, String id, String groupId, int minCredits, 
            String code) {
        super(name, id, groupId, minCredits);
        this.completedState = false;
        this.code = code;
    }

    /**
     * Returns the progress of the course.
     * @return true if the course is finished, false otherwise.
     */
    public boolean isCompleted() {
        return completedState;
    }
    
    /**
     * Returns the code of the course.
     * @return the code of the course.
     */
    public String getCode() {
        return code;
    }
    
    /**
     * Changes the state of the course.
     */
    public void changeState() {
        completedState = !completedState;
    }

    /**
     * Throws exception as nothing can be added to a course.
     * @param module the module to be added, the type can be either StudyModule,
     * GroupingModule or Course.
     * @throws UnsupportedOperationException
     */
    @Override
    public void addItem(DegreeModule module) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

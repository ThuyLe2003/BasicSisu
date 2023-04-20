package fi.tuni.prog3.sisu.modules;

import java.util.ArrayList;

/**
 * An abstract class for storing information on Modules and Courses.
 */
public abstract class DegreeModule {
    private String name;
    private String id;
    private String groupId;
    private int minCredits;
    private ArrayList<Course> courses = new ArrayList<>();
    
    /**
     * A constructor for initializing the member variables.
     * @param name name of the Module or Course.
     * @param id id of the Module or Course.
     * @param groupId group id of the Module or Course.
     * @param minCredits minimum credits of the Module or Course.
     */
    public DegreeModule(String name, String id, String groupId, 
            int minCredits) {
        
        this.name = name;
        this.id = id;
        this.groupId = groupId;
        this.minCredits = minCredits;
    }
    
    /**
     * Returns the name of the Module or Course.
     * @return name of the Module or Course.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Returns the id of the Module or Course.
     * @return id of the Module or Course.
     */
    public String getId() {
        return this.id;
    }
    
    /**
     * Returns the group id of the Module or Course.
     * @return group id of the Module or Course.
     */
    public String getGroupId() {
        return this.groupId;
    }
    
    /**
     * Returns the minimum credits of the Module or Course.
     * @return minimum credits of the Module or Course.
     */
    public int getMinCredits() {
        return this.minCredits;
    }

    /**
     * Return the name and minimum credits of the Module or Course.
     * @return the name and minimum credits of the Module or Course.
     */
    @Override
    public String toString() {
        return name + " (" + minCredits + " credits)";
    }

    /**
     * Adds a module to the correct list.
     * @param module module to be added.
     */
    public void addItem(DegreeModule module) {
        // Do nothing as default.
    };
    
    /**
     * Return all children modules under the module.
     * @return null as default for Course.
     */
    public ArrayList<ArrayList<? extends DegreeModule>> getChildren() {
        return null;
    }
}

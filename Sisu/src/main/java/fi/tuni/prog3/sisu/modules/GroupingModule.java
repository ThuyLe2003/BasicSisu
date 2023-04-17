package fi.tuni.prog3.sisu.modules;

import java.util.ArrayList;

/**
 * A class for storing information about grouping module.
 */
public class GroupingModule extends DegreeModule {
    private ArrayList<StudyModule> studyModules = new ArrayList<>();
    private ArrayList<GroupingModule> subGroupingModules = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<ArrayList<? extends DegreeModule>> allChildren;
    
    /**
     * A constructor for initializing the member variables.
     * @param name name of the GroupingModule.
     * @param id id of the GroupingModule.
     * @param groupId group id of the GroupingModule.
     * @param minCredits minimum credits of the GroupingModule.
     */
    public GroupingModule(String name, String id, String groupId, int minCredits) {
        super(name, id, groupId, minCredits);
    }

    /**
     * Returns all study modules under the GroupingModule.
     * @return all study modules under the GroupingModule.
     */
    public ArrayList<StudyModule> getStudyModules() {
        return studyModules;
    }

     /**
     * Returns all sub-modules under the GroupingModule.
     * @return all sub-modules under the GroupingModule.
     */
    public ArrayList<GroupingModule> getSubGroupingModules() {
        return subGroupingModules;
    }

    /**
     * Returns all the courses under the GroupingModule.
     * @return all the courses under the GroupingModule.
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }
    
    /**
     * Adds a module to the correct list.
     * @param module the module to be added, the type can be either StudyModule
     * or Course.
     */
    @Override
    public void addItem(DegreeModule module) {
        if (module instanceof StudyModule) {
            studyModules.add((StudyModule)module);
        } else if (module instanceof GroupingModule) {
            subGroupingModules.add((GroupingModule)module);
        } else if (module instanceof Course) {
            courses.add((Course)module);
        }
    }

    /**
     * Returns only the name of the GroupingModule.
     * @return only the name of the GroupingModule.
     */
    @Override
    public String toString() {
        return this.getName();
    }
    
    /**
     * Returns all children modules of the GroupingModule.
     * @return all children modules of the GroupingModule.
     */
    @Override
    public ArrayList<ArrayList<? extends DegreeModule>> getChildren() {
        allChildren = new ArrayList<>();

        if (!studyModules.isEmpty()) {
            allChildren.add(studyModules);
        }
        if (!subGroupingModules.isEmpty()) {
            allChildren.add(subGroupingModules);
        }
        if (!courses.isEmpty()) {
            allChildren.add(courses);
        }
        return allChildren;
    }
}

package fi.tuni.prog3.sisu.modules;

import java.util.ArrayList;

/**
 * A class for storing information about study module.
 */
public class StudyModule extends DegreeModule {
    
    private ArrayList<StudyModule> subModules = new ArrayList<>();
    private ArrayList<GroupingModule> groupingModules = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<ArrayList<? extends DegreeModule>> allChildren;
    
    /**
     * A constructor for initializing the member variables.
     * @param name name of the StudyModule.
     * @param id id of the StudyModule.
     * @param groupId group id of the StudyModule.
     * @param minCredits minimum credits of the StudyModule.
     */
    public StudyModule(String name, String id, String groupId, int minCredits) {
        super(name, id, groupId, minCredits);
    }
    
    /**
     * Returns all sub-modules under the StudyModule.
     * @return all sub-modules under the StudyModule.
     */
    public ArrayList<StudyModule> getSubModules() {
        return subModules;
    }

    /**
     * Returns all grouping modules under the StudyModule.
     * @return all grouping modules under the StudyModule.
     */
    public ArrayList<GroupingModule> getGroupingModules() {
        return groupingModules;
    }

    /**
     * Returns all the courses under the StudyModule.
     * @return all the courses under the StudyModule.
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }
    
    /**
     * Adds a module to the correct list.
     * @param module the module to be added, the type can be either StudyModule,
     * GroupingModule or Course.
     */
    @Override
    public void addItem(DegreeModule module) {
        if (module instanceof StudyModule) {
            subModules.add((StudyModule)module);
        }
        else if (module instanceof GroupingModule) {
            groupingModules.add((GroupingModule)module);
        }
        else if (module instanceof Course) {
            courses.add((Course)module);
        }
    }
    
    /**
     * Returns all children modules of the StudyModule.
     * @return all children modules of the StudyModule.
     */
    @Override
    public ArrayList<ArrayList<? extends DegreeModule>> getChildren() {
        allChildren = new ArrayList<>();
        
        if (!subModules.isEmpty()) {
            allChildren.add(subModules);
        }
        if (!groupingModules.isEmpty()) {
            allChildren.add(groupingModules);
        }
        if (!courses.isEmpty()) {
            allChildren.add(courses);
        }
        return allChildren;
    }
}

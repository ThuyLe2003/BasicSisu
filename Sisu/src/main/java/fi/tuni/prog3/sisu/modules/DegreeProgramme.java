package fi.tuni.prog3.sisu.modules;

import java.util.ArrayList;

/**
 * A class for storing information about degree structures.
 */
public class DegreeProgramme extends DegreeModule {
    
    private ArrayList<StudyModule> studyModules = new ArrayList<>();
    private ArrayList<GroupingModule> groupingModules = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<ArrayList<? extends DegreeModule>> allChildren;
    
    /**
     * A constructor for initializing the member variables.
     * @param name name of the DegreeProgramme.
     * @param id id of the DegreeProgramme.
     * @param groupId group id of the DegreeProgramme.
     * @param minCredits minimum credits of the DegreeProgramme.
     */
    public DegreeProgramme(String name, String id, String groupId, 
            int minCredits) {
        super(name, id, groupId, minCredits);
    }

    /**
     * Returns all study modules under the DegreeProgramme.
     * @return all study modules under the DegreeProgramme.
     */
    public ArrayList<StudyModule> getStudyModules() {
        return studyModules;
    }

    /**
     * Returns all grouping modules under the DegreeProgramme.
     * @return all grouping modules under the DegreeProgramme.
     */
    public ArrayList<GroupingModule> getGroupingModules() {
        return groupingModules;
    }

    /**
     * Returns all the courses under the DegreeProgramme.
     * @return all the courses under the DegreeProgramme.
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }
    
    /**
     * Adds a module (or course) to the correct list.
     * @param module the module to be added, the type can be either StudyModule,
     * GroupingModule or Course.
     */
    @Override
    public void addItem(DegreeModule module) {
        if (module instanceof StudyModule) {
            studyModules.add((StudyModule)module);
        }
        else if (module instanceof GroupingModule) {
            groupingModules.add((GroupingModule)module);
        }
        else if (module instanceof Course) {
            courses.add((Course)module);
        }
    }

    /**
     * Returns all children modules of the DegreeProgramme.
     * @return all children modules of the DegreeProgramme.
     */
    @Override
    public ArrayList<ArrayList<? extends DegreeModule>> getChildren() {
        allChildren = new ArrayList<>();
        
        if (!studyModules.isEmpty()) {
            allChildren.add(studyModules);
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

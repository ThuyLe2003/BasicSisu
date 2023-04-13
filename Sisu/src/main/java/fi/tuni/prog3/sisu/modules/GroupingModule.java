package fi.tuni.prog3.sisu.modules;

import java.util.ArrayList;

/**
 * A class for storing information about grouping module.
 */
public class GroupingModule extends DegreeModule {
    private ArrayList<StudyModule> studyModules = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>();
    
    public GroupingModule(String name, String id, String groupId, int minCredits) {
        super(name, id, groupId, minCredits);
    }

    public ArrayList<StudyModule> getStudyModules() {
        return studyModules;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
    
    /**
     * Adds a module to the correct list
     * @param module the module to be added, the type can be either StudyModule
     * or Course
     */
    @Override
    public void addItem(DegreeModule module) {
        if (module instanceof StudyModule) {
            studyModules.add((StudyModule)module);
        }
        else if (module instanceof Course) {
            courses.add((Course)module);
        }
    }

    @Override
    public String toString() {
        return this.getName();
    }
}

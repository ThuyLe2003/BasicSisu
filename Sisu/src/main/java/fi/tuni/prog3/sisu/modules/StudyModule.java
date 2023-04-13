package fi.tuni.prog3.sisu.modules;

import java.util.ArrayList;

/**
 * A class for storing information about study module.
 * @author Admin
 */
public class StudyModule extends DegreeModule {
    
    private ArrayList<StudyModule> subModules = new ArrayList<>();
    private ArrayList<GroupingModule> groupingModules = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>();
    
    public StudyModule(String name, String id, String groupId, int minCredits) {
        super(name, id, groupId, minCredits);
    }
    
    public ArrayList<StudyModule> getSubModules() {
        return subModules;
    }

    public ArrayList<GroupingModule> getGroupingModules() {
        return groupingModules;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
    
    /**
     * Adds a module to the correct list
     * @param module the module to be added, the type can be either StudyModule,
     * GroupingModule or Course
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
}

package fi.tuni.prog3.sisu;

import fi.tuni.prog3.sisu.files.JsonReaderWriter;
import fi.tuni.prog3.sisu.kori.Data;
import fi.tuni.prog3.sisu.modules.Course;
import fi.tuni.prog3.sisu.modules.DegreeModule;
import fi.tuni.prog3.sisu.modules.DegreeProgramme;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeSet;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

/**
 * FXML Controller class for the view after logging in.
 */
public class SelectionViewController implements Initializable {
        
    @FXML
    private ListView<String> chooseDegreeListView;    
    @FXML
    private Label degreeProgLabel;    
    @FXML            
    private Label firstNameLabel;    
    @FXML
    private Label lastNameLabel;   
    @FXML
    private Label studentNumberLabel;   
    @FXML
    private Label startYearLabel;   
    @FXML
    private Label gradYearLabel;   
    @FXML
    private Label creditsLabel;
    @FXML
    private Label contentLabel;
    @FXML
    private Button saveButton;
    @FXML
    private Button logOutButton;   
    @FXML
    private Label errorLabel;
    @FXML
    private TreeView curriculum = new TreeView<>();
    
    private Course selectedCourse;
    
    private int totalCredits = 0;
    
    private TreeSet<String> completedCourses;
    private TreeSet<String> countedCourses = new TreeSet<>();

    private ArrayList<TreeItem> allChildren = new ArrayList<>();
        
    private final Data sisu = new Data();
    
    private final HashMap<String, DegreeProgramme> degrees = sisu.getDegrees();
    
    private Student user = new Student();
    
    private String currentDegree;
    private String degreeToChange;
    
    /**
     * Initialize the view.
     * @param url not used in this application.
     * @param rb not used in this application.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showDegrees();
        completedCourses = user.getCompletedCourses();
        logOutButton.setOnAction(this::handleLogOutButtonAction);
        saveButton.setOnAction(this::handleSaveButtonAction);
        degreeProgLabel.setWrapText(true);
    }
    
    /**
     * Logs user out and returns to start dialogue. Writes student info in file.
     * @param e 
     */
    private void handleLogOutButtonAction(ActionEvent e) {
        JsonReaderWriter writer = new JsonReaderWriter();
        try {
            try {
                writer.writeToFile(user);
            } catch (Exception ex) {
                errorLabel.setText("Something went wrong!");
            }
            Stage primaryStage = (Stage) logOutButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("startDialogue.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            primaryStage.setScene(newScene);
            primaryStage.show();
        }
        catch (IOException ex) {
        }        
    }
    
    /**
     * Logs user out and returns to start dialogue. Writes student info in file.
     * @param e 
     */
    private void handleSaveButtonAction(ActionEvent e) {
        if (!degreeToChange.equals(degreeProgLabel.getText()))
        {
            for (Map.Entry<String, DegreeProgramme> entry : degrees.entrySet()) {
                if (entry.getValue().getName().equals(degreeToChange)) {
                    degreeProgLabel.setText(degreeToChange);
                    currentDegree = entry.getValue().getId();
                    completedCourses = new TreeSet<>();
                    user.addCompletedCourse(completedCourses);
                    totalCredits = 0;
                    chooseDegree();
                    showCredits();
                    return;
                }
            }
        }
        
    }
    
    /**
     * Shows the degrees in the list view and lets the user choose and change
     * a degree programme.
     */
    private void showDegrees() {
        TreeSet<String> degreeNames = new TreeSet<>();
        for (DegreeProgramme prog : degrees.values())
        {
            degreeNames.add(prog.getName());
        }
        chooseDegreeListView.getItems().addAll(degreeNames);
        chooseDegreeListView.getSelectionModel().selectedItemProperty()
                        .addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t,
                    String t1) {
                degreeToChange = chooseDegreeListView.getSelectionModel()
                        .getSelectedItem();
            }
        }); 
    }
    
    /**
     * Load the curriculum view using currentDegree.
     */
    private void chooseDegree() {
        user.setDegree(currentDegree);
        DegreeProgramme degree = sisu.getDegree(sisu
                .getDegrees()
                .get(currentDegree));
        TreeItem rootDegree = new TreeItem<>(degree);
        curriculum.setRoot(rootDegree);
        for (ArrayList<? extends DegreeModule> child : degree.getChildren()) {
            for (DegreeModule module : child) {
                rootDegree.getChildren().add(addChildrenToTree(module));
            }
        }
        updateCompletions();
    }
    
    /**
     * When a course is selected, saves the course into selectedCourse and shows 
     * the completion methods information of the course.
     */
    public void selectItem() {
        if(curriculum.getSelectionModel().getSelectedItem() == null){
            return;
        }
        contentLabel.setText("");
        TreeItem item = (TreeItem) curriculum.getSelectionModel().getSelectedItem();

        if(!(item.getValue() instanceof Course)) {
            selectedCourse = null;
            return;
        }
        Course course = (Course) item.getValue();
        contentLabel.setText(course.getMethods());
        contentLabel.setMaxWidth(268);
        contentLabel.setWrapText(true);
        selectedCourse = course;
    }
    
    /**
     * Saves the progress of the user and updates the total credits.
     */
    public void saveProgress() {
        user.addCompletedCourse(completedCourses);
        showCredits();
    }
    
    /**
     * Populates the curriculum view.
     * @param module module to be added to the curriculum.
     * @return item for the module.
     */
    private TreeItem<DegreeModule> addChildrenToTree(DegreeModule module) {
        TreeItem item = new TreeItem<>(module);
        allChildren.add(item);
        if (module instanceof Course){
            if (completedCourses.contains(module.getId())) {
                item.setValue(module + " (completed)");
                if (!countedCourses.contains(module.getId())) {
                    totalCredits += module.getMinCredits();
                    countedCourses.add(module.getId());
                }
            }
            return item;
        }
        else {
            for (ArrayList<? extends DegreeModule> child : module.getChildren()) {
                for (DegreeModule childModule : child) {

                    item.getChildren().add(addChildrenToTree(childModule));
                }
            }
            return item;
        }
    }
    
    /**
     * Saves the course as completed and adds up the total credits of user.
     */
    public void updateCompletions() {
        if(selectedCourse != null){
            if (!completedCourses.contains(selectedCourse.getId())) {
                completedCourses.add(selectedCourse.getId());
                totalCredits += selectedCourse.getMinCredits();
            }          
        } else {
            return;
        }
        
        for(String id : completedCourses){
            for(TreeItem item : allChildren){
                if(!(item.getValue() instanceof String)) {
                    DegreeModule module = (DegreeModule) item.getValue();
                    if(id.equals(module.getId())){
                        item.setValue(module + " (completed)");
                    }
                }
            }
        }
    }
    
    /**
     * Shows the progress in credit completion of user.
     */
    private void showCredits() {
        int minCredits = degrees.get(currentDegree).getMinCredits();
        creditsLabel.setText(totalCredits + "/" + minCredits);
    }
    
    /**
     * Gets the user data from the start dialogue.
     * @param loggedUser user logged in the application.
     */
    public void initData(Student loggedUser) {
        this.user = loggedUser;
        firstNameLabel.setText(user.getFirstName());
        lastNameLabel.setText(user.getLastName());
        studentNumberLabel.setText(user.getStudentNumber());
        startYearLabel.setText(Integer.toString(user.getStartYear()));
        gradYearLabel.setText(Integer.toString(user.getGradYear()));
        if (!"".equals(user.getDegree())) {
            degreeProgLabel.setText(sisu.getDegrees().get(user.getDegree())
                .getName());
        }
        if (user.isDegreeSet()) {
            completedCourses = user.getCompletedCourses();
            currentDegree = user.getDegree();
            degreeToChange = currentDegree;
            chooseDegree();
            showCredits();
        }
    }    
}

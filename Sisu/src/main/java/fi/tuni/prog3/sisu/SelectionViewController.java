
package fi.tuni.prog3.sisu;

import fi.tuni.prog3.sisu.files.JsonReaderWriter;
import fi.tuni.prog3.sisu.kori.Data;
import fi.tuni.prog3.sisu.modules.Course;
import fi.tuni.prog3.sisu.modules.DegreeProgramme;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeSet;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;

/**
 * FXML Controller class
 */

        
public class SelectionViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Tab studentInfoTab;
    
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
    private Button logOutButton;
        
    private final Data sisu = new Data();
    private final HashMap<String, DegreeProgramme> degrees = sisu.getDegrees();
    private Student user = new Student();
    String currentDegree; 
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showDegrees();
        logOutButton.setOnAction(this::handleLogOutButtonAction);  
    }
    
    private void handleLogOutButtonAction(ActionEvent e)
    {
        JsonReaderWriter writer = new JsonReaderWriter();
        try {
            writer.writeToFile(user);
            Sisu.setRoot("startDialogue");
        } catch (Exception ex) {
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
                chooseDegreeListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                currentDegree = chooseDegreeListView.getSelectionModel().getSelectedItem();
                degreeProgLabel.setText(currentDegree);
            }
        }); 
    }
        
    private void chooseDegree() {
        user.setDegree(currentDegree);
    }
    
    private void showCredits() {
        TreeSet<String> completed = user.getCompletedCourses();
        
              
    }

    public void initData(Student loggedUser) {
        user = loggedUser;
        firstNameLabel.setText(user.getFirstName());
        lastNameLabel.setText(user.getLastName());
        studentNumberLabel.setText(user.getStudentNumber());
        startYearLabel.setText(Integer.toString(user.getStartYear()));
        gradYearLabel.setText(Integer.toString(user.getGradYear()));
        degreeProgLabel.setText(user.getDegree());                
    }
    
}

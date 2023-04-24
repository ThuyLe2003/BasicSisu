package fi.tuni.prog3.sisu;

import fi.tuni.prog3.sisu.files.JsonReaderWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 */
public class StartDialogueController implements Initializable {

    @FXML
    private Button signUpButton;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField startYearField;
    @FXML
    private TextField gradYearField;
    @FXML
    private TextField newStudentNumberField;
    @FXML
    private Button logInButton;
    @FXML
    private TextField studentNumberField;
    @FXML
    private HBox startHBox;
    @FXML
    private Separator startSeparator;
    @FXML
    private Label signUpErrorLabel;
    @FXML
    private Label logInErrorLabel;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startHBox.setStyle("-fx-background-color: #6D94BD;");
        startSeparator.setStyle("-fx-background-color: #88B4D1;");
        
        signUpButton.setOnAction(this::handleSignUpButtonAction);
        
        logInButton.setOnAction(this::handleLogInButtonAction);                       
        }
    
    /**
     * 
     * @param event 
     */
    private void handleSignUpButtonAction(ActionEvent event) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        int startYear = Integer.parseInt(startYearField.getText());
        int gradYear = Integer.parseInt(gradYearField.getText());
        String studentNumber = newStudentNumberField.getText();
        if ("".equals(firstNameField.getText()) || lastNameField.getText() == null
            || startYearField.getText() == null || gradYearField.getText()
            == null || newStudentNumberField.getText() == null) {
            signUpErrorLabel.setText("Enter all information!");
        }
        Student newStudent = new Student(firstName, lastName, studentNumber,
                startYear, gradYear);
        }
    
    /**
     * 
     * @param event 
     */
    private void handleLogInButtonAction(ActionEvent event) {
        
        try {
            String studentNumber = studentNumberField.getText();
            if ("".equals(studentNumber))
            {
                logInErrorLabel.setText("Enter a student number!");
            }
            else
            {
                  Sisu.setRoot("selectionView");
//                if(checkStudent(studentNumber))
//                {
//                    Sisu.setRoot("selectionView");
//                }
//                else
//                {
//                    logInErrorLabel.setText("Wrong student number!");
//                }
            }
        } catch (IOException ex) {
            Logger.getLogger(StartDialogueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Checks if the the student number is used by a student
     * @param studentNumber
     * @return 
     */
    private boolean checkStudent(String studentNumber) throws Exception
    {
        JsonReaderWriter checker = new JsonReaderWriter();
        Student test = checker.readFromFile(studentNumber);
        return test != null;
    }
}   
package fi.tuni.prog3.sisu;

import fi.tuni.prog3.sisu.files.JsonReaderWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
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
    
    private Student newStudent;
    
    private String firstName;
    private String lastName;
    private int startYear;
    private int gradYear;
    private String studentNumber;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        startHBox.setStyle("-fx-background-color: #6D94BD;");
        startSeparator.setStyle("-fx-background-color: #88B4D1;");
        
        signUpButton.setOnAction(this::handleSignUpButtonAction);        
        logInButton.setOnAction(this::handleLogInButtonAction);                       
    }
    
    /**
     * Handles the event, when sign up button is pressed.
     * Checks if info is correct and creates a new student.
     * @param event 
     */
    private void handleSignUpButtonAction(ActionEvent event) {
        
        if ("".equals(firstNameField.getText().trim()) ||
                "".equals(lastNameField.getText().trim()) ||
                "".equals(startYearField.getText().trim()) ||
                "".equals(gradYearField.getText().trim()) ||
                "".equals(newStudentNumberField.getText().trim())) {
            signUpErrorLabel.setText("Enter all information!");
            return;            
        }
        
        firstName = firstNameField.getText();
        lastName = lastNameField.getText();
        try {
            startYear = Integer.parseInt(startYearField.getText());
            gradYear = Integer.parseInt(gradYearField.getText());
            studentNumber = newStudentNumberField.getText();
            if (startYear > gradYear) {
                signUpErrorLabel.setText("Grad year is before start year!");
                return;
            }
        }
        
        catch (NumberFormatException e) {
            signUpErrorLabel.setText("Years have to be numbers!");           
        }
        
        if (checkStudent(studentNumber)) {
            signUpErrorLabel.setText("Student number is taken!");
        }
        else {
            newStudent = new Student(firstName, lastName, studentNumber,
                startYear, gradYear); 
            moveStudentData();
        }                            
    }
    
    /**
     * Handles the event, when log in button is clicked.
     * @param event 
     */
    private void handleLogInButtonAction(ActionEvent event){
        
        studentNumber = studentNumberField.getText();
        if ("".equals(studentNumber.trim()))
        {
            logInErrorLabel.setText("Enter a student number!");
        }
        if (checkStudent(studentNumber)) {
            moveStudentData();
        }
        else {
            logInErrorLabel.setText("Wrong student number!"); 
        }        
    }
    
    /**
     * Checks if the the student number is used by a student
     * @param studentNumber
     * @return 
     */
    private boolean checkStudent(String studentNumber) {
        JsonReaderWriter checker = new JsonReaderWriter();
        Student test = null;
        try {
            test = checker.readFromFile(studentNumber);
            newStudent = test;
        } 
        catch (Exception ex) {
        }
        return test != null;
    }
    
    /**
     * Moves a student object to SelectionViewController class when signed up
     * or logged in.
     */
    public void moveStudentData() {
    
        try {
            Stage primaryStage = (Stage) signUpButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("selectionView.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            SelectionViewController newSceneController = loader.getController();
            newSceneController.initData(newStudent);
            primaryStage.setScene(newScene);
            primaryStage.show();
        }
        catch (IOException ex) {
        }                   
    }         
}   
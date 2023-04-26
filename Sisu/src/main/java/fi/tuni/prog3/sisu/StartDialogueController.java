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
        
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        int startYear = Integer.parseInt(startYearField.getText());
        int gradYear = Integer.parseInt(gradYearField.getText());
        String studentNumber = newStudentNumberField.getText();
        
        try {
            if (checkStudent(studentNumber))
            {
                signUpErrorLabel.setText("Student number already exists!");
            }
        }
        
        catch (Exception ex) {
        }
        
        Student student = new Student(firstName, lastName, studentNumber,
                startYear, gradYear);
        
        try {
            newStudent = student;
            moveStudentData();
        }
        catch (IOException ex) {
        }
    }
    
    /**
     * Handles the event, when log in button is clicked.
     * @param event 
     */
    private void handleLogInButtonAction(ActionEvent event){
        
        String studentNumber = studentNumberField.getText();
        if ("".equals(studentNumber.trim()))
        {
            logInErrorLabel.setText("Enter a student number!");
        }
        
        else try {
            if(checkStudent(studentNumber))
            {               
                moveStudentData();
            }
            else
            {               
                logInErrorLabel.setText("Wrong student number!");
            }
        }
        catch (Exception ex) {
        }
    }
    
    /**
     * Checks if the the student number is used by a student
     * @param studentNumber
     * @return 
     */
    private boolean checkStudent(String studentNumber) throws Exception {
        JsonReaderWriter checker = new JsonReaderWriter();
        Student test = checker.readFromFile(studentNumber);
        if (test != null)
        {
            newStudent = test;
        }
        return test != null;
    }
    
    /**
     * 
     * @throws IOException 
     */
    public void moveStudentData() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("selectionView.fxml"));

        Parent root = loader.load();
        Scene selection = new Scene(root);
        
        SelectionViewController controller = loader.getController();
        controller.initData(newStudent);
        
        Stage primaryStage = (Stage) logInButton.getScene().getWindow();
        primaryStage.setScene(selection);
        primaryStage.show();
    }
         
}   
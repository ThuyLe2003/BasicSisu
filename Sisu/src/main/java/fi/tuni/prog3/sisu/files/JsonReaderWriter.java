package fi.tuni.prog3.sisu.files;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import fi.tuni.prog3.sisu.Student;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * A class for writing and reading student -data from and to json -files.
 */
public class JsonReaderWriter {
    /**
     * Reads JSON from the given file.
     * @param StudentNumber - The student number of the students whose info is wanted
     * @return The data of the student with given student number
     * @throws Exception if the method e.g, cannot find the file with the given student number.
     */
    public Student readFromFile(String StudentNumber) throws Exception{

        try {
            String path = "students\\";
            JsonReader reader = new JsonReader(new FileReader(path + 
                                                    StudentNumber + ".json"));
            JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();
        

            String firstName = root.getAsJsonPrimitive("firstName")
                                    .getAsString();
            
            String lastName = root.getAsJsonPrimitive("lastName")
                                    .getAsString();

            String studentNumber = root.getAsJsonPrimitive("studentNumber")
                                    .getAsString();
            
            String degree = root.getAsJsonPrimitive("degree")
                                    .getAsString();

            int startYear = root.getAsJsonPrimitive("startYear")
                                    .getAsInt();

            int gradYear = root.getAsJsonPrimitive("gradYear")
                                    .getAsInt();

            Student student = new Student(firstName, lastName, studentNumber, 
                                            startYear, gradYear, degree);
            
            JsonArray courses = root.getAsJsonArray("courses");
            for (JsonElement course : courses) {
                student.addCompletedCourse(course.getAsString());
            }
            return student; 
        }
            catch (JsonIOException | JsonSyntaxException | 
                    FileNotFoundException e){
                return null;
        } 
    }

        /**
     * Writes JSON to the given file.
     * @param student - The student whose info is wanted to write
     * @return The data of the student with given student number
     * @throws Exception if the method e.g, cannot find the file with the given student number.
     */
    public boolean writeToFile(Student student) throws Exception {

        try (FileWriter writer = new FileWriter("students\\" +
                                student.getStudentNumber() + ".json")) {

            if(student.getStudentNumber().contains(".") ||
                    student.getStudentNumber().contains("\\")){
                throw new Exception("The student number can't contain "
                        + "special characters");
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject jsonObj = new JsonObject();
            jsonObj.addProperty("firstName", 
                            student.getFirstName());
            jsonObj.addProperty("lastName", 
                            student.getLastName());
            jsonObj.addProperty("studentNumber", 
                            student.getStudentNumber());
            jsonObj.addProperty("startYear", 
                            student.getStartYear());
            jsonObj.addProperty("gradYear", 
                            student.getGradYear());
            jsonObj.addProperty("degree", 
                            student.getDegree());


            if (!student.getCompletedCourses().isEmpty()) {
                JsonArray courses = new JsonArray();

                student.getCompletedCourses().forEach(course -> {
                    courses.add(course);
                });

                jsonObj.add("courses", courses);
            } else {
                jsonObj.add("courses", null);
            }
            gson.toJson(jsonObj, writer);
            return true;
        }
        catch(Exception e) {
            return false;   
        }
    }

}      

package fi.tuni.prog3.sisu.files;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import fi.tuni.prog3.sisu.Student;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.ProcessBuilder.Redirect.Type;

public class JsonReaderWriter{
    /**
     * Reads JSON from the given file.
     * @param StudentNumber
     * @return true if the read was successful, otherwise false.
     * @throws Exception if the method e.g, cannot find the file.
     */
    
    public Student readFromFile(String StudentNumber) throws Exception{

        String path = "students\\";
        JsonReader reader = new JsonReader(new FileReader(path + StudentNumber + ".json"));
        JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();
        
        String firstName = root.getAsJsonPrimitive("firstName").getAsString();
        String lastName = root.getAsJsonPrimitive("lastName").getAsString();

        String studentNumber = root.getAsJsonPrimitive("studentNumber").getAsString();

        int startYear = root.getAsJsonPrimitive("startYear").getAsInt();
        
        int gradYear = root.getAsJsonPrimitive("gradYear").getAsInt();
        
        Student student = new Student(firstName, lastName, studentNumber, startYear, gradYear);
        JsonArray courses = root.getAsJsonArray("courses");
        for(JsonElement course : courses){
            student.addCompletedCourse(course.getAsString());
        }
        
        System.out.println(student);
        System.out.println(student.getCompletedCourses());
        return student;
        
    }
    
        public class StudentAdapter implements JsonSerializer<Student> {

            @Override
            public JsonElement serialize(Student student, java.lang.reflect.Type type, JsonSerializationContext jsc) {

                    JsonObject object = new JsonObject();
                    object.addProperty("firstName", student.getFirstName());
                    object.addProperty("lastName", student.getLastName());
                    object.addProperty("studentNumber", student.getStudentNumber());
                    object.addProperty("startYear", student.getStartYear());
                    object.addProperty("gradYear", student.getGradYear());

                    return object;

            }

            public Student deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    return null;
                }
            }

    
    public boolean writeToFile(Student student) throws Exception {

        try (FileWriter writer = new FileWriter("students\\" + student.getStudentNumber() + ".json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject jsonObj = new JsonObject();
            jsonObj.addProperty("firstName", student.getFirstName());
            jsonObj.addProperty("lastName", student.getLastName());
            jsonObj.addProperty("studentNumber", student.getStudentNumber());
            jsonObj.addProperty("startYear", student.getStartYear());
            jsonObj.addProperty("gradYear", student.getGradYear());
            jsonObj.addProperty("degree", student.getDegree());
            
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
    }




}
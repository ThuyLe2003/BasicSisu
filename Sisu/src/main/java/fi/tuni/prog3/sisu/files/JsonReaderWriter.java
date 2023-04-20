package fi.tuni.prog3.sisu.files;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import fi.tuni.prog3.sisu.modules.Student;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.ProcessBuilder.Redirect.Type;

public class JsonReaderWriter{
    /**
     * Reads JSON from the given file.
     * @param fileName name of the file to read from.
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
        System.out.println(student);
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

        Gson gson = new GsonBuilder().registerTypeAdapter(Student.class, 
                new StudentAdapter()).setPrettyPrinting().create();

        gson.toJson(student);

           try (FileWriter fw = new FileWriter("students\\" + student.getStudentNumber() + ".json")) {
            gson.toJson(student, fw);
        }  



        return false;
    }




}
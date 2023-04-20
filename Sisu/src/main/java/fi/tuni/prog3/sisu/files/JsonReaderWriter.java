package fi.tuni.prog3.sisu.files;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import fi.tuni.prog3.sisu.modules.Course;
import fi.tuni.prog3.sisu.modules.Student;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;

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
        
        //String degree = String.valueOf(root.getAsJsonPrimitive("degree"));
        
     
        

       

       // return new Student("Testy", "Tester", "K0000", 2000, 2100);
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

    
    public boolean writeToFile(String fileName) throws Exception {
        ArrayList<Course> test = new ArrayList<>();
        test.add(new Course("Course", "testId", "testGroupId", 4, "TestCode"));
        Student testStudent = new Student("Testy", "Tester", "K0000", 2000, 2100);//, "Testy´s degree", test);
        Student testStudent2 = new Student("Testy2", "Teste3r", "K00400", 2000, 2100);//, "Testy´s degree", test);

        Gson gson = new GsonBuilder().registerTypeAdapter(Student.class, 
                new StudentAdapter()).setPrettyPrinting().create();

        gson.toJson(testStudent);

           try (FileWriter fw = new FileWriter("students\\" + fileName + ".json")) {
            gson.toJson(testStudent, fw);
            System.out.println("test");
            gson.toJson(testStudent2, fw);
        }  



        return false;
    }




}
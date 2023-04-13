package fi.tuni.prog3.sisu.files;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 */
public class SisuFileReader implements iReadAndWriteToFile {
    private JsonObject jsonObject;
    
    /**
     * Reads JSON from the given file.
     * @param fileName name of the file to read from.
     * @return true if the read was successful, otherwise false.
     * @throws Exception if the method e.g, cannot find the file. 
     */
    @Override
    public boolean readFromFile(String fileName) throws Exception {
        try (BufferedReader reader = Files.newBufferedReader(Path.of(fileName))) {
            jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
        }
        
        catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Write the student progress as JSON into the given file.
     * @param fileName name of the file to write to.
     * @return true if the write was successful, otherwise false.
     * @throws Exception if the method e.g., cannot write to a file.
     */
    @Override
    public boolean writeToFile(String fileName) throws Exception {
        return true;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }
}

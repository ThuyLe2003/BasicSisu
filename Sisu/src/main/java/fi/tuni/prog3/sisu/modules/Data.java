package fi.tuni.prog3.sisu.modules;

import fi.tuni.prog3.sisu.kori.Builder;
import static fi.tuni.prog3.sisu.kori.Builder.getObjAtt;
import static fi.tuni.prog3.sisu.kori.Builder.getObjName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A class for storing data retrieved from Sisu API.
 * @author Admin
 */
public class Data implements Builder {
    private HashMap<String, String> requestURLs = new HashMap<>();
    private HashMap<String, DegreeProgramme> degrees = new HashMap<>();
    private static HttpURLConnection connection;
    
    /**
     * Builds a container of http-request addresses to access Sisu Kori API. 
     */
    private void setRequestAddresses() {
        requestURLs.put("dataReq", "https://sis-tuni.funidata.fi/kori/"
                + "api/module-search?curriculumPeriodId=uta-lvv-2021"
                + "&universityId=tuni-university-root-id"
                + "&moduleType=DegreeProgramme&limit=1000");
        requestURLs.put("idReq", "https://sis-tuni.funidata.fi/kori/"
                + "api/modules/");
        requestURLs.put("groupIdReq", "https://sis-tuni.funidata.fi/"
                + "kori/api/modules/by-group-id?"
                + "&universityId=tuni-university-root-id&groupId=");
        requestURLs.put("courseReq", "https://sis-tuni.funidata.fi/"
                + "kori/api/course-units/by-group-id?"
                + "&universityId=tuni-university-root-id&groupId=");
    }
    
    /**
     * Fetches and returns data retrieved from Sisu API.
     * @param reqType request address type.
     * @param id id or group id value in String form of a module or course unit.
     * @return data of a module or a course unit or an empty String if fails.
     */
    private String fetchData(String reqType, String id) {
        String reqAdd = requestURLs.get(reqType) + id;
        BufferedReader reader;
        String line;
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(reqAdd); 
            connection = (HttpURLConnection) url.openConnection();           
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            
            int status = connection.getResponseCode();            
            if(status > 299) {
                reader = new BufferedReader(
                        new InputStreamReader(connection.getErrorStream())
                );
                while((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
            }
            else {
                reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream())
                );
                while((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
            }
            connection.disconnect();
            return response.toString();
        }
        catch(IOException e) {
            connection.disconnect();
            return "";
        }
    }
    
    /**
     * Receives and parses the data of degree programmes.
     * @param response fetched data of degrees.
     */
    private void parseDegreesData(String response) {
        try {
            JSONObject content = new JSONObject(response);
            JSONArray degreesArray = content.getJSONArray("searchResults");
            
            for(int i = 0; i < degreesArray.length(); i++) {
                try {
                    JSONObject degreeObj = degreesArray.getJSONObject(i);
                    String degreeName = degreeObj.getString("name");
                    int degreeMinCredits = degreeObj
                            .getJSONObject("credits")
                            .getInt("min");
                    String degreeId = degreeObj.getString("id");
                    String degreeGroupId = degreeObj.getString("groupId");
                    DegreeProgramme degreeProgramme = new DegreeProgramme(
                            degreeName, degreeId, 
                            degreeGroupId, degreeMinCredits
                    );
                    degrees.put(degreeId, degreeProgramme); 
                }
                catch(JSONException e) {
                    // Invalid degree programme modules are ignored.
                }
            }
        }
        catch(JSONException e) {
            // Invalid form are ignored.
        }                           
    }
    
    /**
     * Parses the data and updates the Degree Programme.
     * @param response fetched data of the Degree Programme.
     * @param degree the Degree Programme to be parsed.
     */
    private void parseDegreeData(String response, DegreeProgramme degree) {
        JSONObject degreeContent = new JSONObject(response);
        JSONObject ruleContent = degreeContent.getJSONObject("rule");
        parseRule(ruleContent, degree);
    }
    
    /**
     * Parses rule attribute to read all the sub-modules and course units of 
     * the super module.
     * @param ruleObj JSONObject containing a possible sub-rule or multiple 
     * rules. 
     * @param module the DegreeModule whose rules are parsed.
     * @throws JSONException
     */
    private void parseRule(JSONObject ruleObj, DegreeModule module) 
            throws JSONException {
        switch (ruleObj.getString("type")) {
            case "ModuleRule":
                String moduleData = fetchData("groupIdReq",
                        ruleObj.getString("moduleGroupId")); 
                if(!moduleData.equals("")) {
                    parseModuleData(module, moduleData);
                }
                break;
            case "CourseUnitRule":
                String courseData = fetchData("courseReq",
                        ruleObj.getString("courseUnitGroupId"));
                if(!courseData.equals("")) {
                    parseCourseData(module, courseData);
                }
                break;
            default:
                JSONArray rules = getRules(ruleObj);
                if(rules.isEmpty()) {
                    if(!getRule(ruleObj).isEmpty()) {
                        parseRule(getRule(ruleObj), module);
                    }
                    
                }
                else {
                    for(int i = 0; i < rules.length(); i++) {   
                        JSONObject newRuleObj = rules.getJSONObject(i);
                        parseRule(newRuleObj, module);
                    }
                }
                break;
        }
    }
    
    /**
     * Returns degree programmes. Return an empty container if Kori API 
     * http-request fails.
     * @return a HashMap "degrees" with degree programmes of type DegreePogramme, 
     * "degrees" is empty if data fetching fails.
     */
    public HashMap<String, DegreeProgramme> getDegrees() {
        setRequestAddresses();
        String data = fetchData("dataReq", "");
        if(!data.equals("")) {
            parseDegreesData(data);
        }        
        return degrees;
    }
    
    /**
     * Updates the contents of a given degree programme and returns the updated 
     * degree programme.
     * @param degree DegreeProgramme object.
     * @return updated DegreeProgramme object
     * content.
     */
    public DegreeProgramme getDegree(DegreeProgramme degree) {
        setRequestAddresses();
        String data = fetchData("idReq", degree.getId());
        if(!data.equals("")) {
            parseDegreeData(data, degree);
        }        
        return degree;
    }

    private void parseModuleData(DegreeModule module, String moduleData) {
        JSONArray modules = new JSONArray(moduleData);
        for (int i = 0; i < modules.length(); i++) {
            JSONObject moduleObj = modules.getJSONObject(i);

            var name = getObjName(moduleObj);
            var id = getObjAtt(moduleObj, "id");
            var groupId = getObjAtt(moduleObj, "groupId");
            
            JSONObject ruleObj = moduleObj.getJSONObject("rule");
            
            switch (moduleObj.getString("type")) {
                case "StudyModule":
                    int minCredits = moduleObj
                            .getJSONObject("targetCredits")
                            .getInt("min");
                    StudyModule studyModule = new StudyModule(
                            name, 
                            id, 
                            groupId, 
                            minCredits
                    );
                    module.addItem(studyModule);
                    parseRule(ruleObj, studyModule);
                case "GroupingModule":
                    GroupingModule groupingModule = new GroupingModule(
                            name,
                            id,
                            groupId,
                            0
                    );
                    module.addItem(groupingModule);
                    parseRule(ruleObj, groupingModule);
                
                    
            }
        }
    }

    private void parseCourseData(DegreeModule module, String courseData) {
        JSONArray courses = new JSONArray(courseData);
        for (int i = 0; i < courses.length(); i++) {
            JSONObject courseObj = courses.getJSONObject(i);
            
            String name = getObjName(courseObj);
            String code = getObjAtt(courseObj, "code");
            String id = getObjAtt(courseObj, "id");
            String groupId = getObjAtt(courseObj, "groupId");
            int minCredits = courseObj
                    .getJSONObject("credits")
                    .getInt("min");
            
            Course course = new Course(name, id, groupId, minCredits, code);
            module.addItem(course);
        }
    }

    /**
     * Searches a list of rules recursively and returns the first encountered 
     * list of rules, which is empty if no such list is found.
     * @param ruleObj the given rule of JSONObject type.
     * @return JSONArray type "rules" including possible module and course 
     * unit rules. "Rules" is empty, if no such list is found.
     */
    private JSONArray getRules(JSONObject ruleObj) {
        JSONArray rules = new JSONArray();
        if(ruleObj.has("rule")) {
            rules = getRules(ruleObj.getJSONObject("rule"));
        }
        else if(ruleObj.has("rules")) {
            rules = ruleObj.getJSONArray("rules");
        }
        return rules;
    }

    /**
     * Searches a rule of either type "ModuleRule" or "CourseUnitRule" 
     * recursively and returns the first encountered valid rule. Otherwise 
     * returns an empty rule object.
     * @param ruleContents the given rule of JSONObject type.
     * @return JSONObject type "rule", which either represents a "ModuleRule"
     * or "CourseUnitRule". "Rule" is empty, if no valid rule is found.
     */
    private JSONObject getRule(JSONObject ruleObj) {
        JSONObject rule = new JSONObject();
        if(ruleObj.has("rule")) {
            rule = getRule(ruleObj.getJSONObject("rule"));
        }
        else if(ruleObj.has("type")) {
            if(ruleObj.getString("type").equals("ModuleRule") || 
                ruleObj.getString("type").equals("CourseUnitRule")) {
                rule = ruleObj;
            }
        }
        return rule;
    }
}
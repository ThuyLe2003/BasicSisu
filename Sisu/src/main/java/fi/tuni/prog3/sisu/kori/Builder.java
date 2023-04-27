package fi.tuni.prog3.sisu.kori;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 */
public interface Builder {

    /**
     * Return the name of the object using the data retrieved from Sisu API.
     * @param object JSON object retrieved from API.
     * @return name of the object
     */
    public static String getObjName(JSONObject object) {
        String name = "";
        if(object.has("name") && !object.isNull("name")) {
            JSONObject nameObj = object.getJSONObject("name");
            if(nameObj.has("en") && !nameObj.isNull("en")) {
                name = nameObj.getString("en");
            } else if(nameObj.has("fi") && !nameObj.isNull("fi")) {
                name = nameObj.getString("fi");
            } else if(nameObj.has("sv") && !nameObj.isNull("sv")) {
                name = nameObj.getString("fi");
            }
        }
        return name;
    }
    
    /**
     * Return the id or group id or code of the object using the data retrieved  
     * from Sisu API.
     * @param object JSON object retrieved from API.
     * @param type id or group id or code.
     * @return id of the object
     */
    public static String getObjAtt(JSONObject object, String type) {
        String id = "";
        if(object.has(type) && !object.isNull(type)) {
            id = object.getString(type); 
        }
        return id;
    }
    
    /**
     * Return the completion methods of the course using the data retrieved 
     * from Sisu API.
     * @param object JSON object retrieved from API.
     * @return completion methods of the object
     */
    public static String getObjMethods(JSONObject object) {
        String methods = "Missing information about this course's completion "
                + "methods.";
        if(object.has("completionMethods") && 
                !object.isNull("completionMethods")) {
            JSONArray methodsObj = object.getJSONArray("completionMethods");
            methods = "";
            for (int i = 0; i < methodsObj.length(); ++i) {
                JSONObject methodObj = methodsObj.getJSONObject(i);
                
                int ind = i + 1;
                String method = "+ Method " + ind + "\n";
                
                if(methodObj.has("evaluationCriteria") && 
                        !methodObj.isNull("evaluationCriteria")) {
                    String ec = "  - Evaluation Criteria:\n";
                    JSONObject obj = methodObj
                            .getJSONObject("evaluationCriteria");
                    if(obj.has("en") && !obj.isNull("en")) {
                        ec = ec + "   " + obj.getString("en");
                    } else if(obj.has("fi") && !obj.isNull("fi")) {
                        ec = ec + "   " + obj.getString("fi");
                    } else if(obj.has("sv") && !obj.isNull("sv")) {
                        ec = ec + "   " + obj.getString("fi");
                    }
                    method = method + ec + "\n";
                }
                
                if(methodObj.has("description") && 
                        !methodObj.isNull("description")) {
                    String des = "  - Description:\n";
                    JSONObject obj = methodObj
                            .getJSONObject("description");
                    if(obj.has("en") && !obj.isNull("en")) {
                        des = des + "   " + obj.getString("en");
                    } else if(obj.has("fi") && !obj.isNull("fi")) {
                        des = des + "   " + obj.getString("fi");
                    } else if(obj.has("sv") && !obj.isNull("sv")) {
                        des = des + "   " + obj.getString("fi");
                    }
                    method = method + des + "\n";
                }
                methods = methods + method;
            }
        }
        return methods;
    }
}

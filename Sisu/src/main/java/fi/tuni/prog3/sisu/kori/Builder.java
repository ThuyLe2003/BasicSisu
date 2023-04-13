package fi.tuni.prog3.sisu.kori;

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
}

package common.lib;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;

public class JsonHelper {
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static JsonNode jsonStringToJsonNode(String jsonString) {
        try {
            return objectMapper.readTree(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T jsonStringToPojo(String jsonString, Class pojoClass) {
        try {
            return (T) objectMapper.readValue(jsonString, pojoClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> String pojoToJsonString(T pojo) {
        try {
            return objectMapper.writeValueAsString(pojo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T jsonNodeToPojo(JsonNode jsonNode, Class pojoClass) {
        try {
            String nodeString = "";
            JsonNodeType nodeType = jsonNode.getNodeType();

            if (nodeType == JsonNodeType.OBJECT) {
                nodeString = pojoToJsonString(jsonNode);
            } else if (nodeType == JsonNodeType.STRING) {
                nodeString = jsonNode.asText();
            }

            return jsonStringToPojo(nodeString, pojoClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

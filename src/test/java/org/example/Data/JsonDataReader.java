package org.example.Data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class JsonDataReader {

    public List<HashMap<String, String>> getJsonDataToMap() throws IOException {

        //read json to string
        String json_content = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"src//test//java//org//example//Data//submit_order_test_data_set.json"), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(json_content, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;



    }
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
        return data;
    }
}

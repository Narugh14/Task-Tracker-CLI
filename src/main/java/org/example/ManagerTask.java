package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ManagerTask {

    static void optionTask(String commands, boolean statusJson, String filepath){
        int init = commands.indexOf("\"") + 1;
        int end = commands.indexOf("\"",init);
        String task = commands.substring(init,end);

        if(!statusJson)
            CreateJson();

        if(commands.startsWith("add")) {
            optionAdd(task, jsonContent(filepath), filepath);
        }

    }

    static void CreateJson(){
        //Create to Manual JSON
        StringBuilder jsonBuilder = new StringBuilder();
        String json = "{\"done\":[],\"in-progress\":[]}";
        jsonBuilder.append(json);

        //Save JSON archive
        try(FileWriter file = new FileWriter("task.json")){
            file.write(jsonBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static StringBuilder jsonContent(String filepath){
        File jsonFile = new File(filepath);

        StringBuilder jsonContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(jsonFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonContent.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonContent;
    }

    private static void jsonSave(String filepath, String modifiedJson){
        File jsonFile = new File(filepath);
        try (FileWriter fileWriter = new FileWriter(jsonFile)) {
            fileWriter.write(modifiedJson);
            System.out.println("\nJSON modificado guardado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void optionAdd(String task, StringBuilder jsonContent, String filepath) {
        String AddTask;
        String modifiedJson;
        String jsonAdd = jsonContent.toString();
        int initTask = jsonAdd.lastIndexOf("[");
        int endTask = jsonAdd.lastIndexOf("]");
        
        //Enty list in-progress
        if((initTask+1) == endTask) {
        
            AddTask = "{\" id\": 1, \" task\": \"" + task + "\", \"status\": \"in-progress\" }";
            modifiedJson = jsonAdd.substring(0, endTask) + AddTask + jsonAdd.substring(endTask);
            System.out.println(modifiedJson);
            jsonSave(filepath, modifiedJson);
        } else{
            AddTask = ",{\" id\": 1, \" task\": \"" + task + "\", \"status\": \"in-progress\" }";
            modifiedJson = jsonAdd.substring(0, endTask) + AddTask + jsonAdd.substring(endTask);
            System.out.println(modifiedJson);
            jsonSave(filepath, modifiedJson);
        }
    }
}

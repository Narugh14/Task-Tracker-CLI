package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ManagerTask {

    static void optionTask(String commands, boolean statusJson){
        if(!statusJson)
            CreateJson();

        if(commands.startsWith("add"))
            optionAdd(commands);

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

    private static void optionAdd(String commands) {
        
    }
}

package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ManagerTask {

    static void optionTask(String commands, boolean statusJson, String filepath){
        if(!statusJson)
            CreateJson();

        if(commands.startsWith("add"))
            optionAdd(commands, filepath);

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

    private static void optionAdd(String commands, String filepath) {
        int init = commands.indexOf("\"") + 1;
        int end = commands.indexOf("\"",init);
        String task = commands.substring(init,end);

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
        // Mostrar el contenido del JSON original
        System.out.println("Contenido original del JSON:");
        System.out.println(jsonContent);

        String jsonAdd = jsonContent.toString();
        int endJson = jsonAdd.indexOf("]");
        if(jsonAdd.indexOf("]")-1 != jsonAdd.indexOf("]")){
            System.out.println("El json esta Cosas hechas");
        }
        if(jsonAdd.lastIndexOf("]")-1 != jsonAdd.lastIndexOf("]")){
            System.out.println("El json esta en proceso");
            int posicionArray = jsonAdd.lastIndexOf("]");

            String Addjson = "{Texto ejemplo}";
            String jsonActualizado = jsonAdd.substring(0, posicionArray) + Addjson + jsonAdd.substring(posicionArray );
            System.out.println(jsonActualizado);
        }


       /* Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("id","");
        jsonMap.put("task","");
        jsonMap.put("status","done");*/
    }
}

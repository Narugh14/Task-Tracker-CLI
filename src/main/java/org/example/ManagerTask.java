package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerTask {

    static void optionTask(String commands, boolean statusJson, String filepath){


        if(!statusJson)
            CreateJson();

        if(commands.startsWith("add")) {
            int init = commands.indexOf("\"") + 1;
            int end = commands.indexOf("\"",init);
            String task = commands.substring(init,end);

            option(task, jsonContent(filepath), filepath,"add");
        }else if (commands.startsWith("delete")){
            option(commands, jsonContent(filepath), filepath,"delete");
        }else if (commands.startsWith("update")){
            option(commands, jsonContent(filepath), filepath,"update");
        }

    }

    static void CreateJson(){
        //Create to Manual JSON
        StringBuilder jsonBuilder = new StringBuilder();
        String json = "[]";
        jsonBuilder.append(json);

        //Save JSON archive
        try(FileWriter file = new FileWriter("task.json")){
            file.write(jsonBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> JsonToList(String json){
        List<String> listJson = new ArrayList<>();
        // Buscar la posición del array

        int initTask ;
        int endTask ;
        do{
            int start = json.indexOf("\"id\":");
            int end = json.indexOf("}", start);
            String arrayContent = json.substring(start  + 2, end+1).trim();
            if(arrayContent.startsWith(",")){
                arrayContent = arrayContent.replace(",{", "{");
                json = json.replace("[,", "[");
            }
            listJson.add(arrayContent);
            json =  json.replace(arrayContent,"");

            initTask= json.lastIndexOf("[");
            endTask= json.lastIndexOf("]");
        }while((initTask+1) != endTask);

        return listJson;
    }

    private static  String ListToJson(List<String> list){return String.join(",",list);}

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void option(String task, StringBuilder jsonContent, String filepath, String option) {
        String AddTask;
        String modifiedJson;
        int maxId = 0;
        String jsonAdd = jsonContent.toString();
        int posicion;
        int initTask = jsonAdd.lastIndexOf("[");
        int endTask = jsonAdd.lastIndexOf("]");
        
        //Enty list in-progress
        if((initTask+1) == endTask) {
            AddTask = "{\" id\": 1, \" task\": \"" + task + "\", \"status\": \"in-progress\" }";
            modifiedJson = jsonAdd.substring(0, endTask) + AddTask + jsonAdd.substring(endTask);
            System.out.println(modifiedJson);
            jsonSave(filepath, modifiedJson);
        }
        //Command Option is add
        else if (option == "add"){
            List<String> jsonList = JsonToList(jsonAdd);
             int MaxId = 0;
            for(int i = 0;i < jsonList.size() ; i++){
                String item = jsonList.get(i);
                int start = item.indexOf("\"id\":");
                int end = item.indexOf(",", start);
                String idStr = item.substring(end-1,end).trim();
                int id = Integer.parseInt(idStr);
                if(id > MaxId){MaxId = id;}
            }

            String idNew = String.valueOf((MaxId+1));
            AddTask = ",{\" id\": "+idNew+", \" task\": \"" + task + "\", \"status\": \"in-progress\" }";
            modifiedJson = jsonAdd.substring(0, endTask) + AddTask + jsonAdd.substring(endTask);
            System.out.println(modifiedJson);
            jsonSave(filepath, modifiedJson);
        }
        //Commmand Option is delete
        else if (option == "delete") {

            String IdStr = task.replace("delete","").trim();
            int idTaskDelete = Integer.parseInt(IdStr);
            List<String> jsonList = JsonToList(jsonAdd);
            for(int i = 0;i < jsonList.size() ; i++){
                String item = jsonList.get(i);
                int start = item.indexOf("\"id\":");
                int end = item.indexOf(",", start);
                String idStr = item.substring(end-1,end).trim();
                int id = Integer.parseInt(idStr);
                if(id == idTaskDelete){
                    jsonList.remove(i);
                } else if(i == (jsonList.size()-1)){
                    System.out.println("Id no exists");
                    break;
                }
            }
            String json = "";
            if(!jsonList.isEmpty()) {
                json = "[" + ListToJson(jsonList) + "]";
                System.out.println(json);
            }else{
                json = "[]";
                System.out.println("Json is Empty");
            }
            jsonSave(filepath, json);
        }
        //Command Option is Update
        else if (option == "update") {
            List<String> jsonList = JsonToList(jsonAdd);
            String json = "";
            String taskAndId = task.replace("update","");
            int startTaskID = taskAndId.indexOf("\"");
            int endTaskID = taskAndId.lastIndexOf("\"");
            String CommandTask = taskAndId.substring(startTaskID+1, endTaskID);
            String IDStr = taskAndId.replace("\""+CommandTask+"\"","").trim();
            int IdUpdate = Integer.parseInt(IDStr);
            for(int i = 0;i < jsonList.size() ; i++){
                String item = jsonList.get(i);
                int start = item.indexOf("\"id\":");
                int end = item.indexOf(",", start);
                int startTasks = item.indexOf("\" task\":");
                int endTasks = item.indexOf(",", startTasks);
                int startTask =item.indexOf(":", startTasks);
                String idStr = item.substring(end-1,end).trim();
                String TaskUpdate = item.substring(startTask+1,endTasks);
                int id = Integer.parseInt(idStr);
                if(id == IdUpdate){
                   item = item.replaceFirst(TaskUpdate,"\""+CommandTask+"\"");
                    jsonList.set(i,item);
                }
            }
            json = "[" + ListToJson(jsonList) + "]";
            System.out.println(json);
            jsonSave(filepath, json);
        }
    }
}

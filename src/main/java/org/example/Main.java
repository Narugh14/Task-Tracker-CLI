package org.example;
import java.io.BufferedReader;
import java.io.FileReader;


public class Main {



    public static void main(String[] args) {

        String filepath = "task.json";


            JsonAllList(JsonExist(filepath));

    }
    private static StringBuilder JsonExist(String filepath){
        StringBuilder jsonTask = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
            String line;
            while( (line = br.readLine()) != null){
                jsonTask.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return jsonTask.append("");
        }
        return jsonTask;
    }

   private static void JsonAllList(StringBuilder AllTask){
        System.out.println("El contenido del archivo JSON es: ");
        System.out.println(AllTask);
    }
}
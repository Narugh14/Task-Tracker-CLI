package org.example;


public class TaskManager {


     static void ManagerTask(String input){
        int inicioComillas = input.indexOf('"');
        int finComillas =  input.indexOf('"',inicioComillas+1);
        String accion = (input.substring(0,inicioComillas-1));
        String task = input.substring(inicioComillas+1,finComillas-1);

        switch (accion){
            case "add":
                AddTask(task);
                break;
            case "delete":
                UpdateTask(task);
                break;
            case "update":
                DeleteTask(task);
                break;
        }
    }

    private static void AddTask(String input){System.out.println("Add");}

    private static void UpdateTask(String input){System.out.println("Update");}

    private static void DeleteTask(String input){System.out.println("Delete");}


}

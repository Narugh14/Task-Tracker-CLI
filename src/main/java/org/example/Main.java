package org.example;
import java.io.File;
import java.util.Scanner;

import static org.example.ManagerTask.CreateJson;


public class Main {

    public static void main(String[] args) {
        String filepath = "task.json";
        boolean statusJson = CheckJson(filepath);

        if (!statusJson) {CreateJson();statusJson = CheckJson(filepath);}
        Scanner scanner = new Scanner(System.in);
        String commando;

        System.out.println("This is your Task tracker \n W. 'help' for see commands \n W. 'exit' for go out \n W. 'task' for see task tracker ");
        while(true){
            System.out.println(">");
            commando = scanner.nextLine().trim();
            switch (commando.toLowerCase()) {
                case "exit":
                    System.out.println("Exit...");
                    return;
                case "help":
                    ListCommands(false);
                    break;
                case "task":
                    ListCommands(true);
                    break;
                default:
                    if (statusJson && (commando.startsWith("add") || commando.startsWith("delete") || commando.startsWith("update") || commando.startsWith("list"))) {
                        ManagerTask.optionTask(commando, statusJson);
                    }
                    break;
            }
        }

    }

    private static boolean CheckJson(String filepath) {return  new File(filepath).exists();}

    private static void ListCommands(boolean num) {
            System.out.println("Commands for Task ");
            System.out.println("---------------------");
            System.out.println("add 'Task f/add' ");
            System.out.println("delete 'Task f/add' ");
            System.out.println("update 'Task f/add' ");
            System.out.println("list 'Task f/add' ");
        if (num == false) {
            System.out.println("---------------------");
            System.out.println("Commands for list");
            System.out.println("---------------------");
            System.out.println("list done");
            System.out.println("list all");
            System.out.println("list in-progress");
        }
    }
}
package org.example;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String commando;

        System.out.println("This is your Task tracker \n W. 'help' for see commands \n W. 'exit' for go out \n W. 'task' for see task tracker ");
        while(true){
            System.out.println(">");
            commando = scanner.nextLine().trim();

            if(commando.equalsIgnoreCase("exit")){
                System.out.println("Exit...");
                break;
            } else if (commando.equalsIgnoreCase("help")) {
                ListCommands(false);
            }else if (commando.startsWith("task")) {
                ListCommands(true);
            }else if (commando.startsWith("add") || commando.startsWith("delete")||commando.startsWith("update")||commando.startsWith("list")){
                ManagerTask.optionTask(commando);
            }
        }

    }

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
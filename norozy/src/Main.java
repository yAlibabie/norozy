import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import todo.service.StepService;
import todo.service.TaskService;
import db.Database;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        while (true) {
            System.out.print("\nEnter command (type 'help' for list of commands): ");
            String command = scanner.nextLine().trim().toLowerCase();

            try {
                switch (command) {
                    case "add task":
                        System.out.print("Title: ");
                        String taskTitle = scanner.nextLine();

                        System.out.print("Description: ");
                        String taskDescription = scanner.nextLine();

                        System.out.print("Due date (yyyy-MM-dd): ");
                        Date taskDueDate = dateFormat.parse(scanner.nextLine());

                        TaskService.addTask(taskTitle, taskDescription, taskDueDate);
                        break;

                    case "add step":
                        System.out.print("Task ID: ");
                        int taskId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Title: ");
                        String stepTitle = scanner.nextLine();

                        StepService.addStep(taskId, stepTitle);
                        break;

                    case "complete task":
                        System.out.print("Task ID to complete: ");
                        int taskIdToComplete = Integer.parseInt(scanner.nextLine());

                        TaskService.setAsCompleted(taskIdToComplete);
                        break;

                    case "complete step":
                        System.out.print("Step ID to complete: ");
                        int stepIdToComplete = Integer.parseInt(scanner.nextLine());

                        StepService.setAsCompleted(stepIdToComplete);
                        break;

                    case "list tasks":
                        TaskService.getAllTaks();
                        break;

                    case "help":
                        printHelp();
                        break;

                    case "exit":
                        System.out.println("Exiting program...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid command! Type 'help' for available commands");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void printHelp() {
        System.out.println("\nAvailable Commands:");
        System.out.println("add task       - Add a new task");
        System.out.println("add step      - Add a new step to a task");
        System.out.println("complete task - Mark a task as completed");
        System.out.println("complete step - Mark a step as completed");
        System.out.println("list tasks    - List all tasks");
        System.out.println("help          - Show this help message");
        System.out.println("exit          - Exit the program");
    }
}
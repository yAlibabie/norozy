package todo.service;

import db.Database;
import db.Entity;
import db.exeption.EntityNotFoundException;
import db.exeption.InvalidEntityException;

import todo.entity.Step;
import todo.entity.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskService {
    public static void addTask (String title, String description, Date dueDate) throws InvalidEntityException {
        Task task = new Task(title, description, dueDate);
        Database.add(task);
        System.out.println("Task saved successfully.");
        System.out.println("ID: " + task.getId());
    }

    public static void setAsCompleted (int taskId) throws EntityNotFoundException,InvalidEntityException {
        Task task = (Task) Database.get(taskId);
        task.setStatus(Task.Status.COMPLETED);
        Database.update(task);

        List<Step> steps = getStepsForTask(taskId);
        for (Step step : steps) {
            step.setStatus(Step.Status.COMPLETED);
            Database.update(step);
        }

        System.out.println("Successfully update the task to complete.");
    }

    public static void deleteTask(int taskId) throws EntityNotFoundException {
        List <Step> steps = getStepsForTask(taskId);
        for(Step step : steps) {
            Database.delete(step.getId());
        }
        Database.delete(taskId);

        System.out.println("Entity with ID= " + taskId + " successfully deleted.");
    }

    public static void updateTaskTiltle (int taskId,String title, String newTitle) throws EntityNotFoundException, InvalidEntityException {
        Task task = (Task) Database.get(taskId);
        task.setTitle(newTitle);
        Database.update(task);
        System.out.println("Successfully updated the task.");
        System.out.println("Field: title\n" + "Old value: " + title + "\n" + "New value: " + newTitle + "\n" + "Modification:" + new Date());
    }

    public static void updateTaskDescription (int taskId, String newDescription) throws EntityNotFoundException, InvalidEntityException {
        Task task = (Task) Database.get(taskId);
        task.setDescription(newDescription);
        Database.update(task);
        System.out.println("Successfully updated the task.");
        System.out.println("Field: description\n" + "New description: " + newDescription + "\n" + "Modification:" + new Date());
    }

    public static void updateTaskDueDate (int taskId, Date newDueDate) throws EntityNotFoundException, InvalidEntityException {
        Task task = (Task) Database.get(taskId);
        task.setDueDate(newDueDate);
        Database.update(task);
        System.out.println("Successfully updated the task.");
        System.out.println("Field: due date\n" + "New due date: " + newDueDate + "\n" + "Modification:" + new Date());
    }

    public static void getTaskById (int taskId) throws EntityNotFoundException {
        Task task = (Task) Database.get(taskId);
        List <Step> steps = getStepsForTask(taskId);
        System.out.println("ID: " + task.getId());
        System.out.println("Title: " + task.getTitle());
        System.out.println("Due Date: " + task.getDueDate() );
        System.out.println("Status: " + task.getStatus());
        System.out.println("Steps: ");
        for(Step step : steps) {
            System.out.println("   + " + step.getTitle());
            System.out.println("       ID: " + step.getTaskRef());
            System.out.println("       Status: " + step.getStatus());
        }
    }

    public static void getAllTaks() {
        List <Task> task = new ArrayList<>();
        for(Entity entity : Database.getAll(11)) {
            task.add((Task) entity);
        }
        task.sort((t1,t2) -> t1.getDueDate().compareTo(t2.getDueDate()));

        for(Task tasks : task) {
            List <Step> steps = getStepsForTask(tasks.getId());
            System.out.println("ID: " + tasks.getId());
            System.out.println("Title: " + tasks.getTitle());
            System.out.println("Due Date: " + tasks.getDueDate() );
            System.out.println("Status: " + tasks.getStatus());
            System.out.println("Steps: ");
            for(Step step : steps) {
                System.out.println("   + " + step.getTitle());
                System.out.println("       ID: " + step.getTaskRef());
                System.out.println("       Status: " + step.getStatus());
            }
        }
    }

    public static void getIncompleteTasks() {
        List<Task> incompleteTasks = new ArrayList<>();
        for (Entity entity : Database.getAll(11)) {
            Task task = (Task) entity;
            if (task.getStatus() != Task.Status.COMPLETED) {
                incompleteTasks.add(task);
            }
        }
        for (Task tasks : incompleteTasks) {
            List<Step> steps = getStepsForTask(tasks.getId());
            System.out.println("ID: " + tasks.getId());
            System.out.println("Title: " + tasks.getTitle());
            System.out.println("Due Date: " + tasks.getDueDate());
            System.out.println("Status: " + tasks.getStatus());
            System.out.println("Steps: ");
            for (Step step : steps) {
                System.out.println("   + " + step.getTitle());
                System.out.println("       ID: " + step.getTaskRef());
                System.out.println("       Status: " + step.getStatus());
            }
        }
    }

    public static List <Step> getStepsForTask (int taskId) {
        List <Step> steps = new ArrayList<>();
        for(Entity entity : Database.getAll(11)) {
            Step newstep = (Step) entity;
            if(newstep.getTaskRef() == taskId) {
                steps.add(newstep);
            }
        }
        return steps;
    }


}

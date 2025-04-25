package todo.service;

import db.Database;
import db.exeption.EntityNotFoundException;
import db.exeption.InvalidEntityException;
import todo.entity.Step;

import java.util.Date;
import java.util.Scanner;

public class StepService {

    public static void addStep(int taskRef, String title) throws EntityNotFoundException, InvalidEntityException {
        Database.get(taskRef);
        Step step = new Step(title, Step.Status.NOT_STARTED,taskRef);
        Database.add(step);

        System.out.println("Task saved successfully.");
        System.out.println("ID: " + taskRef);
    }

    public static void setAsCompleted(int stepId) throws EntityNotFoundException, InvalidEntityException {
        Step step = (Step) Database.get(stepId);
        step.setStatus(Step.Status.COMPLETED);
        Database.update(step);
        System.out.println("Successfully update the step to completed.");
    }

    public static void deleteStep (int stepId) throws EntityNotFoundException {
        Database.delete(stepId);
        System.out.println("Entity with the Id=" + stepId + " successfully deleted.");
    }

    public static void updateStep (int stepId, String title, String newTitle) throws EntityNotFoundException, InvalidEntityException {
        Step step = (Step) Database.get(stepId);
        step.setTitle(newTitle);
        Database.update(step);
        System.out.println("Successfully update the step.");
        System.out.println("Field: title\n" + "Old value: " + title + "\n" + "New value: " + newTitle + "\n" + "Modification date: " + new Date());
    }
}

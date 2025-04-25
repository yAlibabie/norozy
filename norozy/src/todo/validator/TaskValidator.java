package todo.validator;

import db.Entity;
import db.Validator;
import db.exeption.InvalidEntityException;
import todo.entity.Task;

public class TaskValidator implements Validator {

    @Override
    public void validate (Entity entity) throws InvalidEntityException {
        if (!(entity instanceof Task)) {
            throw new IllegalArgumentException("Entity is not of type Task");
        }

        Task task = (Task) entity;
        if(task.getTitle() == null || task.getTitle() == "" ) {
            throw new InvalidEntityException("Task title cannot be empty");
        }
    }
}

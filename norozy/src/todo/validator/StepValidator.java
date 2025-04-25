package todo.validator;

import db.Database;
import db.Entity;
import db.Validator;
import db.exeption.EntityNotFoundException;
import db.exeption.InvalidEntityException;
import todo.entity.Step;

import javax.xml.crypto.Data;

public class StepValidator implements Validator {
    @Override
    public void validate(Entity entity) throws InvalidEntityException {
        if(!(entity instanceof Step)) {
            throw new IllegalArgumentException("Entity is not of type Step");
        }

        Step step = (Step) entity;
        if(step.getTitle() == null || step.getTitle() == "" ) {
            throw new InvalidEntityException("Step title can not be empty");
        }

        try {
            Database.get(step.getTaskRef());
        } catch (EntityNotFoundException e) {
            throw new InvalidEntityException("cannot find task with id: " + step.getTaskRef());
        }
    }
}

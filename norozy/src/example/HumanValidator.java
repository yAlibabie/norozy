package example;

import db.Entity;
import db.Validator;
import db.exeption.InvalidEntityException;

public class HumanValidator implements Validator {
    @Override
    public void validate (Entity entity) throws InvalidEntityException {
        if (!(entity instanceof Human)) {
            throw new IllegalArgumentException("Entity must be of type of Human");
        }


        Human human = (Human) entity;

        if (human.name == null || human.name == "") {
            throw new InvalidEntityException("Name cannot be empty");
        }

        if (human.age < 0) {
            throw new InvalidEntityException("Age cannot be negetive");
        }

    }
}

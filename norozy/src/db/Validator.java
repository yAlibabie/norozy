package db;

import db.exeption.InvalidEntityException;

public interface Validator {
    public void validate(Entity entity) throws InvalidEntityException;
}

package db;

import java.util.ArrayList;
import java.util.HashMap;

import db.exeption.EntityNotFoundException;
import db.exeption.InvalidEntityException;


public class Database {

    private static ArrayList<Entity> entities = new ArrayList<>();
    private static int nextId = 1;
    public static HashMap<Integer , Validator> validators = new HashMap<>();

    private Database () {}

    public static void add(Entity e) throws InvalidEntityException {
        Validator validator = validators.get(e.getEntityCode());
        if(validator != null) {
            validator.validate(e);
        }
        e.setId(nextId++);
        entities.add(e.copy());
    }

    public static Entity get (int id) throws EntityNotFoundException {
        for (Entity entity : entities) {
            if(entity.getId() == id) {
                return entity.copy();
            }
        }
        throw new EntityNotFoundException(id);
    }

    public static void delete ( int id) throws EntityNotFoundException {
        Entity entity = get(id);
        entities.remove(entity);
    }

    public static void update (Entity e) throws EntityNotFoundException {
        Entity entity = get(e.id);
        int index = entities.indexOf(entity);
        entities.set(index , e.copy());
    }

    public static void registerValidator(int entityCode, Validator validator) {
        if(validators.containsKey(entityCode)) {
            throw new IllegalArgumentException("Validator for this entity code already exists");
        }
        validators.put(entityCode , validator);
    }
}

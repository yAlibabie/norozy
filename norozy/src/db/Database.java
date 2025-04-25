package db;

import java.util.ArrayList;
import java.util.Date;
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

        if (e instanceof Trackable) {
            Trackable track = (Trackable) e;
            Date now = new Date();
            track.setCreationDate(now);
            track.setLastModificationDate(now);
        }

        for(Entity entity : entities) {
            if(entity.id == nextId) {
                e.id = nextId + 1 + 1;
            } else {
                e.id = nextId + 1;
            }
        }
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

    public static void update (Entity e) throws EntityNotFoundException , InvalidEntityException {
        Validator validator = validators.get(e.getEntityCode());
        if (validator != null) {
            validator.validate(e);
        }

        if(e instanceof Trackable) {
            Trackable track = (Trackable) e;
            Date now = new Date();
            track.setLastModificationDate(now);
        }

        Entity entity = null;
        int index = -1;

        for(int i = 0; i < entities.size(); i++) {
            if(entities.get(i).id == e.id) {
                entity = entities.get(i);
                index = i;
                break;
            }
        }
        if(entity == null) {
            throw new EntityNotFoundException(e.id);
        }

        entities.set(index,e.copy());
    }

    public static void registerValidator(int entityCode, Validator validator) {
        if(validators.containsKey(entityCode)) {
            throw new IllegalArgumentException("Validator for this entity code already exists");
        }
        validators.put(entityCode , validator);
    }

    public static ArrayList<Entity> getAll (int entityCode) {
        ArrayList<Entity> result = new ArrayList<>();
        for(Entity entity : entities) {
            if(entity.getEntityCode() == entityCode) {
                result.add(entity.copy());
            }
        }
        return result;
    }
}

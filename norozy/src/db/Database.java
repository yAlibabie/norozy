package db;

import java.util.ArrayList;
import db.exeption.EntityNotFoundException;


public class Database {

    private static ArrayList<Entity> entities = new ArrayList<>();
    private static int nextId = 1;

    private Database () {}

    public static void add(Entity e) {
        e.setId(nextId++);
        entities.add(e);
    }

    public static Entity get (int id) throws EntityNotFoundException {
        for (Entity entity : entities) {
            if(entity.getId() == id) {
                return entity;
            }
        }
        throw new EntityNotFoundException(id);
    }

    public static void delete ( int id) throws EntityNotFoundException {
        Entity entity = get(id);
        entities.remove(entity);
    }

    public static Entity update (Entity e) throws EntityNotFoundException {
        for (Entity entity : entities) {
            if (entity.getId() == e.id) {
                entity = e;
                return entity;
            }
        }
        throw new EntityNotFoundException(e.id);
    }
}

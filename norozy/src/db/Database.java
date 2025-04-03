package db;

import java.util.ArrayList;
import db.exeption.EntityNotFoundException;


public class Database {

    private static ArrayList<Entity> entities = new ArrayList<>();
    private static int nextId = 1;

    private Database () {}

    public static void add(Entity e) {
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
}

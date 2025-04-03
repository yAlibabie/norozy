package db;

public abstract class Entity {
    public int id;

    public int getId() {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public abstract Entity copy();
}

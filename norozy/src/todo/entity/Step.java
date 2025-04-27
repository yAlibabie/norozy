package todo.entity;

import db.Entity;

public class Step extends Entity {

    public enum Status {NOT_STARTED,COMPLETED};

    public static final int STEP_ENTITY_CODE = 12;
    private String title;
    private  Status status;
    private int taskRef;

    public Step (String title, Status status, int taskRef) {
        this.title = title;
        this.status = Status.NOT_STARTED;
        this.taskRef = taskRef;
    }

    @Override
    public int getEntityCode() {
        return STEP_ENTITY_CODE;
    }

    @Override
    public Step copy () {
        Step copy = new Step(this.title, this.status, this.taskRef);
        return copy;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getTitle () {
        return title;
    }

    public void setTaskRef (int taskRef) {
        this.taskRef = taskRef;
    }

    public int getTaskRef () {
        return taskRef;
    }

    public void setStatus (Status status) {
        this.status = status;
    }

    public Status getStatus () {
        return status;
    }

}

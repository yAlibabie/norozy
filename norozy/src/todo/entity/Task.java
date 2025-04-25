package todo.entity;

import db.Entity;
import db.Trackable;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Task extends Entity implements Trackable {

    public static final int TASK_ENTITY_CODE = 11;
    public enum Status {
            NOT_STARTED,IN_PROGRESS,COMPLETED
   }

    private Date creationDate;
    private Date lastModificationDate;
    private String title;
    private String description;
    private Date dueDate;
    private Status status;

    public Task (String title, String description, Date dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = Status.NOT_STARTED;
    }

    @Override
    public int getEntityCode () {
        return TASK_ENTITY_CODE;
    }

    @Override
    public Task copy () {
        Task copy = new Task(this.title, this.description, this.dueDate);
        copy.id = this.id;
        copy.creationDate = this.creationDate;
        copy.lastModificationDate = this.lastModificationDate;
        return copy;

    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getTitle () {
        return title;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public String getDescription () {
        return description;
    }

    public void setDueDate (Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDueDate () {
        return dueDate;
    }

    public void setStatus (Status status) {
        this.status = status;
    }

    public Status getStatus () {
        return status;
    }

    @Override
    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public void setLastModificationDate(Date date){
        this.lastModificationDate = date;
    }

    @Override
    public Date getLastModificationDate() {
        return lastModificationDate;
    }




}

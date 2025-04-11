package example;

import db.Entity;
import db.Trackable;
import java.util.Date;

public class Document extends Entity implements Trackable {
    public String content;
    public Date creationDate ;
    public Date lastModificationDate;
    public static final int DOCUMENT_ENTITY_CODE = 1;


    public Document (String content) {
        this.content = content;
    }

    @Override
    public int getEntityCode () {
        return DOCUMENT_ENTITY_CODE;
    }

    @Override
    public Document copy () {
        Document copy = new Document(this.content);
        copy.id = this.id;
        copy.creationDate = this.creationDate;
        copy.lastModificationDate = this.lastModificationDate;

        return copy;
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

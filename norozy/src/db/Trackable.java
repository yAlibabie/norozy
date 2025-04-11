package db;

import java.util.Date;

public interface Trackable {

    public void setCreationDate(Date date);
    public Date getCreationDate();

    public void setLastModificationDate(Date date);
    public Date getLastModificationDate();

}

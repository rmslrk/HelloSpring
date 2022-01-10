package domain;

import java.sql.Timestamp;

public class ListDTO {
    protected long lid;
    protected String title;
    protected boolean is_deleted;
    protected Timestamp created_at;
    protected Timestamp updated_at;

    public Long getLid() { return lid; }

    public String getTitle() {
        return title;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setLid(long lid) {
        this.lid = lid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}

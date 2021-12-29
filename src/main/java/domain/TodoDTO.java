package domain;

import java.sql.Timestamp;

public class TodoDTO {
    protected int id;
    protected String title;
    protected String content;
    protected Timestamp target_date;
    protected boolean success;
    protected boolean is_deleted;
    protected Timestamp created_at;
    protected Timestamp updated_at;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getTarget_date() {
        return target_date;
    }

    public boolean isSuccess() {
        return success;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTarget_date(Timestamp target_date) {
        this.target_date = target_date;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

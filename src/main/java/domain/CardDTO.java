package domain;

import java.sql.Timestamp;

public class CardDTO {
    protected long id;
    protected long lid;
    protected String title;
    protected String content;
    protected Timestamp target_date;
    protected boolean success;
    protected int position;
    protected boolean is_deleted;
    protected Timestamp created_at;
    protected Timestamp updated_at;

    public long getId() {
        return id;
    }

    public long getLid() {
        return lid;
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

    public int getPosition() { return position; }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLid(long lid) {
        this.lid = lid;
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

    public void setPosition(int position) { this.position = position; }

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

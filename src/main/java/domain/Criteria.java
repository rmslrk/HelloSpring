package domain;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

public class Criteria {
    @ApiParam(required = false, defaultValue = "1")
    private int page = 1;

    @ApiParam(required = false, defaultValue = "10")
    private int limit = 10;

    @ApiModelProperty(hidden = true)
    private int cursor;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit > 50 ? 50 : limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (page < 1){
            this.page = 1;
        }
        else{
            this.page = page;
        }
    }

    public int getCursor() {
        return (page - 1) * limit;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "page=" + page +
                ", limit=" + limit +
                '}';
    }
}
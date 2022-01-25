package domain;

public class CardCriteria extends Criteria{

    private String search;
    private String sort;

    public String getSearch() {
        return search;
    }

    public String getSort() {
        return sort;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}

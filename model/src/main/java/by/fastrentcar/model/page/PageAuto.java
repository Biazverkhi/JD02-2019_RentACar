package by.fastrentcar.model.page;

import by.fastrentcar.model.auto.Auto;
import org.springframework.data.domain.Sort;

import java.util.List;

public class PageAuto {
    private int page;
    private int numPageAll;

    private int size;
    private Sort.Direction sort;
    private List<Auto> autoList;
    private String columnName;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;

    }

    public PageAuto() {
    }

    public PageAuto(int size) {
        this.size = size;
    }

    public PageAuto(int page, int numPageAll, Sort.Direction sort, List<Auto> autoList, String columnName) {
        this.page = page;
        this.numPageAll = numPageAll;
        this.sort = sort;
        this.autoList = autoList;
        this.columnName = columnName;
    }

    public int getPage() {
        return page;
    }


    public void setPage(int page) {
        this.page = page;
    }


    public int getNumPageAll() {
        return numPageAll;
    }


    public void setNumPageAll(int numPageAll) {
        this.numPageAll = numPageAll;
    }


    public int getSize() {
        return size;
    }


    public void setSize(int size) {
        this.size = size;
    }

    public Sort.Direction getSort() {
        return sort;
    }

    public void setSort(Sort.Direction sort) {
        this.sort = sort;
    }

    public List<Auto> getAutoList() {
        return autoList;
    }

    public void setAutoList(List<Auto> autoList) {
        this.autoList = autoList;
    }
}

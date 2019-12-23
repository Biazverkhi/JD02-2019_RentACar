package by.fastrentcar.web.page;

import org.springframework.beans.factory.annotation.Value;

public class PageAutoProperty {

    @Value("#{ T(Integer).valueOf('${size}')}")
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public PageAutoProperty() {
    }

}

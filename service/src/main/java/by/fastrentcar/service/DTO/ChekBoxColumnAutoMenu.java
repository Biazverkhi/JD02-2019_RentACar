package by.fastrentcar.service.DTO;

import java.util.List;

public class ChekBoxColumnAutoMenu {
    List<String> brend;
    List<String> model;

    public ChekBoxColumnAutoMenu() {
    }

    public List<String> getBrend() {
        return brend;
    }

    public void setBrend(List<String> brend) {
        this.brend = brend;
    }

    public List<String> getModel() {
        return model;
    }

    public void setModel(List<String> model) {
        this.model = model;
    }
}

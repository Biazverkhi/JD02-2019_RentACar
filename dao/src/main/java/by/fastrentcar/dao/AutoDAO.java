package by.fastrentcar.dao;

import by.fastrentcar.model.auto.Auto;

import java.util.List;

public interface AutoDAO {
    Long addAutoT(Auto auto);

    boolean updateAutoT(Auto auto);

    boolean deleteAutoT(Long id);

    List<Auto> getListAutoT();

    List<Auto> getListAutoT(int start, int stop);

    Auto getAutoByIdT(Long id);

}

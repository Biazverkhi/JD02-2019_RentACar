package by.fastrentcar.springdata;

import by.fastrentcar.model.auto.Auto;
import by.fastrentcar.model.auto.AutoServices;

import java.util.List;

public interface AutoDAO {
    Long addAutoT(Auto auto);

    boolean updateAutoT(Auto auto);

    boolean deleteAutoT(Long id);

    List<Auto> getListAutoT();

    long getCountAuto();

    List<Auto> getListAutoT(int page, int size);

    Auto getAutoByIdT(Long id);

    List<AutoServices> getAutoServicesByAutoIdT(Long id);


}

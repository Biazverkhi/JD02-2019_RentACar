package by.fastrentcar.dao;

import by.fastrentcar.model.Auto;
import java.util.List;

public interface AutoDAO {
    Long addAutoT(Auto auto);

    boolean updateAutoT(Auto auto);

    boolean deleteAutoT(Long id);

    List<Auto> getListAutoT();

    Auto getAutoByIdT(Long id);

}

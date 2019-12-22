package by.fastrentcar.springdata;

import by.fastrentcar.model.auto.Auto;
import by.fastrentcar.model.auto.AutoServices;
import by.fastrentcar.model.page.PageAuto;

import java.util.List;

public interface AutoDAO {
    Long addAutoT(Auto auto);

    boolean updateAutoT(Auto auto);

    boolean deleteAutoT(Long id);

    List<Auto> getListAutoT();

    long getCountAuto();

    List<String> getDistinctBrendAuto();

    List<String> getDistinctModelAuto();

    PageAuto getListAutoT(PageAuto page);

    PageAuto getListAutoSortT(PageAuto page);

    Auto getAutoByIdT(Long id);

    List<AutoServices> getAutoServicesByAutoIdT(Long id);


}

package by.fastrentcar.service;

import by.fastrentcar.model.auto.Auto;
import by.fastrentcar.model.auto.AutoServices;
import by.fastrentcar.model.page.PageAuto;

import java.util.List;

public interface AutoService {
    Long addAuto(Auto auto);

    boolean updateAuto(Auto auto);

    boolean deleteAuto(Long id);

    List<Auto> getListAuto();

    PageAuto getListAuto(PageAuto page);

    Auto getAuto(Long id);

    List<AutoServices> getAutoServicesByAutoIdT(Long id);

    long getCountAuto();

    List<String> getListBrendAuto();

    List<String> getListModelAuto();


}

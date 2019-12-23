package by.fastrentcar.service;

import by.fastrentcar.model.auto.Auto;
import by.fastrentcar.model.auto.AutoServices;
import by.fastrentcar.model.page.PageAuto;
import by.fastrentcar.service.DTO.ChekBoxColumnAutoMenu;

import java.util.List;
import java.util.Map;

public interface AutoService {
    Long addAuto(Auto auto);

    boolean updateAuto(Auto auto);

    boolean deleteAuto(Long id);

    List<Auto> getListAuto();

    PageAuto getListAuto(PageAuto page);

    Auto getAuto(Long id);

    List<AutoServices> getAutoServicesByAutoIdT(Long id);

    long getCountAuto();

    ChekBoxColumnAutoMenu getCheckBoxColumnAuto();

    PageAuto getListAutoFiltr(PageAuto page, Map<String, List<String>> auto);



}

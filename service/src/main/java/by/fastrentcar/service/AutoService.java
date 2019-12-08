package by.fastrentcar.service;

import by.fastrentcar.model.auto.Auto;
import by.fastrentcar.model.auto.AutoServices;

import java.util.List;

public interface AutoService {
    Long addAuto(Auto auto);

    boolean updateAuto(Auto auto);

    boolean deleteAuto(Long id);

    List<Auto> getListAuto();

    List<Auto> getListAuto(int page, int size);

    Auto getAuto(Long id);

    List<AutoServices> getAutoServicesByAutoIdT(Long id);

    long getCountAuto();


}

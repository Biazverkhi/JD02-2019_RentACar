package by.fastrentcar.service;

import by.fastrentcar.model.auto.Auto;

import java.util.List;

public interface AutoService {
    Long addAuto(Auto auto);

    boolean updateAuto(Auto auto);

    boolean deleteAuto(Long id);

    List<Auto> getListAuto();

    List<Auto> getListAuto(int start, int stop);

    Auto getAuto(Long id);


}

package by.fastrentcar.service;

import by.fastrentcar.model.auto.Auto;

import java.util.List;

public interface AutoService {
    Long addAuto(Auto auto);

    void updateAuto(Auto auto);

    void deleteAuto(Long id);

    List<Auto> getListAuto();

    Auto getAuto(Long id);


}

package by.fastrentcar.springdata;

import by.fastrentcar.model.auto.AutoServices;

import java.util.List;

public interface AutoServicesDAO {
    Long addAutoServicesT(AutoServices autoServices);

    boolean updateAutoServicesT(AutoServices autoServices);

    boolean deleteAutoServicesT(Long id);

    List<AutoServices> getListAutoServicesT();

    AutoServices getAutoServicesByIdT(Long id);

    boolean addAutoServicesToAuto(Long autoId, Long servicesId);
}

package by.fastrentcar.service.impl;

import by.fastrentcar.model.auto.AutoServices;
import by.fastrentcar.service.AutoServicesService;
import by.fastrentcar.springdata.AutoServicesDAO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class DefaultAutoServicesService implements AutoServicesService {


    public DefaultAutoServicesService(AutoServicesDAO autoServicesDAO) {
        this.autoServicesDAO = autoServicesDAO;
    }

    private AutoServicesDAO autoServicesDAO;

    @Override
    public Long addAutoServicesT(AutoServices autoServices) {
        return autoServicesDAO.addAutoServicesT(autoServices);
    }

    @Override
    public boolean updateAutoServicesT(AutoServices autoServices) {
        return autoServicesDAO.updateAutoServicesT(autoServices);
    }

    @Override
    public boolean deleteAutoServicesT(Long id) {
        return autoServicesDAO.deleteAutoServicesT(id);
    }

    @Override
    public List<AutoServices> getListAutoServicesT() {
        return autoServicesDAO.getListAutoServicesT();
    }

    @Override
    public AutoServices getAutoServicesByIdT(Long id) {
        return autoServicesDAO.getAutoServicesByIdT(id);
    }

    @Override
    public boolean addAutoServicesToAuto(Long autoId, Long servicesId) {
        return autoServicesDAO.addAutoServicesToAuto(autoId, servicesId);
    }
}

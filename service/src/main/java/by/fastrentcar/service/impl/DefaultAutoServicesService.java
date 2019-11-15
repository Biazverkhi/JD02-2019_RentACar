package by.fastrentcar.service.impl;

import by.fastrentcar.dao.AutoServicesDAO;
import by.fastrentcar.dao.impl.DefaultAutoServicesDAO;
import by.fastrentcar.model.auto.AutoServices;
import by.fastrentcar.service.AutoServicesService;

import java.util.List;

public class DefaultAutoServicesService implements AutoServicesService {
    private static class SingletonHolder {
        static final AutoServicesService HOLDER_INSTANCE = new DefaultAutoServicesService();
    }

    public static AutoServicesService getInstance() {
        return DefaultAutoServicesService.SingletonHolder.HOLDER_INSTANCE;
    }

    private AutoServicesDAO autoServicesDAO = DefaultAutoServicesDAO.getInstance();

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

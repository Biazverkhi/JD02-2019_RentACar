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

    private AutoServicesDAO defaultAutoServicesDAO = DefaultAutoServicesDAO.getInstance();

    @Override
    public Long addAutoServicesT(AutoServices autoServices) {
        return defaultAutoServicesDAO.addAutoServicesT(autoServices);
    }

    @Override
    public boolean updateAutoServicesT(AutoServices autoServices) {
        return defaultAutoServicesDAO.updateAutoServicesT(autoServices);
    }

    @Override
    public boolean deleteAutoServicesT(Long id) {
        return defaultAutoServicesDAO.deleteAutoServicesT(id);
    }

    @Override
    public List<AutoServices> getListAutoServicesT() {
        return defaultAutoServicesDAO.getListAutoServicesT();
    }

    @Override
    public AutoServices getAutoServicesByIdT(Long id) {
        return defaultAutoServicesDAO.getAutoServicesByIdT(id);
    }

    @Override
    public boolean addAutoServicesToAuto(Long autoId, Long servicesId) {
        return defaultAutoServicesDAO.addAutoServicesToAuto(autoId, servicesId);
    }
}

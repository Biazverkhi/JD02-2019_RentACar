package by.fastrentcar.service.impl;

import by.fastrentcar.dao.AutoDAO;
import by.fastrentcar.dao.impl.DefaultAutoDAO;
import by.fastrentcar.model.auto.Auto;
import by.fastrentcar.service.AutoService;
import java.util.List;

public class DefaultAutoService implements AutoService {
    private DefaultAutoService() {
    }

    private static class SingletonHolder {
        static final AutoService HOLDER_INSTANCE = new DefaultAutoService();
    }

    public static AutoService getInstance() {
        return DefaultAutoService.SingletonHolder.HOLDER_INSTANCE;
    }

    private AutoDAO defaultAutoDAO = DefaultAutoDAO.getInstance();

    @Override
    public Long addAuto(Auto auto) {
        return defaultAutoDAO.addAutoT(auto);
    }

    @Override
    public void updateAuto(Auto auto) {
        defaultAutoDAO.updateAutoT(auto);
    }

    @Override
    public void deleteAuto(Long id) {
        defaultAutoDAO.deleteAutoT(id);
    }

    @Override
    public List<Auto> getListAuto() {
        return defaultAutoDAO.getListAutoT();
    }

    @Override
    public Auto getAuto(Long id) {
        return defaultAutoDAO.getAutoByIdT(id);
    }
}

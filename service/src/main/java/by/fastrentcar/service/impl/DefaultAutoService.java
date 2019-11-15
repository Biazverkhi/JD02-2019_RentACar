package by.fastrentcar.service.impl;

import by.fastrentcar.dao.AutoDAO;
import by.fastrentcar.model.auto.Auto;
import by.fastrentcar.model.auto.AutoServices;
import by.fastrentcar.service.AutoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

    private AutoDAO autoDAO = new ClassPathXmlApplicationContext("beans-by-AutoDao.xml").getBean(AutoDAO.class);


    @Override
    public Long addAuto(Auto auto) {
        return autoDAO.addAutoT(auto);
    }

    @Override
    public boolean updateAuto(Auto auto) {
        return autoDAO.updateAutoT(auto);
    }

    @Override
    public boolean deleteAuto(Long id) {
        return autoDAO.deleteAutoT(id);
    }

    @Override
    public List<Auto> getListAuto() {
        return autoDAO.getListAutoT();
    }

    @Override
    public List<Auto> getListAuto(int start, int stop) {
        return autoDAO.getListAutoT(start, stop);
    }

    @Override
    public Auto getAuto(Long id) {
        return autoDAO.getAutoByIdT(id);
    }

    @Override
    public List<AutoServices> getAutoServicesByAutoIdT(Long id) {
        return autoDAO.getAutoServicesByAutoIdT(id);
    }

    @Override
    public long getCountAuto() {
        return autoDAO.getCountAuto();
    }
}

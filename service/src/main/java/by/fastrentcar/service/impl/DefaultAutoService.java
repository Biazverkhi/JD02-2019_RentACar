package by.fastrentcar.service.impl;

import by.fastrentcar.model.auto.Auto;
import by.fastrentcar.model.auto.AutoServices;
import by.fastrentcar.service.AutoService;
import by.fastrentcar.springdata.AutoDAO;

import java.util.List;

public class DefaultAutoService implements AutoService {

    public DefaultAutoService(AutoDAO autoJpaRepository) {
        this.autoJpaRepository = autoJpaRepository;
    }

    private AutoDAO autoJpaRepository;


    @Override
    public Long addAuto(Auto auto) {
        return autoJpaRepository.addAutoT(auto);
    }

    @Override
    public boolean updateAuto(Auto auto) {
        return autoJpaRepository.updateAutoT(auto);
    }

    @Override
    public boolean deleteAuto(Long id) {
        return autoJpaRepository.deleteAutoT(id);
    }

    @Override
    public List<Auto> getListAuto() {
        return autoJpaRepository.getListAutoT();
    }

    @Override
    public List<Auto> getListAuto(int page, int size) {
        return autoJpaRepository.getListAutoT(page, size);
    }

    @Override
    public Auto getAuto(Long id) {
        return autoJpaRepository.getAutoByIdT(id);
    }

    @Override
    public List<AutoServices> getAutoServicesByAutoIdT(Long id) {
        return autoJpaRepository.getAutoServicesByAutoIdT(id);
    }

    @Override
    public long getCountAuto() {
        return autoJpaRepository.getCountAuto();
    }
}

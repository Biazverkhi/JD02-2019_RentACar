package by.fastrentcar.springdata.impl;

import by.fastrentcar.model.auto.AutoServices;
import by.fastrentcar.springdata.AutoServicesDAO;
import by.fastrentcar.springdata.entities.AutoEntity;
import by.fastrentcar.springdata.entities.AutoServicesEntity;
import by.fastrentcar.springdata.repository.AutoJpaRepository;
import by.fastrentcar.springdata.repository.AutoServicesJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DefaultAutoServicesDAO implements AutoServicesDAO {
    @Autowired
    AutoServicesJpaRepository autoServicesJpaRepository;
    @Autowired
    AutoJpaRepository autoJpaRepository;


    @Override
    public List<AutoServices> getListAutoServicesT() {
        List<AutoServicesEntity> autoServicesEntityList = autoServicesJpaRepository.findAll();
        return autoServicesEntityList.stream().map(AutoServicesEntity::convertAutoServicesByAutoServicesEntity).collect(Collectors.toList());
    }

    @Override
    public AutoServices getAutoServicesByIdT(Long id) {
        Optional<AutoServicesEntity> optional = autoServicesJpaRepository.findById(id);
        return optional.map(AutoServicesEntity::convertAutoServicesByAutoServicesEntity).orElse(null);
    }

    @Override//nтут мошут быть ошибки
    public boolean addAutoServicesToAuto(Long autoId, Long servicesId) {
        AutoServicesEntity autoServicesEntity = autoServicesJpaRepository.getOne(servicesId);
        AutoEntity autoEntity = autoJpaRepository.getOne(autoId);
        autoEntity.getAutoServicesEntity().add(autoServicesEntity);
        autoServicesEntity.getAutoEntity().add(autoEntity);
        //  autoJpaRepository.save(autoEntity);
        autoServicesJpaRepository.save(autoServicesEntity);

        return true;
    }


    @Override
    public Long addAutoServicesT(AutoServices autoServices) {
        return autoServicesJpaRepository.save(new AutoServicesEntity(autoServices)).getId();
    }

    @Override
    public boolean updateAutoServicesT(AutoServices autoServices) {
        addAutoServicesT(autoServices);
        return true;
    }

    @Override
    public boolean deleteAutoServicesT(Long id) {
        autoServicesJpaRepository.deleteById(id);
        return true;
    }
}

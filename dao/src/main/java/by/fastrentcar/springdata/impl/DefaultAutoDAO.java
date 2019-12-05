package by.fastrentcar.springdata.impl;

import by.fastrentcar.model.auto.Auto;
import by.fastrentcar.model.auto.AutoServices;
import by.fastrentcar.springdata.AutoDAO;
import by.fastrentcar.springdata.entities.AutoEntity;
import by.fastrentcar.springdata.entities.AutoServicesEntity;
import by.fastrentcar.springdata.repository.AutoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

public class DefaultAutoDAO implements AutoDAO {
    @Autowired
    AutoJpaRepository autoJpaRepository;

    @Override
    public Long addAutoT(Auto auto) {
        return autoJpaRepository.save(new AutoEntity(auto)).getId();
    }

    @Override
    public boolean updateAutoT(Auto auto) {
        autoJpaRepository.save(new AutoEntity(auto));
        return true;
    }

    @Override
    public boolean deleteAutoT(Long id) {
        autoJpaRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Auto> getListAutoT() {
        List<AutoEntity> autoEntityList = autoJpaRepository.findAll();
        List<Auto> autoList = new ArrayList<>();
        for (AutoEntity autoEntity : autoEntityList) {
            autoList.add(autoEntity.convertAutoByAutoEntity());
        }
        return autoList;
    }

    @Override
    public long getCountAuto() {
        return autoJpaRepository.count();
    }

    @Override
    public List<Auto> getListAutoT(int page, int size) {
        List<AutoEntity> autoEntityList = autoJpaRepository.findAll(PageRequest.of(page, size)).getContent();
        List<Auto> autoList = new ArrayList<>();
        for (AutoEntity autoEntity : autoEntityList) {
            autoList.add(autoEntity.convertAutoByAutoEntity());
        }
        return autoList;
    }

    @Override
    public Auto getAutoByIdT(Long id) {
        return autoJpaRepository.getOne(id).convertAutoByAutoEntity();
    }

    @Override
    public List<AutoServices> getAutoServicesByAutoIdT(Long id) {
        List<AutoServicesEntity> autoServicesEntitiesList = autoJpaRepository.getOne(id).getAutoServicesEntity();
        List<AutoServices> autoServicesList = new ArrayList<>();
        for (AutoServicesEntity ase : autoServicesEntitiesList
        ) {
            autoServicesList.add(ase.convertAutoServicesByAutoServicesEntity());
        }
        return autoServicesList;
    }
}

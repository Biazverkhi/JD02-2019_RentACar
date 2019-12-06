package by.fastrentcar.springdata.impl;

import by.fastrentcar.model.auto.Auto;
import by.fastrentcar.model.auto.AutoServices;
import by.fastrentcar.springdata.AutoDAO;
import by.fastrentcar.springdata.entities.AutoEntity;
import by.fastrentcar.springdata.entities.AutoServicesEntity;
import by.fastrentcar.springdata.repository.AutoJpaRepository;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DefaultAutoDAO implements AutoDAO {
    private AutoJpaRepository autoJpaRepository;

    public DefaultAutoDAO(AutoJpaRepository autoJpaRepository) {
        this.autoJpaRepository = autoJpaRepository;
    }

    public long getCountAuto() {
        return autoJpaRepository.count();
    }


    @Override
    public List<Auto> getListAutoT() {
        List<AutoEntity> autoEntityList = autoJpaRepository.findAll();
        return autoEntityList.stream().map(AutoEntity::convertAutoByAutoEntity).collect(Collectors.toList());
    }


    @Override
    public List<Auto> getListAutoT(int page, int size) {
        List<AutoEntity> autoEntityList = autoJpaRepository.findAll(PageRequest.of(page, size)).getContent();
        return autoEntityList.stream().map(AutoEntity::convertAutoByAutoEntity).collect(Collectors.toList());
    }

    @Override
    public Auto getAutoByIdT(Long id) {
        Optional<AutoEntity> optional = autoJpaRepository.findById(id);
        return optional.isPresent() ? optional.get().convertAutoByAutoEntity() : null;
    }

    @Override
    public List<AutoServices> getAutoServicesByAutoIdT(Long id) {
        Optional<AutoEntity> optional = autoJpaRepository.findById(id);
        return optional.isPresent()
                ? optional.get().getAutoServicesEntity().stream().map(AutoServicesEntity::convertAutoServicesByAutoServicesEntity).collect(Collectors.toList())
                : new ArrayList<>();
    }


    @Override
    public Long addAutoT(Auto auto) {
        return autoJpaRepository.save(new AutoEntity(auto)).getId();
    }

    @Override
    public boolean updateAutoT(Auto auto) {
        addAutoT(auto);
        return true;
    }

    @Override
    public boolean deleteAutoT(Long id) {
        autoJpaRepository.deleteById(id);
        return true;
    }

}

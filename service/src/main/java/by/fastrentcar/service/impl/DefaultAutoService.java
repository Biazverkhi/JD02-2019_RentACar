package by.fastrentcar.service.impl;

import by.fastrentcar.model.auto.Auto;
import by.fastrentcar.model.auto.AutoServices;
import by.fastrentcar.model.page.PageAuto;
import by.fastrentcar.service.AutoService;
import by.fastrentcar.service.DTO.ChekBoxColumnAutoMenu;
import by.fastrentcar.springdata.AutoDAO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
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
    public Auto getAuto(Long id) {
        return autoJpaRepository.getAutoByIdT(id);
    }
    @Override


    public PageAuto getListAuto(PageAuto page) {
        return page.getColumnName() == null ? autoJpaRepository.getListAutoT(page) : autoJpaRepository.getListAutoSortT(page);
    }


    @Override
    public ChekBoxColumnAutoMenu getCheckBoxColumnAuto() {
        ChekBoxColumnAutoMenu ch = new ChekBoxColumnAutoMenu();
        ch.setBrend(autoJpaRepository.getDistinctBrendAuto());
        ch.setModel(autoJpaRepository.getDistinctModelAuto());
        return ch;
    }

    @Override
    public PageAuto getListAutoFiltr(PageAuto page, Map<String, List<String>> auto) {
        return autoJpaRepository.getListAutoFiltr(page, auto);
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

package by.fastrentcar.springdata.impl;

import by.fastrentcar.model.auto.Auto;
import by.fastrentcar.model.auto.AutoServices;
import by.fastrentcar.model.page.PageAuto;
import by.fastrentcar.springdata.AutoDAO;
import by.fastrentcar.springdata.entities.AutoEntity;
import by.fastrentcar.springdata.entities.AutoServicesEntity;
import by.fastrentcar.springdata.repository.AutoJpaRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class DefaultAutoDAO implements AutoDAO {
    private AutoJpaRepository autoJpaRepository;
    private SessionFactory sessionFactory;

    public DefaultAutoDAO(AutoJpaRepository autoJpaRepository, SessionFactory session) {
        this.autoJpaRepository = autoJpaRepository;
        this.sessionFactory = session;
    }

    @Override
    public PageAuto getListAutoFiltr(PageAuto page, Map<String, List<String>> auto) {
        Session session = null;
        StringBuilder sb1 = new StringBuilder("select * from auto a where");
        int count = 0;
        for (Iterator<Map.Entry<String, List<String>>> iterator = auto.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, List<String>> entry = iterator.next();
            if (count != 0 && count < auto.entrySet().size()) {
                sb1.append(" OR ");
            }
            sb1.append(" a." + entry.getKey() + " IN(");
            List<String> list = entry.getValue();
            for (int i = 0; i < list.size(); i++) {
                sb1.append("'" + list.get(i) + "'");
                if (i == list.size() - 1) {
                    sb1.append(")");
                } else {
                    sb1.append(",");
                }
            }
            count++;
        }
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            //  Long count = (Long) session.createQuery("select count(*) from AutoEntity").getResultList().get(0);
            Query query = session.createSQLQuery(sb1.toString())
                    .setFirstResult(page.getPage() * page.getSize())
                    .setMaxResults(page.getSize());
            List<Object[]> autoEntity = query.list();
            page.setNumPageAll(autoEntity.size() / page.getSize() + 1);
            List<Auto> autoList = new ArrayList<>();
            for (Object[] a : autoEntity) {
                AutoEntity au = new AutoEntity();
                AutoServicesEntity autoServicesEntity = new AutoServicesEntity();
                au.setId(((BigInteger) a[0]).longValue());
                au.setBrand((String) a[1]);
                au.setModel((String) a[4]);
                au.setFuel((String) a[3]);
                au.setDate((String) a[2]);
                au.setPrice((Double) a[5]);
                au.setStatus((String) a[6]);
                au.setAutoServicesEntity(new ArrayList<AutoServicesEntity>());
                autoList.add(au.convertAutoByAutoEntity());
            }
            page.setAutoList(autoList);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return page;
    }


    public long getCountAuto() {
        return autoJpaRepository.count();
    }

    @Override
    public List<String> getDistinctBrendAuto() {
        List<String> list = autoJpaRepository.getDistinctByBrand();

        return list;
    }

    @Override
    public List<String> getDistinctModelAuto() {
        List<String> list = autoJpaRepository.getDistinctByModel();
        return list;
    }

    @Override
    public List<Auto> getListAutoT() {
        List<AutoEntity> autoEntityList = autoJpaRepository.findAll();
        return autoEntityList.stream().map(AutoEntity::convertAutoByAutoEntity).collect(Collectors.toList());
    }

    @Override
    public PageAuto getListAutoSortT(PageAuto page) {
        Page<AutoEntity> p = autoJpaRepository.findAll(PageRequest.of(page.getPage(), page.getSize(), page.getSort(), page.getColumnName()));
        page.setAutoList(p.toList().stream().map(AutoEntity::convertAutoByAutoEntity).collect(Collectors.toList()));
        page.setNumPageAll(p.getTotalPages());
        return page;
    }

    @Override
    public PageAuto getListAutoT(PageAuto page) {
        Page<AutoEntity> p = autoJpaRepository.findAll(PageRequest.of(page.getPage(), page.getSize()));
        page.setAutoList(p.toList().stream().map(AutoEntity::convertAutoByAutoEntity).collect(Collectors.toList()));
        page.setNumPageAll(p.getTotalPages());
        return page;
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

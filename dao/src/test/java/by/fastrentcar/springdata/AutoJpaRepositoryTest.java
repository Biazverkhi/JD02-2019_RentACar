package by.fastrentcar.springdata;

import by.fastrentcar.springdata.config.DAOSpringConfig;
import by.fastrentcar.springdata.config.HibernateConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DAOSpringConfig.class})

public class AutoJpaRepositoryTest {
    //static Long ID;
    @Autowired
    AutoDAO defaultAutoDAO;

    @Test
    void getInstance() {
        assertNotNull(defaultAutoDAO.getCountAuto());
    }
//
//
//    @Test
//    void getAutoListTest() {
//        assertNotNull(autoJpaRepository.getListAutoT());
//        assertEquals(false, autoJpaRepository.getListAutoT().isEmpty());
//    }

//    @Test
//    void getAutoListTest2() {
//        assertNotNull(autoJpaRepository.getListAutoT(0, 10));
//    }
//    @Test
//    void getCountAuto() {
//        assertNotNull(autoJpaRepository.count());
//    System.out.println(autoJpaRepository.findById(23l).get().toString());;
//    }
//
//    @Test
//    void addUpdateDeleteAuto() {
//        AutoEntity auto = new AutoEntity(new Auto(null, "1123", "22", "33", "44", 245d, "ww"));
//        AutoEntity sd = autoJpaRepository.save(auto);
//        assertNotNull(sd);
//
//        assertNotNull(ID);
//        Auto auto2 = new Auto(ID, "11113test", "22test", "33test", "44test", 245d, "wwtest");
//        assertTrue(autoJpaRepository.updateAutoT(auto2));
//        assertEquals(true, autoJpaRepository.deleteAutoT(ID));
}



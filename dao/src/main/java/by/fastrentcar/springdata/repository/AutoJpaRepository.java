package by.fastrentcar.springdata.repository;

import by.fastrentcar.springdata.entities.AutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutoJpaRepository extends JpaRepository<AutoEntity, Long> {
  @Query("select distinct(a.brand) from AutoEntity a")
  List<String> getDistinctByBrand();

  @Query("select distinct(a.model) from AutoEntity a")
  List<String> getDistinctByModel();

    // List<AutoEntity> findDistinct;
}

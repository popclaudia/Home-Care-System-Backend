package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.CareGiver;

import java.util.List;
import java.util.Optional;

public interface CaregiverRepository extends JpaRepository<CareGiver, Integer> {

    @Query(value = "SELECT p " +
            "FROM CareGiver p " +
            "left JOIN FETCH p.patients c "+
            "WHERE p.ID = :id ")
    Optional<CareGiver> findCaregiverById(@Param("id") Integer id);

    @Query(value = "SELECT DISTINCT p " +
            "FROM CareGiver p " +
            "left JOIN FETCH p.patients c ")
    List<CareGiver> findCaregivers();

}

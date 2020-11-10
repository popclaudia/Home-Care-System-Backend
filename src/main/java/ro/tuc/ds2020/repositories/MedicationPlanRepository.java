package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.MedicationPlan;

import java.util.List;

public interface MedicationPlanRepository extends JpaRepository<MedicationPlan, Integer> {

    @Query(value = "SELECT p " +
            "FROM MedicationPlan p " +
            "WHERE p.patient.ID = :id ")
    List<MedicationPlan> findByPatient(@Param("id") Integer id);
}

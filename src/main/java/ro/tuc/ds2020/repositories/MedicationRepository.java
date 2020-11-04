package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.CareGiver;
import ro.tuc.ds2020.entities.Medication;

import java.util.Optional;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {

    @Query(value = "SELECT p " +
            "FROM Medication p " +
            "JOIN FETCH p.medication_plans c "+
            "WHERE p.id = :id ")
    Optional<Medication> findMedicationById(@Param("id") Integer id);

}

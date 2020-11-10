package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.entities.Patient;

import java.util.List;
import java.util.Optional;


public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query(value = "SELECT p " +
            "FROM Patient p " +
            "left join fetch p.caregiver c " +
            "WHERE p.caregiver.ID = :id ")
    List<Patient> findByCaregiver(@Param("id") Integer id);

    @Query(value = "SELECT p " +
            "FROM Patient p " +
            "left join fetch p.caregiver c " +
            "WHERE p.ID = :id ")
    Optional<Patient> findByIdd(@Param("id") Integer id);

    @Query(value = "SELECT p " +
            "FROM Patient p " +
            "left join fetch p.caregiver c ")
    List<Patient> findAll();


}

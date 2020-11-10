package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.CareGiver;
import ro.tuc.ds2020.entities.UserAccount;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<UserAccount, Integer> {
    //Optional<UserAccount > findByUsername(String username);
    @Query(value = "SELECT u " +
            "FROM UserAccount u " +
            "JOIN FETCH u.user c "+
            "WHERE u.username = :username ")
    Optional<UserAccount> findByUsername(@Param("username") String username);
}

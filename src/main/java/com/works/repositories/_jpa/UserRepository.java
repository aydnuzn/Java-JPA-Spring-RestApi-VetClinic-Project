package com.works.repositories._jpa;

import com.works.entities.projections.UserRoleDoctorInfo;
import com.works.entities.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmailEqualsAllIgnoreCase(String email);


    @Query(value = "SELECT * FROM `user`\n" +
            "INNER JOIN users_roles on `user`.us_id = users_roles.user_id\n" +
            "INNER JOIN role on users_roles.role_id = role.ro_id\n" +
            "WHERE users_roles.role_id = ?1", nativeQuery = true)
    List<UserRoleDoctorInfo> getUsersForRoleId(Integer ro_id);

}

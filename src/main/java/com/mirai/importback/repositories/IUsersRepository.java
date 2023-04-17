package com.mirai.importback.repositories;

import com.mirai.importback.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsersRepository extends JpaRepository<Users,Long> {

 /*    Users findByDni(String dni);
    List<Users> findByLastName(String lastname);
    List<Users> findByFirstAndLastName(String firstname,String lastname);
    List<Users> findByFirstName(String firstname);

  */
}

package com.sitelogin.Site.repositories;

import com.sitelogin.Site.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
//https://www.javapedia.net/Spring-Data-Access/900
//User, String -> T, ID -> tipul returnat si tipul parametrului din metode
//daca te uiti in definitia metodelor, din interfata, vezi mai bine
// in intellij dai pe "o" din stanga metodei
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findAll();

    Optional<User> findByEmail(String email);

    @Transactional
    @Query(value = "SELECT * from user_tbl c WHERE c.id = :user_id", nativeQuery = true)
    Optional<User> findUserById(@Param("user_id") int user_id);

    @Transactional
    @Modifying // pt query uri care modifica in baza
    @Query(value = "UPDATE [bookster].[dbo].user_tbl SET [first_name] = :first_name_param WHERE id = :user_id", nativeQuery = true)
    int updateUser(@Param("first_name_param") String param, @Param("user_id") int user_id);



}

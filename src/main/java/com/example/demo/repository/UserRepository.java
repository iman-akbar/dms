package com.example.demo.repository;

import com.example.demo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserModel, String> {

    @Query(value = "select * from userLogin where username=:username and password = :password " , nativeQuery = true)
     List<UserModel> checkLogin(@Param("username") String username, @Param("password") String password);

        @Query(value = "select * from userLogin where username='iman' and password = 'akbar'" , nativeQuery = true)
        List<UserModel> checkLogin2();

}

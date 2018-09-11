package com.inger.market.repository;

import com.inger.market.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by IngerJo on 2018/9/5.
 */

public interface UserRepository extends JpaRepository<User,Integer>{
    User findByUsername(String username);

    @Modifying
    @Query(value = "update user set password=:pwd where username = :userName ",nativeQuery = true)
    void changePasswordByUsername(@Param("userName") String user, @Param("pwd") String pwd);

    @Modifying
    @Query(value = "update user set credit=((credit+:credit)/2) where id = :id ",nativeQuery = true)
    void updateCredit(@Param("id") Integer id,@Param("credit") Float credit);
}

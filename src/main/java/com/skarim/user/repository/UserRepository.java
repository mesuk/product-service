package com.skarim.user.repository;

import com.skarim.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    @Override
    List<User> findAll();

    List<User> findAllByMobileNumber(String mobileNumber);

    void deleteById(Long id);
    long countAllByUserName(String userName);
}

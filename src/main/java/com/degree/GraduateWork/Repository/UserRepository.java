package com.degree.GraduateWork.Repository;

import com.degree.GraduateWork.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);
    User findByEmail(String email);
    void deleteById(Long id);
}

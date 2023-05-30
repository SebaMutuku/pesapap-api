package com.pesapap.apiv1.repo;

import com.pesapap.apiv1.models.Student;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    Optional<Student> findByRegistrationId(String registrationId);
}

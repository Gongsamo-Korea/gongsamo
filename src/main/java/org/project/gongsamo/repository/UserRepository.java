package org.project.gongsamo.repository;

import org.project.gongsamo.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {



}

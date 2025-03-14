package org.gerarnome.todosimple.repositories;


import org.gerarnome.todosimple.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Long id(Long id);
}

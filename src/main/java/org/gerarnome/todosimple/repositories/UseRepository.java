package org.gerarnome.todosimple.repositories;


import org.gerarnome.todosimple.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UseRepository extends JpaRepository<User, Long> {





}

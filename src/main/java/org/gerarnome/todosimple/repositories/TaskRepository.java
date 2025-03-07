package org.gerarnome.todosimple.repositories;


import org.gerarnome.todosimple.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    //@Query("SELECT t FROM Task t JOIN t.users u WHERE u.id = :id")
    //List<Task> findByUsers_id(@Param("id") Long id);


    List<Task> findByUsers_id(Long id);


}

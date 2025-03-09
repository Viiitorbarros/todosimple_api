package org.gerarnome.todosimple.services;


import org.gerarnome.todosimple.models.User;
import org.gerarnome.todosimple.repositories.TaskRepository;
import org.gerarnome.todosimple.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired // Gera um tipo de Construtor
    private UserRepository userRepository;

    @Autowired//Gera um tipo de Construtor
    private TaskRepository taskRepository;



    public User findById(Long id) {

        Optional<User> user = this.userRepository.findById(id);

        return user.orElseThrow(() -> new RuntimeException("user " + id + " not found"
        + " in repository" + User.class.getName()));

        //return user.orElse(null);

    }

    public User create(User obj) {
        obj.setId(null);

        obj= this.userRepository.save(obj);

        return obj;

    }

    @Transactional
    public User update(User obj) {
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }


    public void delete(User obj) {
        findById(obj.getId());
        try {
            this.userRepository.delete(obj);
        }catch (Exception e) {
            throw new RuntimeException("Error ao deletar pois o usuario encontra-se relacionado a uma TAsk");
        }
    }


}

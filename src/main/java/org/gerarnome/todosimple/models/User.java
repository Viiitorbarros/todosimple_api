package org.gerarnome.todosimple.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity // Indica que a classe é uma entidade JPA (Representa uma tabela no BCO )
@Table(name = User.TABLE_NAME) //Define explicitamente que esta entidade será mapeada para a tabela "task".
public class User {

    public interface CreateUser{}
    public interface UpdateUser{}

    public static final String TABLE_NAME = "user";

    @Id //Indica que esse campo é a chave primária da tabela.
    @GeneratedValue(strategy = GenerationType.AUTO) //Define como o valor do ID será gerado automaticamente.
    private Long id;


    @Column(name = "username" , nullable = false , length = 100, unique = true)
    @NotEmpty (groups = CreateUser.class)
    @NotNull  (groups = CreateUser.class)
    @Size(groups = CreateUser.class, min = 2, max = 100)

    private String username;

    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY) // impede que o campo password seja exibido quando
    //o objeto for convertido para JSON.
    @Column(name = "password" , nullable = false , length = 60) //Define a coluna "password" na tabela )
    @NotNull (groups = {CreateUser.class, UpdateUser.class}) //O campo não pode ser nulo
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class}) //O campo não pode ser vazio
    @Size(groups = {CreateUser.class, UpdateUser.class}, min = 4, max = 60) //Define o tamanho mínimo e máximo do campo.
    private String password;

    @ManyToMany(mappedBy = "users")//Aqui, o mappedBy indica que o relacionamento é mapeado pela propriedade tasks na
    // classe User. Ou seja, a responsabilidade de gerenciar a relação entre Task e User está na classe User
    private List<Task> tasks;


    public User() {
    }//vazio necessário para que o JPA possa instanciar objetos automaticamente.

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }//Construtor que permite inicializar uma tarefa com valores específicos


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}

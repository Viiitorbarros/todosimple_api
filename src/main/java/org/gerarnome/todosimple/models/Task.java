package org.gerarnome.todosimple.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity // Indica que a classe é uma entidade JPA ( Representa uma tabela no BCO )
@Table (name = Task.TABLE_NAME)//Define explicitamente que esta entidade será mapeada para a tabela "task".
public class Task {

    public static final String TABLE_NAME = "task";

    @Id //Indica que esse campo é a chave primária da tabela.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Faz com que o banco de dados gere o valor do ID
    //automaticamente (normalmente como um campo AUTO_INCREMENT).
    @Column (name = "id", unique = true) //Define a coluna id na tabela, garantindo que ela seja única.
    private Long id;

    @ManyToMany //Define um relacionamento muitos para muitos entre Task e User
    @JoinTable(
            name = "task_user",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id") //→ Especifica a tabela intermediária task_user que
            // será criada para armazenar os relacionamentos.
    )
    private List<User> users = new ArrayList<>();

    @NotNull //Garante que o campo não pode ser null.
    @NotEmpty //Garante que o campo não pode ser vazio (não basta ser diferente de null)
    @Size(min = 1, max = 100)//Define o tamanho mínimo e máximo da string
    @Column(name = "description" ,length = 255, nullable = false) //Define a coluna "description" na tabela )
    private  String description;

    public Task() { //vazio necessário para que o JPA possa instanciar objetos automaticamente.

    }

    public Task(Long id, List<User> users, String description) {//Construtor que permite inicializar uma tarefa com valores específicos.
        this.id = id;
        this.users = users;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) { //
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(users, task.users) && Objects.equals(description, task.description);
    }//O metodo equals() serve para comparar se dois objetos são considerados iguais, baseando-se em certos atributos da classe.

    @Override
    public int hashCode() {
        return Objects.hash(id, users, description);
    }
}

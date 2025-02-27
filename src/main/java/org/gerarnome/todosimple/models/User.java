package org.gerarnome.todosimple.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.sql.Update;



import java.util.Objects;


@Entity
@Table(name = User.TABLE_NAME)
public class User {

    public interface CreateUser{}
    public interface UpdateUser{}

    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id" , unique = true)
    private Long id;

    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "username" , nullable = false , length = 100, unique = true)
    @NotEmpty (groups = CreateUser.class)
    @NotNull  (groups = CreateUser.class)
    @Size(groups = CreateUser.class, min = 2, max = 100)

    private String username;


    @Column(name = "password" , nullable = false , length = 60)
    @NotNull (groups = {CreateUser.class, Update.class})
    @NotEmpty(groups = {CreateUser.class, Update.class})
    @Size(groups = {CreateUser.class, Update.class}, min = 4, max = 60)
    private String password;

    //private List<Task> tasks = New  ArryList<Task>();


    public User() {
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }


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

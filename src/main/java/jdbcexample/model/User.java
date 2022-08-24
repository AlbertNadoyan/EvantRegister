package jdbcexample.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private int event_id;

    public User(String name, String surname, String email, int event_id) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.event_id = event_id;
    }
}

package ElearningBack.model;

import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="admin")
@AllArgsConstructor
@Data

public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long idA;

    @Column(name="pass_word")
    private String password;

    public Admin(String password) {
        super();
        this.password = password;
    }
    public Admin(){}

    public Long getIdA() {
        return idA;
    }

    public void setIdA(Long idA) {
        this.idA = idA;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

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

    @Column(name="code")
    private Integer code;

    @Column(name="password")
    private String password;

    public Admin(String password,Integer code) {
        super();
        this.password = password;
        this.code=code;
    }
    public Admin(){super();}

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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

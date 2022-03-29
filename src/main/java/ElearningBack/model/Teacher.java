package ElearningBack.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name="teachers")
@AllArgsConstructor
@Data

public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdT;

    @Column(name="first_name")
    @NotEmpty
    @Size(min = 2, message ="Firstname should have at least 2 characters!")
    private String firstName;

    @Column(name="last_name")
    @NotEmpty
    @Size(min = 2, message ="Lastname should have at least 2 characters!")
    private String lastName;

    @Column(name="email_id")
    @Email
    private String emailId;


   // @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.LAZY)
   // @JoinTable( name = "T_Professors_Groups_Associations2",
   //         joinColumns = @JoinColumn( name = "idProfs" ),
    //        inverseJoinColumns = @JoinColumn( name = "idStudents" ) )
   // private Collection<Student> studentss;

    @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
    @JoinTable( name = "T_Professors_Groups_Associations1",
            joinColumns = @JoinColumn( name = "idProfessor" ),
            inverseJoinColumns = @JoinColumn( name = "idGroup" ) )
    private Collection<Groupe> groupss;


    @Column(name="pass_word")
    //password not empty and have at least 3 characters
    @NotEmpty
    @Size(min=3, message="length of password minimum 3 characters!")
    private String password;

    @Column(name="access_code")
    //code access not null
    @NotNull
    private Integer accessCode;

    public Teacher() {
    }

    public Teacher(String firstName,String lastName,String emailId,String password,Integer accessCode, Collection<Groupe> groupss) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.password = password;
        this.accessCode = accessCode;
       // this.studentss = studentss;
        this.groupss = groupss;
    }

    public Long getIdT() {
        return IdT;
    }

    public void setIdT(Long idT) {
        IdT = idT;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    //public Collection<Student> getStudentss() {return studentss;}

   // public void setStudentss(Collection<Student> studentss) {this.studentss = studentss;}

    public Collection<Groupe> getGroupss() {
        return groupss;
    }

    public void setGroupss(Collection<Groupe> groupss) {
        this.groupss = groupss;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(Integer accessCode) {
        this.accessCode = accessCode;
    }
}

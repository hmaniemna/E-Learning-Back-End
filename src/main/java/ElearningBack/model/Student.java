package ElearningBack.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="students")
@AllArgsConstructor
@Data
@NoArgsConstructor

public class Student {
    @Id
    //@NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long idS;

    @Column(name="first_name")
    //the validation of firstname:not null & have min 2 characters
    @NotEmpty
    @Size(min = 2, message ="Firstname should have at least 2 characters!")
    private String firstName;

    @Column(name="last_name")
    //the validation of lastname:not null & have min 2 characters
    @NotEmpty
    @Size(min = 2, message ="Lastname should have at least 2 characters!")
    private String lastName;

    @Column(name="email_id")
    //email valid format & not empty
    @NotEmpty
    @Email
    private String emailId;



    @ManyToOne
    @JoinColumn(name = "codeG")
    //@Size(max=1, message="groups are one length characters!")
    private Groupe group;


   // @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
   // @JoinTable( name = "T_Professors_Groups_Associations2",
   //         joinColumns = @JoinColumn( name = "idProfessor" ),
   //         inverseJoinColumns = @JoinColumn( name = "idGroup" ) )
   // private Collection<Teacher> teachers;


    @Column(name="lev_el")
    //level not null and 3 levels
    @NotNull
    @Range(min=1,max = 3)
    private Integer level;

    @Column(name="pass_word")
    //password not empty and have at least 3 characters
    @NotEmpty
    @Size(min=3, message="length of password minimum 3 characters!")
    private String password;

    @Column(name="access_code")
    //code access not null
    @NotNull
    private Integer accessCode;


    public Student() {
    	super();
    }

    public Student(String firstName, String lastName, String emailId, String password, Groupe group, Integer level, Integer accessCode) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.password = password;
        this.group= group;
        this.level= level;
        this.accessCode = accessCode;
       // this.teachers = teachers;
    }



    public Long getIdS() {
        return idS;
    }

    public void setIdS(Long idS) {
        this.idS = idS;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(int accessCode) {
        this.accessCode = accessCode;
    }

    //@JsonIgnore !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public Groupe getGroup() { return group; }

    public void setGroup(Groupe group) { this.group = group; }

    public Integer getLevel() { return level; }

    public void setLevel(int level) { this.level = level; }

   // @JsonIgnore
   // public Collection<Teacher> getTeachers() {return teachers;}

   // public void setTeachers(Collection<Teacher> teachers) {this.teachers = teachers;}
}



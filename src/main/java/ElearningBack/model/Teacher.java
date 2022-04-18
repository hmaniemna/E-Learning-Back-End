package ElearningBack.model;


<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;
=======
//import com.fasterxml.jackson.annotation.JsonIgnore;
>>>>>>> e41b315dbc39a4da608e82ed58affcc78ab56474
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

import javax.persistence.*;
import javax.security.auth.Subject;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
//import java.util.Collection;

@Entity
@Table(name="teachers")
@AllArgsConstructor
@Data

public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdT;

    @Column(name="full_name")
    @NotEmpty
    @Size(min = 5, message ="Fulltname should have at least 2 characters!")
    private String fullName;


    @Column(name="email_id")
    @Email
    private String emailId;


    /**
     * Courses that the teacher teaching.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private Collection<Course> coursesT ;

<<<<<<< HEAD
=======
    //@ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
   // @JsonIgnore
    //@JoinTable( name = "T_Professors_Groups_Associations1",
           // joinColumns = @JoinColumn( name = "idProfessor" ),
           // inverseJoinColumns = @JoinColumn( name = "idGroup" ) )
   // private Collection<Groupe> groupss;

>>>>>>> e41b315dbc39a4da608e82ed58affcc78ab56474
    @ManyToOne
    @JoinColumn(name = "codeG1")
    private Groupe groupT;

    //@Column(name="subjectsT")
    //@OneToMany(mappedBy="teachers",fetch=FetchType.EAGER)
    //private Collection<Subject> subjectsT;

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
        super();
    }

<<<<<<< HEAD
    public Teacher(String fullName,String emailId,String password,Integer accessCode) {
=======
    public Teacher(String fullName,String emailId,String password,Integer accessCode
    		//Collection<Subject> subjectsT
    		) {
>>>>>>> e41b315dbc39a4da608e82ed58affcc78ab56474
        this.fullName = fullName;
        this.emailId = emailId;
        this.password = password;
        this.accessCode = accessCode;
<<<<<<< HEAD
    }



=======
       //this.subjectsT = subjectsT;
    }


>>>>>>> e41b315dbc39a4da608e82ed58affcc78ab56474
    public Long getIdT() {
        return IdT;
    }

    public void setIdT(Long idT) {
        IdT = idT;
    }

    public String getFullName() {return fullName; }

    public void setFullName(String fullName) {this.fullName = fullName;}

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }


<<<<<<< HEAD
    public Groupe getGroupT() {return groupT;}

=======

    public Groupe getGroupT() {return groupT;}

>>>>>>> e41b315dbc39a4da608e82ed58affcc78ab56474
    public void setGroupT(Groupe groupT) {this.groupT = groupT;}

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

<<<<<<< HEAD
    public Collection<Course> getCoursesT() {
        return coursesT;
    }

    public void setCoursesT(Collection<Course> coursesT) {
        this.coursesT = coursesT;
    }
}
=======
    //public Collection<Subject> getSubjectsT() {return subjectsT;}

    //public void setSubjectsT(Collection<Subject> subjectsT) {this.subjectsT = subjectsT;}
}
>>>>>>> e41b315dbc39a4da608e82ed58affcc78ab56474

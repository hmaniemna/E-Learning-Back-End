package ElearningBack.model;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

//import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
//import java.util.Collection;

@Entity
@Table(name="students")
@AllArgsConstructor
@Data


public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long idS;

    @Column(name="firstName")
    //the validation of firstname:not null & have min 2 characters
    @NotEmpty
    @Size(min = 2, message ="Firstname should have at least 2 characters!")
    private String firstName;

    @Column(name="lastName")
    //the validation of lastname:not null & have min 2 characters
    @NotEmpty
    @Size(min = 2, message ="Lastname should have at least 2 characters!")
    private String lastName;

    @Column(name="email")
    //email valid format & not empty
    @NotEmpty
    @Email
    private String email;




    @ManyToOne
    @JoinColumn(name = "idG")
    //@Size(max=1, message="groups are one length characters!")

    private Groupe group;

    /**
     * Student courses. (Only the current year courses.)
     */
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = { @JoinColumn(name = "studentId") },
            inverseJoinColumns = { @JoinColumn(name = "courseId"),
            }
    )
    private Collection<Course> coursesS;
    @Column(name="password")

    //password not empty and have at least 3 characters
    @NotEmpty
    @Size(min=3, message="length of password minimum 3 characters!")
    private String password;

    @Column(name="accessCode")
    //code access not null
    @NotNull
    private Integer accessCode;
    



    public Student() {
        super();
    }



    public Student(String firstName, String lastName, String email, String password, Groupe group, Integer accessCode
    		//,Collection<Teacher> teachers
    		) {

        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.group= group;
        this.accessCode = accessCode;


        //this.teachers = teachers;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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


    @JsonIgnore
    public Collection<Course> getCoursesS() {
        return coursesS;
    }

    public void setCoursesS(Collection<Course> coursesS) {
        this.coursesS = coursesS;
    }




   //@JsonIgnore
   // public Collection<Teacher> getTeachers() {return teachers;}

    //public void setTeachers(Collection<Teacher> teachers) {this.teachers = teachers;}


}



package ElearningBack.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="Groupes")
public class Groupe implements Serializable {
    /**
<<<<<<< HEAD
     *
     */
    private static final long serialVersionUID = 1L;


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long IdG;

    @Column(name="nameG")
    @NotEmpty
    @Size(max=1, message="groups are one length characters!")
    private String nameG;

    @Column(name="levelG")
    @NotNull
    private Integer levelG;

    @Column(name="studentsG")
    @OneToMany(mappedBy ="group",fetch = FetchType.LAZY)
    private Collection<Student> studentss;


    /**
     * Group courses. (Only the current year courses.)
     */
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "group_course",
            joinColumns = { @JoinColumn(name = "studentId") },
            inverseJoinColumns = { @JoinColumn(name = "courseId"),
            }
    )
    private Collection<Course> coursesG;



    public Groupe() {
    }


    public Groupe( String nameG,Integer levelG) {
        this.nameG=nameG;
        this.levelG=levelG;
    }

    public Long getIdG() {
        return IdG;
    }

    public void setIdG(Long idG) {
        IdG = idG;
    }

    public String getNameG() {
        return nameG;
    }

    public void setNameG(String nameG) {
        this.nameG = nameG;
    }

    public Integer getLevelG() {
        return levelG;
    }

    public void setLevelG(Integer levelG) {
        this.levelG = levelG;
    }

    @JsonIgnore
    public Collection<Student> getStudentss() {
        return studentss;
    }

    public void setStudentss(Collection<Student> studentss) {
        this.studentss = studentss;
    }



    public Collection<Course> getCoursesG() {
        return coursesG;}



    public void setCoursesG(Collection<Course> coursesG) {
        this.coursesG = coursesG;
    }


   
}
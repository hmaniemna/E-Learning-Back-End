package ElearningBack.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;

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
    @OneToMany(mappedBy ="group",fetch = FetchType.EAGER)
    private Collection<Student> studentss;

    @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable( name = "T_Professors_Groups_Associations1",
            joinColumns = @JoinColumn( name = "idProfessor" ),
            inverseJoinColumns = @JoinColumn( name = "idGroup" ) )
    private Collection<Teacher> teacherss;

    public Groupe() {
    }

    public Groupe( String nameG,Integer levelG, Collection<Student> studentss,Collection<Teacher> teacherss) {


        this.nameG=nameG;
        this.levelG=levelG;
        this.studentss = studentss;
        this.teacherss = teacherss;
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
    @JsonIgnore
    public Collection<Teacher> getTeacherss() {
        return teacherss;
    }

    public void setTeacherss(Collection<Teacher> teacherss) {
        this.teacherss = teacherss;
    }
}

package ElearningBack.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="Courses")
@AllArgsConstructor
@Data
public class Course implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCourse;

    @Column(name="title")
    @NotEmpty
    @Size(min = 2, message ="name should have at least 2 characters!")
    private String title;
    @Column(name="description")
    @NotEmpty
    private String description;

    @Column(name = "year", nullable = false)
    private int year;

    /**
     * Teacher who teach this subject.


    /**
     * Student who learn this subject.
     */
    /** @JsonIgnore
     @ManyToMany(mappedBy = "coursesS", fetch = FetchType.LAZY)
     private Collection<Student> studentsss;**/

    /**
     * Groups who learn this subject.
     */
    /**@JsonIgnore
     @ManyToMany(mappedBy = "coursesG", fetch = FetchType.EAGER)
     private Collection<Groupe> groupsss;**/

    public Course(String title, int year, String descrip) {
        this.title = title;
        this.year = year;
       // this.teacher = teacher;
        this.description = descrip ;
    }

    public Course() {
    }

    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

   /** public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**@JsonIgnore
    public Collection<Student> getStudents() {
    return studentsss;
    }

    public void setStudents(Collection<Student> studentsss) {
    this.studentsss = studentsss;
    }

    public Collection<Groupe> getGroupsss() {
    return groupsss;
    }

    public void setGroupsss(Collection<Groupe> groupsss) {
    this.groupsss = groupsss;
    }**/
}

package ElearningBack.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="Courses")
@AllArgsConstructor
@Data
public class Course implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
    private Long idCourse;

    @Column(name="title")
    @NotEmpty
    @Size(min = 2, message ="name should have at least 2 characters!")
    private String title;

    
    @Column(name="StartDate")
    @NotEmpty
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date StartDate;
    
    @Column (name="EndDate")
    @NotEmpty
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date EndDate;
    
    

    /**
     * Teacher who teach this subject.
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    /**
     * Student who learn this subject.
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "coursesS", fetch = FetchType.LAZY)
    private Collection<Student> studentsss;

    /**
     * Groups who learn this subject.
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "coursesG", fetch = FetchType.EAGER)
    private Collection<Groupe> groupsss;
    
    public Course() {}

    public Course(String title, Teacher teacher) {
        this.title = title;
        this.teacher = teacher;
    }
    /**
     * Course time in Timetable.
     */
   
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

    
    public Date getStartDate() {
    	return StartDate;
    }
    
    public void setStartDate(Date startDate) {
    	this.StartDate = startDate;
    }
    
    public Date getEndDate() {
    	return EndDate;
    }
    
    public void setEndDate(Date endDate) {
    	this.EndDate = endDate;
    }

    @JsonIgnore
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @JsonIgnore
    public Collection<Student> getStudents() {
        return studentsss;
    }

    public void setStudents(Collection<Student> studentsss) {
        this.studentsss = studentsss;
    }

    @JsonIgnore
    public Collection<Groupe> getGroupsss() {
        return groupsss;
    }

    public void setGroupsss(Collection<Groupe> groupsss) {
        this.groupsss = groupsss;
    }

    
}

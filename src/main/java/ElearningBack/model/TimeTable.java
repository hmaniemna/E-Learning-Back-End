package ElearningBack.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="timetables")
@AllArgsConstructor
@Data
public class TimeTable {

    @Id
    //@NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long idTable;

    /**
     * Day when course hold.
     */
    @Column(name = "hourS", nullable = false)
    private int hourS;

    @Column(name = "hourE", nullable = false)
    private int hourE;

    /**
     * Time when course hold.
     */
    @Column(name = "lesson", nullable = false)
    private int lessonLevel;



    /**
     * The course M.

    @ManyToOne
    private Course Monday;

    /**
     * The course T.

    @ManyToOne
    private Course Tusday;

    /**
     * The course W.

    @ManyToOne
    private Course Wednesday;

    /**
     * The course.

    @ManyToOne
    private Course Thirsday;

    /**
     * The course.

    @ManyToOne
    private Course Friday;

    /**
     * The group (one timetable to each group).
     */
   /** @JsonIgnore
    @OneToMany(mappedBy="timecal", cascade= CascadeType.ALL)
    private Collection<Groupe> group;**/

   @OneToOne(cascade= CascadeType.ALL)
   @JoinColumn(name="group_id",referencedColumnName = "idG")
   private Groupe group;

    @ManyToOne
    private Course course;

    public TimeTable() {
    }


    public TimeTable(int lessonLevel, int hourS, int hourE , Groupe group , Course course) {
        this.lessonLevel = lessonLevel;
        this.hourS=hourS;
        this.hourE=hourE;
        this.group=group;
        this.course=course;
       // this.Monday = Monday;
       // this.Tusday=Tusday;
       // this.Wednesday=Wednesday;
        //this.Thirsday=Thirsday;
       // this.Friday=Friday;
    }

    public Long getIdTable() {
        return idTable;
    }

    public void setIdTable(Long idTable) {
        this.idTable = idTable;
    }

    public int getHourS() {
        return hourS;
    }

    public void setHourS(int hourS) {
        this.hourS = hourS;
    }

    public int getHourE() {
        return hourE;
    }

    public void setHourE(int hourE) {
        this.hourE = hourE;
    }
/**
    public Course getMonday() {
        return Monday;
    }

    public void setMonday(Course monday) {
        Monday = monday;
    }

    public Course getTusday() {
        return Tusday;
    }

    public void setTusday(Course tusday) {
        Tusday = tusday;
    }

    public Course getWednesday() {
        return Wednesday;
    }

    public void setWednesday(Course wednesday) {
        Wednesday = wednesday;
    }

    public Course getThirsday() {
        return Thirsday;
    }

    public void setThirsday(Course thirsday) {
        Thirsday = thirsday;
    }

    public Course getFriday() {
        return Friday;
    }

    public void setFriday(Course friday) {
        Friday = friday;
    }**/

    public int getLessonLevel() {
        return lessonLevel;
    }

    public void setLessonLevel(int lessonLevel) {
        this.lessonLevel = lessonLevel;
    }


    public Groupe getGroup() {
        return group;
    }

    public void setGroup(Groupe group) {
        this.group = group;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

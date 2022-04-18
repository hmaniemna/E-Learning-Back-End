package ElearningBack.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

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
    @Column(name = "day", nullable = false)
    private int day;

    /**
     * Time when course hold.
     */
    @Column(name = "lesson", nullable = false)
    private int lessonNumber;

    /**
     * The course.
     */
    @ManyToOne
    private Course course;

    /**
     * The group (one timetable to each group).
     */
    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="group_id",referencedColumnName = "idG")
    private Groupe group;

    public TimeTable() {
    }

    public TimeTable(int lessonNumber,Groupe group,Course course,int day) {
        this.lessonNumber = lessonNumber;
        this.group = group;
        this.course = course;
        this.day = day;
    }

    public Long getIdTable() {
        return idTable;
    }

    public void setIdTable(Long idTable) {
        this.idTable = idTable;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Groupe getGroup() {
        return group;
    }

    public void setGroup(Groupe group) {
        this.group = group;
    }
}

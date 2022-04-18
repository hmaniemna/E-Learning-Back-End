package ElearningBack.model;

import lombok.Data;

import javax.validation.constraints.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="test")

@Data
public class Test {
    @Id
    //@NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long idTest;

    @Column(name="start_Date")
    //the validation of firstname:not null & have min 2 characters
    @NotEmpty
    @Size(min = 2, message ="")
    private Date start_Date;

    @Column(name="duration")
    //the validation of firstname:not null & have min 2 characters
    @NotEmpty
    @Size(min = 2, message ="")
    private String duration;

    @Column(name="testname")
    //the validation of firstname:not null & have min 2 characters
    @NotEmpty
    @Size(min = 2, message ="")
    private String testname;

    @Column(name="testtype")
    //the validation of firstname:not null & have min 2 characters
    @NotEmpty
    @Size(min = 2, message ="")
    private String testtype;

    @Column(name="doc")
    //the validation of name:not null
    @NotEmpty

    @Size(min = 2, message ="")
    private byte[] doc ;

    //@ManyToOne
    //@JoinColumn(name = "codeT")
    //private Teacher teacher;

    public Test( ) {
        super();
    }

    public Test( Date  start_Date , String duration ,byte[]  doc ,  String testname , String tetsttype) {
        super();

        this.start_Date = start_Date ;
        this.duration = duration ;
        this.doc = doc ;
        //this.teacher = teacher ;
        this.testname = testname ;
        this.testtype = tetsttype;



    }

    public Long getIdTest() {
        return idTest;
    }

    public void setIdTest(Long idTest) {
        this.idTest = idTest;
    }

    public Date getStart_Date() {
        return start_Date;
    }

    public void setStart_Date(Date start_Date) {
        this.start_Date = start_Date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public byte[] getDoc() {
        return doc;
    }

    public void setDoc(byte[] doc ) {
        this.doc = doc;
    }

    public String getTesttype() {
        return testtype;
    }

    public void setTesttype(String testtype) {
        this.testtype = testtype;
    }

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }


}

package ElearningBack.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.Arrays;

@Entity
@Table(name = "doc")
public class Doc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)


    private Long id;

    @Column(name="docname")
    //the validation of firstname:not null & have min 2 characters
    @NotEmpty
    @Size(min = 2, message ="")
    private String docname;

    @Column(name="doctype")
    //the validation of firstname:not null & have min 2 characters
    @NotEmpty
    @Size(min = 2, message ="")
    private String doctype;

    @Column(name="document")
    //the validation of firstname:not null & have min 2 characters

    @Size(min = 2, message ="")
    @Lob
    private byte[] data;



    public Doc() {
    }

    public Doc(String name, String type, byte[] data) {
        this.docname = name;
        this.doctype = type;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return docname;
    }

    public void setName(String name) {
        this.docname = name;
    }

    public String getType() {
        return doctype;
    }

    public void setType(String type) {
        this.doctype = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "Doc{" +
                "id='" + id + '\'' +
                ", name='" + docname + '\'' +
                ", type='" + doctype + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }

}
package ElearningBack.model;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.Arrays;

@Entity
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)


    private Long id;

    @Column(name="filename")
    //the validation of firstname:not null & have min 2 characters
    @NotEmpty
    @Size(min = 2, message ="")
    private String name;

    @Column(name="filetype")
    //the validation of firstname:not null & have min 2 characters
    @NotEmpty
    @Size(min = 2, message ="")
    private String type;

    @Column(name="document")
    //the validation of firstname:not null & have min 2 characters

    @Size(min = 2, message ="")
    @Lob
    private byte[] data;



    public File() {
    }

    public File(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "FileDB{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }

}

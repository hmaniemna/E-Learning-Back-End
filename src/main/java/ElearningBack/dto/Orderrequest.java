package ElearningBack.dto;
import ElearningBack.model.Course;
import ElearningBack.model.Teacher;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Orderrequest {
    private Teacher teacher ;
}
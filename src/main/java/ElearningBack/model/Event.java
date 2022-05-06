package ElearningBack.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name="events")
@AllArgsConstructor
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvent;
    
    @Column(name="dayEvent")
    @NotNull
    private String dayEvent;
    
    @Column(name="monthEvent")
    @NotEmpty
    private String monthEvent;

    @Column(name="yearEvent")
    @NotNull
    private Integer yearEvent;

    @Column(name="nameEvent")
    @NotEmpty
    private String nameEvent;

    @Column(name="description")
    @NotEmpty
    private String description;

    @Column(name="link")
    @NotEmpty
    private String link;

    @Column(name="Duration")
    @NotEmpty
    private String Duration;

    public Event() {
        super();
    }

    public Event(Integer yearEvent,String dayEvent,String monthEvent,String description,String link,String nameEvent,String Duration) {
        this.dayEvent = dayEvent;
        this.monthEvent = monthEvent;
        this.yearEvent = yearEvent;
        this.description = description;
        this.link = link;
        this.nameEvent=nameEvent;
        this.Duration=Duration;
    }

    public Long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
    }

    public String getDayEvent() {
        return dayEvent;
    }

    public void setDayEvent(String dayEvent) {
        this.dayEvent = dayEvent;
    }

    public String getMonthEvent() {
        return monthEvent;
    }

    public void setMonthEvent(String monthEvent) {
        this.monthEvent = monthEvent;
    }

    public Integer getYearEvent() {
        return yearEvent;
    }

    public void setYearEvent(Integer yearEvent) {
        this.yearEvent = yearEvent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String Duration) {
        Duration = Duration;
    }
}

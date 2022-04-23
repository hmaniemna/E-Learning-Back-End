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
    private Integer dayEvent;
    
    @Column(name="monthEvent")
    @NotNull
    private Integer monthEvent;

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
    @NotNull
    private Integer Duration;

    public Event() {
        super();
    }

    public Event(Integer yearEvent,Integer dayEvent,Integer monthEvent,String description,String link,String nameEvent,Integer Duration) {
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

    public Integer getDayEvent() {
        return dayEvent;
    }

    public void setDayEvent(Integer dayEvent) {
        this.dayEvent = dayEvent;
    }

    public Integer getMonthEvent() {
        return monthEvent;
    }

    public void setMonthEvent(Integer monthEvent) {
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

    public Integer getDuration() {
        return Duration;
    }

    public void setDuration(Integer Duration) {
        Duration = Duration;
    }
}

package kodlamaio.hrms.entities.concretes;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "job_titles")
public class JobTitle {

    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;
}

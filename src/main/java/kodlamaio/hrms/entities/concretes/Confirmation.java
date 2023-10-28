package kodlamaio.hrms.entities.concretes;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.ZonedDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "confirmations")
public class Confirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "system_personnel_id", nullable = false)
    private SystemPersonnel systemPersonnel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employer_id", nullable = false)
    private Employer employer;

    @NotNull
    @Column(name = "is_confirmed", nullable = false, columnDefinition = "boolean default true")
    private Boolean isConfirmed;

    @NotNull
    @CreatedDate
    @Column(name = "confirmation_date")
    private ZonedDateTime confirmationDate;

    public Confirmation(SystemPersonnel systemPersonnel, Employer employer) {
        this.systemPersonnel = systemPersonnel;
        this.employer = employer;
    }

    @PrePersist
    public void prePersist() {
        this.isConfirmed = true;
        this.confirmationDate = ZonedDateTime.now();
    }

}

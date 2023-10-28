package kodlamaio.hrms.entities.concretes;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kodlamaio.hrms.entities.abstracts.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.ZonedDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "verification_codes")
public class VerificationCode implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "is_verified", nullable = false, columnDefinition = "boolean default false")
    private Boolean isVerified;

    @NotNull
    @CreatedDate
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NOW()")
    private ZonedDateTime createdAt;

    @Column(name = "verified_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime verifiedDate;

    public VerificationCode(User user, String code) {
        this.user = user;
        this.code = code;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;

        if (isVerified) {
            this.verifiedDate = ZonedDateTime.now();
        }
    }

    @PrePersist
    public void prePersist() {
        this.isVerified = false;
        this.createdAt = ZonedDateTime.now();
    }

}
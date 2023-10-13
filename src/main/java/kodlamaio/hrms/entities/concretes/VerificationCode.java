package kodlamaio.hrms.entities.concretes;

import jakarta.persistence.*;
import kodlamaio.hrms.core.entities.BaseEntity;
import kodlamaio.hrms.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "is_verified", nullable = false, columnDefinition = "default boolean false")
    private boolean isVerified;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NOW()")
    private ZonedDateTime createdAt;

    @Column(name = "verified_date")
    private ZonedDateTime verifiedDate;

    public VerificationCode(User user, String code) {
        this.user = user;
        this.code = code;
        setCreatedAt(ZonedDateTime.now());
    }
}
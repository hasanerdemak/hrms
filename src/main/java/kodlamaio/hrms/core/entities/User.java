package kodlamaio.hrms.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Email(message = "Please enter a valid e-mail address.")
    @NotNull(message = "The e-mail field cannot be null.")
    @NotBlank(message = "The e-mail field cannot be blank.")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotNull(message = "The password field cannot be null.")
    @NotBlank(message = "The password field cannot be blank.")
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull(message = "The user type field cannot be null.")
    @NotBlank(message = "The user type field cannot be blank.")
    @Column(name = "user_type", nullable = false)
    private String userType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NOW()")
    private ZonedDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NOW()")
    private ZonedDateTime updatedAt;

    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
    private Boolean isActive;
}

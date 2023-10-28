package kodlamaio.hrms.entities.concretes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kodlamaio.hrms.core.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
@Table(name = "job_seekers")
public class JobSeeker extends User {

    @NotNull(message = "The first name field cannot be null.")
    @NotBlank(message = "The first name field cannot be blank.")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull(message = "The last name field cannot be null.")
    @NotBlank(message = "The last name field cannot be blank.")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull(message = "The identity number field cannot be null.")
    @NotBlank(message = "The identity number field cannot be blank.")
    @Column(name = "identity_number", unique = true, nullable = false, length = 11)
    private String identityNumber;

    @NotBlank(message = "The birth date field cannot be blank.")
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @NotNull
    @Column(name = "is_verified", nullable = false, columnDefinition = "boolean default false")
    private Boolean isVerified;

    public JobSeeker() {
        setUserType(UserType.JobSeeker);
        this.isVerified = false;
    }
}

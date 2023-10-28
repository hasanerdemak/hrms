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

@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
@Table(name = "employers")
public class Employer extends User {

    @NotNull(message = "The company name field cannot be null.")
    @NotBlank(message = "The company name field cannot be blank.")
    @Column(name = "company_name", nullable = false)
    private String companyName;

    @NotNull(message = "The web address field cannot be null.")
    @NotBlank(message = "The web address field cannot be blank.")
    @Column(name = "web_address", nullable = false)
    private String webAddress;

    @NotNull(message = "The phone number field cannot be null.")
    @NotBlank(message = "The phone number field cannot be blank.")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @NotNull
    @Column(name = "is_verified", nullable = false, columnDefinition = "boolean default false")
    private Boolean isVerified;

    @NotNull
    @Column(name = "is_confirmed", nullable = false, columnDefinition = "boolean default false")
    private Boolean isConfirmed;

    public Employer() {
        setUserType(UserType.Employer);
        this.isVerified = false;
        this.isConfirmed = false;
    }
}

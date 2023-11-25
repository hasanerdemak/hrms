package kodlamaio.hrms.entities.dtos;

import lombok.Data;

import java.sql.Date;

@Data
public class JobSeekerDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String identityNumber;
    private Date birthDate;
}

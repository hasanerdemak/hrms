package kodlamaio.hrms.entities.dtos;

import lombok.Data;

@Data
public class EmployerDto {
    private String email;
    private String password;
    private String companyName;
    private String webAddress;
    private String phoneNumber;
}

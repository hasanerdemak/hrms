package kodlamaio.hrms.entities.dtos;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class VerificationCodeDto {
    private Integer userId;
    private String code;
    private Boolean isVerified;
    private ZonedDateTime createdAt;
    private ZonedDateTime verifiedDate;
}

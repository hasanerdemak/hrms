package kodlamaio.hrms.entities.dtos;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class VerificationCodeDto {
    private Integer id;
    private Integer userId;
    private String code;
    private boolean isVerified;
    private ZonedDateTime createdAt;
    private ZonedDateTime verifiedDate;
}

package kodlamaio.hrms;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "HRMS",
                version = "1.0.0",
                description = "HRMS project for Java & React Camp",
                contact = @Contact(
                        name = "Hasan Erdem Ak",
                        email = "hasanerdemak@gmail.com"
                )

        )
)
public class HrmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrmsApplication.class, args);
    }

}

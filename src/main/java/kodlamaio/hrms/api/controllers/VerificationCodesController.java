package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.mappers.EntityDtoMapper;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.VerificationCode;
import kodlamaio.hrms.entities.dtos.VerificationCodeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/verificationCodes")
public class VerificationCodesController {

    private final VerificationCodeService verificationCodeService;
    private final EntityDtoMapper entityDtoMapper;

    @Autowired
    public VerificationCodesController(VerificationCodeService verificationCodeService,
                                       EntityDtoMapper entityDtoMapper) {
        this.verificationCodeService = verificationCodeService;
        this.entityDtoMapper = entityDtoMapper;
    }

    @GetMapping("/getall")
    public DataResult<List<VerificationCode>> getAll() {
        return this.verificationCodeService.getAll();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Result add(@RequestBody VerificationCodeDto verificationCodeDto) {
        VerificationCode verificationCode = entityDtoMapper.convertToEntity(verificationCodeDto, VerificationCode.class);
        return this.verificationCodeService.add(verificationCode);
    }

    @GetMapping("/{id}")
    public DataResult<VerificationCode> getUserById(@PathVariable Integer id) {
        return verificationCodeService.getById(id);
    }

}

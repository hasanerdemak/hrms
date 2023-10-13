package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.VerificationCode;
import kodlamaio.hrms.entities.dtos.VerificationCodeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/verificationCodes")
public class VerificationCodesController {

    private VerificationCodeService verificationCodeService;
    private ModelMapper modelMapper;

    @Autowired
    public VerificationCodesController(VerificationCodeService verificationCodeService,
                                       ModelMapper modelMapper) {
        this.verificationCodeService = verificationCodeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/getall")
    public DataResult<List<VerificationCode>> getAll() {
        return this.verificationCodeService.getAll();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Result add(@RequestBody VerificationCodeDto verificationCodeDto) {
        VerificationCode verificationCode = convertToEntity(verificationCodeDto);
        return this.verificationCodeService.add(verificationCode);
    }

    @GetMapping("/{id}")
    public DataResult<VerificationCode> getUserById(@PathVariable Integer id) {
        return verificationCodeService.getById(id);
    }

    public VerificationCode convertToEntity(VerificationCodeDto verificationCodeDto) {
        return modelMapper.map(verificationCodeDto, VerificationCode.class);
    }

}

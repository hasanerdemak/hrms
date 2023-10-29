package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.core.utilities.mappers.EntityDtoMapper;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosition;
import kodlamaio.hrms.entities.dtos.JobPositionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobPositions")
public class JobPositionsController {

    private final JobPositionService jobPositionService;
    private final EntityDtoMapper entityDtoMapper;

    @Autowired
    public JobPositionsController(JobPositionService jobPositionService, EntityDtoMapper entityDtoMapper) {
        this.jobPositionService = jobPositionService;
        this.entityDtoMapper = entityDtoMapper;
    }

    @PostMapping("/add")
    public Result add(@RequestBody JobPositionDto jobPositionDto) {
        JobPosition jobPosition = entityDtoMapper.convertToEntity(jobPositionDto, JobPosition.class);
        return this.jobPositionService.add(jobPosition);
    }

    @GetMapping("/getall")
    public DataResult<List<JobPosition>> getAll() {
        return this.jobPositionService.getAll();
    }
}

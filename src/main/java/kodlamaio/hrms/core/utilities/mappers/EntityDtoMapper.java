package kodlamaio.hrms.core.utilities.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public EntityDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <D, E> E convertToEntity(D dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    public <E, D> D convertToDto(E entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }
}
package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

import java.util.List;

public interface BaseEntityService<T, ID> {

    // Create a new entity
    Result add(T entity);

    // Update an existing entity
    Result update(ID id, T entity);

    // Delete an entity by its ID
    Result delete(ID id);

    // Get an entity by its ID
    DataResult<T> getById(ID id);

    // Get all entities of this type
    DataResult<List<T>> getAll();
}

package kodlamaio.hrms.entities.abstracts;

public interface BaseEntity<T> {

    T getId();

    void setId(T id);

}

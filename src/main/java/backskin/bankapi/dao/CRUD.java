package backskin.bankapi.dao;

public interface CRUD<T, Id> {
    void create(T entity) throws Exception;
    T read(Id id) throws Exception;
    void update(T entity, Id id) throws Exception;
    void delete(T entity) throws Exception;
}

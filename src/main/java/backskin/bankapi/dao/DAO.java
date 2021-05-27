package backskin.bankapi.dao;

public interface DAO<T, Id> {
    void create(T entity);
    T get(Id id);
    void update(T entity, Id id);
    void delete(T entity);
}

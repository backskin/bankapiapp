package backskin.bankapi.dao;

/**
 * The interface Crud.
 *
 * @param <T>  the type parameter
 * @param <Id> the type parameter
 */
public interface CRUD<T, Id> {
    /**
     * Create t.
     *
     * @param entity the entity
     * @return the t
     * @throws Exception the exception
     */
    T create(T entity) throws Exception;

    /**
     * Read t.
     *
     * @param id the id
     * @return the t
     * @throws Exception the exception
     */
    T read(Id id) throws Exception;

    /**
     * Update.
     *
     * @param entity the entity
     * @param id     the id
     * @throws Exception the exception
     */
    void update(T entity, Id id) throws Exception;

    /**
     * Delete.
     *
     * @param entity the entity
     * @throws Exception the exception
     */
    void delete(T entity) throws Exception;
}

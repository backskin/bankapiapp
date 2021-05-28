package backskin.bankapi.dao;

import backskin.bankapi.validators.Validator;

import java.util.List;

/**
 * The interface Repo.
 *
 * @param <ModelType> the type parameter
 */
public interface Repo<ModelType> {
    /**
     * Gets all.
     *
     * @return the all
     * @throws Exception the exception
     */
    List<ModelType> getAll() throws Exception;

    /**
     * Find all list.
     *
     * @param <Tag>     the type parameter
     * @param validator the validator
     * @param tag       the tag
     * @return the list
     * @throws Exception the exception
     */
    <Tag> List<ModelType> findAll(Validator<ModelType, Tag> validator, Tag tag) throws Exception;
}

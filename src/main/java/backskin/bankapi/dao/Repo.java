package backskin.bankapi.dao;

import backskin.bankapi.validators.Validator;

import java.util.List;

public interface Repo<ModelType> {
    List<ModelType> getAll() throws Exception;

    <Tag> List<ModelType> findAll(Validator<ModelType, Tag> validator, Tag tag) throws Exception;
}

package backskin.bankapi.dao;

import backskin.bankapi.domain.DatabaseModel;

import java.sql.SQLException;
import java.util.List;

public interface Repo<ModelType extends DatabaseModel> {
    List<ModelType> getAll() throws SQLException;
    <Tag> List<ModelType> findAll(Validator<ModelType, Tag> validator, Tag tag) throws SQLException;
}

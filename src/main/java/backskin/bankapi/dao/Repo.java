package backskin.bankapi.dao;

import backskin.bankapi.models.SqlModel;

import java.sql.SQLException;
import java.util.List;

public interface Repo<ModelType extends SqlModel> {
    List<ModelType> getAll() throws SQLException;
    <Tag> List<ModelType> findAll(Validator<ModelType, Tag> validator, Tag tag) throws SQLException;
}

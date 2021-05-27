package backskin.bankapi.dao;

import backskin.bankapi.models.AbstractModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractRepo<T extends AbstractModel> implements Repo<T>, SqlDAO<T> {

    protected abstract List<T> createContainer();

    @Override
    public List<T> getAll() throws SQLException {
        List<T> container = createContainer();
        String sqlQuery = "SELECT * FROM ?";
        PreparedStatement statement = getConnection().prepareStatement(sqlQuery);
        statement.setString(1, getTableName());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            T value = getMapper().map(resultSet);
            container.add(value);
        }
        return container;
    }

    @Override
    public <Tag> List<T> findAll(Validator<T, Tag> validator, Tag tagValue) throws SQLException {
        List<T> container = createContainer();
        String sqlQuery = "SELECT * FROM ? WHERE ?";
        PreparedStatement statement = getConnection().prepareStatement(sqlQuery);
        statement.setString(1, getTableName());
        statement.setString(2, validator.validationRule(tagValue));
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            T value = getMapper().map(resultSet);
            container.add(value);
        }
        return container;
    }
}

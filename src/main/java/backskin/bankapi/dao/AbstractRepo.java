package backskin.bankapi.dao;

import backskin.bankapi.models.AbstractModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractRepo<T extends AbstractModel> extends AbstractDAO<T> implements Repo<T>{

    protected abstract List<T> createContainer();

    @Override
    public List<T> getAll() throws SQLException {
        List<T> container = createContainer();
        String sqlQuery = "SELECT * FROM ?";
        PreparedStatement statement = getConnection().prepareStatement(sqlQuery);
        Statement statement = statementCreator.create();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM "+getTableName());
        while (resultSet.next()){
            T value = getMapper().mapRow(resultSet, resultSet.getRow());
            container.add(value);
        }
        return container;
    }

    @Override
    public <Tag> List<T> findAll(Validator<T, Tag> validator, Tag tagValue) throws SQLException {
        List<T> container = createContainer();
        Statement statement = statementCreator.create();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM "+getTableName()
                +" WHERE "+validator.validationRule(tagValue));
        while (resultSet.next()){
            T value = getMapper().mapRow(resultSet, resultSet.getRow());
            container.add(value);
        }
        return container;
    }
}

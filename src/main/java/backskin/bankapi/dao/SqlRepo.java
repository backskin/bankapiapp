package backskin.bankapi.dao;

import backskin.bankapi.models.AbstractModel;
import backskin.bankapi.validators.Validator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The interface Sql repo.
 *
 * @param <T> the type parameter
 */
public interface SqlRepo<T extends AbstractModel> extends Repo<T>, SqlDAO<T> {

    /**
     * Create container list.
     *
     * @return the list
     */
    default List<T> createContainer(){
        return new ArrayList<>();
    };

    @Override
    default List<T> getAll() throws SQLException {
        List<T> container = createContainer();
        String sqlQuery = String.format("SELECT * FROM %s", getTableName());
        ResultSet resultSet = getConnection().createStatement().executeQuery(sqlQuery);
        while (resultSet.next()){
            T value = getMapper().map(resultSet);
            container.add(value);
        }
        return container;
    }

    @Override
    default  <Tag> List<T> findAll(Validator<T, Tag> validator, Tag tagValue) throws SQLException {
        List<T> container = createContainer();
        String sqlQuery = String.format("SELECT * FROM %s WHERE %s", getTableName(), validator.validationRule(tagValue));
        ResultSet resultSet = getConnection().createStatement().executeQuery(sqlQuery);
        while (resultSet.next()){
            T value = getMapper().map(resultSet);
            container.add(value);
        }
        return container;
    }
}

package backskin.bankapi.dao.mappers;

import backskin.bankapi.models.AbstractModel;
import backskin.bankapi.models.SqlModel;
import backskin.bankapi.validators.Validator;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Abstract sql mapper.
 *
 * @param <T> the type parameter
 */
@Component
public abstract class AbstractSqlMapper<T extends SqlModel> implements SqlMapper<T> {
    @Getter
    private Validator<AbstractModel, Long> idValidator;

    /**
     * Sets id validator.
     *
     * @param idValidator the id validator
     */
    @Autowired
    public void setIdValidator(Validator<AbstractModel, Long> idValidator) {
        this.idValidator = idValidator;
    }

    public abstract T map(ResultSet resultSet) throws SQLException;
}

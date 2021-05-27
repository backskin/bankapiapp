package backskin.bankapi.dao.mappers;

import backskin.bankapi.dao.Validator;
import backskin.bankapi.models.AbstractModel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public abstract class AbstractMapper<T extends AbstractModel> implements SqlMapper<T, ResultSet> {
    @Getter
    private Validator<AbstractModel, Long> idValidator;
    @Autowired
    public void setIdValidator(Validator<AbstractModel, Long> idValidator) {
        this.idValidator = idValidator;
    }

    public abstract T map(ResultSet resultSet) throws SQLException;
}

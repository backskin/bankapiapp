package backskin.bankapi.validators;

import backskin.bankapi.dao.Validator;
import backskin.bankapi.models.AbstractModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorFactory {
    @Bean
    public static Validator<AbstractModel, Long> byId(){
        return new Validator<>() {
            @Override
            public boolean validateObject(AbstractModel object, Long tag) {
                return object.getId().equals(tag);
            }

            @Override
            public Long extractTag(AbstractModel abstractModel) {
                return abstractModel.getId();
            }

            @Override
            public String tagName() {
                return "id";
            }
        };
    }
}

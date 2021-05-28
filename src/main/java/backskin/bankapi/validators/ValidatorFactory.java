package backskin.bankapi.validators;

import backskin.bankapi.models.AbstractModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * The type Validator factory.
 */
@Configuration
public class ValidatorFactory {
    /**
     * Entity validator by id validator.
     *
     * @return the validator
     */
    @Bean
    @Primary
    public Validator<AbstractModel, Long> entityValidatorById(){
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

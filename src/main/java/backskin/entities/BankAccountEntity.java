package backskin.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Builder(builderClassName = "BankAccountBuilder")
public class BankAccountEntity extends BaseEntity{
    private final String number;
    @Setter
    private BigInteger bankClientId;
    @Setter
    private BigDecimal balance;
}

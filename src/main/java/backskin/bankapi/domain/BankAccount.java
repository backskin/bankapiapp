package backskin.bankapi.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Builder(builderClassName = "BankAccountBuilder")
public class BankAccount extends AbstractModel {
    private final String number;
    @Setter
    private BigInteger bankClientId;
    @Setter
    private BigDecimal balance;
}

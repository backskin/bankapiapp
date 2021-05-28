package backskin.bankapi.presentation;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * The type Deposit info.
 */
@Getter
@Setter
@Builder(builderClassName = "DepositInfoBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class DepositInfo implements Info{
    private BigDecimal depositAmount;
    private BigDecimal currentBalanceAfterDeposit;
    private String accountNumber;
    private Timestamp dateOfOperation;
}

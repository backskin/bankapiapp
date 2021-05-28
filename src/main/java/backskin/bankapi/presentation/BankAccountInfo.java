package backskin.bankapi.presentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * The type Bank account info.
 */
@Getter
@AllArgsConstructor
@Builder
public class BankAccountInfo implements Info{
    private final Long id;
    private final String number;
    private final BigDecimal balance;
}

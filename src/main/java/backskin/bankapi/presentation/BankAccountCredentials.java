package backskin.bankapi.presentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * The type Bank account credentials.
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class BankAccountCredentials implements Credentials{
    private Long clientId;
    private String number;
    private BigDecimal balance;
}

package backskin.bankapi.presentation;

import lombok.*;

import java.math.BigDecimal;

/**
 * The type Deposit credentials.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepositCredentials implements Credentials{
    private Long recipientAccountId;
    @Builder.Default
    private BigDecimal depositAmount = BigDecimal.ZERO;
}

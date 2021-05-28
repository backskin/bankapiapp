package backskin.bankapi.presentation;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Builder
public class DepositCredentials implements Credentials{
    private Long recipientAccountId;
    private BigDecimal depositAmount;
}

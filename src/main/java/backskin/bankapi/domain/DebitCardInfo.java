package backskin.bankapi.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@Getter
@RequiredArgsConstructor
public class DebitCardInfo {
    private final Long Id;
    private final BigInteger accountId;
    private final String number;
    public DebitCardInfo fromDebitCard(DebitCard debitCard){
        return new DebitCardInfo(
                debitCard.getId(),
                debitCard.getBankAccountId(),
                debitCard.getNumber()
        );
    }
}

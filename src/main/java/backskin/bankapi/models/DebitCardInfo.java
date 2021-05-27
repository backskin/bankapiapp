package backskin.bankapi.models;

import backskin.bankapi.domain.DebitCard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

@Getter
@RequiredArgsConstructor
public class DebitCardInfo {
    private final Long Id;
    private final Long accountId;
    private final String number;
    public static DebitCardInfo fromDebitCard(@NotNull DebitCard debitCard){
        return new DebitCardInfo(
                debitCard.getId(),
                debitCard.getBankAccountId(),
                debitCard.getNumber()
        );
    }
}

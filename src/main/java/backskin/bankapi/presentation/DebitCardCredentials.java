package backskin.bankapi.presentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class DebitCardCredentials {
    Long accountId;
    String number;
    String expirationDate;
    String cvvCode;
}

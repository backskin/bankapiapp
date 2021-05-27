package backskin.bankapi.presentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DebitCardCredentials {
    private Long accountId;
    private String number;
    private String expirationDate;
    private String cvvCode;
}

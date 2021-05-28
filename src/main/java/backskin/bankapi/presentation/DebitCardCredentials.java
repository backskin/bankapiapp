package backskin.bankapi.presentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Debit card credentials.
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class DebitCardCredentials implements Credentials{
    private Long accountId;
    private String number;
    private String expirationDate;
    private String cvvCode;
}

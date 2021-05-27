package backskin.bankapi.presentation;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
@Builder
public class BankAccountInfo implements Info{
    private String number;
    private BigDecimal balance;
}

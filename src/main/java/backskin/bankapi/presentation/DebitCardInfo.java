package backskin.bankapi.presentation;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * The type Debit card info.
 */
@Getter
@RequiredArgsConstructor
@Builder
public class DebitCardInfo implements Info {
    private final Long Id;
    private final String number;
}

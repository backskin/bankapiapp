package backskin.bankapi.presentation;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class DebitCardInfo implements Info {
    private final Long Id;
    private final String number;
}

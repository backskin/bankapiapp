package backskin.bankapi.models;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractModel implements Serializable, SqlModel {
    @Builder.Default
    private Long id = 1L;
}

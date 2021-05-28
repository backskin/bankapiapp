package backskin.bankapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * The type Abstract model.
 */
@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractModel implements Serializable, SqlModel {
    @Builder.Default
    private Long id = 1L;
}

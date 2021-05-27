package backskin.bankapi.models;

import lombok.*;

import java.io.Serializable;


/**
 * Abstract Model Class.
 * Describes any data model stored in the database.
 * Any model, eventually, has an id.
 */
@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractModel implements Serializable, SqlModel {
    @NonNull
    private Long id = 0L;
}

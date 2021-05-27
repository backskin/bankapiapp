package backskin.bankapi.models;

import backskin.bankapi.models.DatabaseModel;
import lombok.*;

import java.io.Serializable;


/**
 * Abstract Model Class.
 * Describes any data model stored in the database.
 * Any model, eventually, has an id.
 */
@Setter
@Getter
@RequiredArgsConstructor
public abstract class AbstractModel implements Serializable, DatabaseModel {
    @NonNull
    private final Long id;
}

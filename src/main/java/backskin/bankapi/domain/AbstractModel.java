package backskin.bankapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * Abstract Model Class.
 * Describes any data model stored in the database.
 * Any model, eventually, has an id.
 */
@Setter
@Getter
@AllArgsConstructor
public abstract class AbstractModel implements Serializable, DatabaseModel {
    private final Long id;
}

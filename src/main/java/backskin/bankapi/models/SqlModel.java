package backskin.bankapi.models;

/**
 * The interface Sql model.
 */
public interface SqlModel {
    /**
     * Gets id.
     *
     * @return the id
     */
    Long getId();

    /**
     * Fields string.
     *
     * @return the string
     */
    String fields();

    /**
     * Values string.
     *
     * @return the string
     */
    String values();
}

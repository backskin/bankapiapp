package backskin.bankapi.presentation;

/**
 * The interface Mapper.
 *
 * @param <Presentation> the type parameter
 * @param <Original>     the type parameter
 */
public interface Mapper<Presentation, Original> {

    /**
     * Map presentation.
     *
     * @param original the original
     * @return the presentation
     */
    Presentation map(Original original);
}

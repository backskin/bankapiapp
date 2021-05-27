package backskin.bankapi.presentation;

import java.util.Optional;

public interface Mapper<P, Original> {
    Optional<P> map(Original original);
}

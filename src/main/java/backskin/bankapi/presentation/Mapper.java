package backskin.bankapi.presentation;

import java.util.Optional;

public interface Mapper<P, Original> {
    P map(Original original);
}

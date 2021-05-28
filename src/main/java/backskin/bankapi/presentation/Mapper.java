package backskin.bankapi.presentation;

public interface Mapper<Presentation, Original> {

    Presentation map(Original original);
}

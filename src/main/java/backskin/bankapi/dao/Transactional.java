package backskin.bankapi.dao;

public interface Transactional {
    void beginTransaction();
    void commitTransaction();
    void rollbackTransaction();
}

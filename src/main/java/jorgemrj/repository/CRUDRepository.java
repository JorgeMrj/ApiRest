package jorgemrj.repository;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T, ID> {
    List<T> getAll();
    Optional<T> getById(ID id);
    Optional<T> save(T user);
    Optional<T> update(T user);
    Optional<T> deleteById(ID id);
}
package jorgemrj.repository;

import jorgemrj.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();


    Optional<User> findById(long id);


    User save(User user);


    Optional<User> update(User user);


    boolean deleteById(long id);
}
}

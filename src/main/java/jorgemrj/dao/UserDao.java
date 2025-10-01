package jorgemrj.dao;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    @SqlQuery("SELECT * FROM users")
    List<UserEntity> getAll();


    @SqlQuery("SELECT * FROM users WHERE id = :id")
    Optional<UserEntity> getById(@Bind("id") long id);


    @SqlUpdate("INSERT INTO users (id, name, username, email) VALUES (:id, :name, :username, :email, :created_at, :updated_at)")
    int save(@BindBean UserEntity entity);


    @SqlUpdate("UPDATE users SET id = :id, name = :name, username = :username, email = :email, created_at = :createdAt, updated_at = :uptadetAt WHERE id = :id")
    int update(@BindBean UserEntity entity);


    @SqlUpdate("DELETE FROM users WHERE id = :id")
    int deleteById(@Bind("id") long id);

}

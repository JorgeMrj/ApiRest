package jorgemrj.mapper;

import jorgemrj.dao.UserEntity;
import jorgemrj.model.User;

import java.time.LocalDateTime;

public class Mapper {


    public static User entityToUser(UserEntity entity) {
        User user = new User(
                entity.getId(),
                entity.getName(),
                entity.getUsername(),
                entity.getEmail()
        );
        return user;
    }


    public static UserEntity userToEntity(User user) {
        UserEntity entity = new UserEntity(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        return entity;
    }
}

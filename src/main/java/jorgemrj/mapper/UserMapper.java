package jorgemrj.mapper;

import jorgemrj.dao.UserEntity;
import jorgemrj.model.User;

public class UserMapper {

    private UserMapper() {
    }


    public static UserEntity fromUser(User user) {
        if (user == null) return null;

        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }


    public static User toEntity(UserEntity entity) {
        if (entity == null) return null;

        return User.builder()
                .id(entity.getId())
                .name(entity.getName())
                .username(entity.getUsername())
                .email(entity.getEmail())
                //todo falta arreglar esto
               //.createAt(entity.getCreateAt())
                //.updateAt(entity.getUpdateAt())
                .build();
    }
}




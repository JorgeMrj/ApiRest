package jorgemrj.repository;

import jorgemrj.dao.UserDao;
import jorgemrj.dao.UserEntity;
import jorgemrj.database.JdbiManager;
import jorgemrj.mapper.Mapper;
import jorgemrj.model.User;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

        private final UserDao userDao = JdbiManager.getInstance().getUserDao();

        @Override
        public List<User> getAll() {
            return userDao.getAll().stream()
                    .map(Mapper::entityToUser)
                    .toList();
        }


        @Override
        public Optional<User> getById(Long id) {

            return userDao.getById(id)
                    .map(Mapper::entityToUser);
        }


        @Override
        public Optional<User> save(User user) {
            UserEntity userEntity = Mapper.userToEntity(user);

            if (userDao.save(userEntity) == 1) {
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        }


        @Override
        public Optional<User> update(User user) {

            Optional<UserEntity> userToUpdate = userDao.getById(user.getId());

            if (userToUpdate.isPresent() && userDao.update(Mapper.userToEntity(user)) == 1) {
                return Optional.of(user);
            } else  {
                return Optional.empty();
            }
        }

        @Override
        public Optional<User> deleteById(Long id) {

            Optional<UserEntity> userToDelete = userDao.getById(id);

            if (userToDelete.isPresent() && userDao.deleteById(id) == 1) {
                User deletedUser = Mapper.entityToUser(userToDelete.get());
                return Optional.of(deletedUser);
            } else {
                return Optional.empty();
            }

        }
    }


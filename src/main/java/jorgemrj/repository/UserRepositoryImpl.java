package jorgemrj.repository;

import jorgemrj.dao.UserDao;
import jorgemrj.dao.UserEntity;
import jorgemrj.database.JdbiManager;
import jorgemrj.mapper.Mapper;
import jorgemrj.model.User;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final UserDao userDao;

    public TenistasRepositoryImpl(TenistasDao tenistasDao) {
        this.tenistasDao = tenistasDao;
    }


    @Override
    public List<Tenista> findAll() {
        return tenistasDao.findAll().stream()
                .map(TenistaMapper::fromEntity)
                .toList();
    }


    @Override
    public Optional<Tenista> findById(long id) {
        logger.info("Buscando tenista con id: {}", id);
        return tenistasDao.findById(id).map(TenistaMapper::fromEntity);
    }


    @Override
    public Tenista save(Tenista tenista) {
        // Convertimos a entidad con id = -1
        TenistaEntity entityToSave = TenistaMapper.toEntity(
                Tenista.builder()
                        //.id(Tenista.NEW_TENISTA_ID) // lo toma por defecto!!
                        .nombre(tenista.getNombre())
                        .pais(tenista.getPais())
                        .altura(tenista.getAltura())
                        .peso(tenista.getPeso())
                        .puntos(tenista.getPuntos())
                        .mano(tenista.getMano())
                        .fechaNacimiento(tenista.getFechaNacimiento())
                        .build()
        );


        long newId = tenistasDao.save(entityToSave);

        return Tenista.builder()
                .id(newId)
                .nombre(tenista.getNombre())
                .pais(tenista.getPais())
                .altura(tenista.getAltura())
                .peso(tenista.getPeso())
                .puntos(tenista.getPuntos())
                .mano(tenista.getMano())
                .fechaNacimiento(tenista.getFechaNacimiento())
                .build();
    }

    /** {@inheritDoc} */
    @Override
    public Optional<User> update(User tenista) {
        TenistaEntity entityToUpdate = Mapper.toEntity(user);
        int updatedRows = tenistasDao.update(entityToUpdate);
        return updatedRows > 0 ? Optional.of(tenista) : Optional.empty();
    }

    /** {@inheritDoc} */
    @Override
    public boolean deleteById(long id) {
        logger.info("Borrando tenista con id: {}", id);
        return tenistasDao.delete(id) > 0;
    }
    }


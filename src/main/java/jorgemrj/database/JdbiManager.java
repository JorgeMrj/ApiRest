package jorgemrj.database;

import ch.qos.logback.classic.Logger;
import jorgemrj.config.Config;
import jorgemrj.dao.UserDao;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class JdbiManager {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(JdbiManager.class);

    private static JdbiManager instance;
    private final Jdbi jdbi;

    private JdbiManager() {

        String databaseUrl = Config.getInstance().getDatabaseUrl();
        this.jdbi = Jdbi.create(databaseUrl);
        jdbi.installPlugin(new SqlObjectPlugin());
        boolean initTables = Config.getInstance().initTables();
        if (initTables) {
            logger.debug("JDBI: Creando tablas en la base de datos local");
            executeSqlScriptFromResources("tables.sql");
        }
    }

    public static synchronized JdbiManager getInstance() {
        logger.debug("JDBI: Proporcionando instancia única del JDBIManager");

        if (instance == null) {
            instance = new JdbiManager();
        }
        return instance;
    }

   
    public UserDao getUserDao() {
        logger.debug("JDBI: Obteniendo DAO de usuarios.");
        return jdbi.onDemand(UserDao.class);
    }

   
    private void executeSqlScriptFromResources(String resourcePath) {
        logger.debug("JDBI: Cargando script SQL desde recursos: " + resourcePath);
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            String script = reader.lines().collect(Collectors.joining("\n"));
            jdbi.useHandle(handle -> handle.createScript(script).execute());
        } catch (Exception e) {
            logger.error("JDBI: Error al ejecutar el script SQL desde recursos");
            logger.error("JDBI: Error: {}", e.getMessage());
        }
    }
}

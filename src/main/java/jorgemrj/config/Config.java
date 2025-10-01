package jorgemrj.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

        private static Config instance;
        private static String resourceName = "config.properties";
        private final Properties properties;

        private Config() {
            this.properties = new Properties();

            try (InputStream input = getClass().getClassLoader().getResourceAsStream(resourceName)) {
                if (input == null) {
                    setDefaults();
                } else {
                    properties.load(input);
                }
            } catch (IOException ex) {
                System.err.println("Error al cargar el archivo de propiedades. Se usarán valores por defecto.");
                setDefaults();
            }
        }

        /**
         * Devuelve una instancia única de la configuración, para hacerla Singleton.
         * @return la instancia
         */
        public static synchronized Config getInstance() {

            if (instance == null) {
                instance = new Config();
            }
            return instance;
        }

        /**
         * Establece valores por defecto cuando no se puede leer el archivo de propiedades.
         */
        private void setDefaults() {

            properties.setProperty("database.url", "jdbc:h2:mem:usuarios;DB_CLOSE_DELAY=-1");
            properties.setProperty("database.init.tables", "true");
        }

        /**
         * Obtiene la URL de la base de datos del archivo de propiedades.
         *
         * @return la URL.
         */
        public String getDatabaseUrl() {

            return properties.getProperty("database.url");
        }

        /**
         * Obtiene el valor de la propiedad database.init.tables del archivo de propiedades.
         *
         * @return true si se deben crear tablas, false en caso contrario.
         */
        public boolean initTables() {

            return Boolean.parseBoolean(properties.getProperty("database.init.tables"));
        }

    }

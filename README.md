# Servicio API REST con Almacenamiento Local y Remoto

Este proyecto presenta un servicio web desarrollado en Java para la gestión de datos de usuarios. Implementa una arquitectura de tres niveles que integra una caché en memoria, una base de datos embebida y una API REST remota para garantizar un acceso a los datos eficiente, robusto y de alto rendimiento.

## Arquitectura

El diseño del servicio se basa en una arquitectura de tres niveles para optimizar la latencia y la disponibilidad de los datos:

1.  **Caché en Memoria:** Ofrece acceso de alta velocidad a los datos consultados con mayor frecuencia, actuando como la primera capa de lectura.
2.  **Base de Datos Embebida (H2):** Funciona como un almacenamiento local persistente. Se sincroniza periódicamente con la fuente de datos remota para mantener la consistencia.
3.  **API REST Remota ([JSONPlaceholder](https://jsonplaceholder.typicode.com/)):** Es la fuente principal y autoritativa de los datos. Se consulta únicamente cuando la información no se encuentra en la caché ni en la base de datos local.

Al iniciar, la aplicación purga la base de datos local y la recarga con los datos actualizados desde la API REST. Adicionalmente, un proceso en segundo plano resincroniza la base de datos cada 30 segundos para reflejar cualquier cambio externo.

![Diagrama de la arquitectura](/images/remote_repository.jpg)

## Funcionalidades Principales

El servicio expone una API con las siguientes capacidades:

-   **Listar Usuarios (`GET /users`):** Devuelve el conjunto completo de usuarios desde la base de datos local.
-   **Obtener Usuario por ID (`GET /users/{id}`):** Realiza una búsqueda secuencial: primero en la caché, luego en la base de datos y, finalmente, en la API REST remota.
-   **Crear Usuario (`POST /users`):** La operación se delega a la API REST remota. Tras la confirmación, el nuevo registro se sincroniza en la base de datos local y en la caché.
-   **Actualizar Usuario (`PUT /users/{id}`):** Actualiza el registro en la API REST, y propaga el cambio a la base de datos y la caché para mantener la consistencia.
-   **Eliminar Usuario (`DELETE /users/{id}`):** Elimina el registro en la API REST, la base de datos local y la caché.
-   **Exportación de Datos:** Genera un fichero `JSON` con todos los usuarios del sistema.
-   **Sistema de Notificaciones:** Emite alertas sobre operaciones de creación, actualización, eliminación y durante los ciclos de refresco de la base de datos.
-   **Registro de Actividad (Logging):** Almacena un registro detallado de todas las operaciones y errores, con una retención de los últimos 7 días.

## Modelo de Datos

Para simplificar la implementación, el servicio opera con un subconjunto de los campos del recurso `users` de la API remota:

-   `id`: Identificador único del usuario.
-   `name`: Nombre completo.
-   `username`: Nombre de usuario.
-   `email`: Dirección de correo electrónico.

A nivel de persistencia, la entidad `UserEntity` extiende este modelo añadiendo los campos `createdAt` y `updatedAt` para el control de versiones y auditoría.

## Flujo de Operaciones

El flujo de datos a través de las capas de la arquitectura varía según la operación:

-   **Inicio del Servicio:** Se limpia la base de datos local y se realiza una carga masiva inicial desde la API REST.
-   **Sincronización Periódica:** Cada 30 segundos, el sistema consulta la API REST para actualizar la base de datos y la caché.
-   **Lectura (`GET /users/{id}`):** El flujo de búsqueda sigue la ruta: `Caché -> Base de Datos -> API REST`. Si el dato se encuentra, se propaga hacia las capas superiores para optimizar futuras lecturas.
-   **Escritura (`POST`, `PUT`, `DELETE`):** Las operaciones de modificación siguen la ruta: `API REST -> Base de Datos -> Caché` para garantizar que la fuente autoritativa se actualice primero.

## Stack Tecnológico

-   **Lenguaje:** Java.
-   **Framework:** (Especificar, ej. Spring Boot, Quarkus).
-   **Programación:** Asíncrona y Reactiva (ej. Project Reactor/RxJava).
-   **Almacenamiento Local:** Base de datos embebida H2.
-   **Cliente HTTP:** Para la integración con la API REST remota.
-   **Modelo de Datos:** DTOs para la capa de API, Entidades para la persistencia y un modelo de dominio desacoplado.
-   **Testing:** Pruebas unitarias y de integración (ej. JUnit, Mockito).
-   **Control de Versiones:** Git y GitHub.

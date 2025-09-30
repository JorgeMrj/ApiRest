# Practica: Servicio con almacenamiento local y remoto

Este proyecto implementa un servicio web en Java que gestiona datos de usuarios, utilizando una arquitectura de tres niveles que combina una caché en memoria, una base de datos embebida y una API REST externa.

## Objetivo

El objetivo principal es desarrollar un servicio web robusto y eficiente que permita realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre datos de usuarios, manejando la comunicación con un almacenamiento local (base de datos embebida) y uno remoto (API REST) de forma segura y optimizada.

## Arquitectura

El servicio está diseñado con una arquitectura de tres niveles para optimizar el acceso a los datos y mejorar el rendimiento:

1.  **Caché en Memoria:** Proporciona acceso ultrarrápido a los datos solicitados con frecuencia.
2.  **Base de Datos Embebida:** Almacenamiento local (H2) que actúa como una fuente de datos persistente y sincronizada.
3.  **API REST Remota:** Fuente principal de datos (JSONPlaceholder), consultada cuando la información no está disponible localmente.

Al iniciar, la aplicación borra la base de datos local y la carga con el contenido actualizado de la API REST. Además, un proceso en segundo plano resincroniza la base de datos cada 30 segundos para mantener la consistencia de los datos.

![Diagrama de la arquitectura](/images/remote_repository.jpg)


## Funcionalidades

El servicio ofrece las siguientes operaciones:

-   **Obtener todos los usuarios:** Devuelve los datos desde la base de datos local.
-   **Obtener un usuario por su ID:** Busca en la caché, luego en la base de datos y finalmente en la API REST.
-   **Crear un nuevo usuario:** Envía la petición a la API REST y sincroniza el nuevo registro en la base de datos local y la caché.
-   **Actualizar un usuario existente:** Actualiza el registro en la API REST, la base de datos y la caché.
-   **Eliminar un usuario:** Elimina el registro de la API REST, la base de datos y la caché.
-   **Exportar usuarios a JSON:** Genera un fichero `JSON` con todos los usuarios del sistema.
-   **Notificaciones:** Avisa sobre operaciones CUD y refrescos de la base de datos.
-   **Logging:** Registra todas las operaciones y errores ocurridos en los últimos 7 días.

## Modelo de Datos

Para simplificar el modelo, solo se gestionan los siguientes campos del recurso `users` de la API:

-   `id`: Identificador único.
-   `name`: Nombre completo.
-   `username`: Nombre de usuario.
-   `email`: Dirección de correo electrónico.

A nivel de base de datos, la entidad `UserEntity` incluye además los campos `createdAt` y `updatedAt` para el control de versiones.

## Flujo de Operaciones

-   **Inicio:** La base de datos se limpia y se carga desde la API REST.
-   **Sincronización:** Cada 30 segundos, la base de datos y la caché se actualizan desde la API REST.
-   **Lectura (GET /users/{id}):** El flujo de búsqueda es `Caché -> Base de Datos -> API REST`. El dato encontrado se propaga a los niveles superiores (Caché y BD).
-   **Creación (POST /users):** `API REST -> Base de Datos -> Caché`.
-   **Actualización (PUT /users/{id}):** `API REST -> Base de Datos -> Caché`.
-   **Eliminación (DELETE /users/{id}):** `API REST -> Base de Datos -> Caché`.

## Requisitos Técnicos

-   **Lenguaje:** Java.
-   **Programación:** Asíncrona y Reactiva para máxima eficiencia.
-   **Almacenamiento Local:** Base de datos embebida (H2, SQLite, etc.).
-   **Almacenamiento Remoto:** Integración con una API REST (ej. [JSONPlaceholder](https://jsonplaceholder.typicode.com/)).
-   **Modelo:** DTOs para las peticiones/respuestas de la API, Entidades para la base de datos y un modelo de dominio claro.
-   **Manejo de Errores:** Sistema de errores de dominio bien definidos.
-   **Testing:** Pruebas unitarias y de integración para las capas de servicio y repositorios.
-   **Documentación:** Código y diseño debidamente documentados.
-   **Control de Versiones:** Git y GitHub.

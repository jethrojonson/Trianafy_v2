# Trianafy

<p align="center">
  <img src="https://img.shields.io/badge/STATUS-DEVELOP-yellow" alt="Status del proyecto"/>
  <a href="https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html"><img src="https://img.shields.io/badge/jdk-v17.0.4.1-blue" alt="Versión java" /></a>
  <a href="https://maven.apache.org/download.cgi"><img src="https://img.shields.io/badge/apache--maven-v3.8.6-brown" alt="Versión maven" /></a>
  <a href="https://spring.io/projects/spring-boot"><img src="https://img.shields.io/badge/spring--boot-v2.7.7-green" alt="Versión spring-boot" /></a>
  <img src="https://img.shields.io/badge/release%20date-january-purple" alt="Lanzamiento del proyecto" />
</p>
  

## Descripción
**Trianafy** es una API-REST que gestiona artistas, canciones, álbumes, y playlists.  
En resumen es un spotify al estilo trianero.
## Autores
### Jerónimo M. Pérez González

## Entidades

* ### Artist
* ### Song
* ### Album
* ### Playlist

## Modelo de datos

<p>
    <img src="diagrama UML/modelo_datos.png" alt="Modelo de datos - Trianafy_v2">
</p>

## Estructura del proyecto

#### * Se pretende una organizacion parecida al DDD.

* ### Artist
    - Model
    - Repository
    - Service
    - Controller
    - DTO
    - Exceptions
    - View

* ### Album
    - Model
    - Repository
    - Service
    - Controller
    - DTO
    - Exceptions
    - View

* ### Song
    - Model
    - Repository
    - Service
    - Controller
    - DTO
    - Exceptions
    - View

* ### Playlist
    - Model
    - Repository
    - Service
    - Controller
    - DTO
    - Exceptions
    - View
* ### Error
* ### Config

</br>

## Funcionalidades

### Funcionalidades **Artist**

1. Añadir un nuevo artista - Peticion: **POST - http://localhost:8080/artist/**
2. Obtener todos los artistas - Peticion: **GET - http://localhost:8080/artist/**
3. Obtener un artista - Peticion: **GET - http://localhost:8080/artist/:id**
4. Actualizar un artista - Peticion: **PUT - http://localhost:8080/artist/:id**
5. Eliminar un artista - Peticion: **DELETE - http://localhost:8080/artist/:id**


### Funcionalidades **Album**

1. Añadir un nuevo album - Peticion: **POST - http://localhost:8080/album/**
2. Obtener todos los álbumes - Peticion: **GET - http://localhost:8080/album/**
3. Obtener un album - Peticion: **GET - http://localhost:8080/album/:id**
4. Actualizar un album - Peticion: **PUT - http://localhost:8080/album/:id**
5. Eliminar un album - Peticion: **DELETE - http://localhost:8080/album/:id**

### Funcionalidades **Song**

1. Añadir una nueva canción - Peticion: **POST - http://localhost:8080/song/**
2. Obtener todas las canciones - Peticion: **GET - http://localhost:8080/song/**
3. Obtener una canción - Peticion: **GET - http://localhost:8080/song/:id**
4. Actualizar una canción - Peticion: **PUT - http://localhost:8080/song/:id**
5. Eliminar una canción - Peticion: **DELETE - http://localhost:8080/song/:id**

### Funcionalidades **Playlist**

1. Añadir una nueva playlist - Peticion: **POST - http://localhost:8080/list/**
2. Obtener todas las playlists - Peticion: **GET - http://localhost:8080/list/**
3. Obtener una playlist - Peticion: **GET - http://localhost:8080/list/:id**
4. Actualizar una playlist - Peticion: **PUT - http://localhost:8080/list/:id**
5. Eliminar una playlist - Peticion: **DELETE - http://localhost:8080/list/:id**

#### _Funcionalidades respecto a las canciones._

6. Añadir una canción a una playlist - Peticion: **POST - http://localhost:8080/list/:id1/song/:id2**
7. Obtener canciones de una playlist - Peticion: **GET - http://localhost:8080/list/:id/song**
8. Obtener una canción de una playlist - Peticion: **GET - http://localhost:8080/list/:id1/song/:id2**
9. Eliminar una canción de una playlist - Peticion: **DELETE - http://localhost:8080/list/:id1/song/:id2**


## Arrancar el proyecto

* Descargar IntelliJ IDEA Community Edition
* Descargar Java jdk 17
* Descargar apache-maven 3.8.6
* Configurar la versón de Java dentro de IntelliJ
* Clicar en el botón *Edit Configurations*
* Añadir una nueva configuración de tipo **Maven**
* En el apartado de *Run* escribir **spring-boot:run**

## Disclaimer

- Se utiliza la version 2.7.7 de spring

- Se altera el orden de paketes para usar DDD

- Se modifica el proyecto: 

    * Se añade entidad album
    * La cancion tiene o no un album
    * La cancion tiene ninguno, uno o varios artistas (authors)
    * La cancion tiene una fecha de lanzamiento (release)
    * El artista tiene una lista de canciones
    * El artista tiene una lista de albums

</br>

- _Documentación del código realizada con swagger_

    * Disponible en: http://localhost:8080/swagger-ui/index.html#/
# Proyecto_Gestion_Cursos

## Descripción del Proyecto

Este proyecto de Gestión de Cursos es una plataforma web con autenticación diseñada para administrar cursos educativos donde puedes crear, editar y eliminar cursos según los permisos correspondientes que tenga el usuario. La aplicación utiliza tecnologías como Java, Spring Boot, Thymeleaf y Bootstrap para ofrecer una interfaz intuitiva y funcionalidades esenciales.

## Funcionalidades

- **Autenticación:** Permite a los usuarios registrarse y autenticarse para acceder a las funciones administrativas de los cursos.

- **Gestión de Cursos:** Los usuarios autorizados pueden ver, crear, editar y eliminar cursos.

- **Roles de Usuario:** Se implementan roles como "USER," "CREATOR," "EDITOR," y "ADMIN" para controlar el acceso a diferentes partes de la aplicación.
  
- **Funciones Avanzadas:**
    - Paginado: Navega fácilmente a través de listas extensas de cursos.
    - Búsqueda: Encuentra cursos rápidamente mediante un sistema de búsqueda.
    - Exportar a PDF: Descarga información detallada del curso en formato PDF.
    - Exportar a Excel: Exporta datos del curso a un archivo Excel para un análisis más profundo.

## Habilidades Desarrolladas

- **Java y Spring Boot:** Desarrollé la lógica del backend utilizando Java y el framework Spring Boot para gestionar la lógica de negocio y las operaciones con la base de datos.

- **Thymeleaf y Bootstrap:** Utilicé Thymeleaf como motor de plantillas para integrar la lógica de servidor con las páginas HTML, y Bootstrap para mejorar el diseño y la experiencia del usuario.

- **Seguridad con Spring Security:** Implementé Spring Security para gestionar la autenticación y autorización, asegurando las rutas y funciones de la aplicación.

- **Manejo de Sesiones:** Aprendí a manejar sesiones de usuario para mantener la autenticación durante la interacción del usuario con la aplicación.
  
- **Paginación, Búsqueda y Exportación:** Implementé funciones avanzadas para mejorar la experiencia del usuario.

- **Git y GitHub:** Utilicé Git y GitHub para controlar versiones y colaborar eficientemente en el desarrollo del proyecto.

## Cómo Clonar y Ejecutar Localmente

1. Abre git bash y clona este repositorio en tu máquina local:
` git clone https://github.com/Porico94/Proyecto_Gestion_Cursos` 

2. Abre el proyecto en tu IDE preferido (Eclipse, IntelliJ, etc.).

## Consideraciones previas

1. Modificar el archivo application.properties según software de base de datos que estes usando, tienes que configurar el puerto, nombre de tu base de datos, username, password y driver. (En este proyecto se está usando MySQL).
2. Se esta usando `spring.jpa.hibernate.ddl-auto=update` para crear automaticamente las entities (tablas en tu base de datos), al inicio estas tablas estarán vacias.
3. Se debe ingresar directamente desde el software de base datos, hacia tabla de roles, y creamos los siguientes registros en este caso estamos usando 4 roles:
   
         | role_id   | nombre  |   
         | 1         | ADMIN   |   
         | 2         | USER    |   
         | 3         | EDITOR  |   
         | 4         | CREATOR |
   
5. Se debe ingresar directamente desde el software de base datos, hacia tabla de users, y creamos el registro de admin:

         | user_id   | enabled  | password            | username        |   
         | 1         | 1        | passwordcodificado  | ADMINISTRADOR   |

6. Se debe ingresar directamente desde el software de base datos, hacia tabla de user_roles, y asignamos el rol de admin al usuario ADMINISTRADOR:

         | user_id   | role_id  |
         | 1         | 1        |
        
7. Ejecuta la clase java GestionCursosSpringbootApplication de la aplicación desde el IDE para verificar que todo este correcto.

8. Ingresa al navegador de tu preferencia e ingresa a http://localhost:8080  

## Modo de Uso

1. Inicia sesión con credenciales válidas (Inicialmente tendrás el user ADMINISTRADOR que tiene el rol ADMIN con acceso a todas las funciones).
   
2. Creacion de Usuario:
    - Por temas de seguridad el usuario creado por default el rol de CREATOR y estará habilitado para autenticarse.
    - El rol de CREATOR le permite ver la lista de cursos y crear nuevos cursos.
      
3. Explora la sección de cursos:
   
    - Crea nuevos cursos con el formulario ingresando el titulo, descripcion, nivel y estado de publicacion.
    - Accede a la sección de cursos para ver una lista completa de todos los cursos disponibles.
    - Buscar y filtrar en la lista de cursos por nombre.
    - Generar reportes en formato PDF y EXCEL de la lista de cursos.
    - Funcion de Editar y Eliminar cursos.
  
## Capturas de Pantalla

Inicio de sesión
![Captura de Pantalla 1](https://raw.githubusercontent.com/Porico94/Proyecto_Gestion_Cursos/master/Capturas%20de%20Pantalla/Inicio%20de%20Sesion.png)

Pagina de inicio después de la Autenticación
![Captura de Pantalla 2](https://raw.githubusercontent.com/Porico94/Proyecto_Gestion_Cursos/master/Capturas%20de%20Pantalla/Lista%20de%20Cursos.png)

Reporte generado en formato PDF

![Captura de Pantalla 3](https://raw.githubusercontent.com/Porico94/Proyecto_Gestion_Cursos/master/Capturas%20de%20Pantalla/Reporte%20en%20PDF.png)

Reporte generado en formato EXCEL
![Captura de Pantalla 3](https://raw.githubusercontent.com/Porico94/Proyecto_Gestion_Cursos/master/Capturas%20de%20Pantalla/Reporte%20en%20EXCEL.png)

## Tecnologías Utilizadas

  - Java
  - Spring Boot
  - Thymeleaf
  - Bootstrap
  - Spring Security

## Autor

Pool Rimari Córdova

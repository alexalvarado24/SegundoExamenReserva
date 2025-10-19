# Gestor de Reservas de Aulas ITCA

## Descripción
Este proyecto es un sistema de **gestión de reservas de aulas** para la escuela ITCA.  
Permite registrar aulas, gestionar reservas de clases, prácticas y eventos internos, evitar conflictos de horarios y generar reportes en formato CSV.

Está desarrollado en **Java consola** usando **Programación Orientada a Objetos (POO)**, implementando herencia, polimorfismo, encapsulamiento e interfaces.

---

## Contenido del proyecto

- **Paquete `model`**:  
  - `Aula.java`: representa un aula con id, nombre, tipo y capacidad.  
  - `Reserva.java`: clase abstracta base para todas las reservas.  
  - `ReservaClase.java`, `ReservaPractica.java`, `ReservaEvento.java`: tipos específicos de reservas.  
  - Excepciones personalizadas para manejo de errores.  

- **Paquete `enums`**:  
  - `TipoAula`: Establece las categorias TEORICA, LABORATORIO, AUDITORIO.  
  - `TipoEvento`: Establece las categorias CONFERENCIA, TALLER, REUNION.  

- **Paquete `interfaces`**:  
  - `Validable.java`: interfaz que obliga a las reservas a implementar un método `validar()`.  

- **Paquete `util`**:  
  - `GenerarReportes.java`: exporta reservas y genera reportes combinados en CSV.  

- **Paquete `app`**:  
  - `Main.java`: clase principal con el menú de consola para interactuar con el sistema.  

- **Carpeta `data/`**:  
  - Carpeta donde se generan los archivos CSV de reportes.  
---
## Uso del sistema

1. **Ejecutar `Main.java`**: se mostrará un menú interactivo en la consola.  
2. **Registrar aulas**: se puede agregar el nombre, tipo (selección por menú) y capacidad.  
3. **Registrar reservas**:  
   - Se selecciona el aula por ID.  
   - Se ingresan fecha, hora de inicio, hora de fin y responsable.  
   - Se elige el tipo de reserva (CLASE, PRÁCTICA, EVENTO) mediante menú.  
   - Si es EVENTO, se selecciona el tipo de evento (CONFERENCIA, TALLER, REUNIÓN).  
   - El sistema valida conflictos de horarios y reglas internas.  
4. **Listar reservas y aulas**: muestra la información registrada.  
5. **Cancelar reservas**: cambia el estado a CANCELADA.  
6. **Buscar reservas por responsable**: filtra reservas activas o históricas por nombre.  
7. **Generar reportes**:  
   - Exporta todas las reservas a un archivo CSV (`data/ReporteReservas.csv`).  
   - Incluye top 3 aulas con más horas, ocupación por tipo de aula y distribución por tipo de reserva.  

---
## Formato CSV generado
Dentro de la carpeta data se exportarán las reservas en formato CSV, Top 3 aulas con más horas reservadas, Ocupación por tipo de aula y Distribución por tipo de reserva.

---
## Ejemplo de flujo

1. Registrar aula “Laboratorio 1” tipo LABORATORIO con capacidad 30.  
2. Registrar reserva de CLASE en esa aula el 2025-10-20 de 08:00 a 10:00, responsable “Juan Pérez”.  
3. Registrar reserva de EVENTO tipo CONFERENCIA el mismo día en horario diferente.  
4. Generar reporte → se crea `data/ReporteReservas.csv` con toda la información.  

---

## Autor
Emir Alexander Alvarado Velasco

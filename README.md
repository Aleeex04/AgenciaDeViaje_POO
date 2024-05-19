# Proyecto Agencia de Viajes

Este proyecto consiste en una aplicación de Agencia de Viajes desarrollada en Java como parte de un ejercicio de Programación Orientada a Objetos.

## Descripción
La Agencia de Viajes ofrece diversas funcionalidades para la gestión de reservas de vuelos y hoteles, así como también la administración de destinos. Permite a los usuarios agregar, actualizar y eliminar destinos, así como realizar reservas de vuelos y hoteles para los clientes.

#Base de Datos
El proyecto utiliza una base de datos Mariadb, a continuacion las tablas:

- Destino
- Cliente
- Reserva
- Vuelo
- Hotel

## Clases

El proyecto se compone de varias clases que se encargan de realizar diversas funciones dentro de la aplicación:

- Clase Destino: Representa un destino turístico y contiene información sobre la ciudad, país y descripción del destino.

- Clase Cliente: Representa un cliente de la agencia y contiene información personal como nombre, apellido, DNI, dirección y correo electrónico.

- Clase Reserva: Representa una reserva de vuelo y hotel realizada por un cliente. Contiene información sobre el cliente, destino, hotel, vuelo, fecha de reserva y precio total.

- Clase Vuelo: Representa un vuelo disponible para reserva. Contiene información sobre el número de asiento, destino y precio del vuelo.

- Clase Hotel: Representa un hotel disponible para reserva. Contiene información sobre el nombre, estrellas, número de habitaciones y precio por noche del hotel.

## Test Driven Development (TDD)
Se incluyen dos simples tests para las clases AgenciaTest y GestorBD.
- AgenciaTest: Donde se testea el método agregarDestino.
- GestorBD: donde se testea mediante getters el método agregarDestino() y el método eliminarVehiculoPorId().

### Tecnologías Utilizadas
Java
JDBC (Java Database Connectivity)
JUnit (para pruebas unitarias)

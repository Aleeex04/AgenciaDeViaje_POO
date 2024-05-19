create database agenciaviaje;
use agenciaviaje;

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
                            `idCliente` int(11) NOT NULL,
                            `nombre` varchar(100) DEFAULT NULL,
                            `apellido` varchar(100) DEFAULT NULL,
                            `telefono` varchar(20) DEFAULT NULL,
                            `direccion` varchar(255) DEFAULT NULL,
                            `correoElectronico` varchar(100) DEFAULT NULL,
                            PRIMARY KEY (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

DROP TABLE IF EXISTS `Destino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Destino` (
                           `idDestino` int(11) NOT NULL,
                           `ciudad` varchar(20) DEFAULT NULL,
                           `pais` varchar(20) DEFAULT NULL,
                           `descripcion` varchar(100) DEFAULT NULL,

                           PRIMARY KEY (`idDestino`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `Vuelo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `Vuelo` (
                           `idVuelo` int(11) NOT NULL,
                           `numeroDeAsiento` int(100) DEFAULT NULL,
                           `destino` varchar(100) DEFAULT NULL,
                           `precio` double DEFAULT NULL,
                           PRIMARY KEY (`idVuelo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `Hotel` (
                         `idHotel` int(11) NOT NULL,
                         `nombre` varchar(100) DEFAULT NULL,
                         `estrellas` int(10) DEFAULT NULL,
                         `numeroHabitacion` int(100) DEFAULT NULL,
                         PRIMARY KEY (`idHotel`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `Reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reserva` (
                           `idReserva` int(11) NOT NULL AUTO_INCREMENT,
                           `idCliente` int(11) DEFAULT NULL,
                           `idDestino` int(11) DEFAULT NULL,
                           `hotel` varchar(51) DEFAULT NULL,
                           `vuelo` int(11) DEFAULT NULL,
                           `fechaReserva` date DEFAULT NULL,
                           `precioReserva` decimal(10,2) DEFAULT NULL,
                           PRIMARY KEY (`idReserva`),
                           KEY `idVehiculo` (`idDestino`),
                           KEY `idCliente` (`idCliente`),
                           CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`idDestino`) REFERENCES `destino` (`idDestino`),
                           CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

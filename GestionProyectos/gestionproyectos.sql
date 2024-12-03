-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-11-2024 a las 09:52:39
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

-- Drop and create database
DROP DATABASE IF EXISTS `gestion_proyectos`;
CREATE DATABASE `gestion_proyectos`;
USE `gestion_proyectos`;

START TRANSACTION;

-- Estructura de tabla para la tabla `clientes`
CREATE TABLE `clientes` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcado de datos para la tabla `clientes`
INSERT INTO `clientes` (`id`, `nombre`) VALUES
(2, '[paco]');

-- Estructura de tabla para la tabla `predicciones`
CREATE TABLE `predicciones` (
  `id` bigint(20) NOT NULL,
  `fecha_prediccion` timestamp NULL DEFAULT current_timestamp(),
  `probabilidad_exito` double DEFAULT NULL,
  `proyecto_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura de tabla para la tabla `proyectos`
CREATE TABLE `proyectos` (
  `id` bigint(20) NOT NULL,
  `duracion` int(11) NOT NULL,
  `fecha_fin` date DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_registro` datetime(6) DEFAULT NULL,
  `nombre_proyecto` varchar(255) DEFAULT NULL,
  `presupuesto` double DEFAULT NULL,
  `recursos_asignados` varbinary(255) DEFAULT NULL,
  `resultado` tinyint(4) DEFAULT NULL CHECK (`resultado` between 0 and 1),
  `cliente_id` bigint(20) DEFAULT NULL,
  `certificaciones_requeridas` bit(1) DEFAULT NULL,
  `entregables_oferta` varchar(255) DEFAULT NULL,
  `experiencia_requerida` varchar(255) DEFAULT NULL,
  `facturacion_anual` double DEFAULT NULL,
  `fortaleza_tecnologica` varchar(255) DEFAULT NULL,
  `idiomas` bit(1) DEFAULT NULL,
  `lugar_trabajo` varchar(255) DEFAULT NULL,
  `numero_perfiles_requeridos` int(11) NOT NULL,
  `precio_hora` double DEFAULT NULL,
  `solvencia_economica_empresa` varchar(255) DEFAULT NULL,
  `titulaciones_empleados` varchar(255) DEFAULT NULL,
  `volumetria` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura de tabla para la tabla `proyectos_tecnologias`
CREATE TABLE `proyectos_tecnologias` (
  `id_tecnologia` bigint(20) NOT NULL,
  `id_proyecto` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura de tabla para la tabla `tecnologias`
CREATE TABLE `tecnologias` (
  `id` bigint(20) NOT NULL,
  `frecuencia_uso` bigint(20) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Índices para tablas volcadas

-- Indices de la tabla `clientes`
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`);

-- Indices de la tabla `predicciones`
ALTER TABLE `predicciones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7jfrf74tfe2d1vjcyjfu3ftus` (`proyecto_id`);

-- Indices de la tabla `proyectos`
ALTER TABLE `proyectos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKov5jnq8wg4w8v6lwmil2ommnn` (`cliente_id`);

-- Indices de la tabla `proyectos_tecnologias`
ALTER TABLE `proyectos_tecnologias`
  ADD PRIMARY KEY (`id_tecnologia`,`id_proyecto`),
  ADD KEY `FKb2gtdar522ce8uu03xkqs236n` (`id_proyecto`);

-- Indices de la tabla `tecnologias`
ALTER TABLE `tecnologias`
  ADD PRIMARY KEY (`id`);

-- AUTO_INCREMENT de las tablas volcadas

-- AUTO_INCREMENT de la tabla `clientes`
ALTER TABLE `clientes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

-- AUTO_INCREMENT de la tabla `predicciones`
ALTER TABLE `predicciones`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

-- AUTO_INCREMENT de la tabla `proyectos`
ALTER TABLE `proyectos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

-- AUTO_INCREMENT de la tabla `tecnologias`
ALTER TABLE `tecnologias`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

-- Restricciones para tablas volcadas

-- Filtros para la tabla `predicciones`
ALTER TABLE `predicciones`
  ADD CONSTRAINT `FK7jfrf74tfe2d1vjcyjfu3ftus` FOREIGN KEY (`proyecto_id`) REFERENCES `proyectos` (`id`);

-- Filtros para la tabla `proyectos`
ALTER TABLE `proyectos`
  ADD CONSTRAINT `FK6ox9d0jf9jy52ha3txq9cylw9` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`);

-- Filtros para la tabla `proyectos_tecnologias`
ALTER TABLE `proyectos_tecnologias`
  ADD CONSTRAINT `FKb2gtdar522ce8uu03xkqs236n` FOREIGN KEY (`id_proyecto`) REFERENCES `proyectos` (`id`),
  ADD CONSTRAINT `FKk881gugdfv9dmc0sv7eij9gk2` FOREIGN KEY (`id_tecnologia`) REFERENCES `tecnologias` (`id`);

COMMIT;


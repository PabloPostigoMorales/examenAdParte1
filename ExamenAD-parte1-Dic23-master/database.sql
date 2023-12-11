
--
-- Base de datos: `examenad`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
   `id` bigint(20) UNSIGNED NOT NULL,
   `nombre` varchar(128) NOT NULL,
   `email` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `nombre`, `email`) VALUES
(1, 'Sara García', 'sara.garcia@gmail.com'),
(2, 'Luis Rodríguez', 'luis.rodriguez@yahoo.com'),
(3, 'Eva Martínez', 'eva.martinez@hotmail.com'),
(4, 'Alberto Sánchez', 'alberto.sanchez@gmail.com'),
(5, 'Cristina López', 'cristina.lopez@gmail.com'),
(6, 'Francisco Jiménez', 'francisco.jimenez@yahoo.com'),
(7, 'Marina Torres', 'marina.torres@hotmail.com'),
(8, 'Andrés Ruiz', 'andres.ruiz@gmail.com'),
(9, 'Laura Vargas', 'laura.vargas@yahoo.com'),
(10, 'Roberto Martín', 'roberto.martin@hotmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `garaje`
--

CREATE TABLE `garaje` (
  `id` int(11) NOT NULL,
  `matricula` varchar(8) NOT NULL,
  `modelo` varchar(32) NOT NULL,
  `fabricante` varchar(32) NOT NULL,
  `cliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `garaje`
--

INSERT INTO `garaje` (`id`, `matricula`, `modelo`, `fabricante`, `cliente`) VALUES
(1, 'ABC123', 'Camry', 'Toyota', 1),
(2, 'XYZ789', 'Escape', 'Ford', 2),
(3, 'DEF456', 'Camaro', 'Chevrolet', 5),
(4, 'GHI789', 'Golf', 'Volkswagen', 4),
(5, 'JKL012', 'Frontier', 'Nissan', 5),
(6, 'MNO345', '3 Series', 'BMW', 6),
(7, 'PQR678', 'Odyssey', 'Honda', 9),
(8, 'STU901', 'Tucson', 'Hyundai', 7),
(9, 'VWX234', 'CX-5', 'Mazda', 9),
(10, 'YZA567', 'Sprinter', 'Mercedes-Benz', 10),
(11, 'BCD345', 'RAV4', 'Toyota', 1),
(12, 'CDE678', 'Mustang', 'Ford', 1),
(13, 'EFG901', 'Silverado', 'Chevrolet', 3),
(14, 'GHI234', 'Jetta', 'Volkswagen', 4),
(15, 'IJK567', 'Pathfinder', 'Nissan', 5),
(16, 'KLM890', 'X5', 'BMW', 10),
(17, 'MNO123', 'Pilot', 'Honda', 7),
(18, 'OPQ456', 'Santa Fe', 'Hyundai', 1),
(19, 'QRS789', 'CX-9', 'Mazda', 9),
(20, 'STU012', 'GLC', 'Mercedes-Benz', 10),
(21, 'TUV345', 'Accord', 'Honda', 7),
(22, 'UVW678', 'Sonata', 'Hyundai', 5),
(23, 'VWX901', 'M6', 'BMW', 10),
(24, 'XYZ234', 'Civic', 'Honda', 7),
(25, 'YZA567', 'F-150', 'Ford', 2),
(26, 'ZAB890', 'Sorento', 'Kia', 6),
(27, 'BCD123', 'Mazda6', 'Mazda', 9),
(28, 'CDE456', 'CLA', 'Mercedes-Benz', 10),
(29, 'DEF789', 'Malibu', 'Chevrolet', 3),
(30, 'EFG012', 'Tiguan', 'Volkswagen', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trabajador`
--

CREATE TABLE `trabajador` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `nombre` varchar(64) NOT NULL,
  `dni` varchar(16) NOT NULL,
  `desde` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `trabajador`
--

INSERT INTO `trabajador` (`id`, `nombre`, `dni`, `desde`) VALUES
(1, 'Juan Pérez', '1234567890', '2014-01-15'),
(2, 'María García', '2345678901', '2021-02-15'),
(3, 'Carlos López', '3456789012', '2003-03-10'),
(4, 'Ana Rodríguez', '4567890123', '2023-04-05'),
(5, 'Pedro Martínez', '5678901234', '2013-05-20'),
(6, 'Laura Sánchez', '6789012345', '2013-06-15'),
(7, 'Miguel Torres', '7890123456', '2003-07-01'),
(8, 'Elena Gómez', '8901234567', '2022-08-10'),
(9, 'Sergio Herrera', '9012345678', '2023-09-05'),
(10, 'Carmen Jiménez', '0123456789', '2023-10-15');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
    ADD UNIQUE KEY `id` (`id`);

--
-- Indices de la tabla `garaje`
--
ALTER TABLE `garaje`
    ADD PRIMARY KEY (`id`),
  ADD KEY `cliente` (`cliente`);

--
-- Indices de la tabla `trabajador`
--
ALTER TABLE `trabajador`
    ADD UNIQUE KEY `id` (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
    MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `garaje`
--
ALTER TABLE `garaje`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de la tabla `trabajador`
--
ALTER TABLE `trabajador`
    MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
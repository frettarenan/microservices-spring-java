CREATE TABLE `cidade` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `id_estado` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cidade_id_estado_fk` (`id_estado`),
  CONSTRAINT `cidade_id_estado_fk` FOREIGN KEY (`id_estado`) REFERENCES `estado` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
DROP TABLE IF EXISTS `author`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `author` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `name` varchar(255) NOT NULL,
                          `second_name` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `book`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `book` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `author_id` bigint(20) NOT NULL,
                        `name` varchar(255) NOT NULL,
                        PRIMARY KEY (`id`),
                        KEY `fk_autor_id_idx` (`author_id`),
                        CONSTRAINT `fk_autor_id` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema paseodb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `paseodb` ;

-- -----------------------------------------------------
-- Schema paseodb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `paseodb` DEFAULT CHARACTER SET utf8 ;
USE `paseodb` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gender`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gender` ;

CREATE TABLE IF NOT EXISTS `gender` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `gender` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `first_name` VARCHAR(100) NULL,
  `last_name` VARCHAR(45) NULL,
  `birthdate` DATE NULL,
  `description` TEXT NULL,
  `enabled` TINYINT NULL,
  `role` VARCHAR(45) NULL,
  `profile_image_url` VARCHAR(2000) NULL,
  `address_id` INT NULL,
  `gender_id` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_user_address1_idx` (`address_id` ASC),
  INDEX `fk_user_gender1_idx` (`gender_id` ASC),
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_gender1`
    FOREIGN KEY (`gender_id`)
    REFERENCES `gender` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `walk_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `walk_type` ;

CREATE TABLE IF NOT EXISTS `walk_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `walk_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `walk_category` ;

CREATE TABLE IF NOT EXISTS `walk_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `walk_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `walk_location` ;

CREATE TABLE IF NOT EXISTS `walk_location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` TEXT NULL,
  `address_id` INT NOT NULL,
  `image_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_location_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_location_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `walk`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `walk` ;

CREATE TABLE IF NOT EXISTS `walk` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NULL,
  `date` DATE NULL,
  `description` TEXT NULL,
  `walk_category_id` INT NOT NULL,
  `walk_type_id` INT NOT NULL,
  `location_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `privacy` TINYINT NULL,
  `start_time` TIME NULL,
  `end_time` TIME NULL,
  `main_image_url` VARCHAR(2000) NULL,
  `enabled` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_walk_walk_category1_idx` (`walk_category_id` ASC),
  INDEX `fk_walk_walk_type1_idx` (`walk_type_id` ASC),
  INDEX `fk_walk_location1_idx` (`location_id` ASC),
  INDEX `fk_walk_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_walk_walk_category1`
    FOREIGN KEY (`walk_category_id`)
    REFERENCES `walk_category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_walk_walk_type1`
    FOREIGN KEY (`walk_type_id`)
    REFERENCES `walk_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_walk_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `walk_location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_walk_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `message` ;

CREATE TABLE IF NOT EXISTS `message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `contents` TEXT NOT NULL,
  `date_sent` DATETIME NULL,
  `sender` INT NOT NULL,
  `receiver` INT NOT NULL,
  `seen` TINYINT NULL,
  `enabled` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_message_user1_idx` (`sender` ASC),
  INDEX `fk_message_user2_idx` (`receiver` ASC),
  CONSTRAINT `fk_message_user1`
    FOREIGN KEY (`sender`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_message_user2`
    FOREIGN KEY (`receiver`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `walk_image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `walk_image` ;

CREATE TABLE IF NOT EXISTS `walk_image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `image_url` VARCHAR(2000) NULL,
  `walk_id` INT NOT NULL,
  `description` TEXT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_walk_image_walk1_idx` (`walk_id` ASC),
  INDEX `fk_walk_image_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_walk_image_walk1`
    FOREIGN KEY (`walk_id`)
    REFERENCES `walk` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_walk_image_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_walk`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_walk` ;

CREATE TABLE IF NOT EXISTS `user_walk` (
  `description` VARCHAR(250) NULL,
  `user_id` INT NOT NULL,
  `rating` INT NULL,
  `walk_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `walk_id`),
  INDEX `fk_walk_rating_user1_idx` (`user_id` ASC),
  INDEX `fk_walk_rating_walk1_idx` (`walk_id` ASC),
  CONSTRAINT `fk_walk_rating_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_walk_rating_walk1`
    FOREIGN KEY (`walk_id`)
    REFERENCES `walk` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `followed_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `followed_user` ;

CREATE TABLE IF NOT EXISTS `followed_user` (
  `user_id` INT NOT NULL,
  `followed_user_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `followed_user_id`),
  INDEX `fk_friend_list_user2_idx` (`followed_user_id` ASC),
  CONSTRAINT `fk_friend_list_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_friend_list_user2`
    FOREIGN KEY (`followed_user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `preferred_gender`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `preferred_gender` ;

CREATE TABLE IF NOT EXISTS `preferred_gender` (
  `user_id` INT NOT NULL,
  `gender_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `gender_id`),
  INDEX `fk_user_has_gender_gender1_idx` (`gender_id` ASC),
  INDEX `fk_user_has_gender_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_gender_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_gender_gender1`
    FOREIGN KEY (`gender_id`)
    REFERENCES `gender` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `preferred_walk_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `preferred_walk_location` ;

CREATE TABLE IF NOT EXISTS `preferred_walk_location` (
  `user_id` INT NOT NULL,
  `walk_location_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `walk_location_id`),
  INDEX `fk_user_has_walk_location_walk_location1_idx` (`walk_location_id` ASC),
  INDEX `fk_user_has_walk_location_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_walk_location_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_walk_location_walk_location1`
    FOREIGN KEY (`walk_location_id`)
    REFERENCES `walk_location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `preferred_walk_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `preferred_walk_type` ;

CREATE TABLE IF NOT EXISTS `preferred_walk_type` (
  `user_id` INT NOT NULL,
  `walk_type_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `walk_type_id`),
  INDEX `fk_user_has_walk_type_walk_type1_idx` (`walk_type_id` ASC),
  INDEX `fk_user_has_walk_type_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_walk_type_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_walk_type_walk_type1`
    FOREIGN KEY (`walk_type_id`)
    REFERENCES `walk_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `preferred_walk_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `preferred_walk_category` ;

CREATE TABLE IF NOT EXISTS `preferred_walk_category` (
  `user_id` INT NOT NULL,
  `walk_category_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `walk_category_id`),
  INDEX `fk_user_has_walk_category_walk_category1_idx` (`walk_category_id` ASC),
  INDEX `fk_user_has_walk_category_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_walk_category_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_walk_category_walk_category1`
    FOREIGN KEY (`walk_category_id`)
    REFERENCES `walk_category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS paseo@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'paseo'@'localhost' IDENTIFIED BY 'paseo';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'paseo'@'localhost';
SET SQL_MODE = '';
DROP USER IF EXISTS paseo;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'paseo' IDENTIFIED BY 'paseo';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`) VALUES (1, '123 street', 'la mirada', 'CA', '123456');

COMMIT;


-- -----------------------------------------------------
-- Data for table `gender`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `gender` (`id`, `gender`) VALUES (1, 'female');
INSERT INTO `gender` (`id`, `gender`) VALUES (2, 'male');
INSERT INTO `gender` (`id`, `gender`) VALUES (3, 'any');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `birthdate`, `description`, `enabled`, `role`, `profile_image_url`, `address_id`, `gender_id`) VALUES (1, 'admin', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'Bob', 'hope', NULL, NULL, 1, NULL, NULL, NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `walk_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `walk_type` (`id`, `name`, `description`) VALUES (1, 'stroll', NULL);
INSERT INTO `walk_type` (`id`, `name`, `description`) VALUES (2, 'dog_walking', NULL);
INSERT INTO `walk_type` (`id`, `name`, `description`) VALUES (3, 'speed_walking', NULL);
INSERT INTO `walk_type` (`id`, `name`, `description`) VALUES (4, 'any', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `walk_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `walk_category` (`id`, `name`, `description`) VALUES (1, 'city walking', NULL);
INSERT INTO `walk_category` (`id`, `name`, `description`) VALUES (2, 'park', NULL);
INSERT INTO `walk_category` (`id`, `name`, `description`) VALUES (3, 'nature', NULL);
INSERT INTO `walk_category` (`id`, `name`, `description`) VALUES (4, 'mall walking', NULL);
INSERT INTO `walk_category` (`id`, `name`, `description`) VALUES (5, 'tracks', NULL);
INSERT INTO `walk_category` (`id`, `name`, `description`) VALUES (6, 'trails', NULL);
INSERT INTO `walk_category` (`id`, `name`, `description`) VALUES (7, 'any', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `walk_location`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `walk_location` (`id`, `name`, `description`, `address_id`, `image_url`) VALUES (1, 'Sunside Park', 'Sunny grassy knoll. ', 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `walk`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `walk` (`id`, `name`, `date`, `description`, `walk_category_id`, `walk_type_id`, `location_id`, `user_id`, `privacy`, `start_time`, `end_time`, `main_image_url`, `enabled`) VALUES (1, 'Fun in the sun', '2023-01-12', 'This was an amazing walk! ', 1, 1, 1, 1, 1, NULL, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `message`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `message` (`id`, `contents`, `date_sent`, `sender`, `receiver`, `seen`, `enabled`) VALUES (1, 'Let\'s go for a walk! ', '2023-12-01', 1, 1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `walk_image`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `walk_image` (`id`, `image_url`, `walk_id`, `description`, `user_id`) VALUES (1, 'https://pullmanchamber.com/wp-content/uploads/2015/04/Parks11.jpg', 1, NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_walk`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `user_walk` (`description`, `user_id`, `rating`, `walk_id`) VALUES ('This is a description', 1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `followed_user`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `followed_user` (`user_id`, `followed_user_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `preferred_gender`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `preferred_gender` (`user_id`, `gender_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `preferred_walk_location`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `preferred_walk_location` (`user_id`, `walk_location_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `preferred_walk_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `preferred_walk_type` (`user_id`, `walk_type_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `preferred_walk_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `preferred_walk_category` (`user_id`, `walk_category_id`) VALUES (1, 1);

COMMIT;


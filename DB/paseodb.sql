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
-- Table `location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `location` ;

CREATE TABLE IF NOT EXISTS `location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `state` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `description` VARCHAR(100) NULL,
  `locationcol` VARCHAR(45) NULL,
  `walk_user_id` INT NOT NULL,
  `walk_location_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `profile_img`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `profile_img` ;

CREATE TABLE IF NOT EXISTS `profile_img` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `img_Url` VARCHAR(45) NULL,
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
  `gender` VARCHAR(45) NULL,
  `age` INT NULL,
  `description` VARCHAR(250) NULL,
  `enabled` TINYINT NULL,
  `role` VARCHAR(45) NULL,
  `location_id` INT NULL,
  `location_walk_location_id1` INT NULL,
  `location_walk_user_id1` INT NULL,
  `profile_img_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_location1_idx` (`location_id` ASC),
  INDEX `fk_user_profile_img1_idx` (`profile_img_id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  CONSTRAINT `fk_user_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_profile_img1`
    FOREIGN KEY (`profile_img_id`)
    REFERENCES `profile_img` (`id`)
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
  `descripion` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `walk_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `walk_category` ;

CREATE TABLE IF NOT EXISTS `walk_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `location_categoriescol` VARCHAR(45) NULL,
  `description` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `preference`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `preference` ;

CREATE TABLE IF NOT EXISTS `preference` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `gender` VARCHAR(45) NULL,
  `description` VARCHAR(100) NULL,
  `user_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_preference_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_preference_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
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
  `description` VARCHAR(200) NULL,
  `walk_category_id` INT NOT NULL,
  `walk_type_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_walk_walk_category1_idx` (`walk_category_id` ASC),
  INDEX `fk_walk_walk_type1_idx` (`walk_type_id` ASC),
  CONSTRAINT `fk_walk_walk_category1`
    FOREIGN KEY (`walk_category_id`)
    REFERENCES `walk_category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_walk_walk_type1`
    FOREIGN KEY (`walk_type_id`)
    REFERENCES `walk_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_has_walk`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_walk` ;

CREATE TABLE IF NOT EXISTS `user_has_walk` (
  `user_id` INT NOT NULL,
  `user_setting_id` VARCHAR(45) NOT NULL,
  `user_level_id` VARCHAR(45) NOT NULL,
  `user_interest_id` VARCHAR(45) NOT NULL,
  `user_location_category_id` VARCHAR(100) NOT NULL,
  `walk_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `user_setting_id`, `user_level_id`, `user_interest_id`, `user_location_category_id`, `walk_id`),
  INDEX `fk_user_has_walk_user_idx` (`user_id` ASC, `user_setting_id` ASC, `user_level_id` ASC, `user_interest_id` ASC, `user_location_category_id` ASC),
  CONSTRAINT `fk_user_has_walk_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `walk_type_has_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `walk_type_has_user` ;

CREATE TABLE IF NOT EXISTS `walk_type_has_user` (
  `walk_type_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `user_walk_type_id` VARCHAR(45) NOT NULL,
  `user_preference_id` VARCHAR(45) NOT NULL,
  `user_interest_id` VARCHAR(45) NOT NULL,
  `user_location_category_id` VARCHAR(100) NOT NULL,
  `user_walk_location_id` INT NOT NULL,
  PRIMARY KEY (`walk_type_id`, `user_id`, `user_walk_type_id`, `user_preference_id`, `user_interest_id`, `user_location_category_id`, `user_walk_location_id`),
  INDEX `fk_walk_type_has_user_user1_idx` (`user_id` ASC, `user_walk_type_id` ASC, `user_preference_id` ASC, `user_interest_id` ASC, `user_location_category_id` ASC, `user_walk_location_id` ASC),
  INDEX `fk_walk_type_has_user_walk_type1_idx` (`walk_type_id` ASC),
  CONSTRAINT `fk_walk_type_has_user_walk_type1`
    FOREIGN KEY (`walk_type_id`)
    REFERENCES `walk_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_walk_type_has_user_user1`
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
  `contents` VARCHAR(500) NOT NULL,
  `date_sent` DATETIME NULL,
  `user_id1` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_message_user1_idx` (`user_id1` ASC),
  CONSTRAINT `fk_message_user1`
    FOREIGN KEY (`user_id1`)
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
  `img_Url` VARCHAR(45) NULL,
  `walk_id` INT NOT NULL,
  `walk_walk_category_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_walk_image_walk1_idx` (`walk_id` ASC),
  CONSTRAINT `fk_walk_image_walk1`
    FOREIGN KEY (`walk_id`)
    REFERENCES `walk` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `walk_rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `walk_rating` ;

CREATE TABLE IF NOT EXISTS `walk_rating` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(250) NULL,
  `walk_id` INT NOT NULL,
  `walk_walk_category_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`, `walk_id`, `walk_walk_category_id`, `user_id`),
  INDEX `fk_walk_rating_walk1_idx` (`walk_id` ASC),
  INDEX `fk_walk_rating_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_walk_rating_walk1`
    FOREIGN KEY (`walk_id`)
    REFERENCES `walk` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_walk_rating_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
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

SET SQL_MODE = '';
DROP USER IF EXISTS paseo@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'paseo'@'localhost' IDENTIFIED BY 'paseo';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'paseo'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `gender`, `age`, `description`, `enabled`, `role`, `location_id`, `location_walk_location_id1`, `location_walk_user_id1`, `profile_img_id`) VALUES (1, 'admin', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'Bob', 'hope', 'M', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `walk_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `walk_type` (`id`, `name`, `descripion`) VALUES (1, 'stroll', NULL);
INSERT INTO `walk_type` (`id`, `name`, `descripion`) VALUES (2, 'dog_walking', NULL);
INSERT INTO `walk_type` (`id`, `name`, `descripion`) VALUES (3, 'speed_walking', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `walk_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `walk_category` (`id`, `name`, `location_categoriescol`, `description`) VALUES (1, 'city walking', NULL, NULL);
INSERT INTO `walk_category` (`id`, `name`, `location_categoriescol`, `description`) VALUES (2, 'park', NULL, NULL);
INSERT INTO `walk_category` (`id`, `name`, `location_categoriescol`, `description`) VALUES (3, 'nature', NULL, NULL);
INSERT INTO `walk_category` (`id`, `name`, `location_categoriescol`, `description`) VALUES (4, 'mall walking', NULL, NULL);
INSERT INTO `walk_category` (`id`, `name`, `location_categoriescol`, `description`) VALUES (5, 'tracks', NULL, NULL);
INSERT INTO `walk_category` (`id`, `name`, `location_categoriescol`, `description`) VALUES (6, 'trails', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `preference`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `preference` (`id`, `gender`, `description`, `user_id`) VALUES (1, 'women', NULL, NULL);
INSERT INTO `preference` (`id`, `gender`, `description`, `user_id`) VALUES (2, 'men', NULL, NULL);
INSERT INTO `preference` (`id`, `gender`, `description`, `user_id`) VALUES (3, 'family', NULL, NULL);

COMMIT;


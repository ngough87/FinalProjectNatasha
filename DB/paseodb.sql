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
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `birthdate`, `description`, `enabled`, `role`, `profile_image_url`, `address_id`, `gender_id`) VALUES (1, 'admin', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'Bob', 'hope', '1988-02-15', NULL, 1, 'admin', 'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&w=1600', 1, 1);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `birthdate`, `description`, `enabled`, `role`, `profile_image_url`, `address_id`, `gender_id`) VALUES (2, 'walkerpuffy', 'password1', 'Johnson', 'Parker', '2000-04-14', 'I love to run. It keeps me in shape. ', 1, 'standard', 'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&w=1600', 1, 2);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `birthdate`, `description`, `enabled`, `role`, `profile_image_url`, `address_id`, `gender_id`) VALUES (3, 'walkerlongterm', 'password2', 'Omer', 'Washington', '1974-04-15', 'Walking is cool', 1, 'standard', 'https://images.pexels.com/photos/697509/pexels-photo-697509.jpeg?auto=compress&cs=tinysrgb&w=1600', 1, 1);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `birthdate`, `description`, `enabled`, `role`, `profile_image_url`, `address_id`, `gender_id`) VALUES (4, 'walkerstriking', 'password3', 'Dion', 'Cervantes', '1983-08-09', 'Find me on sunday', 1, 'standard', 'https://images.pexels.com/photos/1040881/pexels-photo-1040881.jpeg?auto=compress&cs=tinysrgb&w=1600', 1, 2);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `birthdate`, `description`, `enabled`, `role`, `profile_image_url`, `address_id`, `gender_id`) VALUES (5, 'walkervivid', 'password4', 'Sylvia', 'Owen', '1997-05-17', 'Meow Mix', 1, 'standard', 'https://images.pexels.com/photos/1542085/pexels-photo-1542085.jpeg?auto=compress&cs=tinysrgb&w=1600', 1, 1);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `birthdate`, `description`, `enabled`, `role`, `profile_image_url`, `address_id`, `gender_id`) VALUES (6, 'walkerhardtofind', 'password5', 'Bradly', 'Howe', '1998-01-26', 'Walk', 1, 'standard', 'https://images.pexels.com/photos/1040880/pexels-photo-1040880.jpeg?auto=compress&cs=tinysrgb&w=1600', 1, 2);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `birthdate`, `description`, `enabled`, `role`, `profile_image_url`, `address_id`, `gender_id`) VALUES (7, 'walkerprime', 'password6', 'Janet', 'Poole', '1971-07-13', 'Yay walk with me', 1, 'standard', 'https://images.pexels.com/photos/774909/pexels-photo-774909.jpeg?auto=compress&cs=tinysrgb&w=800', 1, 1);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `birthdate`, `description`, `enabled`, `role`, `profile_image_url`, `address_id`, `gender_id`) VALUES (8, 'chivalrouswalker', 'password7', 'Hattie', 'Stephens', '1980-07-30', 'I\'m excited', 1, 'standard', 'https://www.pexels.com/photo/woman-sitting-on-escalator-15173983/', 1, 2);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `birthdate`, `description`, `enabled`, `role`, `profile_image_url`, `address_id`, `gender_id`) VALUES (9, 'walkerinformal', 'password8', 'Shaun', 'Hogan', '2000-01-19', 'Let\'s go', 1, 'standard', 'https://images.pexels.com/photos/5601771/pexels-photo-5601771.jpeg?auto=compress&cs=tinysrgb&w=800', 1, 1);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `birthdate`, `description`, `enabled`, `role`, `profile_image_url`, `address_id`, `gender_id`) VALUES (10, 'runwigeon', 'password9', 'Stacy', 'Fox', '1987-06-13', 'Walk with me', 1, 'standard', 'https://images.pexels.com/photos/15422002/pexels-photo-15422002.jpeg?auto=compress&cs=tinysrgb&w=800', 1, 2);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `birthdate`, `description`, `enabled`, `role`, `profile_image_url`, `address_id`, `gender_id`) VALUES (11, 'woodchuckrun', 'password10', 'Hung', 'Huff', '1999-03-20', 'I love to run but meeting people is cool too', 1, 'standard', 'https://images.pexels.com/photos/15422002/pexels-photo-15422002.jpeg?auto=compress&cs=tinysrgb&w=800', 1, 1);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `birthdate`, `description`, `enabled`, `role`, `profile_image_url`, `address_id`, `gender_id`) VALUES (12, 'runyak', 'password11', 'Yong', 'Ashley', '1980-09-06', 'Wait wait, do you mind if we walk and eat', 1, 'standard', 'https://www.pexels.com/photo/topless-man-in-black-shorts-running-on-the-brown-sand-6280358/', 1, 2);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `birthdate`, `description`, `enabled`, `role`, `profile_image_url`, `address_id`, `gender_id`) VALUES (13, 'ploverrun', 'password12', 'Nelda', 'Walsh', '1992-12-30', 'Coffee and walk in the city is Jam', 1, 'standard', 'https://images.pexels.com/photos/2346018/pexels-photo-2346018.jpeg?auto=compress&cs=tinysrgb&w=800', 1, 1);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `birthdate`, `description`, `enabled`, `role`, `profile_image_url`, `address_id`, `gender_id`) VALUES (14, 'jackrabbitrun', 'password13', 'Blanca', 'Black', '2001-03-13', 'I can\'t do cold weather but hit me up when it\'s warmer', 1, 'standard', 'https://images.pexels.com/photos/9789981/pexels-photo-9789981.jpeg?auto=compress&cs=tinysrgb&w=600', 1, 2);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `birthdate`, `description`, `enabled`, `role`, `profile_image_url`, `address_id`, `gender_id`) VALUES (15, 'barracudarun', 'password14', 'Trent', 'Cooke', '1992-04-10', 'Hello, hi there.', 1, 'standard', 'https://images.pexels.com/photos/5749775/pexels-photo-5749775.jpeg?auto=compress&cs=tinysrgb&w=600', 1, 1);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `birthdate`, `description`, `enabled`, `role`, `profile_image_url`, `address_id`, `gender_id`) VALUES (16, 'molerun', 'password15', 'Jerome', 'Burke', '2000-10-08', 'Walk baby', 1, 'standard', 'https://images.pexels.com/photos/3932863/pexels-photo-3932863.jpeg?auto=compress&cs=tinysrgb&w=600', 1, 2);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `birthdate`, `description`, `enabled`, `role`, `profile_image_url`, `address_id`, `gender_id`) VALUES (17, 'crocodilerun', 'password16', 'Jordon', 'Blanchard', '2003-09-22', 'Walk walk ', 1, 'standard', 'https://images.pexels.com/photos/1612847/pexels-photo-1612847.jpeg?auto=compress&cs=tinysrgb&w=600', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `walk_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `walk_type` (`id`, `name`, `description`) VALUES (1, 'stroll', NULL);
INSERT INTO `walk_type` (`id`, `name`, `description`) VALUES (2, 'dog walking', NULL);
INSERT INTO `walk_type` (`id`, `name`, `description`) VALUES (3, 'speed walking', NULL);
INSERT INTO `walk_type` (`id`, `name`, `description`) VALUES (4, 'hiking', NULL);
INSERT INTO `walk_type` (`id`, `name`, `description`) VALUES (5, 'HIIT', NULL);
INSERT INTO `walk_type` (`id`, `name`, `description`) VALUES (6, 'leisure', NULL);

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

COMMIT;


-- -----------------------------------------------------
-- Data for table `walk_location`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `walk_location` (`id`, `name`, `description`, `address_id`, `image_url`) VALUES (1, 'Sunside Park', 'Sunny grassy knoll. ', 1, 'https://media.istockphoto.com/id/841278554/photo/beautiful-morning-light-in-public-park-with-green-grass-field.jpg?s=612x612&w=0&k=20&c=rXOM3Uq9kPbpM5IWnCAnffHOP8KKpVKCJDMuNBlTNls=');
INSERT INTO `walk_location` (`id`, `name`, `description`, `address_id`, `image_url`) VALUES (2, 'Capital park', 'Wide open park', 1, 'https://www.shutterstock.com/image-photo/panorama-beautiful-city-park-260nw-523559155.jpg');
INSERT INTO `walk_location` (`id`, `name`, `description`, `address_id`, `image_url`) VALUES (3, 'Hellmand trail', 'steep inclines, great views! ', 1, 'https://nobodyhikesinla.files.wordpress.com/2011/10/hiking-2011-10-311.jpg');
INSERT INTO `walk_location` (`id`, `name`, `description`, `address_id`, `image_url`) VALUES (4, 'Fontanelle Forest', 'dense forest, great for bird watching', 1, 'https://forestoration.com/wp-content/uploads/2019/05/IMG_8420.jpg');

COMMIT;


-- -----------------------------------------------------
-- Data for table `walk`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `walk` (`id`, `name`, `date`, `description`, `walk_category_id`, `walk_type_id`, `location_id`, `user_id`, `privacy`, `start_time`, `end_time`, `main_image_url`, `enabled`) VALUES (1, 'Fun in the sun', '2023-01-12', 'This was an amazing walk! ', 1, 1, 1, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `walk` (`id`, `name`, `date`, `description`, `walk_category_id`, `walk_type_id`, `location_id`, `user_id`, `privacy`, `start_time`, `end_time`, `main_image_url`, `enabled`) VALUES (2, 'Fontanelle Hike', '2023-01-02', 'Fun hike in Fontanell forest! ', 6, 2, 1, 2, 1, NULL, NULL, NULL, NULL);
INSERT INTO `walk` (`id`, `name`, `date`, `description`, `walk_category_id`, `walk_type_id`, `location_id`, `user_id`, `privacy`, `start_time`, `end_time`, `main_image_url`, `enabled`) VALUES (3, 'Boardwalk stroll', '2023-02-15', 'Leisurely walk on the river front boardwalk.', 1, 3, 1, 3, 0, NULL, NULL, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQqTzkXa34umOS9CuQG-HjIKrKsD09vsWHCw2-GEzyQ2ijWqM3--QHYDvXjksUan3hqV80&usqp=CAU', NULL);
INSERT INTO `walk` (`id`, `name`, `date`, `description`, `walk_category_id`, `walk_type_id`, `location_id`, `user_id`, `privacy`, `start_time`, `end_time`, `main_image_url`, `enabled`) VALUES (4, 'Around the block', '2023-02-08', 'Walk with the dogs to the park.', 2, 4, 1, 4, 0, NULL, NULL, 'https://www.papillion.org/ImageRepository/Document?documentID=3643', NULL);
INSERT INTO `walk` (`id`, `name`, `date`, `description`, `walk_category_id`, `walk_type_id`, `location_id`, `user_id`, `privacy`, `start_time`, `end_time`, `main_image_url`, `enabled`) VALUES (5, 'Hellmand hills', '2023-01-05', 'Tough hike up hellman trails!', 6, 5, 1, 2, 0, NULL, NULL, 'https://fastly.4sqi.net/img/general/600x600/eI0qe3KZh6s3RR8mkUzsIz5VEzJss2mise1sVnb-0zo.jpg', NULL);
INSERT INTO `walk` (`id`, `name`, `date`, `description`, `walk_category_id`, `walk_type_id`, `location_id`, `user_id`, `privacy`, `start_time`, `end_time`, `main_image_url`, `enabled`) VALUES (6, 'Walk around the track', '2023-01-15', '5 miles walking in circles', 5, 2, 1, 1, 1, NULL, NULL, 'https://www.dynamicsportsconstruction.com/wp-content/uploads/2016/03/outdoor-track.jpg', NULL);
INSERT INTO `walk` (`id`, `name`, `date`, `description`, `walk_category_id`, `walk_type_id`, `location_id`, `user_id`, `privacy`, `start_time`, `end_time`, `main_image_url`, `enabled`) VALUES (7, 'Strollers and dogs', '2023-02-14', 'Walk on railroad trail with kids', 6, 2, 1, 3, 1, NULL, NULL, 'https://newengland.com/wp-content/uploads/Boardwalk-scaled.jpg', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `message`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `message` (`id`, `contents`, `date_sent`, `sender`, `receiver`, `seen`, `enabled`) VALUES (1, 'Let\'s go for a walk! ', '2023-12-01', 1, 1, 1, 1);
INSERT INTO `message` (`id`, `contents`, `date_sent`, `sender`, `receiver`, `seen`, `enabled`) VALUES (2, 'Wow what an amazing idea!', '2023-01-05', 2, 3, 2, 1);
INSERT INTO `message` (`id`, `contents`, `date_sent`, `sender`, `receiver`, `seen`, `enabled`) VALUES (3, 'Let\'s go on a walk!', '2023-05-01', 1, 2, 1, 1);
INSERT INTO `message` (`id`, `contents`, `date_sent`, `sender`, `receiver`, `seen`, `enabled`) VALUES (4, 'Want to meet for a walk?', '2023-01-02', 3, 1, 0, 1);
INSERT INTO `message` (`id`, `contents`, `date_sent`, `sender`, `receiver`, `seen`, `enabled`) VALUES (5, 'Hi!', '2023-02-01', 1, 2, 0, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `walk_image`
-- -----------------------------------------------------
START TRANSACTION;
USE `paseodb`;
INSERT INTO `walk_image` (`id`, `image_url`, `walk_id`, `description`, `user_id`) VALUES (1, 'https://pullmanchamber.com/wp-content/uploads/2015/04/Parks11.jpg', 1, NULL, 1);
INSERT INTO `walk_image` (`id`, `image_url`, `walk_id`, `description`, `user_id`) VALUES (2, 'https://forestoration.com/wp-content/uploads/2019/05/IMG_8420.jpg', 2, NULL, 2);
INSERT INTO `walk_image` (`id`, `image_url`, `walk_id`, `description`, `user_id`) VALUES (3, 'https://kubrick.htvapps.com/htv-prod-media.s3.amazonaws.com/images/2-forbidden-forest-at-fontenelle-1572456166.jpg?crop=1.00xw:0.846xh;0,0.154xh&resize=1200:*', 2, NULL, 2);
INSERT INTO `walk_image` (`id`, `image_url`, `walk_id`, `description`, `user_id`) VALUES (4, 'https://nobodyhikesinla.files.wordpress.com/2011/10/hiking-2011-10-311.jpg', 3, NULL, 1);

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
INSERT INTO `followed_user` (`user_id`, `followed_user_id`) VALUES (1, 2);

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


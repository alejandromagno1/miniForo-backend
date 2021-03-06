-- MySQL Script generated by MySQL Workbench
-- Sat Nov 13 15:54:10 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema dbforo
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dbforo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dbforo` DEFAULT CHARACTER SET utf8 ;
USE `dbforo` ;

-- -----------------------------------------------------
-- Table `dbforo`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbforo`.`users` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `gender` VARCHAR(1) NOT NULL,
  `state` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`idUser`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbforo`.`posts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbforo`.`posts` (
  `idPost` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `body` LONGTEXT NOT NULL,
  `state` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`idPost`),
  INDEX `id_post` (`idPost` ASC) VISIBLE,
  INDEX `idUser_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `idUser`
    FOREIGN KEY (`userId`)
    REFERENCES `dbforo`.`users` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbforo`.`comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbforo`.`comments` (
  `idComm` INT NOT NULL AUTO_INCREMENT,
  `postId` INT NOT NULL,
  `userId` INT NOT NULL,
  `body` LONGTEXT NOT NULL,
  `state` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`idComm`),
  INDEX `IdPost_idx` (`postId` ASC) VISIBLE,
  INDEX `IdUser_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `idPostComm`
    FOREIGN KEY (`postId`)
    REFERENCES `dbforo`.`posts` (`idPost`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idUserComm`
    FOREIGN KEY (`userId`)
    REFERENCES `dbforo`.`users` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

/*******************************************************************************
Description: Schema creation script (database creation)
Author: Andres Felipe Villamizar Collazos
Date 12-03-2024
*******************************************************************************/

-- Database creation
CREATE DATABASE IF NOT EXISTS pokemon_app;
USE pokemon_app;

-- Table that stores user data
CREATE TABLE IF NOT EXISTS `user` (
    `user_id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(45) NOT NULL,
    `last_name` VARCHAR(45) NOT NULL,
    `password_hash` VARCHAR(100) NOT NULL,
    `phone_number` VARCHAR(20) NOT NULL,
    `email` VARCHAR(255) NOT NULL UNIQUE,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

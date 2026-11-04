CREATE DATABASE IF NOT EXISTS fitness_tracker;
USE fitness_tracker;

CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL      -- SHA-256 hash (64 chars)
);

CREATE TABLE IF NOT EXISTS workouts (
    workout_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    duration INT NOT NULL,
    calories_burned DOUBLE NOT NULL,
    logged_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS meals (
    meal_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    calories DOUBLE NOT NULL,
    nutritional_info VARCHAR(255),
    logged_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS goals (
    user_id INT PRIMARY KEY,
    daily_calorie_intake DOUBLE NOT NULL DEFAULT 0,
    daily_calorie_burn DOUBLE NOT NULL DEFAULT 0,
    weekly_workout_minutes INT NOT NULL DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
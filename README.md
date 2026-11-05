# Fitness Tracker Application

A desktop fitness tracker built with Java Swing for midterm project. Users can log workouts and meals, set fitness goals, and view their progress.

## Project Status

About 50% done as of November 6, 2026.

- Done: GUI of all pages, MySQL database and connection, model classes
- In progress: logic behind the screens (login, register, saving and loading workouts, goals progress, reports)

## Features

- Login and Register
- Main Dashboard with sidebar navigation
- Workouts: log name, duration, and calories burned
- Meals: log name, calories, and nutritional info
- Goals: daily calorie intake, daily calorie burn, weekly workout minutes
- Progress and report summary
- Account settings: change name, username, and password

## Data Structures and Algorithms

- Workouts and meals are stored in arrays
- Meals can be sorted by calories, name, or nutritional info
- Meals can be searched by name using binary search
- Workout search by ID (binary search) is still in progress

## Built With

- Java (JDK 24), Swing (null layout)
- Maven, NetBeans
- MySQL through WampServer
- mysql-connector-j 9.7.0 (the only external library)

## Project Structure

Package: `com.mycompany.fitnesstrackerapp`

| Package | Contents |
| --- | --- |
| `db` | DatabaseConnection |
| `model` | Workout, Meal, User |
| `auth` | AuthService |
| `profile` | UserProfile (profile and goals) |
| `workout` | WorkoutManager |
| `meal` | MealManager |
| `report` | ReportGenerator |
| `ui` | All screens |

## How to Run

1. Install WampServer and start all services.
2. Open phpMyAdmin and import `schema.sql` (it is in the `FITNESSTRACKERAPP` folder).
3. Open the `FITNESSTRACKERAPP` folder in NetBeans (JDK 24 or higher).
4. Clean and Build, then run `FITNESSTRACKERAPP.java`.

The app connects to MySQL on `localhost:3306` as `root` with no password. Database name: `fitness_tracker`.

## Group Members
| Member | Part |
| --- | --- |
| Noel Dickenson | GUI and Main Dashboard |
| Alexa Olaes | Workout Management |
| Sharmaine Legasto | Meals and Nutrition |
| King Aruta | User Profile and Fitness Goals |
| Marvs Marfil | Reports, Integration, Database |


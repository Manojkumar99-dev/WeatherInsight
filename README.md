# 🌦️ Weather Insight

A modern Android Weather application built using Kotlin, Jetpack Compose, MVVM, Clean Architecture, Hilt, Room and Retrofit.

## Features

- 🌤 Current Weather
- 🔍 Search by City
- 🕒 Hourly Forecast
- 📅 7-Day Forecast
- 💾 Offline Cache (Room)
- 🔄 Pull to Refresh
- ⚡ Background Sync (WorkManager)
- 🎨 Dynamic Weather Theme
- ⚠ Weather Alerts

## Architecture

MVVM + Clean Architecture

Presentation
↓
ViewModel
↓
UseCase
↓
Repository
↓
Room + Retrofit

## Tech Stack

- Kotlin
- Jetpack Compose
- MVVM
- Clean Architecture
- Hilt
- Retrofit
- Room
- Coroutines
- Flow
- WorkManager
- Coil
- Material 3

## Project Structure

core/
data/
domain/
di/
presentation/

## API

https://www.weatherapi.com/

## Setup

1. Clone the repository.
2. Add your WeatherAPI key to `local.properties`:
3. Sync Gradle.
4. Run the app.

## Future Improvements

- Unit Tests
- UI Tests
- Current Location
- Favorite Cities
- Weather Notifications

## Author

**Manojkumar P**
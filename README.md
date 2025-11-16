# Game of Thrones Characters App

A modern Android application that displays information about Game of Thrones characters. The app allows users to browse and search for characters from the popular TV series.

![App Screenshot](https://via.placeholder.com/800x400.png?text=Game+of+Thrones+Characters+App)

## Features

- Display a list of Game of Thrones characters
- View character details including:
  - Name
  - Gender
  - Culture
  - Death information (if applicable)
  - Seasons appeared in
- Search functionality to filter characters by name
- Error handling with user-friendly messages

## Architecture

The app follows the MVVM (Model-View-ViewModel) architecture pattern with a clean separation of concerns:

- **UI Layer**: Jetpack Compose UI components
- **ViewModel Layer**: Manages UI state and business logic
- **Business Layer**: Contains use cases that encapsulate business rules
- **Data Layer**: Handles data operations and API communication

### Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/gotcharacters/
│   │   │   ├── core/
│   │   │   │   └── di/           # Core dependency injection
│   │   │   ├── home/
│   │   │   │   ├── business/     # Business logic and use cases
│   │   │   │   ├── data/         # Data sources and repositories
│   │   │   │   ├── di/           # Feature-specific DI
│   │   │   │   └── ui/           # UI components
│   │   │   └── GOTCharactersApplication.kt
│   │   └── res/                  # Resources
│   └── test/                     # Unit tests
└── build.gradle.kts
```

## Technologies Used

- **Kotlin**: Primary programming language
- **Jetpack Compose**: Modern UI toolkit for building native UI
- **Coroutines & Flow**: For asynchronous programming
- **Hilt**: Dependency injection
- **Retrofit & OkHttp**: Network communication
- **Gson**: JSON parsing
- **JUnit & MockK**: Unit testing

## Getting Started

### Prerequisites

- Android Studio Arctic Fox or newer
- Android SDK 21 or higher
- Kotlin 1.5.0 or higher

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/GOTCharactersApp.git
   ```

2. Open the project in Android Studio

3. Build and run the app on an emulator or physical device

## Testing

The app includes unit tests for all layers:
- Business logic tests
- Repository tests
- ViewModel tests

Run tests using:
```bash
./gradlew test
```

## SSH Configuration

If you encounter the error "git@github.com: Permission denied (publickey)" when trying to access this repository, please refer to [README_SSH_FIX.md](README_SSH_FIX.md) for detailed instructions on how to fix the SSH host key verification issue.

## Future Enhancements

- **Character Details Page**: Implement a detailed view for each character using NavigableListDetailPaneScaffold
- **Loading Indicators**: Add progress bars and shimmer effects for better UX
- **Offline Support**: Implement caching with Room database
- **UI Improvements**: Enhanced themes and custom logo
- **Additional Testing**: Add UI tests with Espresso or Robolectric

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- [An API of Ice And Fire](https://anapioficeandfire.com/) for providing the Game of Thrones data
- The Game of Thrones TV series and books for the inspiration

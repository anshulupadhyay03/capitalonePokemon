# PokemonApp

A simple Android application that fetches and displays a list of Pokémon using the PokeAPI. Users can view a list of Pokémon names and tap on one to see detailed information including the Pokémon's name, image, and height.

## Technical Decisions

- **Architecture**: Implemented MVVM pattern for clear separation of concerns, making the app maintainable and testable.
- **Framework**: Used Jetpack Compose for modern, declarative UI development, aligning with Android's latest recommendations.
- **Dependency Injection**: Utilized Hilt for its simplicity and integration with Android components.
- **Image Loading**: Chose Coil for its Kotlin-first approach and seamless integration with Compose.
- **Networking**: Used Retrofit with Gson for robust API handling and data parsing.
- **Testing**: Included unit tests for the repository layer using MockK for mocking dependencies, and a Compose UI test to verify list rendering and click handling.
- **UI Design**: Followed Material 3 guidelines for a modern, clean look with responsive layouts.

## What's Missing

- **Pagination**: Due to time constraints, the app loads a fixed number of Pokémon (20). In a production app, I'd implement pagination using Jetpack Paging 3 to load more Pokémon as the user scrolls.
- **Error Handling**: Basic error handling is implemented, but a more robust solution with retry mechanisms and user-friendly error messages could be added.
- **Caching**: No offline caching is implemented. Room database integration would enable offline support.
- **Additional Tests**: More comprehensive unit tests (e.g., for ViewModel) and additional UI tests could be added for better coverage.

## Additional Notes

- The app assumes a stable internet connection for API calls.
- The UI is kept simple but follows Material Design principles for a polished look.
- The project is structured to be easily extensible for additional features like search or filtering.

To run the app:
1. Clone the repository.
2. Open in Android Studio.
3. Sync the project with Gradle.
4. Run on an emulator or device (minSdk 24).

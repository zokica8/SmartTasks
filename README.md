Smart Tasks App

Application written purely in Kotlin, and libraries used are also purely written in Kotlin. Modern UI framework Jetpack Compose is used to build the UI of the app.

3rd party libraries used:

Dependency injection - koin
API request - ktor and kotlinx serialization for serializing JSON
UI - Jetpack Compose

With regards to project structure, the code is structured with Clean Architecture in mind, where components of the app are separated into 3 main parts: 

1. presentation - UI is communicating with the ViewModel to update changes via Actions from the user and the State object to observe the UI state
2. domain - where business logic lies, what does the application do, with abstractions
3. data - where data is getting picked up from the outside sources

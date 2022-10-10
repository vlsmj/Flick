# 10072022-van-laurence-javier-android

## Flick
Flick is a movie search application that uses MVVM + Repository + Room with the help of iTunes public API for the movie database.

## Features
- Display featured movie on top of the landing screen.
- Display lists of movies horizontally.
- Add or remove a movie from favorites.
- Show list of favorite movies.
- Dynamic favorite movies status. If star is filled or not after adding or removing.
- Search movies that are also not saved yet in the local database and add can be added to favorites.
- Video player that plays the trailer of the movies.
- Placeholder of thumbnails if the images are not loaded properly.
- Show date and time toast message when the user last visited.
- Animated JSON icon file for the splash screen.

## Visuals
![Alt Text](https://media.giphy.com/media/WMc5xFplouDUFW5A1f/giphy.gif) ![Alt Text](https://media.giphy.com/media/P4XH8oBXUud2gUxSJT/giphy.gif) ![Alt Text](https://media.giphy.com/media/nt3AzkNVNYsgBzE1nD/giphy.gif) 
![Alt Text](https://media.giphy.com/media/HjyzYBDbb78FVdF6Ie/giphy.gif) ![Alt Text](https://media.giphy.com/media/DHIxYmKg7Jzu6KiKHo/giphy.gif) ![Alt Text](https://media.giphy.com/media/gMVDx8uSo5263FNQgj/giphy.gif)

## Architecture
The architecture or design pattern for this application is MVVM. I chose MVVM because it is much easier to maintain and it separates concerns effectively. I also implemented Clean Architecture with use cases that I can reuse 
in ViewModels. The difficulty I have been experiencing when using MVVM is it boilerplate your codes, but the advantages outweigh that.

## Persistence
Flick uses Room as the single source of truth thus it can still work as long as the functionalities do not need internet connectivity. I chose Room because it fits perfectly for my needs in the MVVM + Repository pattern.

## Libraries & Tools
- Jetpack Compose
- Kotlin
- MVVM
- Kotlin Coroutines
- Flows
- Repository
- Retrofit
- OkHttp
- Clean Architecture
- Use Case
- Room
- Hilt
- Lottie
- Coil
- JsonToKotlinClass
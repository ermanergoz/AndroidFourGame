# Android Four Game

Android Four Game is a two-player game that is played on a board consists of nxn fields, where each field contains a value between 0 and 4. Initially, all the fields contain the value of 0. If a player chooses a field, then the value of the field and its neighbours are incremented by one if the value is less than 4. The player’s score represents how many fields are made to have the value of 4. If a value of a field reaches to 4, then the field is colorized with the color of the player (red or blue). When all fields reaches to the value of 4, the game ends.

<p float="center">
	<img src="https://github.com/ermanergoz/AndroidFourGame/blob/master/resources/ss1.png" height="500">
	<img src="https://github.com/ermanergoz/AndroidFourGame/blob/master/resources/ss2.png" height="500">
</p>

## Build & Run

- Clone git repository:

	```sh
	git clone https://github.com/ermanergoz/AndroidFourGame.git
	```

- Import the project into Android Studio:
	- Open Android Studio. After that Click on “Open an existing Android Studio project”. If Android Studio is already open, click on File and then Click on Open.
	- Select the location of the cloned repository and then Click OK.
	- After Gradle operations are finished, the application will be ready to run.

## Techniques & Dependencies

- [MVVM architecture](https://developer.android.com/jetpack/guide)
- [Data binding](https://developer.android.com/topic/libraries/data-binding)
- [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
- [Koin](https://insert-koin.io/)
- [Custom View](https://developer.android.com/training/custom-views/create-view)

## Meta

Yusuf Erman ERGÖZ – erman.ergoz@gmail.com

Distributed under the MIT license. See ``LICENSE`` for more information.

[https://github.com/ermanergoz](https://github.com/ermanergoz)

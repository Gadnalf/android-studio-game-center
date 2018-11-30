# CS Project
https://markus.teach.cs.toronto.edu/git/csc207-2018-09-reg/group_0684

### GAME #1: Sliding Tiles
- The goal of this game is to exchange the blank tiles with its adjacent tiles to arrange the tiles in order.
- Once the game is solved the game will be automatically resetted.
- The available board sizes are 3x3, 4x4, and 5x5 which are freely chosen by an user.
- The exchange of the blank tiles with adjacent tiles are done by tapping onto the adjacent tiles (to be swapped) to the blank tiles.
- Scores are updated based on the time and the number of moves made. Lesser moves made and lesser times spent to solve the puzzle will earn the user a better score.
- The game with the highest score will be recorded on the scoreboard. 

### GAME #2: Sea Invaders
- The goal of this game is to shoot the invaders (squid) to prevent them from reaching the bottom of the screen. 
- The only available board size is 5x5 board.
- The user is able to choose the seconds before the new invaders are spawn to the board. 
- The shooting can be done by tapping onto the ship.
- The user will notice that, in the column where the ship is placed, an invader will disappear (shot and dead) from the bottom every time the ship is tapped.
- The user obtains a higher score by resisting and fending off the invaders for a longer duration.
- The game with the highest score will be recorded on the scoreboard. 

### GAME #3: Alphabet Tiles 
- The goal of this game is to stack the alphabet tiles to reach the K tile. Yet, if the player wishes to continue on with the game, he/she may do so upto the Z tile. 
- If no more moves can be made, the game is over and resets.
- Once the Z tile is reached the game will automatically reset.  
- A player of this game can swipe up, down, left or right.
- The swipping on the board will stack the same tiles together and slide all the tiles in the direction of the swipe.
- The available board sizes are 4x4, 5x5, 6x6 which are freely chosen by an user.
- Scores are updated based on the available tiles on the board only. The alphabets worth powers of two. later the alphabets the users reach, better the scores (by an exponential increase) the users will earn. 
- Only the higheset scored game will be recorded on the scoreboard. 
- This game was inspired by the existing game 2048. 

### lifting up the application
1. pull the master
2. open android studio, open existing android studio project
  - open from the GameCentre folder
3. Build -> clean
    - build is a top option in android studio
4. Build -> rebuild
5. Gradle sync
6. hit play

### Possible null point error
- If the null point exception error occur, wipe out the data from AVD manager.
- How to wipe out:
    - Tools -> AVD Manager -> Downward Arrow on the right side -> Wipe Data

### Testing the application
- run all the tests in the test folder
    - right click on testing folder, select run all tests with coverage
- to evaluate coverage: 
    - order by class% in coverage
    - double click on fall2018, and continue clicking through to get the coverage in each of our classes
        - fall2018 -> csc2017 -> GameCentre

#### scoreboard activity tests
- uncomment lines 13 and 14 from GameScoreBoardActivity
    - and check it populates with sliding tiles scores
    - start off sea invaders, wait for the board to fill, check that user and game score board populates

**note: if the application does not lift, please alarm the rest of the group immediately**

### Note
- be sure anything that needs to be saved implements Serializable like BoardManager does
- to print stuff see MovementContoller

### Testing
- you can inherit from TestingGameLaunch to put your tests
- please kick off ScoreBoardTest after changes you've made

## Scoreboard
- takes user, game
- uses static variables to store top games per game and per player
- assumes User and Game will store these max scores

#### GameActivity and Starting Activity
- both now kick off the game launch center and get the boardmanager from that

#### Board Manager
- if the game is finished, the first time this is called the scores are updated and stored
- Altered BoardManager to take ScoreBoard as param
    - only class passing a param to this was StartingActivity
    - Altered Starting Activity to require an input boardManager passed to it

### troubleshooting
- Build -> clean
- Build -> rebuild
- double check your AndroidManifest.xml which is located in: Phase1/GameCentre/app/src/main
    - I've had some weird issues with this not versioning in git, and also it being a git tracked file, which is weird
    - should look like the following:

```{java}
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="fall2018.csc2017.GameCentre">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="GameCentre"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.AppCompat.Light.NoActionBar">
        <activity android:name="fall2018.csc2017.GameCentre.StartingActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity android:name="fall2018.csc2017.GameCentre.GameActivity"></activity>
    </application>

</manifest>
```


### work allocations
phillip: app or account
Sam: board manager
John: board manager
scoreboard: me, also game launch center and timer functionality
  - rename game centre


### renaming folder
- renaming package: http://mobileandroidappdevelopment.blogspot.com/2015/11/how-to-rename-android-studio-project.html
- rename folder from finder. then open project in android studio
    - clean project
    - build project

### when making a new activity
- be sure to select GameCentre not slidingtiles

### resources
- google docs: https://drive.google.com/drive/folders/1XiT48jNzuS5Zl_41no0xJYRhyoBdAgU2
- repo: 
- git tutorial: https://www.atlassian.com/git/tutorials/comparing-workflows/feature-branch-workflow
- vscode: 
    - use for viewing conflicts

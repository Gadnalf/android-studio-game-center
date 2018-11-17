# CS Project
https://markus.teach.cs.toronto.edu/git/csc207-2018-09-reg/group_0684

### lifting up the application
1. pull the master
2. open android studio, open existing android studio project
  - open from the GameCentre folder
3. Build -> clean
    - build is a top option in android studio
4. Build -> rebuild
5. Gradle sync
6. hit play

### Testing the application
- run all the tests in the test folder
- uncomment lines 13 and 14 from UserScoreBoardActivity and GameScoreBoardActivity

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
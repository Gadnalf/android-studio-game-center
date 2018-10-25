# CS Project

### lifting up the application
1. pull the master
2. open android studio, open existing android studio project
  - open from the GameCentre folder
3. Build -> clean
    - build is a top option in android studio
4. Build -> rebuild
5. Gradle sync
6. hit play

### Note
- be sure anything that needs to be saved implements Serializable like BoardManager does
- to print stuff see MovementContoller

### Testing
- you can inherit from TestingGameLaunch to put your tests
- please kick off ScoreBoardTest after changes you've made

### Scoreboard
- takes user, game, and boardmanager
- from board manager it pulls the number of moves
- uses static variables to store top games per game and per player
- assumes User and Game will store these max scores

### troubleshooting
- Build -> clean
- Build -> rebuild


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

### resources
- google docs: https://drive.google.com/drive/folders/1XiT48jNzuS5Zl_41no0xJYRhyoBdAgU2
- repo: 
- git tutorial: https://www.atlassian.com/git/tutorials/comparing-workflows/feature-branch-workflow
- vscode: 
    - use for viewing conflicts
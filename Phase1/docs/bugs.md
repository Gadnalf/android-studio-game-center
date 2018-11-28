
- no password -> username already in user
    - doesnt work
- double blank square

### killing the invaders
- weird edge case where we cant kill the top left corner invader
- also we cant seem to fire after moving to the further LHS of the board

### scoreboard
- doesnt seem to sync up with the invaders

### fixed bugs
- app crashes when trying to load a game from a new user
    - seems to be working now?
- crashes from scoreboard tests now

### invaders
- game is now resetting
    - but the 6 is moving to random places?
    - we added notify observers to set tiles?
        - dont think would cause this
- after you reset the game, the 6 stops firing when you click on it?
group_0521

# Documentation files (README, TEAM, etc.)

- Generally, very nice commit messages! We could usually tell why the commit happened. It’s clear you made a good effort on your commit messages. Keep up the good work!

- We noticed that some members such as "Hokyun" had very few significant commits compared to others (like "bryparker"). This is going to be a larger issue in Phase 2, and might dramatically affect the mark. Please make sure that everyone on the team is contributing equally.

- You had a README.md file, and it fairly clearly described how to set up the project. However, you didn't give any instructions on how to play your game. For Phase 2, please update this file to describe the steps necessary to clone your project and play your games.

- You forgot to include TEAM.md in your repository. We had to find it through a link in your README.md which is not the right place for this file to be! However, nice job on the meeting notes! Keep that up. You might go into a bit more detail about why design decisions were made, and even perhaps the alternatives.



# Functionality

- Logically speaking, authentication step must be prior to game play! In your game, one can play the game without authentication!

- Proper message is not shown when attempting to login with a non-existing username and password!

- Your autosave feature worked well. Good job on that! 

- There is something wrong with the undo feature, after undoing until no more undos are available, the game keeps changing the puzzle!


# Design and code quality

- Not enough comments! Please try to write good explanatory comments for phase 2! 

- Java naming conventions on point! Good job!

- The types of variables should be as high in the inheritance hierarchy as possible, so "private HashMap<String, Long> max_scores = new HashMap<>();" in "Account" class type should be Map, not HashMap. That allows you to change the object type without having to change any other code.

- For Phase 2, make sure to write Javadoc for every method, including code you borrowed from the web.

- Some code smells are found in your code (like duplicate code...)! Please refactor them for phase 2! 


# Bonus features

- 1% Unlimited undo

Overall grade: 6.1/10



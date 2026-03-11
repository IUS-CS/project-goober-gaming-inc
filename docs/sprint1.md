# Sprint Ceremony Minutes

Date: 2026-02-25

Members present: 

* Tynan Anfield

* Brielle Dickens

* Andy Gomez

* Ronalyn Gonzalez

* Sara Nguyen

## Demonstration

This sprint, we completed:

**1.** The prototype of the project of which included 3 classes: 


**Character.java class** - stores the HP (max at 10) and a dead status, can deal random damage between 
1 through 3, heal for 3 HP without exceeding 10, and take damage that lowers HP and makes the player 
as dead when HP reaches 0. 

**Enemy.java class** - has HP capped at 10 and an isDead flag, can perform basic attack (1-3 damage) or 
a heavy attack (4-5 damage), can take damage and die at 0 HP, and includes a random action that chooses
an attack with the heavy attack occurring at most 1/3 of the time. 

**GameHandler.java class** - runs the game loop by creating a player and enemy, handling user input 
for actions, applying attacks and healing, showing HP each turn, and ending the game when either of the
character dies. 

**2.** Successful testing of the prototype.

**3.** The required documentations including organization.md, proposal.md, README.md, LICENSE file, AUTHORS file,
and the .gitignore file. 

## Reflection

**What worked best** 

**a.** Discord app for communicating within the team. 

**b.** The project tab helped each team breakdown tasks into smaller manageable tasks.

**c.** Clear understanding of what each member should be doing in order to contribute.  

**What can we improve on**

**a.** Communicate when a team member is stuck on a task. 

**b.** Finish tasks so testing can be done in a timely manner. 

**Response to issues/comments posted**

We addressed the concern about uneven stats by agreeing that whenever someone gets stuck on a task, they will communicate openly with the group so another team member can step in and help. 

## Operational Commitments

As a team, we will communicate clearly, divide work fairly, support each other, and deliver a well-designed Java console game on time. We'll combine our strengths, share our knowledge, defeat bugs together, and complete the mission of building a fully playable Java turn-based console game. 

## Upcoming Sprint Planning:

**1 . Create Room Class** 

**2. Create RoomHandler Class**

**3. Update GameHandler Class**

**4. Write sprint.md**

**4. Write designpatterns.md**

**5. Test New Design** 

**6. Document results**






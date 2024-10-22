Contributors:

Alec Burch-DeWitt 

Timothy Smith

James Ramsey

Alex Murray

Summary: This is a Dungeons and Dragons Loot Generation Program that allows users to type in how many items they would like to generate.
The program then pulls items from the [5th edition API](https://api.open5e.com/)  and displays the information of the items generated to the user.
The user can generate again which will clear the current contents of the table and display all new items.
The user can also refresh the API to update it to the most current version of the API if needed. :)

Test Build Instructions:
Tests need to be ran twice. The first time to generate files from the API then again to actual test against them.

Build Instructions:
Navigate to Gradle's task directory and find the "run" task under the "application" subdirectory.
Double-click to run this task.
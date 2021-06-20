
# Todo List Android App
An android app built with Kotlin that uses Room Database to store the todos on user's device and uses Recycler View to display the todos.

To clone the repo run the following command from cmd/terminal:

`git clone https://github.com/PlacementRush/Todo-List-App.git`
OR you can download the zip file of this project.



<u><b>Note</b></u>: 
- If you want to test the room database, remove `suspend` keyword from all the methods in the `TodoDao` interface in the `TodoDao.kt` file. Then run the `TodoDatabaseTest.kt` file to test the Room Database.
- Before running the application, ensure that the `suspend` keyword is added to all the methods in the `TodoDao` interface except for `getAllTodos()` method.

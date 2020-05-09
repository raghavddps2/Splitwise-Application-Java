## Steps to run the project:

>The project follows an MVC(Model View Controller) pattern. The Beans(Package) deals with the Necessary classes required for OOP Usage in the application.

>The dao(Data Access Objects) basically handle the connection with the database and other DB Interaction.

>The servlets package which basically has the routing and the application Logic code.**

    1. Run the database.sql file in your mysql database shell to set up the initial tables

        i)The sql file creates the database as well. So, you have no need to change the db.

        ii) You can specify the username and the password for the JDBC Connection in the file DBConnection.java under the dao(Data Access Objects) package.

    2. Here is the flow of the application:

        i) User creates an account.

        ii) He/she logins to the account.

        iii) User creates a group and gets an invite code to share with other members.

        iv) Now, create two or (more) user accounts who can join the group the code.

        v) Once done, start adding expenses from all the accounts

        vi) You can see the current transaction logs and the group details anytime.

        vii) If you wish to see the current distribution, click on settle up, and it will show the current split.

        viii) As a feature, it is a condition that only the owner of the group can settle up the bills, the settle up button is disabled for all the other members of the group.

    3. The expense splitting algorithm (Crux of application)  is under the settleUp.java servlet and is currently an O(mn) approach, where m and n are the number of members who should pay and who must be paid respectively.

    4. Important routes while running the project:

        i) Index: http://localhost:8080/Project-3/index

        ii) Register:http://localhost:8080/Project-3/register

        iii)Login: http://localhost:8080/Project-3/login

        iv) Profile: http://localhost:8080/Project-3/profile

        v) GroupCreation: http://localhost:8080/Project-3/createGroup

        vi) JoinGroup: http://localhost:8080/Project-3/joinGroup

        vii) GroupDetails: http://localhost:8080/Project-3/groupDetails?groupId=YOUR_OWN_GROUP_ID

        viii)Add Expense: http://localhost:8080/Project-3/addTransaction?groupId=YOUR_OWN_GROUP_ID

        ix)SettleUp: http://localhost:8080/Project-3/settleUp?groupId=YOUR_OWN_GROUP_ID&groupOwner=GROUP_OWNER

        x)Logout: http://localhost:8080/Project-3/logout


    5. Project Inspiration: Splitwise/SettleUp


    6. Implemented Features:

            i) One code groupJoin
            ii) Split Expense
            iii) Split Expense according to members involved in expense too.
            iv) Owner can only settleUp the group.
            v) All the transaction log and groupDetails(Members/owners etc)available for reference.

    7. Drawbacks and Future Implementations:

        i) Poor GUI (The graphical user interface needs development with proper buttons to route user accordingly) and the within application routing is not user friendly.

        ii) Login functionality uses sessions, and sometimes session invalidates but session attributes are not lost - To be fixed. (So, for current purposes, either login again(which changes the session attributes), or use different browsers.)

        iii) Algorithmic Approach to money split can be improved and can be brought down to O(max(m,n)), where m and n are as specified above.

        iv) Debug print statements present:
            The code contains a lot of print statement, that were written while developing the application, they have not been removed yet.
    
    8. Contributors: 
            Rohit Nagraj (1MS17IS094)
            Raghav Maheshwari (1MS17IS086)

    

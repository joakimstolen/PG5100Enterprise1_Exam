# README for PG5100 Enterprise Programming 1

### How to run
1. Install Maven dependencies: `mvn install`
2. Run all tests `mvn clean verify`
3. To launch app: go to frontend module and run LocalApplicationRunner
4. Visit `localhost:8080`


###Notes
This website lets a user signup and play a game of Gacha. When a user is signed up s/he is given 3 free lootboxes and 700 "coins". If a user is not logged in/signed up they will have the ability to see the complete list of items they can get through the Gacha game. They also get the ability to search for a given name or a given price. When a user is signed up they can go to their user page and see their currency and their available lootboxes. They get the option to open a lootbox or purchase a lootbox. If they purchase a lootbox a new lootbox is added to their available lootboxes and 100 coins are withdrawn from their currency. If they open one of the lootboxes they obtained through signing up or purchasing, they will get 1 new item that will get stored in a list on the same page. If they get a duplicate item it will stack, and they can easily seehow many of each item they have. You can also at any time mill an item and get the value of the item added to your currency.  

### Coverage
All test do run.
- Backend: 90% coverage
- Frontend: 87% coverage 
- Total: 90% coverage

###Functionality
####R1: 
- [x] 3 JPA entities: Copy, Users, Item
- [x] 3 @Service classes: CopyService, UserService, ItemService
- [x] create user
- [x] create item
- [x] redeem lootbox with obtained lootbox
- [x] buy lootbox for currency
- [x] mill copy of item for currency
- [x] Using flyway to initialize

###R2:
- [x] Write integration tests for each of the @Service classes using JUnit and @SpringBootTest annotation
- [x] Code coverage: 90%

###R3:
- [x] Homepage: display the n items that exists in the game.
- [x] User login/signup page, based on Spring Security and storing of user info on the SQL database.
- [x]  It should be possible to logout from any of the pages (e.g., via a button).
- [x] When a login/signup fails, you MUST show an error message.
- [x] Own collection page: for a logged-in user show his/her collection of items.
- [x] Provide a way to redeem a loot-box. Provide a way to sell/mill an item. Provide a way to buy a new loot-box with in-game currency. Available loot-boxes and in-game currency should be displayed as well in this page
- [x] fake test data
- [x] accesible at localhost:8080

###R4:
- [x] For each web page, implement a corresponding Page Object.
- [x] testDisplayHomePage
- [x] testEmptyCollection
- [x] testRedeemLootBox
- [x] testFailedReedemLootBox
- [x] testBuyLootbox
- [x] Total code coverage: 90%


### Shortcomings
- I did not manage to get the mill-test working in the frontend

###Extra
- Added a searchfield where a user can search through all items by name or by value
- Added a check if password is set blank when signing up
                                                                                                    
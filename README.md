# Productivity Java Spring Boot Backend

## Objective
- Create a scaleable Relational Database and REST API for a Productivity Progressive Web Application (PWA)
- The app will start with a to-do list, recipe card storage, and shopping list capabilities
- Database will be structured in such a way that further addons or plugins can be implemented so that this can become an all-in-one productivity app

## MVP
- Create table for users with full CRUD
    - Users need the following columns:
        - userid (unique)
        - username (unique)
        - password
        - email (optional)
        - fname
        - lname
    - Users need to be assigned a role
    - Users should be able to create tasks (todo items) that are categorized
    - Allow users to create a new recipe - adding quantities and items to recipe card
    - Allow users to subscribe to the reciepes of other users (a relationship allowing for 
      always-up-to-date info from the original author)
      
- Roles
    - Initially there will be two roles of 'user' and 'admin'
    - New accounts default to 'user' and require an admin to grant other roles if necessary
    - A User can have many roles
    
- Tasks
    - Tasks will have a name, a boolean stating if it's been completed, and an associated category
    - A User can have many roles
    
- Categories
    - A category will just have a name, and tasks will be added to that category.
    - Categories may be used more via stretch goals.
    
- Recipes
    - Recipes will contain a description as well as detailed information of items and quantities 
      needed to complete the recipe.
    - Items can be selected from items table, but can be created from a recipe if the 
      appropriate item does not yet exist in database
    - Recipes will have a connection to the user via tha authorid field
    - A User can create many recipes, and a Recipe can only have one author
    
- Recipe Subscriptions
    - Users can subscribe to many recipes
    - Recipes can have many subscribers
      
- Items & Recipe Items
    - Items can be any shopping item. They will have an item name and a price.
    - Optionally, they 
      can have a URL to an online store if the creator of the Item has a specific product they 
      would like to share with their recipe.

- Shopping List (UserItems)
    - User should be able to populate a shopping list from a recipe cart using all the 
      associated Items
    - User will be suggested a quantity, but will be able to edit the quantity.

## Stretch
- Add a moderator role that admins can grant users to remove or edit flagged inappropriate content
- Implement Google API to suggest pricing when adding an item to a recipe card
- Create password reset service if user has email
- add more productivity plugins to app (fitness tracking, sleep tracking, calendar integration, 
  etc.)
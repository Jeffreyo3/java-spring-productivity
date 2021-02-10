package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.*;
import com.jeffreyorndorff.productivity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userrepo;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public User findUserById(long id) {
        return userrepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User " + id + " Not Found"));
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();

        userrepo.findAll().iterator().forEachRemaining(list::add);

        return list;
    }

    @Override
    public User findByUsername(String username) {
        User user = userrepo.findByUsernameIgnoringCase(username);
        if (user == null) {
            throw new EntityNotFoundException("User " + username + " Not Found");
        }

        return user;
    }

    @Override
    public List<User> findByNameContaining(String substring) {
        return userrepo.findByUsernameContainingIgnoringCase(substring.toLowerCase());
    }

    @Transactional
    @Override
    public User save(User user) {

        User newUser = new User();

        // keeps auto-generated id from incrementing even tho model has 'unique = true' constraint
        User existingUser = userrepo.findByUsernameIgnoringCase(user.getUsername());

        if (existingUser != null) {
            throw new ValidationException("User " + user.getUsername() + " has already been taken");
        }

        if (user.getUserid() != 0) {
            findUserById(user.getUserid());
            newUser.setUserid(user.getUserid());
        }

        newUser.setUsername(user.getUsername().toLowerCase());
        newUser.setPassword(user.getPassword());
        newUser.setFname(user.getFname());
        newUser.setLname(user.getLname());
        newUser.setEmail(user.getEmail());

        newUser.getRoles()
                .clear();
        for (UserRole ur : user.getRoles()) {
            Role addRole = roleService.findRoleById(ur.getRole().getRoleid());
            newUser.getRoles().add(new UserRole(newUser, addRole));
        }


        newUser.getItems()
                .clear();
        for (UserItem ui : user.getItems()) {
            if (ui.getQuantity() <= 0) {
                throw new ValidationException("Quantity must be greater than zero");
            }
            Item addItem = itemService.findItemById(ui.getItem().getItemid());
            newUser.getItems().add(new UserItem(newUser, addItem, ui.getQuantity(), ui.getNotes()));
        }

        newUser.getTasks()
                .clear();
        for (Task t : user.getTasks()) {
            Category addCategory =
                    categoryService.findCategoryById(t.getCategory().getCategoryid());
            newUser.getTasks().add(new Task(newUser, t.getTask(), addCategory));
        }

        newUser.getRecipes()
                .clear();
        for (Recipe r : user.getRecipes()) {
            Recipe newRecipe = new Recipe(newUser, r.getRecipe(), r.getInstructions());
            newRecipe.getItems()
                    .clear();

            for (RecipeItem ri : r.getItems()) {
                Item newItem = itemService.findItemById(ri.getItem().getItemid());
                newRecipe.getItems().add(new RecipeItem(newRecipe, newItem, ri.getQuantity(),
                        ri.getMeasurement()));
            }

            newUser.getRecipes().add(newRecipe);
        }

        newUser.getSubscribedRecipes()
                .clear();
        for (UserSubscribedRecipe usr : user.getSubscribedRecipes()) {
            Recipe newRecipe = recipeService.findRecipeById(usr.getRecipe().getRecipeid());
            newUser.getSubscribedRecipes().add(new UserSubscribedRecipe(newUser, newRecipe));
        }

        return userrepo.save(newUser);
    }

    @Override
    public User update(User user, long id) {
        User userToUpdate = findUserById(id);

        if (user.getUsername() != null) {
            User existingUser = userrepo.findByUsernameIgnoringCase(user.getUsername());
            if (existingUser != null && existingUser.getUserid() != id) {
                throw new ValidationException("Username " + user.getUsername() + " has already " +
                        "been taken");
            }

            userToUpdate.setUsername(user.getUsername());
        }

        if (user.getPassword() != null) {
            userToUpdate.setPassword(user.getPassword());
        }

        if (user.getEmail() != null) {
            userToUpdate.setEmail(user.getEmail());
        }

        if (user.getFname() != null) {
            userToUpdate.setFname(user.getFname());
        }

        if (user.getLname() != null) {
            userToUpdate.setLname(user.getLname());
        }

        if (user.getRoles().size() > 0) {
            userToUpdate.getRoles()
                    .clear();
            for (UserRole ur : user.getRoles()) {
                Role addRole = roleService.findRoleById(ur.getRole().getRoleid());
                userToUpdate.getRoles().add(new UserRole(userToUpdate, addRole));
            }
        }


        if (user.getItems().size() > 0) {
            userToUpdate.getItems()
                    .clear();
            for (UserItem ui : user.getItems()) {
                if (ui.getQuantity() <= 0) {
                    throw new ValidationException("Quantity must be greater than zero");
                }
                Item addItem = itemService.findItemById(ui.getItem().getItemid());
                userToUpdate.getItems().add(new UserItem(userToUpdate, addItem, ui.getQuantity(), ui.getNotes()));
            }
        }

        // TODO: explore why the following code increments userid by 4 on save
        if (user.getTasks().size() > 0) {
            userToUpdate.getTasks()
                    .clear();
            for (Task t : user.getTasks()) {
                Category addCategory =
                        categoryService.findCategoryById(t.getCategory().getCategoryid());
                userToUpdate.getTasks().add(new Task(userToUpdate, t.getTask(), addCategory));
            }
        }
        //

        // TODO: explore why the following code increments userid by 3 on save
        if (user.getRecipes().size() > 0) {
            userToUpdate.getRecipes()
                    .clear();
            for (Recipe r : user.getRecipes()) {
                Recipe newRecipe = new Recipe(userToUpdate, r.getRecipe(), r.getInstructions());
                newRecipe.getItems()
                        .clear();

                for (RecipeItem ri : r.getItems()) {
                    Item newItem = itemService.findItemById(ri.getItem().getItemid());
                    newRecipe.getItems().add(new RecipeItem(newRecipe, newItem, ri.getQuantity(),
                            ri.getMeasurement()));
                }

                userToUpdate.getRecipes().add(newRecipe);
            }
        }
        //

        if (user.getSubscribedRecipes().size() > 0) {
            userToUpdate.getSubscribedRecipes()
                    .clear();
            for (UserSubscribedRecipe usr : user.getSubscribedRecipes()) {
                Recipe newRecipe = recipeService.findRecipeById(usr.getRecipe().getRecipeid());
                userToUpdate.getSubscribedRecipes().add(new UserSubscribedRecipe(userToUpdate, newRecipe));
            }
        }


        return userrepo.save(userToUpdate);
    }

    @Transactional
    @Override
    public void delete(long id) {
        if (userrepo.findById(id).isPresent()) {
            userrepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("User with id " + id + " Not Found!");
        }
    }
}

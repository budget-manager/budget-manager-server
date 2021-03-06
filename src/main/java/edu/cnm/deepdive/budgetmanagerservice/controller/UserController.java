package edu.cnm.deepdive.budgetmanagerservice.controller;

import edu.cnm.deepdive.budgetmanagerservice.model.entity.Budget;
import edu.cnm.deepdive.budgetmanagerservice.model.entity.User;
import edu.cnm.deepdive.budgetmanagerservice.service.UserService;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The UserController Class controls the data flow for the User class into model object and updates
 * the view whenever data changes.
 */
@RestController
@RequestMapping("/users")
@ExposesResourceFor(User.class)
public class UserController {


  private final UserService userService;

  /**
   * @param userService
   */
  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * @param id
   * @return
   */
  @GetMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public User get(@PathVariable long id, Authentication auth) {
    return userService.get(auth)
        .map((user) -> {
          if (user.getId() != id) {
            throw new NoSuchElementException();

          }
          return user;
        })
        .orElseThrow(NoSuchElementException::new);
  }

  /**
   * @param auth
   * @return
   */
  @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> get(Authentication auth) {
    return ResponseEntity.of(userService.get(auth));
  }


}

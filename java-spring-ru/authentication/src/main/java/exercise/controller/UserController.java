package exercise.controller;

import exercise.UserNotFoundException;
import exercise.model.User;
import exercise.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Кодировщик BCrypt
    // Используйте для хеширования пароля
    @Autowired
    private PasswordEncoder encoder;

    @GetMapping(path = "")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    // BEGIN
    @GetMapping(path = "/{id}")
    public User getUser(@PathVariable long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
    @PostMapping(path = "")
    public void createUser(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    // END
}

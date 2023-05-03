package exercise.controller;
import exercise.model.User;
import exercise.model.QUser;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

// Зависимости для самостоятельной работы
// import org.springframework.data.querydsl.binding.QuerydslPredicate;
// import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    // BEGIN
    @GetMapping(path = "")
    public Iterable<User> getUserByFilter(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName) {
        if (lastName == null && firstName == null) {
            return this.userRepository.findAll();
        } else if (lastName == null) {
            return this.userRepository.findAll(QUser.user.firstName.containsIgnoreCase(firstName));
        } else if (firstName == null) {
            return this.userRepository.findAll(QUser.user.lastName.containsIgnoreCase(lastName));
        }
        return userRepository.findAll(
                QUser.user.firstName.containsIgnoreCase(firstName).and(QUser.user.lastName.containsIgnoreCase(lastName)));
    }
}



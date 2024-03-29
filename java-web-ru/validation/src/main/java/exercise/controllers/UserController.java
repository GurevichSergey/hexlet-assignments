package exercise.controllers;

import io.javalin.http.Handler;
import java.util.List;
import java.util.Map;
import io.javalin.core.validation.Validator;
import io.javalin.core.validation.ValidationError;
import io.javalin.core.validation.JavalinValidation;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

import exercise.domain.User;
import exercise.domain.query.QUser;

public final class UserController {

    public static Handler listUsers = ctx -> {

        List<User> users = new QUser()
            .orderBy()
                .id.asc()
            .findList();

        ctx.attribute("users", users);
        ctx.render("users/index.html");
    };

    public static Handler showUser = ctx -> {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);

        User user = new QUser()
            .id.equalTo(id)
            .findOne();

        ctx.attribute("user", user);
        ctx.render("users/show.html");
    };

    public static Handler newUser = ctx -> {

        ctx.attribute("errors", Map.of());
        ctx.attribute("user", Map.of());
        ctx.render("users/new.html");
    };

    public static Handler createUser = ctx -> {
        // BEGIN
        String firstName = ctx.formParam("firstName");
        String lastName = ctx.formParam("lastName");
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");
        Validator<String> firstNameValidator = ctx.formParamAsClass("firstName", String.class)
                .check(value -> !value.isEmpty(), "Name must not be empty");
        Validator<String> lastNameValidator = ctx.formParamAsClass("lastName", String.class)
                .check(value -> !value.isEmpty(), "Surname must not be empty");
        Validator<String> emailValidator = ctx.formParamAsClass("email", String.class)
                .check(value -> EmailValidator.getInstance().isValid(value), "Email is not valid");
        Validator<String> passwordValidator = ctx.formParamAsClass("password", String.class)
                .check(value -> StringUtils.isNumeric(value)
                        , "password must be contain only numbers")
                .check(value -> value.length() >= 4, "password must be least 4 characters");
        Map<String, List<ValidationError<? extends Object>>> errors = JavalinValidation.collectErrors(
                firstNameValidator, lastNameValidator, emailValidator, passwordValidator);
        if (!errors.isEmpty()) {
            ctx.status(422  );
            ctx.attribute("errors", errors);
            User inValidUser = new User(firstName, lastName, email, password);
            ctx.attribute("user", inValidUser);
            ctx.render("users/new.html");
            return;
        }
        User user = new User(firstName, lastName, email, password);
        user.save();
        ctx.sessionAttribute("flash", "User successfully created");
        ctx.redirect("/users");
        // END
    };
}

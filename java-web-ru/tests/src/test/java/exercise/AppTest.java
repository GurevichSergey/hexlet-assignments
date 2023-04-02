package exercise;

import exercise.domain.query.QUser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    public static void beforeAll() {
        app = App.getApp();
        app.start(0);
        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }
    @AfterAll
    public static void afterAll() {
        app.stop();
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("user");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @Test
    void validUserDate() {

        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", "Goga")
                .field("lastName", "Ivanov")
                .field("email", "sobaka@mail.ru")
                .field("password", "123321")
                .asString();
        assertThat(response.getStatus()).isEqualTo(302);
        User addedUser = new QUser()
                .email.equalTo("sobaka@mail.ru")
                .findOne();
        assertThat(addedUser).isNotNull();
        assertThat(addedUser.getFirstName()).isEqualTo("Goga");
        assertThat(addedUser.getLastName()).isEqualTo("Ivanov");
        assertThat(addedUser.getEmail()).isEqualTo("sobaka@mail.ru");
        assertThat(addedUser.getPassword()).isEqualTo("123321");
    }
    @Test
    void invalidUserDate() {
        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", "Goga")
                .field("lastName", "Ivanov")
                .field("email", "sobaka@mail.ru")
                .field("password", "12")
                .asString();
        assertThat(response.getStatus()).isEqualTo(422);
        User wrongUser = new QUser()
                .email.equalTo("sobaka@mail.ru")
                        .findOne();
        assertThat(wrongUser).isNull();
        String content = response.getBody();
        assertThat(content).isNotNull();
        assertThat(content).contains("Пароль должен содержать не менее 4 символов");
    }
    // END
}

package exercise.controller;

import exercise.model.Person;
import exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.Map;

@RestController
@RequestMapping("/people")
public class PeopleController {

    // Автоматически заполняем значение поля
    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person getPerson(@PathVariable long id) {
        return this.personRepository.findById(id);
    }

    @GetMapping(path = "")
    public Iterable<Person> getPeople() {
        return this.personRepository.findAll();
    }

    // BEGIN
    @PostMapping(path = "")
    public void postPerson(@RequestBody Map<String, String> person) {
        Person person1 = new Person();
        person1.setFirstName(person.get("firstName"));
        person1.setLastName(person.get("lastName"));
        this.personRepository.save(person1);
    }
    @DeleteMapping(path = "/{id}")
    public void deletePerson(@PathVariable Long id) {
        this.personRepository.deleteById(id);
    }
    @PatchMapping(path = "{id}")
    public void patchPerson(@PathVariable Long id, @RequestBody Map<String, String> person)  {
        Person updatedPerson = getPerson(id);
        updatedPerson.setLastName(person.get("lastName"));
        updatedPerson.setFirstName(person.get("firstName"));
    }
    // END
}

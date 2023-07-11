package ru.alishev.springcourse.dao;

import org.springframework.stereotype.Component;

/**
 * @author Neil Alishev
 */
@Component
public class PersonDAO {

//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> index() {
//        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Person show(int id) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny().orElse(null);
//    }
//
//    public void save(Person person) {
//        jdbcTemplate.update("INSERT INTO Person(fio, birthday) VALUES(?, ?)", person.getFio(), person.getBirthday());
//    }
//
//    public void update(int id, Person updatedPerson) {
//        jdbcTemplate.update("UPDATE Person SET fio=?, birthday=? WHERE id=?", updatedPerson.getFio(),
//                updatedPerson.getBirthday(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
//    }
//
//    public Optional<Person> getPersonByFio(String fio) {
//      return   jdbcTemplate.query("SELECT * FROM Person WHERE fio=?", new Object[]{fio}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny();
//    }
}

package ru.alishev.springcourse.dao;

import org.springframework.stereotype.Component;

@Component
public class BookDAO {

//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public BookDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Book> index() {
//        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
//    }
//
//    public Book show(int id) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
//                .stream().findAny().orElse(null);
//    }
//
//    public Person getPerson(int id) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny().orElse(null);
//    }
//
//    public void save(Book book) {
//        jdbcTemplate.update("INSERT INTO Book(name, author, year) VALUES(?, ?, ?)", book.getName(), book.getAuthor(),
//                book.getYear());
//    }
//
//    public void update(int id, Book updatedBook) {
//        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE id=?", updatedBook.getName(),
//                updatedBook.getAuthor(), updatedBook.getYear(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
//    }
//
//    public List<Book> getBooksByIdPerson(int id) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
//    }
//
//    public void assign(int idBook, int idPerson) {
//        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", idPerson, idBook);
//    }
//
//    public void release(int id) {
//        jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE id=?", id);
//    }
//
//    public Optional<Person> getBookOwnerById(int id) {
//         return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.id WHERE Book.id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
//                 .stream().findAny();
//    }
}

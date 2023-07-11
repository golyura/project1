package ru.alishev.springcourse.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.repositories.PeopleRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final BooksService booksService;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, BooksService booksService) {
        this.peopleRepository = peopleRepository;
        this.booksService = booksService;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public Page<Person> findAll(PageRequest of) {
        return peopleRepository.findAll(of);
    }

    public List<Person> findAll(Sort name) {
        return peopleRepository.findAll(name);
    }


    public Optional<Person> getPersonByFio(String fio) {
        return peopleRepository.findPeopleByFio(fio).stream().findAny();
    }

    public List<Book> getBooksByPersonId(int personId) {
        Optional<Person> person = peopleRepository.findById(personId);
        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());

            person.get().getBooks().stream().forEach(book -> {
                if (new Date().getTime() - book.getDate().getTime() > 864000000) {
                    book.setOverdue(true);
                }
            });
            return person.get().getBooks();
        } else {
            return Collections.emptyList();
        }


//        return booksService.getBooksByIdPerson(personId);
    }
}

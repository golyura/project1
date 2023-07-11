package ru.alishev.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.repositories.BooksRepository;
import ru.alishev.springcourse.repositories.PeopleRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findOne(int id) {
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        Book bookToBeUpdated = booksRepository.findById(id).get();
        updatedBook.setId(id);
        updatedBook.setOwner(bookToBeUpdated.getOwner());
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }


    public Page<Book> findAll(PageRequest of) {
        return booksRepository.findAll(of);
    }

    public List<Book> findAll(Sort name) {
        return booksRepository.findAll(name);
    }


    public Person getBookOwnerById(int bookId) {
        return booksRepository.findById(bookId).map(Book::getOwner).orElse(null);
//        Optional<Book> bookOptional = booksRepository.findById(bookId);
//        if (bookOptional.isPresent() && bookOptional.get().getOwner() != null)
//            return peopleRepository.findById(bookOptional.get().getOwner().getId());
//        return Optional.empty();
    }

    @Transactional
    public void assign(int idBook, Person person) {
        Optional<Book> bookOptional = booksRepository.findById(idBook);
        bookOptional.ifPresent(book -> {
            book.setOwner(person);
            book.setDate(new Date());
        });
    }

    @Transactional
    public void release(int id) {
        Optional<Book> bookOptional = booksRepository.findById(id);
        bookOptional.ifPresent(book -> {
            book.setOwner(null);
            book.setDate(null);
        });
    }

    public List<Book> getBooksByIdPerson(int id) {
        return booksRepository.findBookByOwnerId(id).stream().map(book -> {
            if (new Date().getTime() - book.getDate().getTime() > 864000000) {
                book.setOverdue(true);
            }
            return book;
        }).collect(Collectors.toList());
    }

    public List<Book> findBook(String lookfor) {
        return booksRepository.findByNameContainsIgnoreCase(lookfor);
    }
}

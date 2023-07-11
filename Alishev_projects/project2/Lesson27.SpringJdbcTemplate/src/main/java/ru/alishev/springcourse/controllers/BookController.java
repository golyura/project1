package ru.alishev.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.services.BooksService;
import ru.alishev.springcourse.services.PeopleService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/books")
public class BookController {

    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "booksPerPage", required = false) Integer booksPerPage) {
        if (page == null || booksPerPage == null) {
            model.addAttribute("books", booksService.findAll());
        } else {
            model.addAttribute("books", booksService.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent());
        }
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", booksService.findOne(id));
//        Optional<Person> personOptional = bookDAO.getBookOwnerById(id);
        Person personOptional = booksService.getBookOwnerById(id);
        if (personOptional != null) {
            model.addAttribute("owner", personOptional);
        } else {
            model.addAttribute("people", peopleService.findAll());
        }
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";

        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person,
                         @ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";
//        bookDAO.update(id, book);
        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@ModelAttribute("person") Person person,
                         @PathVariable("id") int id) {
//        bookDAO.assign(id, person.getId());

        booksService.assign(id, person);
        return "redirect:/books/" + id;
    }


    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
//        bookDAO.release(id);
        booksService.release(id);
        return "redirect:/books/" + id;
    }

    @GetMapping("/search")
    public String search() {
        return "books/search";
    }


    @PostMapping("/search")
    public String search(Model model, @ModelAttribute("lookfor") String lookfor) {
//        if (lookfor.isEmpty()) {
//            return "books/search";
//        }

       List<Book> books = booksService.findBook(lookfor);
//        if (optionalBook.isPresent()) {
            model.addAttribute("books", books);
//            if (optionalBook.get().getOwner() != null) {
//                model.addAttribute("person", optionalBook.get().getOwner());
//            }
//        }

        return "books/search";
    }

}

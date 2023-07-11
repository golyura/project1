package ru.alishev.springcourse.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "FIO should not be empty")
    @Size(min = 3, max = 100, message = "FIO should be between 10 and 100 characters")
    private String fio;

    @NotNull(message = "Year should not be empty")
    @Min(value = 1900, message = "Year should be greater than 1900")
    @Max(value = 2023, message = "Year should be lower than 2024")
    private int birthday;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person() {

    }

    public Person(String fio, int birthday) {
        this.fio = fio;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}

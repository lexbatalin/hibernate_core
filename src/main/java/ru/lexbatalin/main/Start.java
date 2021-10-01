package ru.lexbatalin.main;

import ru.lexbatalin.entity.Author;
import ru.lexbatalin.entity.Book;
import ru.lexbatalin.helper.AuthorHelper;
import ru.lexbatalin.helper.BookHelper;
import ru.lexbatalin.util.HibernateUtil;

public class Start {

    public static void main(String[] args) {
        Author author = new Author("test");
//        author.setName("Mikhail");
//        author.setSecondName("Lermontov");
//        author.setId(1L);

        new AuthorHelper().addAuthor(author);
    }
}

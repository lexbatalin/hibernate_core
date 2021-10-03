package ru.lexbatalin.main;

import org.jboss.logging.Logger;
import ru.lexbatalin.entity.Author;
import ru.lexbatalin.entity.Book;
import ru.lexbatalin.helper.AuthorHelper;
import ru.lexbatalin.helper.BookHelper;
import ru.lexbatalin.util.HibernateUtil;

public class Start {

    private  static final Logger LOG = Logger.getLogger(AuthorHelper.class);

    public static void main(String[] args) {

//        new AuthorHelper().getAuthorListWithParams();
//        System.out.println(new AuthorHelper().getAuthorById(1L));
        System.out.println(new BookHelper().getBookList());
    }
}

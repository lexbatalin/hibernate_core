package ru.lexbatalin.helper;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.lexbatalin.entity.Book;
import ru.lexbatalin.util.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static java.util.Objects.nonNull;

public class BookHelper {

    private SessionFactory sessionFactory;

    public BookHelper() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Book> getBookList() {

        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Book.class);
        Root<Book> root = cq.from(Book.class);
        cq.select(root);

        Query query = session.createQuery(cq);
        List<Book> bookList = query.getResultList();
        session.close();
        return bookList;
    }

    public Book addBook(Book book) {
        Session session = sessionFactory.openSession();

        Book excitingBook;
        if(nonNull(book.getId())) {
            excitingBook = session.get(Book.class, book.getId());
            if (nonNull(excitingBook)) {
                excitingBook.setName(book.getName());
                excitingBook.setAuthor(book.getAuthor());
                book = excitingBook;
            }
        }

        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        session.close();
        return book;
    }
}

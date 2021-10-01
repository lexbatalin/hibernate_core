package ru.lexbatalin.helper;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.lexbatalin.entity.Author;
import ru.lexbatalin.util.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static java.util.Objects.nonNull;

public class AuthorHelper {

    private SessionFactory sessionFactory;

    public AuthorHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Author> getAuthorList() {

        Session session = sessionFactory.openSession();

        // Подготовка запроса
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Author.class);
        Root<Author> root = cq.from(Author.class);
        cq.select(root);

        // Выполнение запроса
        Query query = session.createQuery(cq);
        List<Author> authorList = query.getResultList();
        session.close();
        return authorList;
    }

    public Author getAuthorById(long id) {

        Session session = sessionFactory.openSession();
        session.get(Author.class, id);

        return null;
    }

    public Author addAuthor(Author author) {
        Session session = sessionFactory.openSession();

        Author excitingAuthor;
        if(nonNull(author.getId())) {
            excitingAuthor = session.get(Author.class, author.getId());
            if(nonNull(excitingAuthor) ) {
                excitingAuthor.setName(author.getName());
                excitingAuthor.setSecondName(author.getSecondName());
                author = excitingAuthor;
            }
        }

        session.beginTransaction();
        session.save(author);
        session.getTransaction().commit();
        session.close();
        return author;
    }
}

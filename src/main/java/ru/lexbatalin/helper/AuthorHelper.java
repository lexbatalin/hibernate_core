package ru.lexbatalin.helper;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.lexbatalin.entity.Author;
import ru.lexbatalin.util.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.*;
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

    public List<Author> getAuthorListSelection() {

        Session session = sessionFactory.openSession();

        session.get(Author.class, 1L);

        // Подготовка запроса
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Author.class);
        Root<Author> root = cq.from(Author.class);

        //выборка полей(no type-safe), в классе Author должен быть конструктор с этими переменными
        Selection[] selection = {root.get("id"), root.get("name")};
        cq.select(cb.construct(Author.class, selection));

        // Выполнение запроса
        Query query = session.createQuery(cq);
        List<Author> authorList = query.getResultList();
        session.close();
        return authorList;
    }

    public List<Author> getAuthorListWithParams() {

        Session session = sessionFactory.openSession();

        session.get(Author.class, 1L);

        // Подготовка запроса
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Author.class);
        Root<Author> root = cq.from(Author.class);

        // Формируем запрос с выбором по параметрам like
        Selection[] selection = {root.get("name")};
        ParameterExpression<String> nameParam = cb.parameter(String.class, "name");
        cq.select(cb.construct(Author.class, selection))
                .where(cb.like(root.get("name"), nameParam));

        // Выполнение запроса
        Query query = session.createQuery(cq);

        // вставляем параметры в запрос
        query.setParameter("name" , "%1");
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

    public void add200Author() {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        for (int i = 0; i < 200; i++) {
            Author author = new Author("name_" + i);
            if (i % 10 == 0) {
                session.flush();
            }
            session.save(author);
        }
        session.getTransaction().commit();
        session.close();
    }


}

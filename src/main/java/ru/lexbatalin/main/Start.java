package ru.lexbatalin.main;

import org.hibernate.Session;
import ru.lexbatalin.util.HibernateUtil;

public class Start {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        HibernateUtil.getSessionFactory().close();
    }
}

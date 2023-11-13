package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user.getCar());
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByCar(String model, int series) {
        User user = null;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(
                    "FROM User WHERE car.model = :model and car.series = :series", User.class);
            query.setParameter("model", model);
            query.setParameter("series", series);
            user = (User) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Произошла ошибка!!!!!");
            ;
        }
        return user;
    }

}

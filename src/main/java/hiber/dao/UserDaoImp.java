package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(Object o) {
      sessionFactory.getCurrentSession().save(o);
   }
   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   @SuppressWarnings("unchecked")
   public List<Car> listCars() {
      TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("from Car");
      return query.getResultList();
   }
   @Override
   @SuppressWarnings("unchecked")
   public User getUserbycar(String model, int series){


      TypedQuery<User> query = sessionFactory.getCurrentSession()
              .createQuery("from User where car.model =: model and car.series =: series");
      query.setParameter("model"  , model);
      query.setParameter("series"  , series);
      return query.getSingleResult();
   }

   @Override
   public void dropAll(){
      Session query = sessionFactory.getCurrentSession();
      query.createSQLQuery("DROP TABLE IF EXISTS cars , users").executeUpdate();

      System.out.println("И напоследок всё зачистить");
//      надоело вручную
   }
}

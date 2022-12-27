package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserDao {

   void dropAll() ;

   void add(Object o);
   List<User> listUsers();
   List<Car> listCars();

   User getUserbycar (String model, int series);
}

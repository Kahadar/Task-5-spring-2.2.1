package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {

      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);



      userService.add(new User("Абрам", "Бэхин", "user1@mail.ru"));
      userService.add(new User("Ахмет", "Тазоев", "user2@mail.ru"));
      userService.add(new User("Иван", "Старперов", "user3@mail.ru"));
      userService.add(new User("Тарас", "Нищеброденко", "user4@mail.ru"));

      userService.add(new Car("BMW",5));
      userService.add(new Car("Lada",2101));
      userService.add(new Car("GAZ",69));
      userService.add(new Car("ZAZ",968));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      List<Car> cars = userService.listCars();
      for (Car car : cars){
         System.out.println("Id = "  + car.getId() + "  "+ car.getModel() + "  "+ car.getSeries());
         System.out.println("Владелец "  + userService.getUserbycar(car.getModel(), car.getSeries()).getLastName());
         System.out.println("----------------------");
         System.out.println();
      }

      User target = userService.getUserbycar("Lada",2101);
      System.out.println("-----------------------");
      System.out.println("Счастливый обладатель великолепного автомобиля "+ target.getCar().getModel() + " " + target.getCar().getSeries());
      System.out.println(target.getFirstName());
      System.out.println(target.getLastName());
      System.out.println("-----------------------");

      //зачистка вручную
//      userService.dropAll();

      context.close();



   }
}

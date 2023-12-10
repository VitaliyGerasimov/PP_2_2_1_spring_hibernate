package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;


public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      User user1 = new User("Artem", "Kezhnaev", "Artem@mail.ru");
      user1.setCar(new Car("VAZ", 2107));
      userService.add(user1);

      User user2 = new User("Alex", "Enohin", "Alex@mail.ru");
      user2.setCar(new Car("BMW", 5));
      userService.add(user2);

      User user3 = new User("Anton", "E4maev", "Anton@mail.ru");
      user3.setCar(new Car("Mercedes", 63));
      userService.add(user3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         if (user.getCar() != null) {
            System.out.println("Car model = " + user.getCar().getModel());
            System.out.println("Car series = " + user.getCar().getSeries());
         } else {
            System.out.println("Отсутствует автомобиль");
         }
      }
      User user = userService.getUserByCarModelAndSeries("Mercedes",63);
      System.out.println(user);

      context.close();
   }
}

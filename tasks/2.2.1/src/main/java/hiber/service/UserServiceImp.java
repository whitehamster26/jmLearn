package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
   @Autowired
   SessionFactory sessionFactory;

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional(readOnly = true)
   @Override
   public User getUserByCar(String model, int series) {
      User user = null;
      @SuppressWarnings("unchecked")
      TypedQuery<User> usersQuery = sessionFactory.getCurrentSession().createQuery(
                      "select u from User as u join u.car as c where c.model = :model and c.series = :series",
                      User.class)
              .setParameter("model", model)
              .setParameter("series", series);
      user = usersQuery.getSingleResult();
      return user;
   }

}

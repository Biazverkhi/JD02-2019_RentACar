package by.fastrentcar.dao;

import by.fastrentcar.model.User;


public interface UserDAO {
    Long addUserT(User user);

    boolean updateUserT(User user);

    //User getUserByIdT(long id);

    //  User getUserByLogin(String login) ;

    // List<User> getUsersT() ;

   //  boolean deleteUserT(long id) ;

}

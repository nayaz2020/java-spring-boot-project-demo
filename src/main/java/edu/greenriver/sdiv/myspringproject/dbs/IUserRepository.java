package edu.greenriver.sdiv.myspringproject.dbs;

import edu.greenriver.sdiv.myspringproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author nemat
 * @version 1.2
 * User Repository to save user, find user by username
 */
public interface IUserRepository extends JpaRepository<User, Integer>
{
    /**
     * @param username user to search for
     * @return the user if exist
     */
    User findByUsername(String username);
}

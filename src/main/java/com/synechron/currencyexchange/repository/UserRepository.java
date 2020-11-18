package com.synechron.currencyexchange.repository;

import com.synechron.currencyexchange.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface User repository.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Find by user name user.
     *
     * @param userName the user name
     * @return the user
     */
    User findByUserName(String userName);
}

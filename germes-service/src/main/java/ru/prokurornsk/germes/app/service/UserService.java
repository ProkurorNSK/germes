package ru.prokurornsk.germes.app.service;

import java.util.List;
import java.util.Optional;

import ru.prokurornsk.germes.app.model.entity.person.User;

/**
 * Provides API for the user management 
 * @author ProkurorNSK
 *
 */
public interface UserService {
	/**
	 * Saves(creates or modifies) specified user instance
	 * @param user
	 */
	void save(User user);
	
	/**
	 * Returns user with specified identifier boxed into Optional
	 * @param userId
	 * @return
	 */
	Optional<User> findById(int userId);
	
	/**
	 * Delete city with specified identifier
	 * @param userId
	 */
	void delete(int userId);
	
	/**
	 * Returns all the cities
	 * @return
	 */
	List<User> findAll();

}

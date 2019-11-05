package ru.prokurornsk.germes.app.service.impl;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import ru.prokurornsk.germes.app.infra.cdi.DBSource;
import ru.prokurornsk.germes.app.infra.util.SecurityUtil;
import ru.prokurornsk.germes.app.model.entity.person.User;
import ru.prokurornsk.germes.app.persistence.repository.UserRepository;
import ru.prokurornsk.germes.app.service.UserService;


/**
 * Default and managed(by CDI container) implementation of UserService
 *
 * @author ProkurorNSK
 *
 */
@Named
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Inject
	public UserServiceImpl(@DBSource UserRepository userRepository) {
		this.userRepository = userRepository;

		User user = new User();
		user.setUserName("root");
		user.setPassword(SecurityUtil.encryptSHA("54321"));
		userRepository.save(user);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public Optional<User> findById(int userId) {
		return userRepository.findById(userId);
	}

	@Override
	public void delete(int userId) {
		userRepository.delete(userId);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
}


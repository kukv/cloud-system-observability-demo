package jp.kukv.user_api.application.service;

import jp.kukv.user_api.application.repository.UserRepository;
import jp.kukv.user_api.domain.model.Id;
import jp.kukv.user_api.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  UserRepository userRepository;

  public User get(Id id) {
    return userRepository.get(id);
  }

  UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
}

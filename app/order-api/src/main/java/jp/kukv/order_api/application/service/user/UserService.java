package jp.kukv.order_api.application.service.user;

import jp.kukv.order_api.application.repository.user.UserRepository;
import jp.kukv.order_api.domain.model.user.User;
import jp.kukv.order_api.domain.model.user.UserId;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  UserRepository userRepository;

  public User get(UserId userId) {
    return userRepository.get(userId);
  }

  UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
}

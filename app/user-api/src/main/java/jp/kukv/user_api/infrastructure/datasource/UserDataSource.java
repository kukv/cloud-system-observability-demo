package jp.kukv.user_api.infrastructure.datasource;

import jp.kukv.user_api.application.repository.UserRepository;
import jp.kukv.user_api.domain.model.Id;
import jp.kukv.user_api.domain.model.User;
import jp.kukv.user_api.domain.policy.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
class UserDataSource implements UserRepository {

  UserMapper userMapper;

  @Override
  public User get(Id id) {
    User user = userMapper.get(id);
    if (user == null) {
      throw new ResourceNotFoundException(String.format("User not found. id: %s", id));
    }

    return user;
  }

  UserDataSource(UserMapper userMapper) {
    this.userMapper = userMapper;
  }
}

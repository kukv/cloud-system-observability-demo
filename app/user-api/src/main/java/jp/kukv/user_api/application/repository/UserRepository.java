package jp.kukv.user_api.application.repository;

import jp.kukv.user_api.domain.model.Id;
import jp.kukv.user_api.domain.model.User;

public interface UserRepository {
  User get(Id id);
}

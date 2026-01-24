package jp.kukv.order_api.application.repository.user;

import jp.kukv.order_api.domain.model.user.User;
import jp.kukv.order_api.domain.model.user.UserId;

public interface UserRepository {
  User get(UserId userId);
}

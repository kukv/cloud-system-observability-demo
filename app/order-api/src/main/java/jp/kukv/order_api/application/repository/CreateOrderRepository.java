package jp.kukv.order_api.application.repository;

import jp.kukv.order_api.domain.model.Id;
import jp.kukv.order_api.domain.model.user.UserId;

public interface CreateOrderRepository {
  Id create(UserId userId);
}

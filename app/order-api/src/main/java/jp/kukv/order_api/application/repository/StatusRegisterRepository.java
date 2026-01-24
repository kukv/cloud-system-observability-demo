package jp.kukv.order_api.application.repository;

import jp.kukv.order_api.domain.model.Id;
import jp.kukv.order_api.domain.model.OrderStatus;

public interface StatusRegisterRepository {
  void register(OrderStatus status, Id orderId);
}

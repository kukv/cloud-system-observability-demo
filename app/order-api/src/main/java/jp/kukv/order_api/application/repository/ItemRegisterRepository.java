package jp.kukv.order_api.application.repository;

import jp.kukv.order_api.domain.model.Id;
import jp.kukv.order_api.domain.model.Item;

public interface ItemRegisterRepository {
  void register(Item item, Id orderId);
}

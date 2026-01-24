package jp.kukv.order_api.infrastructure.datasource;

import jp.kukv.order_api.application.repository.ItemRegisterRepository;
import jp.kukv.order_api.domain.model.Id;
import jp.kukv.order_api.domain.model.Item;
import org.springframework.stereotype.Repository;

@Repository
class ItemRegisterDataSource implements ItemRegisterRepository {

  ItemRegisterMapper itemRegisterMapper;

  @Override
  public void register(Item item, Id orderId) {
    itemRegisterMapper.register(item, orderId, CreatedTime.now());
  }

  ItemRegisterDataSource(ItemRegisterMapper itemRegisterMapper) {
    this.itemRegisterMapper = itemRegisterMapper;
  }
}

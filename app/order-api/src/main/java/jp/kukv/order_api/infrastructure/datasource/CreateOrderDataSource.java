package jp.kukv.order_api.infrastructure.datasource;

import jp.kukv.order_api.application.repository.CreateOrderRepository;
import jp.kukv.order_api.domain.model.Id;
import jp.kukv.order_api.domain.model.user.UserId;
import org.springframework.stereotype.Repository;

@Repository
class CreateOrderDataSource implements CreateOrderRepository {

  CreateOrderMapper createOrderMapper;

  @Override
  public Id create(UserId userId) {
    CreatedTime createdTime = CreatedTime.now();
    return createOrderMapper.create(userId, createdTime);
  }

  CreateOrderDataSource(CreateOrderMapper createOrderMapper) {
    this.createOrderMapper = createOrderMapper;
  }
}

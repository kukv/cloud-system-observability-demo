package jp.kukv.order_api.infrastructure.datasource;

import jp.kukv.order_api.application.repository.StatusRegisterRepository;
import jp.kukv.order_api.domain.model.Id;
import jp.kukv.order_api.domain.model.OrderStatus;
import org.springframework.stereotype.Repository;

@Repository
class StatusRegisterDataSource implements StatusRegisterRepository {

  StatusRegisterMapper statusRegisterMapper;

  @Override
  public void register(OrderStatus status, Id orderId) {
    statusRegisterMapper.register(status, orderId, CreatedTime.now());
  }

  StatusRegisterDataSource(StatusRegisterMapper statusRegisterMapper) {
    this.statusRegisterMapper = statusRegisterMapper;
  }
}

package jp.kukv.order_api.application.service;

import jp.kukv.order_api.application.repository.CreateOrderRepository;
import jp.kukv.order_api.domain.model.Id;
import jp.kukv.order_api.domain.model.user.UserId;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderService {

  CreateOrderRepository createOrderRepository;

  public Id create(UserId userId) {
    return createOrderRepository.create(userId);
  }

  CreateOrderService(CreateOrderRepository createOrderRepository) {
    this.createOrderRepository = createOrderRepository;
  }
}

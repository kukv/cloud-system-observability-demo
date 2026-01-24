package jp.kukv.order_api.application.service;

import jp.kukv.order_api.application.repository.StatusRegisterRepository;
import jp.kukv.order_api.domain.model.Id;
import jp.kukv.order_api.domain.model.OrderStatus;
import org.springframework.stereotype.Service;

@Service
public class StatusRegisterService {

  StatusRegisterRepository statusRegisterRepository;

  public void register(OrderStatus status, Id orderId) {
    statusRegisterRepository.register(status, orderId);
  }

  StatusRegisterService(StatusRegisterRepository statusRegisterRepository) {
    this.statusRegisterRepository = statusRegisterRepository;
  }
}

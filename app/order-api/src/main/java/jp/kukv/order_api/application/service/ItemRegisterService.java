package jp.kukv.order_api.application.service;

import jp.kukv.order_api.application.repository.ItemRegisterRepository;
import jp.kukv.order_api.domain.model.Id;
import jp.kukv.order_api.domain.model.Item;
import org.springframework.stereotype.Service;

@Service
public class ItemRegisterService {

  ItemRegisterRepository itemRegisterRepository;

  public void register(Item item, Id orderId) {
    itemRegisterRepository.register(item, orderId);
  }

  ItemRegisterService(ItemRegisterRepository itemRegisterRepository) {
    this.itemRegisterRepository = itemRegisterRepository;
  }
}

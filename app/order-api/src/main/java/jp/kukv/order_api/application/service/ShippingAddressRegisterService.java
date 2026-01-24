package jp.kukv.order_api.application.service;

import jp.kukv.order_api.application.repository.ShippingAddressRegisterRepository;
import jp.kukv.order_api.domain.model.Id;
import jp.kukv.order_api.domain.model.user.contact.address.Address;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressRegisterService {

  ShippingAddressRegisterRepository shippingAddressRegisterRepository;

  public void register(Address address, Id orderId) {
    shippingAddressRegisterRepository.register(address, orderId);
  }

  ShippingAddressRegisterService(
      ShippingAddressRegisterRepository shippingAddressRegisterRepository) {
    this.shippingAddressRegisterRepository = shippingAddressRegisterRepository;
  }
}

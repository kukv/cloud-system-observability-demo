package jp.kukv.order_api.application.repository;

import jp.kukv.order_api.domain.model.Id;
import jp.kukv.order_api.domain.model.user.contact.address.Address;

public interface ShippingAddressRegisterRepository {
  void register(Address address, Id orderId);
}

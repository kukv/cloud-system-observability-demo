package jp.kukv.order_api.infrastructure.datasource;

import jp.kukv.order_api.application.repository.ShippingAddressRegisterRepository;
import jp.kukv.order_api.domain.model.Id;
import jp.kukv.order_api.domain.model.user.contact.address.Address;
import org.springframework.stereotype.Repository;

@Repository
class ShippingAddressRegisterDataSource implements ShippingAddressRegisterRepository {

  ShippingAddressRegisterMapper shippingAddressRegisterMapper;

  @Override
  public void register(Address address, Id orderId) {
    shippingAddressRegisterMapper.register(address, orderId, CreatedTime.now());
  }

  ShippingAddressRegisterDataSource(ShippingAddressRegisterMapper shippingAddressRegisterMapper) {
    this.shippingAddressRegisterMapper = shippingAddressRegisterMapper;
  }
}

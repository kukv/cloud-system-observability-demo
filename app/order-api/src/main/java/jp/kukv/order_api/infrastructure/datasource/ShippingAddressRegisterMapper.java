package jp.kukv.order_api.infrastructure.datasource;

import jp.kukv.order_api.domain.model.Id;
import jp.kukv.order_api.domain.model.user.contact.address.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
interface ShippingAddressRegisterMapper {
  void register(
      @Param("address") Address address,
      @Param("orderId") Id orderId,
      @Param("createdTime") CreatedTime createdTime);
}

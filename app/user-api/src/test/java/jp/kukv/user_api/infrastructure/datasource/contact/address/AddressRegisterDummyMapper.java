package jp.kukv.user_api.infrastructure.datasource.contact.address;

import jp.kukv.user_api.domain.model.Id;
import jp.kukv.user_api.domain.model.contact.address.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AddressRegisterDummyMapper {
  void register(@Param("address") Address address, @Param("id") Id id);
}

package jp.kukv.product_api.infrastructure.datasource;

import jp.kukv.product_api.domain.model.Id;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductRegisterDummyMapper {
  void register(@Param("id") Id id);
}

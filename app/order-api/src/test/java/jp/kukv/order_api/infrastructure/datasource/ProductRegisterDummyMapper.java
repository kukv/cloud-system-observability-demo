package jp.kukv.order_api.infrastructure.datasource;

import jp.kukv.order_api.domain.model.product.ProductId;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductRegisterDummyMapper {
  void register(@Param("id") ProductId id);
}

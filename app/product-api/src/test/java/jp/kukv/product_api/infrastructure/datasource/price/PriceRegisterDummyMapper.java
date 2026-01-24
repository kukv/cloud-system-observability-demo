package jp.kukv.product_api.infrastructure.datasource.price;

import jp.kukv.product_api.domain.model.Id;
import jp.kukv.product_api.domain.model.Price;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PriceRegisterDummyMapper {
  void register(@Param("price") Price price, @Param("id") Id id);
}

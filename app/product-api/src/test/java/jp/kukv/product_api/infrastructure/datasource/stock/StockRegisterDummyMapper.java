package jp.kukv.product_api.infrastructure.datasource.stock;

import jp.kukv.product_api.domain.model.Id;
import jp.kukv.product_api.domain.model.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StockRegisterDummyMapper {
  void register(@Param("stock") Stock stock, @Param("id") Id id);
}

package jp.kukv.product_api.infrastructure.datasource;

import jp.kukv.product_api.domain.model.Id;
import jp.kukv.product_api.domain.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
interface ProductMapper {
  Product get(@Param("id") Id id);
}

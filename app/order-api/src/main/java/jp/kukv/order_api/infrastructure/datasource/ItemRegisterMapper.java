package jp.kukv.order_api.infrastructure.datasource;

import jp.kukv.order_api.domain.model.Id;
import jp.kukv.order_api.domain.model.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
interface ItemRegisterMapper {
  void register(
      @Param("item") Item item,
      @Param("orderId") Id orderId,
      @Param("createdTime") CreatedTime createdTime);
}

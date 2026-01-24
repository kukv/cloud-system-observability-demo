package jp.kukv.order_api.infrastructure.datasource;

import jp.kukv.order_api.domain.model.Id;
import jp.kukv.order_api.domain.model.OrderStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
interface StatusRegisterMapper {
  void register(
      @Param("status") OrderStatus status,
      @Param("orderId") Id orderId,
      @Param("createdTime") CreatedTime createdTime);
}

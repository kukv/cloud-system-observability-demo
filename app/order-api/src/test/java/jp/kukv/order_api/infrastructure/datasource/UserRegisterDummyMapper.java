package jp.kukv.order_api.infrastructure.datasource;

import jp.kukv.order_api.domain.model.user.UserId;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRegisterDummyMapper {
  void register(@Param("id") UserId id);
}

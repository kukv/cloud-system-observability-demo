package jp.kukv.user_api.infrastructure.datasource;

import jp.kukv.user_api.domain.model.Id;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRegisterDummyMapper {
  void register(@Param("id") Id id);
}

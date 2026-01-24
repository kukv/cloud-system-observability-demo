package jp.kukv.user_api.infrastructure.datasource;

import jp.kukv.user_api.domain.model.Id;
import jp.kukv.user_api.domain.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
interface UserMapper {
  User get(@Param("id") Id id);
}

package jp.kukv.user_api.infrastructure.datasource.profile;

import jp.kukv.user_api.domain.model.Id;
import jp.kukv.user_api.domain.model.profile.Profile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProfileRegisterDummyMapper {
  void register(@Param("profile") Profile profile, @Param("id") Id id);
}

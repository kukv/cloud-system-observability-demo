package jp.kukv.user_api.infrastructure.datasource.contact;

import jp.kukv.user_api.domain.model.Id;
import jp.kukv.user_api.domain.model.contact.Contact;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ContactRegisterEndpointDummyMapper {
  void register(@Param("contact") Contact contact, @Param("id") Id id);
}

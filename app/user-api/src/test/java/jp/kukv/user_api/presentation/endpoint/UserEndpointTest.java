package jp.kukv.user_api.presentation.endpoint;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jp.kukv.user_api.domain.model.Id;
import jp.kukv.user_api.domain.model.contact.Contact;
import jp.kukv.user_api.domain.model.contact.ContactDummyFactory;
import jp.kukv.user_api.domain.model.contact.address.Address;
import jp.kukv.user_api.domain.model.contact.address.AddressDummyFactory;
import jp.kukv.user_api.domain.model.profile.Profile;
import jp.kukv.user_api.domain.model.profile.ProfileDummyFactory;
import jp.kukv.user_api.infrastructure.datasource.UserRegisterDummyMapper;
import jp.kukv.user_api.infrastructure.datasource.contact.ContactRegisterEndpointDummyMapper;
import jp.kukv.user_api.infrastructure.datasource.contact.address.AddressRegisterDummyMapper;
import jp.kukv.user_api.infrastructure.datasource.profile.ProfileRegisterDummyMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Transactional
class UserEndpointTest {

  @Autowired WebApplicationContext context;
  @Autowired UserRegisterDummyMapper userRegisterDummyMapper;
  @Autowired ProfileRegisterDummyMapper profileRegisterDummyMapper;
  @Autowired ContactRegisterEndpointDummyMapper contactRegisterEndpointDummyMapper;
  @Autowired AddressRegisterDummyMapper addressRegisterDummyMapper;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  void ユーザーを取得できる() throws Exception {
    Id id = new Id(1001);
    Profile profile = ProfileDummyFactory.create("山田", "太郎");

    Address address =
        AddressDummyFactory.create("1234567", "東京都", "渋谷区", "千駄ヶ谷1-1", "千駄ヶ谷ビル", "1F");
    Contact contact = ContactDummyFactory.create("taro.yamada@example.com", "09012345678", address);

    userRegisterDummyMapper.register(id);
    profileRegisterDummyMapper.register(profile, id);
    contactRegisterEndpointDummyMapper.register(contact, id);
    addressRegisterDummyMapper.register(contact.address(), id);

    mockMvc
        .perform(get("/v1/user").param("id", Integer.toString(1001)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.profile.first_name").value("山田"))
        .andExpect(jsonPath("$.profile.last_name").value("太郎"))
        .andExpect(jsonPath("$.contact.mail_address").value("taro.yamada@example.com"))
        .andExpect(jsonPath("$.contact.phone_number").value("09012345678"))
        .andExpect(jsonPath("$.contact.address.postal_code").value("1234567"))
        .andExpect(jsonPath("$.contact.address.prefecture").value("東京都"))
        .andExpect(jsonPath("$.contact.address.city").value("渋谷区"))
        .andExpect(jsonPath("$.contact.address.address_line").value("千駄ヶ谷1-1"))
        .andExpect(jsonPath("$.contact.address.building_name").value("千駄ヶ谷ビル"))
        .andExpect(jsonPath("$.contact.address.building_number").value("1F"));
  }

  @Test
  void パラメータにIDが存在しない場合エラーを返す() throws Exception {
    mockMvc.perform(get("/v1/user")).andExpect(status().isBadRequest());
  }

  @Test
  void パラメータに指定したIDに紐づくユーザーが存在しない場合エラーを返す() throws Exception {
    mockMvc
        .perform(get("/v1/user").param("id", Integer.toString(1002)))
        .andExpect(status().isNotFound());
  }
}

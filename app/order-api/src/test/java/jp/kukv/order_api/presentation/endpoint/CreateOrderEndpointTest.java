package jp.kukv.order_api.presentation.endpoint;

import static org.hamcrest.Matchers.greaterThan;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jp.kukv.order_api.application.repository.product.ProductRepository;
import jp.kukv.order_api.application.repository.user.UserRepository;
import jp.kukv.order_api.domain.model.product.*;
import jp.kukv.order_api.domain.model.product.overview.Overview;
import jp.kukv.order_api.domain.model.product.overview.OverviewDummyFactory;
import jp.kukv.order_api.domain.model.user.User;
import jp.kukv.order_api.domain.model.user.UserDummyFactory;
import jp.kukv.order_api.domain.model.user.UserId;
import jp.kukv.order_api.domain.model.user.contact.Contact;
import jp.kukv.order_api.domain.model.user.contact.ContactDummyFactory;
import jp.kukv.order_api.domain.model.user.contact.address.Address;
import jp.kukv.order_api.domain.model.user.contact.address.AddressDummyFactory;
import jp.kukv.order_api.domain.model.user.profile.Profile;
import jp.kukv.order_api.domain.model.user.profile.ProfileDummyFactory;
import jp.kukv.order_api.domain.policy.exception.ResourceNotFoundException;
import jp.kukv.order_api.infrastructure.datasource.ProductRegisterDummyMapper;
import jp.kukv.order_api.infrastructure.datasource.UserRegisterDummyMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Transactional
class CreateOrderEndpointTest {

  private static final int TEST_USER_ID = 9001;
  private static final int TEST_PRODUCT_ID = 9002;

  private static final String VALID_REQUEST_BODY =
      """
      {
        "user_id": 9001,
        "order_item": {
          "product_id": 9002,
          "quantity": 2
        }
      }
      """;

  private static final String INVALID_REQUEST_BODY =
      """
      {
        "user_id": null,
        "order_item": null
      }
      """;

  @Autowired WebApplicationContext context;
  @Autowired UserRegisterDummyMapper userRegisterDummyMapper;
  @Autowired ProductRegisterDummyMapper productRegisterDummyMapper;
  @MockitoBean UserRepository userRepository;
  @MockitoBean ProductRepository productRepository;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  void オーダーを作成できる() throws Exception {
    Profile profile = ProfileDummyFactory.create("山田", "太郎");
    Address address =
        AddressDummyFactory.create("1234567", "東京都", "渋谷区", "千駄ヶ谷1-1", "千駄ヶ谷ビル", "1F");
    Contact contact = ContactDummyFactory.create("taro.yamada@example.com", "09012345678", address);
    User user = UserDummyFactory.create(profile, contact);

    Overview overview = OverviewDummyFactory.create("サンプル商品", "テスト商品です。");
    Product product = ProductDummyFactory.create(overview, 1200, 5);

    when(userRepository.get(any())).thenReturn(user);
    when(productRepository.get(any())).thenReturn(product);

    userRegisterDummyMapper.register(new UserId(TEST_USER_ID));
    productRegisterDummyMapper.register(new ProductId(TEST_PRODUCT_ID));

    mockMvc
        .perform(
            post("/v1/order/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_REQUEST_BODY))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$").value(greaterThan(0)));
  }

  @Test
  void リクエストボディが不正の場合エラーを返す() throws Exception {
    mockMvc
        .perform(
            post("/v1/order/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(INVALID_REQUEST_BODY))
        .andExpect(status().isBadRequest());
  }

  @Test
  void ユーザー情報が存在しない場合エラーを返す() throws Exception {
    when(userRepository.get(any()))
        .thenThrow(new ResourceNotFoundException("User not found. id: 9001"));

    mockMvc
        .perform(
            post("/v1/order/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_REQUEST_BODY))
        .andExpect(status().isNotFound());
  }

  @Test
  void 商品情報が存在しない場合エラーを返す() throws Exception {
    userRegisterDummyMapper.register(new UserId(TEST_USER_ID));
    when(productRepository.get(any()))
        .thenThrow(new ResourceNotFoundException("Product not found. id: 9002"));

    mockMvc
        .perform(
            post("/v1/order/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_REQUEST_BODY))
        .andExpect(status().isNotFound());
  }
}

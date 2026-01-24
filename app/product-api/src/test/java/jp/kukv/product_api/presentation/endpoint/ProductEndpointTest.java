package jp.kukv.product_api.presentation.endpoint;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jp.kukv.product_api.domain.model.Id;
import jp.kukv.product_api.domain.model.Price;
import jp.kukv.product_api.domain.model.ProductDummyFactory;
import jp.kukv.product_api.domain.model.Stock;
import jp.kukv.product_api.domain.model.overview.Overview;
import jp.kukv.product_api.domain.model.overview.OverviewDummyFactory;
import jp.kukv.product_api.infrastructure.datasource.ProductRegisterDummyMapper;
import jp.kukv.product_api.infrastructure.datasource.overview.OverviewRegisterDummyMapper;
import jp.kukv.product_api.infrastructure.datasource.price.PriceRegisterDummyMapper;
import jp.kukv.product_api.infrastructure.datasource.stock.StockRegisterDummyMapper;
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
class ProductEndpointTest {

  @Autowired WebApplicationContext context;
  @Autowired ProductRegisterDummyMapper productRegisterDummyMapper;
  @Autowired OverviewRegisterDummyMapper overviewRegisterDummyMapper;
  @Autowired PriceRegisterDummyMapper priceRegisterDummyMapper;
  @Autowired StockRegisterDummyMapper stockRegisterDummyMapper;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  void 商品を取得できる() throws Exception {
    Id id = new Id(1001);
    Overview overview = OverviewDummyFactory.create("サンプル商品", "テスト商品です。");
    Price price = ProductDummyFactory.createPrice(1200);
    Stock stock = ProductDummyFactory.createStock(5);

    productRegisterDummyMapper.register(id);
    overviewRegisterDummyMapper.register(overview, id);
    priceRegisterDummyMapper.register(price, id);
    stockRegisterDummyMapper.register(stock, id);

    mockMvc
        .perform(get("/v1/product").param("id", Integer.toString(1001)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.overview.name").value("サンプル商品"))
        .andExpect(jsonPath("$.overview.description").value("テスト商品です。"))
        .andExpect(jsonPath("$.price").value(1200))
        .andExpect(jsonPath("$.stock").value(5));
  }

  @Test
  void パラメータにIDが存在しない場合エラーを返す() throws Exception {
    mockMvc.perform(get("/v1/product")).andExpect(status().isBadRequest());
  }

  @Test
  void パラメータに指定したIDに紐づく商品が存在しない場合エラーを返す() throws Exception {
    mockMvc
        .perform(get("/v1/product").param("id", Integer.toString(1002)))
        .andExpect(status().isNotFound());
  }
}

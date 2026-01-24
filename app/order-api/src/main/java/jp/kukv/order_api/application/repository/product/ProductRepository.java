package jp.kukv.order_api.application.repository.product;

import jp.kukv.order_api.domain.model.product.Product;
import jp.kukv.order_api.domain.model.product.ProductId;

public interface ProductRepository {
  Product get(ProductId productId);
}

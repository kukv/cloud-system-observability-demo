package jp.kukv.product_api.application.repository;

import jp.kukv.product_api.domain.model.Id;
import jp.kukv.product_api.domain.model.Product;

public interface ProductRepository {
  Product get(Id id);
}

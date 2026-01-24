package jp.kukv.product_api.infrastructure.datasource;

import jp.kukv.product_api.application.repository.ProductRepository;
import jp.kukv.product_api.domain.model.Id;
import jp.kukv.product_api.domain.model.Product;
import jp.kukv.product_api.domain.policy.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
class ProductDataSource implements ProductRepository {

  ProductMapper productMapper;

  @Override
  public Product get(Id id) {
    Product product = productMapper.get(id);
    if (product == null) {
      throw new ResourceNotFoundException(String.format("Product not found. id: %s", id));
    }

    return product;
  }

  ProductDataSource(ProductMapper productMapper) {
    this.productMapper = productMapper;
  }
}

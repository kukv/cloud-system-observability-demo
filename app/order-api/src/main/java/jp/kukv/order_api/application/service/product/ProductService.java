package jp.kukv.order_api.application.service.product;

import jp.kukv.order_api.application.repository.product.ProductRepository;
import jp.kukv.order_api.domain.model.product.Product;
import jp.kukv.order_api.domain.model.product.ProductId;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  ProductRepository productRepository;

  public Product get(ProductId productId) {
    return productRepository.get(productId);
  }

  ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }
}

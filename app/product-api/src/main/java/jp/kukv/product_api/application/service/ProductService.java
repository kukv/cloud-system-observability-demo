package jp.kukv.product_api.application.service;

import jp.kukv.product_api.application.repository.ProductRepository;
import jp.kukv.product_api.domain.model.Id;
import jp.kukv.product_api.domain.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  ProductRepository productRepository;

  public Product get(Id id) {
    return productRepository.get(id);
  }

  ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }
}

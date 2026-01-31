package jp.kukv.order_api.application.scenario;

import jp.kukv.order_api.application.service.CreateOrderService;
import jp.kukv.order_api.application.service.ItemRegisterService;
import jp.kukv.order_api.application.service.ShippingAddressRegisterService;
import jp.kukv.order_api.application.service.StatusRegisterService;
import jp.kukv.order_api.application.service.product.ProductService;
import jp.kukv.order_api.application.service.user.UserService;
import jp.kukv.order_api.domain.model.*;
import jp.kukv.order_api.domain.model.product.Product;
import jp.kukv.order_api.domain.model.user.User;
import jp.kukv.order_api.domain.model.user.UserId;
import jp.kukv.order_api.domain.model.user.contact.Contact;
import jp.kukv.order_api.domain.model.user.contact.address.Address;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateOrderScenario {

  CreateOrderService createOrderService;
  ShippingAddressRegisterService shippingAddressRegisterService;
  ItemRegisterService itemRegisterService;
  StatusRegisterService statusRegisterService;
  UserService userService;
  ProductService productService;

  @Transactional
  public Id create(UserId userId, OrderItem orderItem) {

    User user = userService.get(userId);
    Product product = productService.get(orderItem.productId());

    Id orderId = createOrderService.create(userId);

    Contact contact = user.contact();
    Address address = contact.address();
    shippingAddressRegisterService.register(address, orderId);

    Item item = new Item(orderItem.productId(), product.price(), orderItem.quantity());
    itemRegisterService.register(item, orderId);
    statusRegisterService.register(OrderStatus.ORDERED, orderId);

    return orderId;
  }

  CreateOrderScenario(
      CreateOrderService createOrderService,
      ShippingAddressRegisterService shippingAddressRegisterService,
      ItemRegisterService itemRegisterService,
      StatusRegisterService statusRegisterService,
      UserService userService,
      ProductService productService) {
    this.createOrderService = createOrderService;
    this.shippingAddressRegisterService = shippingAddressRegisterService;
    this.itemRegisterService = itemRegisterService;
    this.statusRegisterService = statusRegisterService;
    this.userService = userService;
    this.productService = productService;
  }
}

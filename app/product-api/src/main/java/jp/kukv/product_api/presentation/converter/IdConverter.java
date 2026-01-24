package jp.kukv.product_api.presentation.converter;

import jp.kukv.product_api.domain.model.Id;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class IdConverter implements Converter<String, Id> {

  @Override
  public Id convert(String source) {
    return new Id(Integer.parseInt(source));
  }
}

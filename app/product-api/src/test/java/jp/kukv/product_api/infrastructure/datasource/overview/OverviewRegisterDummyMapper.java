package jp.kukv.product_api.infrastructure.datasource.overview;

import jp.kukv.product_api.domain.model.Id;
import jp.kukv.product_api.domain.model.overview.Overview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OverviewRegisterDummyMapper {
  void register(@Param("overview") Overview overview, @Param("id") Id id);
}

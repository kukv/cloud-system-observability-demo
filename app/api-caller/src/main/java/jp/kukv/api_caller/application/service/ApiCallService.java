package jp.kukv.api_caller.application.service;

import java.util.List;
import jp.kukv.api_caller.application.repository.ApiCallRepository;
import org.springframework.stereotype.Service;

@Service
public class ApiCallService {

  List<ApiCallRepository> repositories;

  public void call() {
    for (int i = 0; i < Math.random() * repositories.size(); i++) {
      ApiCallRepository repository = repositories.get(i);
      repository.call();
    }
  }

  ApiCallService(List<ApiCallRepository> repositories) {
    this.repositories = repositories;
  }
}

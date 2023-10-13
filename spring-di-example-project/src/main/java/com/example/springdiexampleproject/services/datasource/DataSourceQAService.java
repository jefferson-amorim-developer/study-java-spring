package com.example.springdiexampleproject.services.datasource;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("qa")
@Service("dataSourceService")
public class DataSourceQAService implements DataSourceService {

  @Override
  public String value() {
    return "qa";
  }

}

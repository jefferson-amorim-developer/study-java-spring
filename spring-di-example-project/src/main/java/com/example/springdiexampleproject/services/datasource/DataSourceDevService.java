package com.example.springdiexampleproject.services.datasource;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"dev", "default"})
@Service("dataSourceService")
public class DataSourceDevService implements DataSourceService {

  @Override
  public String value() {
    return "dev";
  }

}

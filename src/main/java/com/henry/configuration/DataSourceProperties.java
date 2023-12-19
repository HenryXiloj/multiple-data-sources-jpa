package com.henry.configuration;

import com.henry.record.OracleRecord;
import com.henry.record.PostgreRecord;
import com.henry.record.MysqlRecord;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("spring.datasource")
@Data
public class DataSourceProperties {
   private MysqlRecord mysql;
   private PostgreRecord postgres;
   private OracleRecord oracle;

}

package com.yx.scan.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 系统接口扫描配置
 *
 * @author yx
 * @date 2019/12/17
 */
@Data
@ConfigurationProperties(prefix = "yx.scan")
public class ScanProperties {

    private ScanPersistenceType type = ScanPersistenceType.FEIGN;
}

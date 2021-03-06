package com.yx.log;


import com.alibaba.fastjson.JSONObject;
import com.yx.log.aspect.SysLogAspect;
import com.yx.log.event.SysLogListener;
import com.yx.log.interceptor.MdcMvcConfigurer;
import com.yx.log.monitor.PointUtil;
import com.yx.log.properties.OptLogProperties;
import com.yx.log.aspect.SysLogAspect;
import com.yx.log.event.SysLogListener;
import com.yx.log.interceptor.MdcMvcConfigurer;
import com.yx.log.monitor.PointUtil;
import com.yx.log.properties.OptLogProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 日志自动配置
 * <p>
 * 启动条件：
 * 1，存在web环境
 * 2，配置文件中yx.log.enabled=true 或者 配置文件中不存在：yx.log.enabled 值
 *
 * @author yx
 * @date 2019/2/1
 */
@EnableAsync
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = OptLogProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class LogAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SysLogAspect sysLogAspect() {
        return new SysLogAspect();
    }

    @Bean
    public MdcMvcConfigurer getMdcMvcConfigurer() {
        return new MdcMvcConfigurer();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnExpression("${yx.log.enabled:true} && 'LOGGER'.equals('${yx.log.type:LOGGER}')")
    public SysLogListener sysLogListener() {
        return new SysLogListener((log) -> {
            PointUtil.debug("0", "OPT_LOG", JSONObject.toJSONString(log));
        });
    }
}

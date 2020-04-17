package com.yx.security.config;

import com.yx.security.aspect.AuthAspect;
import com.yx.security.auth.AuthFun;
import com.yx.security.feign.UserResolverService;
import com.yx.security.properties.ContextProperties;
import com.yx.security.properties.UserProperties;
import com.yx.security.aspect.AuthAspect;
import com.yx.security.properties.ContextProperties;
import com.yx.security.properties.UserProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

/**
 * 权限认证配置类
 *
 * @author yx
 * @date 2020年03月29日22:34:45
 */
@Order
@AllArgsConstructor
@EnableConfigurationProperties({UserProperties.class, ContextProperties.class})
public class SecureConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = UserProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
    public AuthAspect authAspect(AuthFun authFun) {
        return new AuthAspect(authFun);
    }

    @Bean("fun")
    public AuthFun getAuthFun(UserResolverService userResolverService) {
        return new AuthFun(userResolverService);
    }

    @Bean
    public GlobalMvcConfigurer getGlobalMvcConfigurer(ContextProperties contextProperties) {
        return new GlobalMvcConfigurer(contextProperties);
    }

}

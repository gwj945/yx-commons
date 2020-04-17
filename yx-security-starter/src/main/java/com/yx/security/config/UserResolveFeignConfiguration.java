package com.yx.security.config;

import com.yx.base.R;
import com.yx.security.feign.UserQuery;
import com.yx.security.feign.UserResolverService;
import com.yx.security.model.SysUser;
import com.yx.utils.SpringUtils;
import com.yx.security.model.SysUser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 类型为Feign时，使用的的实现类
 *
 * @author yx
 * @date 2020年02月29日21:35:37
 */
@Configuration
@ConditionalOnProperty(name = "yx.scan.type", havingValue = "FEIGN", matchIfMissing = true)
@EnableFeignClients(basePackageClasses = UserResolveFeignConfiguration.UserResolveApi.class)
public class UserResolveFeignConfiguration {

    @Bean
    @ConditionalOnMissingBean(UserResolverService.class)
    public UserResolverService getUserResolverService(UserResolveApi userResolveApi) {
        return new UserResolverServiceFeignImpl(userResolveApi);
    }

    @Bean
    @ConditionalOnMissingBean(SpringUtils.class)
    public SpringUtils getSpringUtils(ApplicationContext applicationContext) {
        SpringUtils.setApplicationContext(applicationContext);
        return new SpringUtils();
    }

    @FeignClient(name = "${yx.feign.oauth-server:yx-oauth-server}", path = "/user",
            fallback = UserResolveApiFallback.class)
    public interface UserResolveApi {

        /**
         * 根据id 查询用户详情
         *
         * @param id
         * @param userQuery
         * @return
         */
        @PostMapping(value = "/anno/id/{id}")
        R<SysUser> getById(@PathVariable("id") Long id, @RequestBody UserQuery userQuery);
    }

    /**
     * feign 实现
     *
     * @author yx
     * @date 2020年02月24日10:51:46
     */
    public class UserResolverServiceFeignImpl implements UserResolverService {
        final UserResolveApi userResolveApi;

        public UserResolverServiceFeignImpl(UserResolveApi userResolveApi) {
            this.userResolveApi = userResolveApi;
        }

        @Override
        public R<SysUser> getById(Long id, UserQuery userQuery) {
            return userResolveApi.getById(id, userQuery);
        }
    }

    /**
     * 用户API熔断
     *
     * @author yx
     * @date 2019/07/10
     */
    @Component
    public class UserResolveApiFallback implements UserResolveApi {
        @Override
        public R<SysUser> getById(Long id, UserQuery userQuery) {
            return R.timeout();
        }
    }
}

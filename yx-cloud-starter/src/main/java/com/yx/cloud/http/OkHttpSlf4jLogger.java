package com.yx.cloud.http;

import lombok.extern.slf4j.Slf4j;

/**
 * OkHttp Slf4j logger
 *
 * @author yx
 */
@Slf4j
public class OkHttpSlf4jLogger implements HttpLoggingInterceptor.Logger {
    @Override
    public void log(String message) {
        log.info(message);
    }
}

package com.yx.log.event;


import com.yx.log.entity.OptLogDTO;
import com.yx.log.entity.OptLogDTO;
import org.springframework.context.ApplicationEvent;

/**
 * 系统日志事件
 *
 * @author zuihou
 * @date 2019-07-01 15:13
 */
public class SysLogEvent extends ApplicationEvent {

    public SysLogEvent(OptLogDTO source) {
        super(source);
    }
}

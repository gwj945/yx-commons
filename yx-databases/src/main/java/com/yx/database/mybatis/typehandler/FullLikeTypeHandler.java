package com.yx.database.mybatis.typehandler;

import org.apache.ibatis.type.Alias;

/**
 * 仅仅用于like查询
 *
 * @author yx
 */
@Alias("fullLike")
public class FullLikeTypeHandler extends BaseLikeTypeHandler {
    public FullLikeTypeHandler() {
        super(true, true);
    }
}

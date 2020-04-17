package com.yx.database.mybatis.typehandler;


import org.apache.ibatis.type.Alias;

/**
 * 仅仅用于like查询
 *
 * @author yx
 */
@Alias("leftLike")
public class LeftLikeTypeHandler extends BaseLikeTypeHandler {
    public LeftLikeTypeHandler() {
        super(true, false);
    }
}

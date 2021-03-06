package com.yx.scan.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * API接口
 * </p>
 *
 * @author yx
 * @since 2019-12-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "SystemApiSaveDTO", description = "API接口")
public class SystemApiSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接口编码
     */
    @ApiModelProperty(value = "接口编码")
    @NotEmpty(message = "接口编码不能为空")
    @Length(max = 255, message = "接口编码长度不能超过255")
    private String code;
    /**
     * 接口名称
     */
    @ApiModelProperty(value = "接口名称")
    @NotEmpty(message = "接口名称不能为空")
    @Length(max = 100, message = "接口名称长度不能超过100")
    private String name;
    /**
     * 资源描述
     */
    @ApiModelProperty(value = "资源描述")
    @Length(max = 100, message = "资源描述长度不能超过100")
    private String describe;
    /**
     * 请求方式
     */
    @ApiModelProperty(value = "请求方式")
    @Length(max = 255, message = "请求方式长度不能超过255")
    private String requestMethod;
    /**
     * 响应类型
     */
    @ApiModelProperty(value = "响应类型")
    @Length(max = 255, message = "响应类型长度不能超过255")
    private String contentType;
    /**
     * 服务ID
     */
    @ApiModelProperty(value = "服务ID")
    @NotEmpty(message = "服务ID不能为空")
    @Length(max = 50, message = "服务ID长度不能超过50")
    private String serviceId;
    /**
     * 请求路径
     */
    @ApiModelProperty(value = "请求路径")
    @Length(max = 255, message = "请求路径长度不能超过255")
    private String path;
    /**
     * 状态
     * :0-无效 1-有效
     */
    @ApiModelProperty(value = "状态")
    private Boolean status;
    /**
     * 保留数据
     * 0-否 1-是 系统内资数据,不允许删除
     */
    @ApiModelProperty(value = "保留数据")
    private Boolean isPersist;
    /**
     * 是否需要认证
     * : 0-无认证 1-身份认证 默认:1
     */
    @ApiModelProperty(value = "是否需要认证")
    private Boolean isAuth;
    /**
     * 是否公开
     * : 0-内部的 1-公开的
     */
    @ApiModelProperty(value = "是否公开")
    private Boolean isOpen;
    /**
     * 类名
     */
    @ApiModelProperty(value = "类名")
    @Length(max = 255, message = "类名长度不能超过255")
    private String className;
    /**
     * 方法名
     */
    @ApiModelProperty(value = "方法名")
    @Length(max = 255, message = "方法名长度不能超过255")
    private String methodName;


}

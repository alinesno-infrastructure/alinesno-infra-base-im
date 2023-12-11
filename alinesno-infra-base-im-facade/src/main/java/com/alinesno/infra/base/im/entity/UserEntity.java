package com.alinesno.infra.base.im.entity;

import com.alinesno.infra.base.im.enums.AccountType;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

// 用户实体详情类
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("account_info")
public class UserEntity extends InfraBaseEntity {

    @Column(type = MySqlTypeConstant.TINYINT, isNull = true, comment = "账号类型(0平台账号|1Agent账号|9其它类型)")
    private int accountType = AccountType.PLATFORM.getValue() ;  // 账号类型(0平台账号|1Agent账号|9其它类型)

    @Column(type = MySqlTypeConstant.BIGINT, length = 11, isNull = false, comment = "用户ID")
    private Long accountId;

    @Column(type = MySqlTypeConstant.VARCHAR , length = 50, isNull = false, comment = "用户名")
    private String accountName;

    @Column(type = MySqlTypeConstant.VARCHAR , length = 50, isNull = false, comment = "角色名称")
    private String roleName;

    @Column(type = MySqlTypeConstant.VARCHAR, length = 100, isNull = true, comment = "用户头像存储路径")
    private String avatar;

    @Column(type = MySqlTypeConstant.VARCHAR, length = 20, isNull = true, comment = "用户状态")
    private String status;

    @Column(type = MySqlTypeConstant.VARCHAR, length = 32, isNull = true, comment = "Agent账户ID")
    private String agentId ; // Agent账户ID

}
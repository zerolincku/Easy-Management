package com.linck.management.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linck.management.system.entity.SysUser;
import com.linck.management.system.model.dto.LoginUserDTO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author linck
 * @since 2020-08-09
 */
public interface SysUserService extends IService<SysUser> {
    /**
     * 登录功能
     *
     * @param loginUser
     * @return 生成JWT的Token
     */
    String login(LoginUserDTO loginUser);

    /**
     * 注册功能
     *
     * @param loginUser
     * @return
     */
    SysUser register(LoginUserDTO loginUser);

}

package com.xz.platform.mapper.custom;

import com.xz.platform.model.auto.TsysUser;

/**
 * @ClassName: TsysUserDao
 * @author xz
 * @date 2018年8月25日
 *
 */
public interface TsysUserDao {
	/**
	 * 根据用户名字查询用户
	 * @param username
	 * @return
	 */
	public TsysUser queryUserName(String username);
}

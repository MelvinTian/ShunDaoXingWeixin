/*******************************************************************************
 * 
 * 
 * 
 * 
 ******************************************************************************/
package com.sdx.common.service;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.stereotype.Service;

/*
 import com.cnpc.oms.sys.domain.SysAccount;
 import com.cnpc.oms.sys.service.IAccountService;
 import com.cnpc.oms.sys.service.IDictionaryService;
 import com.cnpc.oms.utils.exception.CustomException.AccountNotFountException;
 import com.cnpc.oms.utils.exception.CustomException.LockedException;
 import com.cnpc.oms.utils.exception.CustomException.PasswordErrorException;
 import com.cnpc.oms.utils.security.Digests;
 */
/*******************************************************************************
 * 自定义Realm方法，用于认证和授权的自定义操作 ShiroFilter 回调认证、授权方法，以完成用户身份鉴定、授权功能
 * @author 刘树凯
 * @since 2013-3-26
 ******************************************************************************/
@Service
public class ShiroRealm extends AuthorizingRealm
{

	/*
	 * @Autowired IAccountService accountService;
	 * @Autowired IDictionaryService dictionaryService;
	 */

	/**
	 * 认证方法，认证是用户登录时加载shiro的login方法时加载;
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException
	{
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		return null;
	}

	/**
	 * 授权方法，授权是首次登录后第一次访问需要权限页面时进行加载
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		String userName = (String) principals.fromRealm(getName()).iterator().next();
		return null;
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal)
	{
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

}

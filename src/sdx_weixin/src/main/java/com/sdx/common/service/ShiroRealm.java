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
 * 
 * @author 刘树凯
 * @since 2013-3-26
 ******************************************************************************/
@Service
public class ShiroRealm extends AuthorizingRealm {

	/*@Autowired
	IAccountService accountService;
	@Autowired
	IDictionaryService dictionaryService;*/

	/**
	 * 认证方法，认证是用户登录时加载shiro的login方法时加载;
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		 UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
	        // 根据登录账号查找是否有对应的账号
	      /*  SysAccount account = accountService.findAccount(token.getUsername());
	        // 用户名不存在
	        if (account == null)
	        {
	            // 抛出用户名不存在异常!
	            throw new AccountNotFountException();
	        }
	        else
	        {
	            // 获得用户名与密码
	            String username = token.getUsername();
	            String passwordUnMd5 = String.valueOf(token.getPassword());
	            
	            // 与数据库中用户名和密码进行比对。比对成功则返回info，比对失败则抛出对应信息的异常AuthenticationException
	            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, passwordUnMd5.toCharArray(), getName());
	            String password = Digests.encodeByMd5(passwordUnMd5);
	            if (username.equals(account.getAccountname()) && password.equals(account.getPassword()))
	            {
	                // 判断是否锁定
	                if (account.getIslocked() == 1)
	                {
	                    // 抛出锁定异常
	                    throw new LockedException();
	                }else if(!"1".equals(passwordMgtService.comfirmPasswordPolicy(username, passwordUnMd5,account.getUpdatepwdtime()))){
	                    //密码策略不正确
	                    throw new PasswordPolicyErrorException();
	                }
	                else
	                {
	                    // 无错误，返回信息
	                    return info;
	                }
	            }
	            else
	            {
	                // 抛出密码错误异常
	                throw new PasswordErrorException();
	            }
	        }*/

		/*
		 * Subject currentUser = SecurityUtils.getSubject(); Session session =
		 * currentUser.getSession();
		 * 
		 * try { UsernamePasswordToken token = (UsernamePasswordToken)
		 * authcToken; SysAccount account = accountService
		 * .findAccount(token.getUsername()); // 获得用户名与密码 String username =
		 * token.getUsername(); String password =
		 * String.valueOf(token.getPassword()); //
		 * 与数据库中用户名和密码进行比对。比对成功则返回info，比对失败则抛出对应信息的异常AuthenticationException
		 * SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
		 * username, password.toCharArray(), getName());
		 * password=Digests.encodeByMd5(password); if
		 * (username.equals(account.getAccountname()) &&
		 * password.equals(account.getPassword())) {
		 * session.setAttribute("account", account); // 初始化数据字典
		 * dictionaryService.initDictionaryStaticCache();
		 * System.out.println("login success"); return info; } else { return
		 * null; } } catch (Exception e) { e.printStackTrace(); } return null;
		 */
	        return null;
	}

	/**
	 * 授权方法，授权是首次登录后第一次访问需要权限页面时进行加载
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String userName = (String) principals.fromRealm(getName()).iterator()
				.next();
	/*	SysAccount user = accountService.findAccount(userName);
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			info.addRole("admin");
			System.out.println("login sccuess2");
			return info;
		} else {
			return null;
		}*/
		return null;
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

}


package com.sdx.weixin.client;

/**
 * 移动端处理异常
 * @author 田广文
 * @date 2014年5月19日-下午3:00:58
 */
public class ClientException extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = -2817729293470192764L;

    private Exception e;

    private String msg;

    public ClientException(String msg)
    {
        this.msg = msg;
    }

    public ClientException(String msg, Exception e)
    {
        this.msg = msg;
        this.e = e;
    }

    /**
     * @return the e
     */
    public Exception getException()
    {
        return e;
    }

    /**
     * @return the msg
     */
    public String getMsg()
    {
        return msg;
    }
}
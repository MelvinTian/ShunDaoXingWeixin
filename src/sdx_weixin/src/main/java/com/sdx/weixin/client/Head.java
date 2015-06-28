
package com.sdx.weixin.client;

/**
 * 接口请求头
 * @author 田广文
 * @date 2014年5月19日-下午3:00:15
 */
public class Head
{
    private int clientType = 0; // 1: android 2: iPhone 3: iPad

    private boolean isCompress; // 是否压缩

    private boolean isEncript; // 是否加密

    private String type; // 请求类型

    private String ver; // 版本号

    private String desc;

    private int status;

    public Head(int clientType, boolean isCompress, boolean isEncript)
    {
        this.clientType = clientType;
        this.isCompress = isCompress;
        this.isEncript = isEncript;
    }

    /**
     * @return the clientType
     */
    public int getClientType()
    {
        return clientType;
    }

    /**
     * @param clientType
     *        the clientType to set
     */
    public Head setClientType(int clientType)
    {
        this.clientType = clientType;
        return this;
    }

    /**
     * @return the isCompress
     */
    public boolean isCompress()
    {
        return isCompress;
    }

    /**
     * @param isCompress
     *        the isCompress to set
     */
    public Head setCompress(boolean isCompress)
    {
        this.isCompress = isCompress;
        return this;
    }

    /**
     * @return the isEncript
     */
    public boolean isEncript()
    {
        return isEncript;
    }

    /**
     * @param isEncript
     *        the isEncript to set
     */
    public Head setEncript(boolean isEncript)
    {
        this.isEncript = isEncript;
        return this;
    }

    /**
     * @return the type
     */
    public String getType()
    {
        return type;
    }

    /**
     * @param type
     *        the type to set
     */
    public Head setType(String type)
    {
        this.type = type;
        return this;
    }

    /**
     * @return the ver
     */
    public String getVer()
    {
        return ver;
    }

    /**
     * @param ver
     *        the ver to set
     */
    public Head setVer(String ver)
    {
        this.ver = ver;
        return this;
    }

    /**
     * @return the desc
     */
    public String getDesc()
    {
        return desc;
    }

    /**
     * @param desc
     *        the desc to set
     */
    public Head setDesc(String desc)
    {
        this.desc = desc;
        return this;
    }

    /**
     * @return the status
     */
    public int getStatus()
    {
        return status;
    }

    /**
     * @param status
     *        the status to set
     */
    public Head setStatus(int status)
    {
        this.status = status;
        return this;
    }

}

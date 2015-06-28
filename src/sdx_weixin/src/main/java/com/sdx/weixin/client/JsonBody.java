
package com.sdx.weixin.client;

import com.sdx.utils.JSONUtil;

import net.sf.json.JSONObject;

/**
 * 接口内容
 * @author 田广文
 * @date 2014年5月19日-下午3:00:58
 */
public class JsonBody
{
    private Head header = null;

    private JSONObject body = new JSONObject();

    public JsonBody(int clientType, boolean isCompress, boolean isEncript)
    {
        this.header = new Head(clientType, isCompress, isEncript);
    }

    public JsonBody setContent(String content)
    {
        JSONObject body = JSONUtil.parseJSONObject(content);
        JSONObject header = body.getJSONObject("header");
        this.header.setType(header.getString("type"));
        this.header.setVer(header.getString("ver"));
        this.body = body.getJSONObject("body");
        return this;
    }

    public Head getHeader()
    {
        return header;
    }

    public JSONObject getBody()
    {
        return body;
    }
}
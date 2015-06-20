package com.sdx.utils.common;

/**
 * 查询数据模板方法
 */
public final class SqlUtils
{
    private SqlUtils()
    {
    }

    public static String generateLikeString(String word)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("'%");
        for (int i = 0; i < word.length(); i++)
        {
            switch (word.charAt(i))
            {
                case '_':
                    sb.append("/_");
                    break;
                case '%':
                    sb.append("/%");
                    break;
                case '\\':
                    sb.append("//");
                    break;
                case '\'':
                    sb.append("/'");
                    break;
                case '\"':
                    sb.append("/\"");
                    break;
                case '/':
                    break;
                default:
                    sb.append(word.charAt(i));
            }
        }
        sb.append("%' escape '/'");
        return sb.toString();
    }
}

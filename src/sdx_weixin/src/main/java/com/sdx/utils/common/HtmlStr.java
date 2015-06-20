package com.sdx.utils.common;


public final class HtmlStr
{
    public static String htmlEncode(String s)
    {
        if(s == null)
            return "";
        StringBuilder stringbuilder = new StringBuilder();
        for(int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            switch(c)
            {
            case 34: // '"'
                stringbuilder.append("&quot;");
                break;

            case 38: // '&'
                stringbuilder.append("&amp;");
                break;

            case 60: // '<'
                stringbuilder.append("&lt;");
                break;

            case 62: // '>'
                stringbuilder.append("&gt;");
                break;

            case 39: // '\''
                stringbuilder.append("&#039;");
                break;

            case 9: // '\t'
            case 32: // ' '
                stringbuilder.append("&nbsp;");
                break;

            default:
                stringbuilder.append(c);
                break;
            }
        }

        return stringbuilder.toString();
    }

    public static String htmlEncode(char c)
    {
        switch(c)
        {
        case 34: // '"'
            return "&quot;";

        case 38: // '&'
            return "&amp;";

        case 60: // '<'
            return "&lt;";

        case 62: // '>'
            return "&gt;";

        case 39: // '\''
            return "&#039;";

        case 9: // '\t'
        case 32: // ' '
            return "&nbsp;";
        }
        return (new StringBuilder()).append("").append(c).toString();
    }
}

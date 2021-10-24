/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package cn.yuan.yutool.util;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * 使用jackons进行json和对象砬之间的转换
 *
 * @author PanFowa
 * @date 2021-10-23
 * @version 1.0-jdk1.6
 * @since 1.0-jdk1.6
 */
public class JsonUtils
{
    public JsonUtils()
    {
    }

    


    private static String handleString(String jsonString)
    {
        StringBuffer strBuf = new StringBuffer(jsonString);
        strBuf.deleteCharAt(0);
        strBuf.deleteCharAt(strBuf.length() - 1);
        String jsons[] = strBuf.toString().split(",");
        if(jsons != null)
        {
            String newJsons[] = new String[jsons.length];
            for(int i = 0; i < jsons.length; i++)
            {
                String json = jsons[i];
                String keyValue[] = json.split(":");
                if(keyValue != null)
                {
                    String keyString = keyValue[0];
                    StringBuffer key = new StringBuffer(keyString);
                    if('"' != keyString.charAt(0))
                        key.insert(0, '"');
                    if('"' != keyString.charAt(keyString.length() - 1))
                        key.append('"');
                    keyValue[0] = key.toString();
                    json = StringUtils.join(keyValue, ":");
                }
                newJsons[i] = json;
            }

            jsonString = StringUtils.join(newJsons, ",");
        }
        strBuf = new StringBuffer(jsonString);
        strBuf.insert(0, "{");
        strBuf.append("}");
        jsonString = strBuf.toString();
        return jsonString;
    }


    
    public static String obj2Json(Object obj)  {
        ObjectMapper mapper = new ObjectMapper();
        String res = null;
        try {
            res = mapper.writeValueAsString(obj);
        } catch (Exception e) {
        }
        return res;
    }
    
    public static <T> T json2Obj(String jsonStr, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        T res = null;
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        res = mapper.readValue(jsonStr, clazz);
        return res;
    }

    public static String toJsonStr(Object obj)
    {
        String jsonStr = new String();
        if(obj == null)
            return "";
        try {
            jsonStr = getInstance().writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonStr;
    }

    public static ObjectMapper getInstance()
    {
        return jsonOm;
    }

    public static final ObjectMapper jsonOm = new ObjectMapper();
}


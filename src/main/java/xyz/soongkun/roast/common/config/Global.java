package xyz.soongkun.roast.common.config;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import xyz.soongkun.roast.common.utils.PropertiesLoader;

import java.util.Map;

/**
 * 全局配置
 */
public class Global {

    /**
     * 保存全局属性值
     */
    private static Map<String, String> sMap = Maps.newHashMap();

    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader loader = new PropertiesLoader("kucrm.properties");

    /**
     * 页面获取常量
     * @see ${fns:getConst('YES')}
     */
    public static Object getConst(String field) {
        try {
            return Global.class.getField(field).get(null);
        } catch (Exception e) {
            // 异常代表无配置，这里什么也不做
        }
        return null;
    }


    /**
     * 获取配置
     * @see ${fns:getConfig('adminPath')}
     */
    public static String getConfig(String key) {
        String value = sMap.get(key);
        if (value == null){
            value = loader.getProperty(key);
            sMap.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }
}

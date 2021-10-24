package cn.yuan.yutool.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class IterUtil {
    /**
     * 将Entry集合转换为HashMap
     *
     * @param <K>       键类型
     * @param <V>       值类型
     * @param entryIter entry集合
     * @return Map
     */
    public static <K, V> HashMap<K, V> toMap(Iterable<Map.Entry<K, V>> entryIter) {
        final HashMap<K, V> map = new HashMap();
        if (isNotEmpty(entryIter)) {
            for (Map.Entry<K, V> entry : entryIter) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        return map;
    }

    /**
     * 将键列表和值列表转换为Map<br>
     * 以键为准，值与键位置需对应。如果键元素数多于值元素，多余部分值用null代替。<br>
     * 如果值多于键，忽略多余的值。
     *
     * @param <K>    键类型
     * @param <V>    值类型
     * @param keys   键列表
     * @param values 值列表
     * @return 标题内容Map
     * @since 1.0-jdk1.6
     */
    public static <K, V> Map<K, V> toMap(Iterable<K> keys, Iterable<V> values) {
        return toMap(null == keys ? null : keys.iterator(), null == values ? null : values.iterator());
    }

    /**
     * 将键列表和值列表转换为Map<br>
     * 以键为准，值与键位置需对应。如果键元素数多于值元素，多余部分值用null代替。<br>
     * 如果值多于键，忽略多余的值。
     *
     * @param <K>    键类型
     * @param <V>    值类型
     * @param keys   键列表
     * @param values 值列表
     * @return 标题内容Map
     * @since 1.0-jdk1.6
     */
    public static <K, V> Map<K, V> toMap(Iterator<K> keys, Iterator<V> values) {
        final Map<K, V> resultMap = new HashMap();
        if (isNotEmpty(keys)) {
            while (keys.hasNext()) {
                resultMap.put(keys.next(), (null != values && values.hasNext()) ? values.next() : null);
            }
        }
        return resultMap;
    }

    /**
     * Iterable是否为空
     *
     * @param iterable Iterable对象
     * @return 是否为空
     */
    public static boolean isNotEmpty(Iterable<?> iterable) {
        return null != iterable && isNotEmpty(iterable.iterator());
    }

    /**
     * Iterator是否为空
     *
     * @param Iterator Iterator对象
     * @return 是否为空
     */
    public static boolean isNotEmpty(Iterator<?> Iterator) {
        return null != Iterator && Iterator.hasNext();
    }
}

package com.huizhaobiao.User.common;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by Administrator on 2019/7/10.
 */
public class MapUtil {

    /*
    * 将map转换为Object对象，转换全部按照（key和对象之间的关系进行转换）
    * */
    public static <T> T  mapToObject(Map<String, Object> map, T t) {
        return mapToObject(map, t,null);
    }

    /*删除map中指定的key值*/

    public static boolean removeEntries(Map<String, Object> map,String[] excludeKeys)
    {

        for (String excludeKey:excludeKeys)
        {

            map.remove(excludeKey);
        }
        return true;
    }



   /*
   * 将map转换为object对象，排除指定key
   * */
   public static <T> T  mapToObject(Map<String, Object> map, T t, String[] excludeKeys) {
       Class beanClass = t.getClass();
       String[] declaredFieldsName = getDeclaredFieldsName(beanClass);
       if (ArrayUtils.isNotEmpty(excludeKeys)) {
           MapUtil.removeEntries(map, excludeKeys);
       }

       for (Object k : map.keySet()) {
           Object v = map.get(k);
           if (ArrayUtils.contains(declaredFieldsName, k.toString())) {
               try {
                   Field field = beanClass.getDeclaredField(k.toString());
                   field.setAccessible(true);
                   field.set(t, v);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }
       return t;
   }

   /*
   * 获取转换好的对象的所有属性名称，以字符串数组形式返回
   * */
    public static String[] getDeclaredFieldsName(Class beanClass) {
        Field[] fields = beanClass.getDeclaredFields();
        int size = fields.length;
        String[] fieldsName = new String[size];
        for (int i = 0; i < size; i++) {
            fieldsName[i] = fields[i].getName();
        }
        return fieldsName;
    }


}

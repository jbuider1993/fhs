package com.fhs.core.trans;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fhs.common.utils.ReflectUtils;

public class ClassInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final Logger LOGGER = LoggerFactory.getLogger(ClassInfo.class);

    private Class<?> clazz;

    private Field idField;

    String[] transTypes;

    private Map<String, Field> fieldMap = new HashMap<String, Field>();

    /**
     * 获取翻译字段 key 翻译的类型比如字典的类型为wordbook
     */
    private Map<String,List<Field>> transFieldMap = new HashMap<String,List<Field>>();

    public ClassInfo() {
        super();
    }

    public <T> ClassInfo(Class<?> clazz) throws InstantiationException, IllegalAccessException {
        super();
        this.clazz = clazz;
        getClazzFieldMap();
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Field getIdField() {
        return idField;
    }

    public void setIdField(Field idField) {
        this.idField = idField;
    }

    public Map<String, Field> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, Field> fieldMap) {
        this.fieldMap = fieldMap;
    }

    /**
     * 获取需要翻译的类型
     * @return 需要翻译的类型
     */
    public String[] getTransTypes()
    {
        return transTypes;
    }

    /**
     * 获取需要翻译的字段
     * @param type 翻译类型
     * @return 字段集合
     */
    public List<Field> getTransField(String type){
        return  transFieldMap.get(type);
    }

    private void getClazzFieldMap() throws InstantiationException, IllegalAccessException {
        // PO 类和其祖先类声明的字段名称集合
        List<Field> declaredFields = ReflectUtils.getAllField(clazz.newInstance());
        TransTypes transTypes = clazz.getAnnotation(TransTypes.class);
        if(transTypes!=null)
        {
            this.transTypes = transTypes.types();
        }
        int mod = 0;
        // 循环遍历所有的属性进行判断
        for (Field field : declaredFields) {
            mod = field.getModifiers();
            // 如果是 static, final, volatile, transient 的字段，则直接跳过
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod) || Modifier.isVolatile(mod)) {
                continue;
            }


            Trans trans = field.getAnnotation(Trans.class) != null ? field.getAnnotation(Trans.class) : null;
            if(trans!=null)
            {
                if(trans.type() == null || trans.key() == null)
                {
                    LOGGER.warn("类 {} 属性 [{}]  key 或者type为空。", clazz.getName(), field.getName());
                }
                else
                {
                    List<Field> fieldList = transFieldMap.get(trans.type());
                    fieldList = fieldList != null ? fieldList : new ArrayList<Field>();
                    fieldList.add(field);
                    transFieldMap.put(trans.type(),fieldList);
                }
            }
        }
    }

    public Map<String, List<Field>> getTransFieldMap() {
        return transFieldMap;
    }

    public void setTransFieldMap(Map<String, List<Field>> transFieldMap) {
        this.transFieldMap = transFieldMap;
    }

    public void setTransTypes(String[] transTypes) {
        this.transTypes = transTypes;
    }


}

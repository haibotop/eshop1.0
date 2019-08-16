package com.gsj.www.common.util;

import com.sun.org.apache.regexp.internal.RE;

import javax.management.relation.Relation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 基础POJO类
 *
 * @author Holy
 * @create 2019 - 07 - 23 7:12
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class AbstractObject {
    /**
     * 浅度克隆
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T clone(Class<T> clazz) throws Exception{
        T target = clazz.newInstance();
        BeanCopierUtils.copyProperties(this,target);
        return target;
    }

    /**
     * 深度克隆
     * @param clazz
     * @param cloneDirection
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T clone(Class<T> clazz, Integer cloneDirection) throws Exception{
        //先完成基本字段的浅克隆
        T target = clazz.newInstance();
        BeanCopierUtils.copyProperties(this, target);

        //完成所有List类型的深度克隆
        Class<?> thisClazz = this.getClass(); //categoryDTO

        Field[] fields = thisClazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            //如果判断某个字段是List类型的
            if(field.getType() == List.class){//field = private List<Relation> relations;
                //field.getType() List 不是 List<Relation>

                //获取List集合中的范型类型
                Class<?> listGenericClazz = getListGenericType(field);//RelationDTO
                //获取要克隆的目标类型
                Class<?> cloneTargetClazz = getCloneTargetClazz(listGenericClazz, cloneDirection);
                //假设CloneDirection是反向，此时获取到的就是RelationVO

                //将list集合克隆到目标list集合中去
                List cloneList = new ArrayList();
                List<?> list = (List<?>)field.get(this);//List<RelationDTO>
                cloneList(list,cloneList,cloneTargetClazz,cloneDirection);

                //获取设置克隆好的list的方法名称
                Method setFieldMethod = getSetCloneListFieldMethodName(field,clazz);//setRlations
                setFieldMethod.invoke(target, cloneList);
                //target是Category对象，此时就是调用CatagoryVO的setRelations方法，将克隆好的List<CategoryVO给设置进去
            }
        }
        return target;
    }

    /**
     * 将一个List克隆到另外一个List
     * @param sourceList
     * @param targetList
     * @param cloneTargetClazz
     * @param cloneDirection
     * @throws Exception
     */
    private void cloneList(List sourceList, List targetList, Class cloneTargetClazz, Integer cloneDirection) throws  Exception{
        for (Object o : sourceList) {
            AbstractObject targetObject = (AbstractObject) o;
            AbstractObject clonedObject = (AbstractObject) targetObject.clone(cloneTargetClazz,cloneDirection);
            //将集合中的RelationDT，调用其clone()方法，将其往RelationVO去克隆
            targetList.add(clonedObject);
        }
    }

    /**
     * 获取List集合的泛型类型
     * @param field
     * @return
     * @throws Exception
     */
    private Class<?> getListGenericType(Field field) throws Exception{
        // genericType = List<RelationDTO>，不是List
        Type genericType = field.getGenericType();
        if(genericType instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            return (Class<?>) parameterizedType.getActualTypeArguments()[0];
        }
        return null;
    }

    /**
     * 获取目标类名
     * @param clazz
     * @param cloneDirection
     * @return
     * @throws Exception
     */
    private Class<?> getCloneTargetClazz(Class<?> clazz, Integer cloneDirection) throws Exception{
        String cloneTargetClassName = null;

        String className = clazz.getName();//ReflectionDTO

        if(cloneDirection.equals(CloneDirection.FORWARD)){
            if(className.endsWith(DomainType.VO)){
                cloneTargetClassName = className.substring(0, className.length() - 2) + "DTO";

            }else if(className.endsWith(DomainType.DTO)){
                cloneTargetClassName = className.substring(0, className.length() - 3) + "DO";
            }
        }

        if(cloneDirection.equals(CloneDirection.OPPOSITE)){
            if(className.endsWith(DomainType.DO)){
                cloneTargetClassName = className.substring(0, className.length() - 2) + "DTO";
            }else if(className.endsWith(DomainType.DTO)){
                cloneTargetClassName = className.substring(0, className.length() - 3) + "VO";
            }
        }
        return Class.forName(cloneTargetClassName);
    }

    /**
     * 获取设置克隆好的list的方法名称
     * @param field
     * @param clazz
     * @return
     * @throws Exception
     */
    private Method getSetCloneListFieldMethodName(Field field, Class<?> clazz) throws Exception{
        String name = field.getName();
        String setMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);

        Method setFieldMethod = null;

        for (Method method : clazz.getDeclaredMethods()) {
            if(method.getName().equals(setMethodName)){
                setFieldMethod = method;
                break;
            }
        }

        return setFieldMethod;
    }
}

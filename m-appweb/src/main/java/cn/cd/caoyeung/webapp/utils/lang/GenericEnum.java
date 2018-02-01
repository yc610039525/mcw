package cn.cd.caoyeung.webapp.utils.lang;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class GenericEnum <E> implements Serializable{
    private final TreeMap<E, String> enumMap = new TreeMap();

    public GenericEnum() {
    }

    public String getName(E value){
        return enumMap.get(value);
    }

    public E getValue(String name){
        E value = null;
        if(enumMap.containsValue(name)){
            Iterator i = enumMap.keySet().iterator();
            while(i.hasNext()){
                E key = (E)i.next();
                String enumName = enumMap.get(key);
                if(enumName.equals(name)){
                    value = key;
                    break;
                }
            }
        }else{
        	throw new RuntimeException("[" + this.getClass().getSimpleName() +
                    "]未知枚举名称：" + name);
        }
        return value;
    }

    public String[] getAllNames(){
        String[] names = new String[enumMap.size()];
        enumMap.values().toArray(names);
        return names;
    }

    public void putEnum(E value, String name){
        enumMap.put(value, name);
    }

    public Map<E, String> getAllEnum(){
        return this.enumMap;
    }
}

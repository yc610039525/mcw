
package cn.cd.caoyeung.webapp.utils.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
* 集合工具类
* 
* @author liuhua
*/
public class CollectionHelper {

	/**
	 * 判断一个集合对象是否为null或为空
	 * 
	 * @param collection
	 *            要检测的集合对象
	 * @return 若集合对象为null或为空，则返回true；否则返回false
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}

	/**
	 * 判断一个集合对象是否不为null且不为空
	 * 
	 * @param collection
	 *            要检测的集合对象
	 * @return 若集合对象不为null且不为空，则返回true；否则返回false
	 */
	public static boolean isNotEmpty(Collection<?> collection) {
		return !CollectionHelper.isEmpty(collection);
	}

	/**
	 * 得到集合的第一个元素
	 * 
	 * @param <T>
	 *            泛型
	 * @param collection
	 *            集合
	 * @return 集合的第一个元素，若集合为null，则返回null
	 */
	public static <T> T getFirst(Collection<T> collection) {
		return isEmpty(collection) ? null : collection.iterator().next();
	}
	/**
	 * 
	 * @param <T>
	 *          泛型
	 * @param list
	 * 		           集合
	 * @param pageSize
	 *          每组记录数
	 * @return  分组后的集合组成新的集合
	 */
	public static <T> List<List<T>> groupList(List<T> list,int pageSize){
		List<List<T>> resultList = new ArrayList<List<T>>();
		if(isEmpty(list)) return resultList;
		int total = list.size();
		int totalPages = total%pageSize == 0? total/pageSize:total/pageSize+1;
		for(int i=1 ;i<=totalPages;i++){
			resultList.add(list.subList((i-1)*pageSize, (i*pageSize)<=total?(i*pageSize):total));
		}
		return resultList;
	} 
	public static void main(String args[]){
		List<Integer> list = new ArrayList<Integer>();
		for(int i=1;i<=5;i++){
			list.add(i);
		}
		List<List<Integer>> l = groupList(list, 2);
		for(List<Integer> t:l){
			for(Integer i: t){
				System.out.println(i);
			}
		}
	}
}


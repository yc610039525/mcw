package cn.cd.caoyeung.webapp.utils.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class CollectionHelper {
	private CollectionHelper(){}
	private static final CollectionHelper helper=new CollectionHelper();
	private static CollectionHelper getInstance(){
		return helper;
	}

	public static void main(String[] args) {
		Map<String,Object> m1=new HashMap<String, Object>();
		m1.put("reA","A");
		m1.put("reB","B");
		Map<String,Object> m11=new HashMap<String, Object>();
		m11.put("reA","C");
		m11.put("reB","A");
		Map<String,Object> m111=new HashMap<String, Object>();
		m111.put("reA","C");
		m111.put("reB","D");
		Map<String,Object> m1111=new HashMap<String, Object>();
		m1111.put("reA","D");
		m1111.put("reB","A");
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		list.add(m1);
		list.add(m11);
		list.add(m111);
		list.add(m1111);
		List<Map<String,Object>>  result=CollectionHelper.sortObject(list);
	}
	
	public static List<Map<String,Object>>  sortObject(List<Map<String,Object>> objs){
		List<Map<String,Object>> newList=new ArrayList<Map<String,Object>>();
		if(newList.size()==0){
			newList.add(objs.get(0));
			objs.remove(0);
		}
		boolean flag=true;
		while (objs.size() > 0 && flag) {
			for (int i = 0; i < objs.size(); i++) {
				Map<String, Object> ele = objs.get(i);
				String reA = (String) ele.get("reA");
				String reB = (String) ele.get("reB");
				if (newList.get(0).get("reA").equals(reB)) {
					newList.add(0, ele);
					objs.remove(i);
					flag=true;
					break;
				} else if (newList.get(0).get("reA").equals(reA)) {
					ele.put("reA", reB);
					ele.put("reB", reA);
					newList.add(0, ele);
					objs.remove(i);
					flag=true;
					break;
				} else if (newList.get(newList.size() - 1).get("reB").equals(
						reA)) {
					newList.add(ele);
					objs.remove(i);
					flag=true;
					break;
				} else if (newList.get(newList.size() - 1).get("reB").equals(
						reB)) {
					ele.put("reA", reB);
					ele.put("reB", reA);
					newList.add(ele);
					objs.remove(i);
					flag=true;
					break;
				} else {
					flag = false;
				}
			}
		}
		return 	newList;	
	}
	
	
	
	public List<String> parseSet2List(Set<String> set){
		List<String> list=new ArrayList<String>();
		list.addAll(set);
		return list;
	}

	public void scanMap(Map<String, Object> mp) {
		for (Map.Entry<String, Object> ele : mp.entrySet()) {
			System.out.println(ele.getKey()+","+ele.getValue());
		}
	}
	public static List<Map<String, List<String>>> getIdGroup(List<Map<String, Object>> list, String idKey) {
		int groupIdx = 1;
		List<Map<String, List<String>>> idGroup = new ArrayList<Map<String, List<String>>>();
		for(Map<String, Object> obj : list) {
			String portid = obj.get(idKey).toString();
			List<String> ptpCuidList = null;
			
			if(idGroup.size() < groupIdx) {
				Map<String, List<String>> map = new HashMap<String, List<String>>();
				ptpCuidList = new ArrayList<String>();
				map.put("list", ptpCuidList);
				idGroup.add(map);
			}else {
				Map<String, List<String>> map = idGroup.get(groupIdx - 1);
				ptpCuidList = map.get("list");
			}
			
			ptpCuidList.add(portid);
			if(ptpCuidList.size() >= 1000) {
				groupIdx++;
			}
			
		}
		return idGroup;
	}
}

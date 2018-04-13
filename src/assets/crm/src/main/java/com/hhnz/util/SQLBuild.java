package com.hhnz.util;
/**
 * SQL分页神器
 * @author Yangcx
 *
 */
public class SQLBuild {

	/**
	 * 分页器
	 * @param sql 原SQL
	 * @param page 页码
	 * @param size 页数
	 * @return 分页SQL
	 */
	public static String pager(String sql,int page,int size){
		int start=(page-1)*size;
		int end = page*size;
		if(size == 0){
			return sql;
		}else{
			
			String rsql="SELECT * FROM (select tt.*,rownum rn from ( "+sql+") tt where rownum <="+end+") where rn >"+start;
			//String rsql="SELECT * FROM (select tt.*,rownum rn from ( "+sql+") tt ) where rn >"+start+" and rn <="+end ;
			return rsql;
		}
	}

	/**
	 * 分页器
	 * @param sql 原SQL
	 * @param size 页数
	 * @return 分页SQL
	 */
	public static String pagerLimit(String sql,int start,int size){
		int end = start+size;
		if(size == 0){
			return sql;
		}else{
			String rsql="SELECT * FROM (select tt.*,rownum rn from ( "+sql+") tt where rownum <="+end+") where rn >"+start;
			//String rsql="SELECT * FROM (select tt.*,rownum rn from ( "+sql+") tt ) where rn >"+start+" and rn <="+end ;
			return rsql;
		}
	}
	/**
	 * 分页器(规避版本）
	 * @param sql 原SQL
	 * @param page 页码
	 * @param size 页数
	 * @return 分页SQL
	 */
	public static String pagerO(String sql,int page,int size){
		int start=(page-1)*size;
		int end = page*size;
		if(size == 0){
			return sql;
		}else{
			
			String rsql="with v_sql as ("+sql+") SELECT * FROM (select tt.*,rownum rn from v_sql tt where rownum <="+end+") where rn >"+start;
			//String rsql="SELECT * FROM (select tt.*,rownum rn from ( "+sql+") tt ) where rn >"+start+" and rn <="+end ;
			return rsql;
		}
	}
}

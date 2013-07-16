package com.ly.comm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import org.nutz.dao.Cnd;

import com.ly.sys.vo.Employee;
import com.ly.util.DateUtils;

public class ParseObj {
	
	private Object obj = null;
	
	private Cnd cnd = null;
	
	private String params = "";
	
	public ParseObj(Object obj){
		if(obj == null){
			return;
		}
		this.obj = obj;
		this.parse();
	}
	
	/**
	 * 获取查询条件
	 * @return
	 */
	public Cnd getCnd() {
		return cnd;
	}

	/**
	 * 获取参数
	 * @return
	 */
	public String getParams() {
		return params;
	}


	private void parse(){
		
		Cnd c = null;
		StringBuilder sb = new StringBuilder();
		try {
			Object o;
			String typeName;
			Class<? extends Object> cls = obj.getClass();
			Field[] fields = cls.getDeclaredFields();
			for (Field f : fields) {
				o = invokeMethod(obj, f.getName(), null);
				if (o == null || o.equals(""))
					continue;
				typeName = f.getType().getSimpleName();
				if (typeName.equals("String")) {
					String s =o.toString().trim();
					if(c==null){
						c=Cnd.where(f.getName(), "like", "%"+s+"%");
					}else{
						c.and(f.getName(), "like", "%"+s+"%");
					}
				} else if (typeName.equals("Date")) {
					Date date = (Date) o;
					if(c==null){
						c=Cnd.where(f.getName(), "=", date);
						//Expression e = (Expression)new MyCnd("trunc("+f.getName()+")=to_date('"+DateUtils.format(date)+"','yyyy-mm-dd')");
						//c = Cnd.where(e);
					}else{
						//Expression e = (Expression)new MyCnd("trunc("+f.getName()+")=to_date('"+DateUtils.format(date)+"','yyyy-mm-dd')");
						//c.and(e);
						c.and(f.getName(), "=", date);
					}
				} else {
					if(c==null){	
						c=Cnd.where(f.getName(), "=", o);
					}else{
						c.and(f.getName(), "=", o);
					}
				}
				sb.append("&");
				sb.append(f.getName());
				sb.append("=");
				sb.append(o.toString().trim());
			}
		} catch (Exception exception) {
			c = null;
		}finally{
			this.cnd = c;
			this.params = sb.toString();
		}
	}


	/**
	 * 获取日期大小比较的SQL 条件
	 * 
	 * @param obj
	 *            传入对象
	 * @param ldate
	 *            小日期
	 * @param hdate
	 *            大日期
	 * @return
	 * @throws Exception
	 */
	public static String getSqlWhDate(Object obj, String ldate, String hdate)
			throws Exception {
		if (obj == null)
			return null;
		Object o;
		String typeName;
		StringBuilder sb = new StringBuilder();
		Class<? extends Object> cls = obj.getClass();
		Field[] fields = cls.getDeclaredFields();
		int i = 0;
		for (Field f : fields) {
			o = invokeMethod(obj, f.getName(), null);
			if (o == null)
				continue;
			if (i > 0) {
				sb.append(" and ");
			}

			typeName = f.getType().getSimpleName();
			if (typeName.equals("String")) {
				sb.append(f.getName()).append(" like '%").append(o.toString()).append("%' ");
			} else if (typeName.equals("Date")) {
				java.util.Date date = (java.util.Date) o;
				if (f.getName().equals(ldate)) {
					sb.append(f.getName()).append(">=to_date('").append(DateUtils.format(date)).append("','yyyy-mm-dd')");
				} else if (f.getName().equals(hdate)) {
					sb.append(f.getName()).append("<= to_date('").append(DateUtils.format(date)).append("','yyyy-mm-dd')");
				} else {
					sb.append(f.getName()).append("= to_date('").append(DateUtils.format(date)).append("','yyyy-mm-dd')");
				}
			} else {
				sb.append(f.getName()).append("=").append(o.toString());
			}

			System.out.println(typeName + "  "
					+ invokeMethod(obj, f.getName(), null));
			i++;
		}

		String sql = sb.toString();
		if (sql.endsWith("\r\nwhere ")) {
			sql = sql.substring(0, sql.lastIndexOf("\r\nwhere "));
		}
		if (sql.endsWith(" and \r\n")) {
			sql = sql.substring(0, sql.lastIndexOf(" and \r\n"));
		}
		return sql;
	}

        @SuppressWarnings({"null", "ConstantConditions"})
	private static Object invokeMethod(Object owner, String methodName,
			Object[] args) throws Exception {
		Class ownerClass = owner.getClass();

		methodName = methodName.substring(0, 1).toUpperCase()
				+ methodName.substring(1);
		Method method = null;
		try {
			method = ownerClass.getMethod("get" + methodName);
		} catch (Exception e) {
			method = null;
		} finally {
			return method.invoke(owner);
		}
	}

	public static void main(String[] args) throws Exception {
		Employee e = new Employee();
//		e.setAddr("李");
//		e.setAge(22);
		ParseObj par = new ParseObj(e);
		System.out.println(par.getCnd());
		System.out.println(par.getParams());
		
	}

}

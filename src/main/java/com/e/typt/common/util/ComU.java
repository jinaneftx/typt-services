package com.e.typt.common.util;

import java.math.BigDecimal;
import java.sql.Clob;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import org.springframework.web.context.ContextLoader;

/**
* 公用处理类(不包括日期相关的处理方法).<p/>
*    方法含义:<br/>
*      set*    :设置值<br/>
*      get*    :取得值<br/>
*      is*     :验证<br/>
*      format* :转换,格式化
*
* @author   张大松 
* @version  2011/07/07    
*/
public class ComU {
    /**  空字符串 */
    private static final String EMPTY = "";
	private ComU(){}
	
	
	
	//**********  设置值 ************************************************  start
	
	
	
	//**********  设置值 ************************************************  end
	//--------------------------------------------------------------------------
	//--------------------------------------------------------------------------
	//--------------------------------------------------------------------------
	//--------------------------------------------------------------------------

    /**
     * 根据属性名得到Map中的值  若为空返回空字符串""
     * 
     * @param   map   map集合
     * @param   name  map中的key
     * @return  根据key取得的值
     */
	public static  String getMapValue(Map<String,Object> map,String name){
        return map.get(name) ==  null ? EMPTY : map.get(name).toString() ;
    }
	
    /**
     * 根据属性名得到Map中的值  若为空返回字符串"null"
     * 
     * @param   map   map集合
     * @param   name  map中的key
     * @return  根据key取得的值
     */
	public static  String getMapValueNull(Map<String,Object> map,String name){
        return map.get(name) ==  null ? "null" : map.get(name).toString() ;
    }
	
    /**
     * 若对象为null,则返回""，否则返回字符串格式的对象值
     * 
     * @param   obj  源对象
     * @return  对象转换的字符串
     */
	public static  String getStr(Object obj){
        return obj ==  null ? EMPTY : obj.toString() ;
    }

	//**********  取得值 ************************************************  end
	//--------------------------------------------------------------------------
	//--------------------------------------------------------------------------
	//--------------------------------------------------------------------------
	//--------------------------------------------------------------------------
	//**********  验证值 ************************************************  start
	
	/**
	* 判断字符串是否为空(null 或"")
	* @param   str  源字符串
	* @return  true : 字符串为空  false:字符串不为空  
	*/
	public static boolean isEmpty(String str){
		return str == null || str.length() == 0;
	}

	/**
	* 判断字符串是否为空(null 或"")
	* @param   str  源字符串
	* @return  true : 字符串不为空  false:字符串为空  
	*/
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
	
	/**
	* 字符串去掉空格后，判断是否为空(null 或"")
	* @param   str  源字符串
	* @return  true : 字符串为空  false:字符串不为空  
	*/
	public static boolean isTrimEmpty(String str){
		return str == null || str.trim().length() == 0;
	}
	
	/**
	* 判断字符串是否都是数字(0123456789)
	* @param   str  源字符串
	* @return  true : 字符串是数字  false:字符串含有数字以外字符或字符串为空
	*/
	public static boolean isNumber(String str){
		
        if(str == null || str.equals(EMPTY)){
        	return false;
        }
        String num = "0123456789";
      //  str.matches("\\d*")
        int len = str.length();
        for(int i = 0; i < len; i++){
            if(num.indexOf(str.charAt(i)) < 0){
            	return false;
            }
        }
                
        return true;
	}
	
	/**
	 * 判断字符串是否是正实数
	 * 
	 * @param text          源字符串
	 * @return true:是   false:不是         ( +0.77 ：false)
	 */
    public static boolean isPlusDouble(String text){
        Pattern p = Pattern.compile("^(\\d+)(\\.\\d+)?$");
        Matcher m = p.matcher(text);
        return m.matches();
    }
    
	/**
	 * 判断字符串是否是实数
	 *  
	 * @param text          源字符串
	 * @return true:是   false:不是        ( +0.77 ：false)
	 */
    public static boolean isDouble(String text){
        Pattern p = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
        Matcher m = p.matcher(text);
        return m.matches();
    }
	//**********  验证值 ************************************************  end
	//--------------------------------------------------------------------------
	//--------------------------------------------------------------------------
	//--------------------------------------------------------------------------
	//--------------------------------------------------------------------------
	//**********  转换值 ************************************************  start
	/**
	* BigDecimal类型保留指定的小数数位
	* @param   bd   BigDecimal
	* @param   len  小数位数
	* @return  保留小数后的数字
	*/
	public static String formatBigDecimal(BigDecimal bd,int len){
		if(bd==null){
			return EMPTY;
		}
		// 设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入) 
		 BigDecimal bigdecimal = bd.setScale(len, BigDecimal.ROUND_HALF_UP);

		return bigdecimal.toString();
	}

	/**
	* 输出指定格式的数字
	* @param   obj   接收类型 Double,Long,BigDecimal
	* @param   formatType  格式(如: 0.0; 0.0kg; ##000.00; -000.0; -0,000.0#; 0.00%; 0.00\u2030;  0.00% )
	* @return  格式转换后的数字
	*/
	public static String formatData(Object obj,String formatType){
		if(obj==null){
			return EMPTY;
		}

		DecimalFormat df = new DecimalFormat();
		df.applyPattern(formatType);
		
		if(obj instanceof Double){
			return df.format((Double)obj);
		} else if(obj instanceof Long){
			return df.format((Long)obj);
		} else if(obj instanceof BigDecimal){
			return df.format((BigDecimal)obj);
		}

		return EMPTY;
	}
	
	/**
	 * 获取当前年份
	 * @param  无
	 * @return  当前年份
	 */
	public static int getYear(){
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT-8"));
		
		c.setTime(new Date()); 
		 
		return c.get(Calendar.YEAR);
	} 
	
	
	//**********  转换值 ************************************************  end
	//--------------------------------------------------------------------------
	//--------------------------------------------------------------------------
	//--------------------------------------------------------------------------
	//--------------------------------------------------------------------------
	
	//public static void main(String[] a){
	//	System.out.println(formatData(new Double("4444"),"-000.0"));
	//}
	
	/**
	 * 对列表进行排序
	 * @param data   要进行排序的list数据
	 * @param key    要排序的关键字 
	 * @param type   1:升序  2：降序
	 * @return  无
	 */
	public static void sortList(List<Map> data, Object key, int type){
		if(data != null && data.size() >0 && key !=null){
			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.size() - i - 1; j++) {
					if(type == 1){
						if (ComU.getMapValue(data.get(j), key.toString()).hashCode()
								 > ComU.getMapValue(data.get(j+1), key.toString()).hashCode()) {
							data.add(j, data.get(j + 1));
							data.remove(j + 2);
						}
					}else if(type ==2){
						if (ComU.getMapValue(data.get(j), key.toString()).hashCode() 
								< ComU.getMapValue(data.get(j+1), key.toString()).hashCode()) {
							data.add(j, data.get(j + 1));
							data.remove(j + 2);
						}
					}
				}
			}
		}

	}
	
	/**
	 * 对列表的某字段进行合计
	 * @param data        list
	 * @param key         要合计的关键字 
	*  @param  formatType  返回格式(如: 0.0; 0.0kg; ##000.00; -000.0; -0,000.0#; 0.00%; 0.00\u2030;  0.00% )
	 * @return 合计
	 */
	public static String sumList(List<Map> data, Object key, String formatType){
		Double db = 0.0;
		if(data != null && data.size() >0 && key !=null){
			for (int i = 0; i < data.size(); i++) {
				Object numstr = data.get(i).get(key);
				if(numstr != null && !"".equals(numstr)){
					Double num = Double.valueOf(numstr.toString());
					db = db + num;
				}
			}
		}
		
		return formatData(db,formatType);
	}
	
	/**
     * List<Map>拼接为字符串
     * @param result 数据源
     * @param key 键值
     * @param isChar 是否为字符型，字符型要加单引号
     * @return 字符串
     */
    public static String list2Str(List<Map> result, String key, boolean isChar) {
        if(result == null) {
            return "''";
        }
        //拼接in字句
        String rs = "";
        for (Map map : result) {
            rs += (isChar?"'":"") + map.get(key).toString() + (isChar?"'":"") + ",";
        }
        if (rs.length() > 0) {
            rs = rs.substring(0, rs.length() - 1);
            return rs;
        } else {
            return "''";
        }
    }
    
    /**
     * List<Map>拼接为字符串
     * @param result 数据源
     * @param key 键值
     * @return 字符串,逗号隔开，不带单引号
     */
    public static String list2Str(List<Map> result, String key) {
        if(result == null) {
            return "";
        }
        //拼接in字句
        String rs = "";
        for (Map map : result) {
            rs += map.get(key).toString() + ",";
        }
        if (rs.length() > 0) {
            rs = rs.substring(0, rs.length() - 1);
            return rs;
        } else {
            return "";
        }
    }
    
    /**
     * String[]拼接为字符串
     * @param result 数据源
     * @param key 键值
     * @param isChar 是否为字符型，字符型要加单引号
     * @return 字符串
     */
    public static String list2Str(String[] result, boolean isChar) {
        if(result == null) {
            return "''";
        }
        //拼接in字句
        String rs = "";
        for (String key : result) {
            rs += (isChar?"'":"") + key + (isChar?"'":"") + ",";
        }
        if (rs.length() > 0) {
            rs = rs.substring(0, rs.length() - 1);
            return rs;
        } else {
            return "''";
        }
    }
    
    
    /**
	* 根据传入参数拼接出oracle中in条件语句，类似( xxx IN (...) OR xxx IN (...) ...)
	* @param fieldName
	* @param splitValue
	* @param splitFlag
	* @param groupNum
	* @return
	*/
	public static String genInOralceSQL(String fieldName, String splitValue, String splitFlag, int groupNum) {
		StringBuffer resultSQL = new StringBuffer();
		String[] arrValue = splitValue.split(splitFlag);
		// 如果返回结果为空，则直接跳出本函数，并返回空字符串
		if (arrValue.length == 0) {
			return "";
		}
		resultSQL.append(" (");
		for (int i = 0; i < arrValue.length; i++) {
			// 如果是1000的整数倍，如0,1000,2000
			if (i == 0) {// 如果是第一次执行
				resultSQL.append(fieldName).append(" IN (").append(arrValue[i]);
			} else if ((i % groupNum) == 0) {
				resultSQL.append(") OR ").append(fieldName).append(" IN (").append(arrValue[i]);
			} else {
				resultSQL.append(",").append(arrValue[i]);
			}
			//最后一条,结束
			if ((i == arrValue.length - 1)) {
				resultSQL.append(")");
			}
		}
		resultSQL.append(") ");
		return resultSQL.toString();
	}
	
	/**
	* 根据传入参数拼接出oracle中in条件语句，类似:  ( xxx NOT IN (...) AND xxx NOT IN (...) ...)
	* @param fieldName
	* @param splitValue
	* @param splitFlag
	* @param groupNum
	* @return
	*/
	public static String genNotInOralceSQL(String fieldName, String splitValue, String splitFlag, int groupNum) {
		StringBuffer resultSQL = new StringBuffer();
		String[] arrValue = splitValue.split(splitFlag);
		// 如果返回结果为空，则直接跳出本函数，并返回空字符串
		if (arrValue.length == 0) {
			return "";
		}
		resultSQL.append(" (");
		for (int i = 0; i < arrValue.length; i++) {
			// 如果是1000的整数倍，如0,1000,2000
			if (i == 0) {// 如果是第一次执行
				resultSQL.append(fieldName).append(" NOT IN (").append(arrValue[i]);
			} else if ((i % groupNum) == 0) {
				resultSQL.append(") AND ").append(fieldName).append(" NOT IN (").append(arrValue[i]);
			} else {
				resultSQL.append(",").append(arrValue[i]);
			}
			//最后一条,结束
			if ((i == arrValue.length - 1)) {
				resultSQL.append(")");
			}
		}
		resultSQL.append(") ");
		return resultSQL.toString();
	}
	
	   /**
	    * 根据传入参数拼接出oracle中in条件语句，类似( xxx IN ('...') OR xxx IN ('...') ...)
	    * @param fieldName
	    * @param splitValue
	    * @param splitFlag
	    * @param groupNum
	    * @return
	    */
	    public static String getInOralceSQL(String fieldName, String splitValue, String splitFlag, int groupNum) {
	        StringBuffer resultSQL = new StringBuffer();
	        String[] arrValue = splitValue.split(splitFlag);
	        // 如果返回结果为空，则直接跳出本函数，并返回空字符串
	        if (arrValue.length == 0) {
	            return "";
	        }
	        resultSQL.append(" (");
	        for (int i = 0; i < arrValue.length; i++) {
	            // 如果是1000的整数倍，如0,1000,2000
	            if (i == 0) {// 如果是第一次执行
	                resultSQL.append(fieldName).append(" IN ('").append(arrValue[i]);
	            } else if ((i % groupNum) == 0) {
	                resultSQL.append("') OR ").append(fieldName).append(" IN ('").append(arrValue[i]);
	            } else {
	                resultSQL.append("','").append(arrValue[i]);
	            }
	            //最后一条,结束
	            if ((i == arrValue.length - 1)) {
	                resultSQL.append("')");
	            }
	        }
	        resultSQL.append(") ");
	        return resultSQL.toString();
	    }
	    
	   /**
	    * 根据传入参数拼接出oracle中in条件语句，类似:  ( xxx NOT IN ('...') AND xxx NOT IN ('...') ...)
	    * @param fieldName
	    * @param splitValue
	    * @param splitFlag
	    * @param groupNum
	    * @return
	    */
	    public static String getNotInOralceSQL(String fieldName, String splitValue, String splitFlag, int groupNum) {
	        StringBuffer resultSQL = new StringBuffer();
	        String[] arrValue = splitValue.split(splitFlag);
	        // 如果返回结果为空，则直接跳出本函数，并返回空字符串
	        if (arrValue.length == 0) {
	            return "";
	        }
	        resultSQL.append(" (");
	        for (int i = 0; i < arrValue.length; i++) {
	            // 如果是1000的整数倍，如0,1000,2000
	            if (i == 0) {// 如果是第一次执行
	                resultSQL.append(fieldName).append(" NOT IN ('").append(arrValue[i]);
	            } else if ((i % groupNum) == 0) {
	                resultSQL.append("') AND ").append(fieldName).append(" NOT IN ('").append(arrValue[i]);
	            } else {
	                resultSQL.append("','").append(arrValue[i]);
	            }
	            //最后一条,结束
	            if ((i == arrValue.length - 1)) {
	                resultSQL.append("')");
	            }
	        }
	        resultSQL.append(") ");
	        return resultSQL.toString();
	    }
	
    
    /**
	 * oracle in （）限制于1000，转换为 and in（） or in（）模式
	  */
	public static String oracleWhithClassStringId(String lses,String pk,String table){
		
		String []  ls = lses.split(",");//拆分专业
		
		String limit1000="";
		String ids="";
		if(ls.length==0||"".equals(lses))
			return "";
		for(int i = 0; i <ls.length; i++) {
			if("".equals(ids)){
				ids=ls[i];
			}else{
				ids+=","+ls[i];
			}
								
			if((i+1)%1000==0)
				{
				    if("".equals(limit1000))
					{
				    	limit1000=" AND ( "+table+"."+pk+" in ("+ids+")";
					
					}else
						limit1000+=" OR "+table+"."+pk+" in ("+ids+")";
				    ids="";
				}else if(i+1==ls.length){
					 if(ls.length>=1000)
					      limit1000+=" OR "+table+"."+pk+" in ("+ids+")";
					 else
						 limit1000=" AND ( "+table+"."+pk+" in ("+ids+")";
				}
		}
		if(!"".equals(limit1000))
			limit1000+=") ";
		return limit1000;
	}
    
    
    /**
     * 将字符串转为Clob类型
     * @param str
     * @return
     */
    public static Clob stringToClob(String str) {  
        if (null == str)  
            return null;  
        else {  
            try {  
                java.sql.Clob c = new javax.sql.rowset.serial.SerialClob(str  
                        .toCharArray());  
                return c;  
            } catch (Exception e) {  
                return null;  
            }  
        }  
    }  
    
    /**
     * 判断数字是否为空
     * @param bigDecimal
     * @return true : 数字为空  false:数字不为空  
     */
    public static boolean isBigDecimalNull(BigDecimal bigDecimal){
        if(null == bigDecimal){
            return true;
        }
        String str = bigDecimal.toString();
        return null == str || str.trim().length() == 0;
    }
    
    /** 
     * 判断字符串长度 
     * @param str 字符串
     * @return true:格式正确   false:格式不正确
     */  
    public static int strTrimLength(String str) {  
    	if(str == null || str.trim().equals("")){
            return 0;
        }else{
        	return str.trim().replaceAll("[^\\x00-\\xff]", "**").length();//汉字两个字节长度
        }
    }
    /**
     *@Title:sql中in的多值改为占位符
     *@author:sunmj
     *@Description:TODO
     *@param:@param parmas value值string数组
     *@param:@return 
     *@return:Map sqlkey是sql中的？ paramsValue参数（list）
     *
     *调用描述：
     * 		Map mapsql = ComU.toinParamstoSqlPlaceHolder(spec.split(","));
		    sql.append(mapsql.get("sqlkey"));
		    List paramValue = (List) mapsql.get("paramsValue");
		    for(int num1 = 0 ;num1 < paramValue.size();num1++){
			params.add(paramValue.get(num1));
		    }
     */
    public static Map toinParamstoSqlPlaceHolder(String[] parmas){
    	Map map = new HashMap();
    	String sqlkey = "";
    	List paramsValue = new ArrayList(); 
    	for(int num = 0; num < parmas.length; num++){
    		if(num == (parmas.length-1)){
    			sqlkey = sqlkey+"?";
    			paramsValue.add(parmas[num]);
    		}else{
    			sqlkey = sqlkey+"?,";
    			paramsValue.add(parmas[num]);
    		}
    	}
    	map.put("sqlkey", sqlkey);
    	map.put("paramsValue", paramsValue);
		return map;
    	
    }
    /**
     * 替换占位符打印sql
     * @author: lichuanzhao
     * @time:Jan 31, 2018 9:16:12 AM
     * @param sql sql语句
     * @param params 参数的List集合
     */
    public static void SystemOutSql(String sql,List params){
        StringBuilder str = new StringBuilder (sql);
        for(int j = 0;j<params.size();j++){
            int i = str.indexOf("?");
            str = str.replace(i,i+1,"'"+params.get(j).toString()+"'");
        }
        System.out.println(str.toString());
    }
    
    
    /**
     *  获取浏览器版本信息
     *  基于request.getHeader("User-Agent").toLowerCase();
     * @param request:request请求
     * @return String
     */
    public static String getBrowserName(HttpServletRequest request) {
        //获取浏览器相关信息
        String agent = request.getHeader("User-Agent");
        if (isNotEmpty(agent)){
            agent = agent.toLowerCase();
        }else{
            return "无法获取用户浏览器相关信息;";//无法获取浏览器信息直接返回
        }
        String version = "";
        if (agent.indexOf("msie 7") > 0) {
            version = "ie7";
        } else if (agent.indexOf("msie 8") > 0) {
            version = "ie8";
        } else if (agent.indexOf("msie 9") > 0) {
            version = "ie9";
        } else if (agent.indexOf("msie 10") > 0) {
            version = "ie10";
        } else if (agent.indexOf("msie") > 0) {
            version = "ie";
        } else if (agent.indexOf("chrome") > 0) {
            version = "chrome";
        } else if (agent.indexOf("opera") > 0) {
            version = "opera";
        } else if (agent.indexOf("firefox") > 0) {
            version = "firefox";
        } else if (agent.indexOf("webkit") > 0) {
            version = "webkit";
        } else if (agent.indexOf("gecko") > 0 && agent.indexOf("rv:11") > 0) {
            version = "ie11";
        } else {
            version = "Others";
        }
        return "浏览器信息:"+agent+";版本:"+version;
    }

}

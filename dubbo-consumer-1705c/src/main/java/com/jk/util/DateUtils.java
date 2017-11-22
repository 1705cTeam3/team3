/** 
 * @Title: DateUtils.java 
 * @Package com.thunisoft.hyxx 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author shangfeng 
 * @date 2017-8-27 下午2:27:01 
 * @version V1.0 
 */
package com.jk.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: DateUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author shangfeng
 * @date 2017-8-27 下午2:27:01
 */
public class DateUtils
{
    /**
     * 
     * @Title: objectToDatetime
     * @Description: TODO(将Object转换为Datetime:yyyy-MM-dd HH:mm:ss)
     * @author shangfeng
     * @param object
     * @return
     * @throws
     */
    public static Date objectToDatetime(Object object)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datetime = null;
        if (object != null)
        {
            String dateStr = object.toString();
            try
            {
                datetime = sdf.parse(dateStr);
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        else
        {

        }
        return datetime;
    }

    /**
     * 
     * @Title: objectToDate
     * @Description: TODO(将Object转换为Datetime:yyyy-MM-dd)
     * @author shangfeng
     * @param object
     * @return
     * @throws
     * SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
     */
    public static Date objectToDate(Object object)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date datetime = null;
        if (object != null)
        {
            String dateStr = object.toString();
            try
            {
                datetime = sdf.parse(dateStr);
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        else
        {

        }
        return datetime;
    }
    
    
    public static String  dateToString(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(date!=null){
        	return sdf.format(date);
        }
       return null;
    }
}

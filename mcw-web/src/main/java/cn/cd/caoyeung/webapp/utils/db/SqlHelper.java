/**
 * ------------------------------------------------------------*
 *          COPYRIGHT (C) 2006 BOCO Inter-Telecom INC          *
 *   CONFIDENTIAL AND PROPRIETARY. ALL RIGHTS RESERVED.        *
 *                                                             *
 *  This work contains confidential business information       *
 *  and intellectual property of BOCO  Inc, Beijing, CN.       *
 *  All rights reserved.                                       *
 * ------------------------------------------------------------*
 *
 *
 */
/**
 *Revision Information:
 *
 *@version 1.0 2006-2-5 release(zhangxu)
 */

package cn.cd.caoyeung.webapp.utils.db;

import cn.cd.caoyeung.webapp.utils.lang.TimeFormatHelper;

public class SqlHelper {
    private static final String SO_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String SO_DATE_FORMAT = "yyyy-MM-dd";

    private SqlHelper() {
    }


    public static String getDate(DbType dbType, java.sql.Date date) throws Exception{
        String sqlDate = "";

        if(dbType == DbType.DB_TYPE_ORACLE){
            String dateStr = TimeFormatHelper.getFormatDate(date, SO_TIMESTAMP_FORMAT);
            sqlDate = "CHAR_TO_DATE('" + dateStr + "','YYYY-MM-DD hh24:mi:ss')";
        }else if(dbType == DbType.DB_TYPE_INFORMIX){
            //sqlDate = "'" + TimeFormatHelper.getFormatDate(date, SO_TIMESTAMP_FORMAT) + "'"; by zhanglei 06-11-17
            String dateStr = TimeFormatHelper.getFormatDate(date, SO_DATE_FORMAT);
            sqlDate = "CHAR_TO_DATE('" + dateStr + "','%Y-%m-%d')";
        }else{
            sqlDate = "'" + TimeFormatHelper.getFormatDate(date, SO_DATE_FORMAT) + "'";
        }

        return sqlDate;
    }
    public static String getDate(DbType dbType, java.util.Date date) throws Exception{
        String sqlDate = "";

        if(dbType == DbType.DB_TYPE_ORACLE){
            String dateStr = TimeFormatHelper.getFormatDate(date, SO_TIMESTAMP_FORMAT);
            sqlDate = "CHAR_TO_DATE('" + dateStr + "','YYYY-MM-DD hh24:mi:ss')";
        }else if(dbType == DbType.DB_TYPE_INFORMIX){
            //sqlDate = "'" + TimeFormatHelper.getFormatDate(date, SO_TIMESTAMP_FORMAT) + "'"; by zhanglei 06-11-17
            String dateStr = TimeFormatHelper.getFormatDate(date, SO_DATE_FORMAT);
            sqlDate = "CHAR_TO_DATE('" + dateStr + "','%Y-%m-%d')";
        }else{
            sqlDate = "'" + TimeFormatHelper.getFormatDate(date, SO_DATE_FORMAT) + "'";
        }

        return sqlDate;
    }


    public static String getTimestamp(DbType dbType, java.sql.Timestamp timestamp) throws Exception{
        String sqlTimestamp = "";

        if(dbType == DbType.DB_TYPE_ORACLE){
            String timestampStr = TimeFormatHelper.getFormatDate(timestamp, SO_TIMESTAMP_FORMAT);
            sqlTimestamp = "CHAR_TO_DATE('" + timestampStr + "','YYYY-MM-DD HH24:MI:SS')";
        }else if(dbType == DbType.DB_TYPE_INFORMIX) {
            //sqlTimestamp = "'" + TimeFormatHelper.getFormatDate(timestamp, SO_TIMESTAMP_FORMAT) + "'"; by zhanglei 06-11-17
            String timestampStr = TimeFormatHelper.getFormatDate(timestamp, SO_TIMESTAMP_FORMAT);
            sqlTimestamp = "CHAR_TO_DATE('" + timestampStr + "','%Y-%m-%d  %H:%M:%S')";
        }else{
            sqlTimestamp = "'" + TimeFormatHelper.getFormatDate(timestamp, SO_TIMESTAMP_FORMAT) + "'";
        }

        return sqlTimestamp;
    }

    
    
}

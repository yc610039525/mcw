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


public class DbType {
    private int value;

    public static final int _DB_TYPE_ORACLE    = 1;
    public static final int _DB_TYPE_SYBASE    = 2;
    public static final int _DB_TYPE_HSQL      = 4;
    public static final int _DB_TYPE_INFORMIX  = 5;
    public static final int _DB_TYPE_FIREBIRD  = 6;

    public static final DbType DB_TYPE_ORACLE = new DbType(_DB_TYPE_ORACLE);
    public static final DbType DB_TYPE_SYBASE = new DbType(_DB_TYPE_SYBASE);
    public static final DbType DB_TYPE_HSQL = new DbType(_DB_TYPE_HSQL);
    public static final DbType DB_TYPE_INFORMIX = new DbType(_DB_TYPE_INFORMIX);
    public static final DbType DB_TYPE_FIREBIRD= new DbType(_DB_TYPE_FIREBIRD);

    protected DbType(final int value) {
        this.value = value;
    }

    public int value () {
      return value;
    }

    public static DbType from_int(final int value) {
        switch (value) {
            case _DB_TYPE_ORACLE:
                return DB_TYPE_ORACLE;
            case _DB_TYPE_SYBASE:
                return DB_TYPE_SYBASE;
            case _DB_TYPE_HSQL:
                return DB_TYPE_HSQL;
            case _DB_TYPE_INFORMIX:
                return DB_TYPE_INFORMIX;
            case _DB_TYPE_FIREBIRD:
                return DB_TYPE_FIREBIRD;
            default:
                throw new RuntimeException("非法的数据库类型：" + value);
        }
    }
}

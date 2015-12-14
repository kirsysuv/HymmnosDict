package com.binasphere.hymmnosdict.db;

public class DBInfo {
    public static final String DB_NAME = "hymmnos.db";
    public static final String TABLE_NAME = "t_dict";
    public static final int DB_VERSION = 1;
    public static final int ORDER_ALPHA=0;
    public static final int ORDER_DIALECT=1;
    //use in alphabet query for other words such as numbers
    public static final String ALPHA_OTHERS = "others";
    //use in alphabet query, indicate the id of other words
    public static final String ID_OTHERS = "15";

    public static final String[] Projection=new String[]{
            Colums.ID,
            Colums.HYMMNOS,
            Colums.EXP_JP,
            Colums.KATAKANA,
            Colums.DIALECT
    };
    public static class ProjectionIndex {
        public static final int ID = 0;
        public static final int HYMMNOS = 1;
        public static final int EXP_JP = 2;
        public static final int KATAKANA = 3;
        public static final int DIALECT = 4;
    }

    public static class Colums {
        /*
         * "create table t_dict(id integer primary key autoincrement,hymmnos
         * varchar(10),exp_jp varchar(10),katakana varchar(10),source
         * varchar(10))"
         *
         */
        public static final String ID = "id";
        public static final String HYMMNOS = "hymmnos";
        public static final String EXP_JP = "exp_jp";
        public static final String KATAKANA = "katakana";
        public static final String DIALECT = "dialect";

    }
}

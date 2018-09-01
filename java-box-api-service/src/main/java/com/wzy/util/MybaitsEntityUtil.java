package com.wzy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MybaitsEntityUtil {

    public static void main(String[] args) {
        MybaitsEntityUtil mybaist = new MybaitsEntityUtil();
        mybaist.generate("box_moudularapi");
    }

    public static final String url = "jdbc:mysql://localhost:3306/java_box?useUnicode=true&characterEncoding=utf-8";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "wangyang";
    public Connection conn = null;
    public PreparedStatement pst = null;
    public ResultSet resultSet = null;

    // 初始化数据库链接
    public void initConnection() {
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 生成信息打印在终端里
    public void generate(String tableName){
        try{
            List<TableClass> tableLs = new ArrayList<>();
            String sql = "desc "+tableName;
            initConnection();
            pst = conn.prepareStatement(sql);
            resultSet = pst.executeQuery();
            while (resultSet.next()) {
                TableClass tclass = new TableClass();
                tclass.setTabname(resultSet.getString("Field"));
                tclass.setTabtype(resultSet.getString("Type"));
                tclass.setPrimaryKey(resultSet.getString("Key"));
                tableLs.add(tclass);
            }

            // 启动生成函数
            generateEntity(tableLs, tableName);

            close();
        } catch (Exception e) {

        }
    }

    // 生成实体类
    public void generateEntity(List<TableClass> tableLs, String tableName){
        System.out.println("生成实体类Start------------------------------------------");
        System.out.println("");

        String selectsql = "select ";
        String whereSql = " where ";
        String deleteSql = "delete from "+tableName+" t ";
        String installSql = "insert into "+tableName;
        String installSqlTable = "(";
        String installSqlValues = "(";
        for (TableClass entity:tableLs) {

            // 生成实体类
            String entityArg = "";
            if (entity.getTabtype().toLowerCase().indexOf("varchar") != -1) {
                entityArg += "String ";
            } else if (entity.getTabtype().toLowerCase().indexOf("int") != -1) {
                entityArg += "Integer ";
            } else if (entity.getTabtype().toLowerCase().indexOf("date") != -1) {
                entityArg += "Date ";
            } else if (entity.getTabtype().toLowerCase().indexOf("year") != -1) {
                entityArg += "String ";
            } else if (entity.getTabtype().toLowerCase().indexOf("decimal") != -1) {
                entityArg += "BigDecimal ";
            } else if (entity.getTabtype().toLowerCase().indexOf("timestamp") != -1) {
                entityArg += "Date ";
            } else if (entity.getTabtype().toLowerCase().indexOf("text") != -1) {
                entityArg += "String ";
            }

            // 主键
            if (entity.getPrimaryKey().toLowerCase().equals("pri")) {
                whereSql += "t."+entity.getTabname()+"=" +"#{"+entity.getTabname()+"}";
            }

            entityArg += entity.getTabname()+";";
            selectsql += "t."+entity.getTabname()+",";

            // 输出实体类信息
            System.out.println(entityArg);

            installSqlTable += entity.getTabname()+",";
            installSqlValues += "#{"+entity.getTabname()+"},";
        }
        System.out.println("");
        System.out.println("生成实体类End------------------------------------------");
        System.out.println("");
        System.out.println("生成MapperStart------------------------------------------");
        System.out.println("");
        selectsql = selectsql.substring(0,selectsql.lastIndexOf(","));
        installSqlTable = installSqlTable.substring(0,installSqlTable.lastIndexOf(","))+") values ";
        installSqlValues = installSqlValues.substring(0,installSqlValues.lastIndexOf(","))+")";
        System.out.println("@Select(\""+selectsql + " from "+tableName+" t"+ whereSql+"\")");
        System.out.println("@Delete(\""+deleteSql+ whereSql+"\")");
        System.out.println("@Insert(\""+installSql+ installSqlTable+installSqlValues+"\")");

        System.out.println("");
        System.out.println("生成MapperEnd------------------------------------------");
    }


    public void close() {
        try {
            this.conn.close();
        } catch (Exception e) {

        }
        try {
            this.pst.close();
        } catch (Exception e) {

        }
        try {
            resultSet.close();
        } catch (Exception e) {

        }
    }

    // 表结构实体类型
    class TableClass {
        String tabname;
        String tabtype;
        String PrimaryKey;
        String selectSql;
        String insertSql;
        String updateSql;
        String deleteSql;

        public String getTabname() {
            return tabname;
        }
        public void setTabname(String tabname) {
            this.tabname = tabname;
        }

        public String getTabtype() {
            return tabtype;
        }

        public void setTabtype(String tabtype) {
            this.tabtype = tabtype;
        }

        public String getPrimaryKey() {
            return PrimaryKey;
        }

        public String getSelectSql() {
            return selectSql;
        }

        public void setSelectSql(String selectSql) {
            this.selectSql = selectSql;
        }

        public String getInsertSql() {
            return insertSql;
        }

        public void setInsertSql(String insertSql) {
            this.insertSql = insertSql;
        }

        public String getUpdateSql() {
            return updateSql;
        }

        public void setUpdateSql(String updateSql) {
            this.updateSql = updateSql;
        }

        public String getDeleteSql() {
            return deleteSql;
        }

        public void setDeleteSql(String deleteSql) {
            this.deleteSql = deleteSql;
        }

        public String isPrimaryKey() {
            return PrimaryKey;
        }

        public void setPrimaryKey(String primaryKey) {
            PrimaryKey = primaryKey;
        }
    }
}

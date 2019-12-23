package com.sgg.rest.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sgg.rest.dto.ModelDto;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FtlUtil {
	public void generate (Map<String,Object> dataMap,String sourceFile, String targetFile,String outPath) {
		try {
	        Configuration configuration = new Configuration();
	        configuration.setDefaultEncoding("UTF-8");
	        configuration.setDirectoryForTemplateLoading(new File("doc/"));
	        File outFile = new File(outPath+targetFile);
	        Template template = configuration.getTemplate(sourceFile, "UTF-8");
	        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"), 10240);
	        template.process(dataMap, out);
	        out.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
    public Map<String,Object> generateData(String ip,String db,String name, String passwd, String tableName,String tableIndex) throws Exception {
        Connection con = new MySqlUtil().getConnection(ip,db,name,passwd);
        Statement pStemt = con.createStatement();
        Map<String,Object> colNamesComment = new HashMap<String, Object>();
        String[] colTypes; 
        String[] colNames; 
        ResultSet rsComment = pStemt.executeQuery("show full columns from "+tableName);
          List<ModelDto> model_list = new ArrayList<ModelDto>();
          PreparedStatement pStemt1 = con.prepareStatement("select * from "+tableName);
          ResultSetMetaData rsmd = pStemt1.getMetaData();
          int size = rsmd.getColumnCount();
          colTypes = new String[size];
          colNames = new String[size];
          for (int i = 0; i < size; i++) {
              colTypes[i] = rsmd.getColumnTypeName(i + 1);
              colNames[i] = rsmd.getColumnName(i + 1);
          }
          while (rsComment.next()) {
        	  model_list.add(new ModelDto(rsComment.getString("Comment"),underlineToCamel(rsComment.getString("Field"))
        			  ,rsComment.getString("Type"),rsComment.getString("Null")));
            }
          colNamesComment.put("title", tableIndex);
          colNamesComment.put("class",tableName.split("_").length>1?underlineToCamel(tableName):tableName);
          colNamesComment.put("modelList", model_list);
          return colNamesComment;
        }
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == '_') {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    public void getTables(String ip,String db,String name, String passwd) throws Exception {
    	Connection con = new MySqlUtil().getConnection(ip,db,name,passwd);
    	Statement stmt = con.createStatement();
		String sql = "SHOW TABLE STATUS FROM "+db+";";
		ResultSet rs = stmt.executeQuery(sql);
//		HashSet<String> tables=new HashSet<String>();
		while (rs.next()) {
			String tname =rs.getString("Name");
			String tcomment =rs.getString("Comment");
			System.out.println(">>>>>>tcomment"+tcomment);
//			tables.add(tname);
		}
		rs.close();
//		for(String tb:tables){			
//			stmt.execute("optimize table "+tb);
//		}
		stmt.close();
		con.close();
    }
	public static void main(String[] args) throws Exception {
	    String  tableName = "sys_menu";
	    String  tableIndex = "菜单";
	    String db ="demo";
	    String ip= "localhost";
	    String capTableName = tableName.split("_").length>1?underlineToCamel(tableName):tableName;
	    String outPath="";
//	    new FtlUtil().generate(new FtlUtil().generateData(ip,db,tableName,tableIndex),"service.ftl",capTableName+"Service.ts",outPath);
//	    new FtlUtil().generate(new FtlUtil().generateData(tableName),"list_html.ftl",capTableName+"list.component.html");
//	    new FtlUtil().generate(new FtlUtil().generateData(tableName),"list_ts.ftl",capTableName+"list.component.ts");
//	    new FtlUtil().getTables("demo");
	}
}

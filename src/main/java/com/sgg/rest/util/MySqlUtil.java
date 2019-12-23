package com.sgg.rest.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.sgg.rest.dto.TableDto;


public class MySqlUtil {
	  private static final String NAME = "root";
	  private static final String PASS = "root";
	  private static final String DRIVER = "com.mysql.jdbc.Driver";
	  public Connection getConnection(String ip,String db,String name, String passwd) {
	        Connection con = null;
	        try {
		        Class.forName(DRIVER);
				con = DriverManager.getConnection("jdbc:mysql://"+ip+":3306/"+db, 
						name, passwd);
			} catch (Exception e) {
				e.printStackTrace();
			}
	        return con;
	  }
	  
	    public List<TableDto> getTables(String ip,String db,String name, String passwd) throws Exception {
	    	Connection con = new MySqlUtil().getConnection(ip,db,name, passwd);
	    	Statement stmt = con.createStatement();
			String sql = "SHOW TABLE STATUS FROM "+db+";";
			ResultSet rs = stmt.executeQuery(sql);
			List<TableDto> tables=new ArrayList<TableDto>();
			while (rs.next()) {
				TableDto  tableDto =new TableDto();
				tableDto.setName(rs.getString("Name"));
				tableDto.setComment(rs.getString("Comment"));
				tables.add(tableDto);
//				tables.add(new TableDto(rs.getString("Name"),rs.getString("Comment")));
			}
//			List<String> dlist = new ArrayList<String>();
//			dlist.add("sys_menu");
//			dlist.add("user");
//			dlist.add("3434");
//			for(int j =0;j<dlist.size();j++) {
//				for(int i=0;i<tables.size();i++) {
//				if(tables.get(i).getName().equals(dlist.get(j))) {
//					tables.remove(i);
//				}
//			}
//		}
			rs.close();
			stmt.close();
			con.close();
			return tables;
	    }

}

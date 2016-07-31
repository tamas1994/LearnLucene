package com.folkcat.lucene.indexdb;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.lucene.queryparser.classic.ParseException;

public class Test {

	public static void main(String[] args)
			throws IOException, SQLException, ParseException {
		TestLucene test = new TestLucene();
		//String sql = "select * from Authors";
		//ResultSet rs = test.getResult(sql);
		//test.Index(rs);
		long start=System.currentTimeMillis();
		test.seacher("3");
		long end=System.currentTimeMillis();
		System.out.println("Cost:"+(end-start));
	}
}
package com.folkcat.lucene.indexdb;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class TestLucene {

	static String sql = null;
	static DBHelper db1 = null;
	static ResultSet ret = null;
	String indexPath = "E:\\lucene\\index";

	/**
	 * 获取数据库中的数据，返回ResultSet
	 * 
	 * @param sql
	 *                需要执行的sql语句
	 */

	public ResultSet getResult(String sql) {
		db1 = new DBHelper(sql);// 创建DBHelper对象
		try {
			ret = db1.pst.executeQuery(sql);
			return ret;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public Analyzer getAnalyzer() {
		return new SmartChineseAnalyzer();
	}

	/**
	 * 为数据库的数据建立索引
	 * 
	 * @param rs
	 *                从数据库中返回的数据
	 * @throws IOException
	 * @throws SQLException
	 */
	public void Index(ResultSet rs) throws IOException, SQLException {
		Directory directory = FSDirectory.open(Paths.get(indexPath));
		Analyzer analyzer = getAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		iwc.setOpenMode(OpenMode.CREATE);
		IndexWriter writer = new IndexWriter(directory, iwc);

		while (rs.next()) {
			Document document = new Document();
			Field field = new StringField("id",
					rs.getString("au_id"), Field.Store.YES);
			document.add(field);
			field = new StringField("name", rs.getString("au_name"),
					Field.Store.YES);
			document.add(field);
			field = new StringField("phone", rs.getString("Phone"),
					Field.Store.YES);
			document.add(field);
			writer.addDocument(document);
			System.out.println("adding: " + rs.getString("au_id")
					+ "\t" + rs.getString("au_name") + "\t"
					+ rs.getString("Phone"));
		}
		writer.close();
	}

	/**
	 * 查询
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public void seacher(String queryString)
			throws IOException, ParseException {
		String defualtField = "id";
		TopDocs topDocs = null;

		IndexReader reader = DirectoryReader
				.open(FSDirectory.open(Paths.get(indexPath)));
		IndexSearcher searcher = new IndexSearcher(reader);

		Analyzer analyzer = getAnalyzer();

		QueryParser queryParser = new QueryParser(defualtField,
				analyzer);
		Query query = queryParser.parse(queryString);

		topDocs = searcher.search(query, 10);

		ScoreDoc[] hits = topDocs.scoreDocs;
		for (ScoreDoc hit : hits) {
			Document hitDoc = searcher.doc(hit.doc);
			System.out.println(hitDoc.get(defualtField) + " "
					+ hitDoc.get("name"));
		}
	}

}
package com.folkcat.lucene.analyzer;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

public class AnalyzerTest {
	public static void main(String args[]) {
		//cjk();
		smartChinese();
		//standar();
	}

	// CJKAnalyzer二元覆盖的方式分词
	private static void cjk() {
		Analyzer analyzer = new CJKAnalyzer();
		TokenStream tokenStream = analyzer.tokenStream("myfiled",
				new StringReader("待切分文本"));
		try {
			tokenStream.reset();
			while (tokenStream.incrementToken()) {
				// 取得下一个分词
				System.out.println("token:" + tokenStream);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (analyzer != null)
				analyzer.close();
		}

	}

	// 聪明中文分词
	private static void smartChinese() {
		Analyzer analyzer = new SmartChineseAnalyzer();
		TokenStream tokenStream = analyzer.tokenStream("myfiled",
				new StringReader("待切分文本"));
		try {
			tokenStream.reset();
			while (tokenStream.incrementToken()) {
				// 取得下一个分词
				System.out.println("token:" + tokenStream);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (analyzer != null)
				analyzer.close();
		}
	}

	//StandardAnalyzer单字切分
	private static void standar() {
		Analyzer analyzer = new StandardAnalyzer();
		TokenStream tokenStream = analyzer.tokenStream("myfiled",
				new StringReader("待切分文本"));
		try{
			tokenStream.reset();
			while (tokenStream.incrementToken()) {
				// 取得下一个分词
				System.out.println("token:" + tokenStream);
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(analyzer!=null)
				analyzer.close();
		}
	}

}

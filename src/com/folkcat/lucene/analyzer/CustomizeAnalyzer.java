package com.folkcat.lucene.analyzer;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.analysis.miscellaneous.LengthFilter;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class CustomizeAnalyzer extends Analyzer {

	public CustomizeAnalyzer() {
	}

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		final Tokenizer source = new WhitespaceTokenizer();
		TokenStream result = new LengthFilter(source, 3,
				Integer.MAX_VALUE);
		return new TokenStreamComponents(source, result);
	}

	public static void main(String[] args) throws IOException {
		// text to tokenize
		final String text = "This is a demo of the TokenStream API";

		CustomizeAnalyzer analyzer = new CustomizeAnalyzer();
		TokenStream stream = analyzer.tokenStream("field",
				new StringReader(text));

		// get the CharTermAttribute from the TokenStream
		CharTermAttribute termAtt = stream
				.addAttribute(CharTermAttribute.class);

		try {
			stream.reset();

			// print all tokens until stream is exhausted
			while (stream.incrementToken()) {
				System.out.println(termAtt.toString());
			}

			stream.end();
		} finally {
			stream.close();
		}
	}
}
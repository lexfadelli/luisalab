package br.lexfadelli.search;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.Set;

public class Vectorizer {
	private static final String TOKENIZER_CHARS = "#:,\\.\\-/\\\\";
	private static Vectorizer instance;
	
	private Vectorizer() {
	}
	
	public static Vectorizer getInstance() {
		if(instance == null) {
			instance = new Vectorizer();
		}
		return instance;
	}
	
	public String normalize(String content) {
		content = content.toLowerCase();
		content = Normalizer.normalize(content, Normalizer.Form.NFD);
		content = content.replaceAll("[^\\p{ASCII}]", "");
		return content;
	}

	public String tokenize(String content) {
		content = content.replaceAll(String.format("[%s]", TOKENIZER_CHARS), " ");
		content = content.replaceAll(" +", " ");
		return content;
	}

	public String[] getVector(String content) {
		String[] vector = content.split("[\\t\\n\\x0B\\f\\r\\s]+");
		Set<String> set = new HashSet<String>();
		for (String item : vector) {
			set.add(item);
		}
		String[] result = set.stream().toArray(String[]::new);
		return result;
	}

}

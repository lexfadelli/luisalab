package br.lexfadelli.search;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.Set;

/***
 * Class responsible for vectorizing content (query terms and documents)
 * @author lexfadelli
 *
 */
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
	
	/***
	 * Normalize strings by removing special chars and converting to lower case.
	 * @param content
	 * @return
	 */
	public String normalize(String content) {
		content = content.toLowerCase();
		content = Normalizer.normalize(content, Normalizer.Form.NFD);
		content = content.replaceAll("[^\\p{ASCII}]", "");
		return content;
	}

	/***
	 * Remove string tokens
	 * @param content
	 * @return
	 */
	public String tokenize(String content) {
		content = content.replaceAll(String.format("[%s]", TOKENIZER_CHARS), " ");
		content = content.replaceAll(" +", " ");
		return content;
	}
	
	/***
	 * Returns the calculated string vector
	 * @param content
	 * @return
	 */
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

package br.lexfadelli.search;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class VectorizerTest {
	@Test
	void testTokenize() {
		Vectorizer v = Vectorizer.getInstance();
		String actual = v.tokenize("Av. Paes de Barros, 1915 - apto 1 / Mooca \\ SP");
		String expected = "Av Paes de Barros 1915 apto 1 Mooca SP";
		
		
		//improve in future so datetime are correctly identified. 
		actual = v.tokenize("1:30-2:40 1.988 #123");
		expected = "1 30 2 40 1 988 123";
		assertEquals(expected, actual);
	}
	
	@Test
	void testNormalize() {
		Vectorizer v = Vectorizer.getInstance();
		String actual = v.normalize("José Assunção de Oliveira");
		assertEquals("jose assuncao de oliveira", actual);
	}
	
	@Test
	void testGetVector() {
		Vectorizer v = Vectorizer.getInstance();
		String[] actual = v.getVector("bruce willis bruce wayne");
		assertEquals(3, actual.length);
	}
}
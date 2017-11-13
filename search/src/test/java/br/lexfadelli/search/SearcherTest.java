package br.lexfadelli.search;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class SearcherTest {
	@Test
	public void testSearch() {
		HashMap<String, ArrayList<String>> index = new HashMap<String, ArrayList<String>>();
		
		Collection<String> col1 = Arrays.asList("1", "2");
		Collection<String> col2 = Arrays.asList("1", "2", "3");
		Collection<String> col3 = Arrays.asList("3");
		
		index.put("xyz", new ArrayList<>(col1));
		index.put("abc", new ArrayList<>(col2));
		index.put("def", new ArrayList<>(col3));
		
		Searcher s = new Searcher(index);
		Collection<String> result = s.search("xyz");
		assertEquals(col1, result);
		
		result = s.search("abc");
		assertEquals(col2, result);
		
		result = s.search("def");
		assertEquals(col3, result);
		
		result = s.search("xxx");
		assertEquals(0, result.size());
	}
}
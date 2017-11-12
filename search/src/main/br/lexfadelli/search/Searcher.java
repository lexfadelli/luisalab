package br.lexfadelli.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Searcher {
	HashMap<String, ArrayList<String>> index;
	Vectorizer vectorizer;
	
	public Searcher(HashMap<String, ArrayList<String>> index) {
		this.vectorizer = Vectorizer.getInstance();
		this.index = index;
	}
	
	private <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();

        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }
        return list;
    }
	
	public Collection<String> search(String query) {
		// query term processing
		query = vectorizer.normalize(query);
		query = vectorizer.tokenize(query);

		// vectorizing
		String[] vector = vectorizer.getVector(query);
		
		//search
		List<String> result = new ArrayList<String>();
		for(String term : vector) {
			List<String> partialResult = index.get(term);
			if(result.isEmpty()) {
				result.addAll(partialResult);
			} else {
				result = intersection(result, partialResult);
			}
		}
		
		return result;
	}
}
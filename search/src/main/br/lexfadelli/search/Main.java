package br.lexfadelli.search;

import java.io.IOException;
import java.util.Collection;

public class Main {

	public static void main(String[] args) {
		String queryTerm = null;
		
		if(args.length != 1) {
			System.out.println("Invalid query parameters!");
			System.exit(-1);
		} else {
			queryTerm = args[0];
		}
		
		System.out.println("**** Search ****");
		Indexer indexer = new Indexer();
		try {
			indexer.Run();
		} catch (IOException e) {
			System.out.print("Failed to run indexer!");
			e.printStackTrace();
		}
		
		System.out.printf("Searching for %s...\n", queryTerm);
		Searcher searcher = new Searcher(indexer.getIndex());
		Collection<String> result = searcher.search(queryTerm);
		
		System.out.printf("%d documents found!\n", result.size());
		for(String item : result) {
			System.out.printf("\t-%s\n", item);
		}
	}
}
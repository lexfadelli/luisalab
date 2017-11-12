package br.lexfadelli.search;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/***
 * Main class
 * @author lexfadelli
 *
 */
public class Main {

	public static void main(String[] args) {
		String queryTerm = null;
		
		if(args.length != 1) {
			System.out.println("Invalid query parameters!");
			System.exit(-1);
		} else {
			queryTerm = args[0];
		}
		
		//index the zip file contents
		System.out.println("**** Index ****");
		long indexStartTime = System.nanoTime();    
		Indexer indexer = new Indexer();
		try {
			indexer.Run();
		} catch (IOException e) {
			System.out.print("Failed to run indexer!");
			e.printStackTrace();
		}
		long indexEndTime = System.nanoTime();
		
		//search
		System.out.printf("Searching for %s...\n", queryTerm);
		
		long queryStartTime = System.nanoTime();    
		Searcher searcher = new Searcher(indexer.getIndex());
		Collection<String> result = searcher.search(queryTerm);
		long queryEndTime = System.nanoTime();

		//display the results
		System.out.printf("%d documents found!\n", result.size());
		for(String item : result) {
			System.out.printf("\t-%s\n", item);
		}
		
		//benchmarking		
		long indexTime = TimeUnit.NANOSECONDS.toMillis(indexEndTime - indexStartTime);
		long queryTime = TimeUnit.NANOSECONDS.toMillis(queryEndTime - queryStartTime);
		System.out.printf("Index time: %d milliseconds\n", indexTime);
		System.out.printf("Query time: %d milliseconds\n", queryTime);
		System.out.printf("Total elapsed time: %d milliseconds", indexTime + queryTime);
	}
}
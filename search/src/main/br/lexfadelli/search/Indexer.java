package br.lexfadelli.search;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/***
 * Class responsible for indexing content.
 * Initially it only indexes zipped TXT files with a hardcoded location (/resources/movies.zip)
 * @author lmonteir
 *
 */
public class Indexer {
	
	private HashMap<String, ArrayList<String>> index;
	private static final String FILE_TO_INDEX = "/resources/movies.zip";
	private static final String INDEX_PATH = "src/resources/index.ser";

	public Indexer() {
		index = new HashMap<String, ArrayList<String>>();
	}

	private String getContent(InputStream is) {
		StringBuffer sb = new StringBuffer();
		Scanner sc = new Scanner(is);
		while (sc.hasNext()) {
			sb.append(sc.nextLine());
		}
		return sb.toString();
	}

	
	public void serializeIndex() throws Exception {
		
		try {
			File f = new File(INDEX_PATH);
			FileOutputStream bos = new FileOutputStream(f);
			ObjectOutput out = null;
			out = new ObjectOutputStream(bos);
			out.writeObject(this.index);
			bos.close();
		} catch (IOException e) {
			throw new Exception("Failed to serialize index!", e);
		}
		
	}

	public void desearializeIndex() throws Exception {
		
		try {
			HashMap<String, ArrayList<String>> index = null;
			FileInputStream fileIn = new FileInputStream(INDEX_PATH);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			index = (HashMap<String, ArrayList<String>>) in.readObject();
			this.index = index;
			in.close();
			fileIn.close();
		} catch(ClassNotFoundException e) {
			throw new Exception("Invalid index!", e);
		} catch(IOException e) {
			throw new Exception("Failed to load index!", e);
		}
		
	}
	
	public HashMap<String, ArrayList<String>> getIndex() {
		return this.index;
	}

	public void Run() throws IOException {
		System.out.println("Indexing...");
		
		Vectorizer vectorizer = Vectorizer.getInstance();
		
		InputStream is = this.getClass().getResourceAsStream(FILE_TO_INDEX);
		ZipInputStream zis = new ZipInputStream(is);
		ZipEntry entry = null;
		while ((entry = zis.getNextEntry()) != null) {
			if (!entry.isDirectory()) {
				String filename = entry.getName().toLowerCase();
				if(filename.endsWith("txt")) {
					// content processing
					String content = getContent(zis);
					content = vectorizer.normalize(content);
					content = vectorizer.tokenize(content);

					// vectorizing
					String[] vector = vectorizer.getVector(content);

					// index
					for (String word : vector) {
						if (!index.containsKey(word))
							index.put(word, new ArrayList<>());

						index.get(word).add(filename);
					}	
				}
				
			}
		}

		System.out.println("Indexing finished!");
	}
}
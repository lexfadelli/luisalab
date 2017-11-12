package br.lexfadelli.search;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Indexer {
	public Indexer() {
		
	}
	
	private String getContent(InputStream is) {
		StringBuffer sb = new StringBuffer();
		Scanner sc = new Scanner(is);
		while(sc.hasNext()) {
			sb.append(sc.nextLine());
		}
		return sb.toString();
	}
	
	public void Run() throws IOException {
		
		InputStream is = this.getClass().getResourceAsStream("/resources/movies.zip");
		ZipInputStream zis = new ZipInputStream(is);
		ZipEntry entry = null;
		while((entry = zis.getNextEntry()) != null) {
			if(!entry.isDirectory()) {
				String filename = entry.getName();
				String content = getContent(zis);
			}
		}
	}
}
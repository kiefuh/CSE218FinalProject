package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.App;
import javafx.scene.control.TextArea;

public class ViewHelper {
	public static String wordCounter(String string) {
		String[] wordArray=string.split("\\s+|\\n|\\t");
		String length= String.valueOf(wordArray.length);
		
		try {
			if(wordArray[0].equals("")||wordArray[0].equals(" ")) {
				String zeroCase="0";
				return zeroCase;
			}
		} catch (Exception e) {
			return length;
		}
		return length;
	}
	
	
	public static double  wordCounterLoop(String string) {
		String[] wordArray=string.split("\\s+|\\n|\\t");
		double length= wordArray.length;
		
		try {
			if(wordArray[0].equals("")||wordArray[0].equals(" ")) {
				int zeroCase=0;
				return zeroCase;
			}
		} catch (Exception e) {
			return length;
		}
		return length;
	}
	
	public static String sentenceCounter(String string) {
		String[] sentenceArray= string.split("[!.?]+");
		String sentenceCount = String.valueOf(sentenceArray.length);
		return sentenceCount;
	}
	
	public static String sentenceCounterGUI(String string) {
		String patternString = "[!.?]+";
		Pattern pattern= Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(string);
		int counter = 0;
		while(matcher.find()) {
				counter++;
		}
		return String.valueOf(counter);
	}
	
	public static double sentenceCounterLoop(String string) {
		String[] sentenceArray= string.split("[!.?]+");
		double sentenceCount = sentenceArray.length;
		return sentenceCount;
	}
	
	public static String fileOpener(File file) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			Exceptions.fileIOException();
		}
		StringBuilder sb= new StringBuilder();
		String line;
		try {
			while((line=reader.readLine())!=null) {
				sb.append(line+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String fullText= sb.toString();
		return fullText;
	}
	
	public static void save(String fileLocation) {
		FileWriter fw;
		try {
			fw = new FileWriter(fileLocation);
		} catch (IOException e) {
			fw=null;
			Exceptions.fileIOException();
		}
		PrintWriter pw = new PrintWriter(fw);
		TextArea text=(TextArea) App.getRoot().getCenter();
		String te=text.getText();
		File file = new File(te);
		Scanner scanner = new Scanner(te);
		while(scanner.hasNextLine()) {
		String line = scanner.nextLine();
		pw.println(line);
		}
		pw.close();
	}
	
	public static void saveMarkov(String fileLocation) {
		FileWriter fw;
		try {
			fw = new FileWriter(fileLocation);
		} catch (IOException e) {
			fw=null;
		}
		PrintWriter pw = new PrintWriter(fw);
		TextArea text=(TextArea) App.getRoot().getCenter();
		String te=text.getText();
		pw.println(te);
		pw.close();
	}
	
	public static void saveTruncate(String fileLocation,double increment) {
		FileWriter fw;
		try {
			fw = new FileWriter(fileLocation);
		} catch (IOException e) {
			fw=null;
		}
		PrintWriter pw = new PrintWriter(fw);
		TextArea text=(TextArea) App.getRoot().getCenter();
		String te=text.getText();
		int end=(int)(te.length()*increment);
		te=te.substring(0, end);
		pw.println(te);
		pw.close();
	}
	
	public static double syllableCounter() {
		TextArea text=(TextArea) App.getRoot().getCenter();
		String te=text.getText().toLowerCase();
		int counter=0;
		String[] next=te.split("(?!^)");
		for (int i=0;i<next.length;i++) {
			if(next[i].equals("a")||next[i].equals("e")||next[i].equals("i")||next[i].equals("o")||next[i].equals("u")||next[i].equals("y")) {
				if(next[i].equals("e")&&next[i+1].equals("")) {
					
				}else {
				if(i==next.length-1) {
					counter++;
					break;
				}
				
				if(!next[i].equals(next[i+1])) {
					counter++;	
				}
				}
			}
		}
		return counter;
	}
	
	
	
	public static double syllableCounterLoop(String string) {
		String te=string.toLowerCase();
		int counter=0;
		String[] next=te.split("(?!^)");
		for (int i=0;i<next.length;i++) {
			if(i==next.length-1&&!next[i].equals("e")&&next[i].equals("i")&&next[i].equals("o")&&next[i].equals("u")&&next[i].equals("y")&&next[i].equals("a")) {
				counter++;
				break;
			}
			if(i==next.length-1&&next[i].equals("e")) {
				break;
			}
			if(i==next.length-1) {
				break;
			}
			if(next[i].equals("a")||next[i].equals("e")||next[i].equals("i")||next[i].equals("o")||next[i].equals("u")||next[i].equals("y")) {
				if(next[i].equals("e")&&(next[i+1].equals(" ")||next[i+1].equals(".")||next[i+1].equals("!")||next[i+1].equals("?"))) {
					if(next[i].equals("e")&&next[i-1].equals("h")) {
						counter++;
					}
				}else {
				
				if(!next[i+1].equals("a")&&!next[i+1].equals("e")&&!next[i+1].equals("i")&&!next[i+1].equals("o")&&!next[i+1].equals("u")&&!next[i+1].equals("y")) {
					counter++;	
				}
				}
			}
		}
		System.out.println(counter);
		return counter;
//		while(scanner.hasNext()) {
//			String next=scanner.next();
//			if(next.equals("a")||next.equals("e")||next.equals("i")||next.equals("o")||next.equals("u")) {
//			counter++;	
//			}
//		}
//		return counter;
	}
	
	public static String fleschScore(String string) {
		String textString=string;
		double sentenceCount=Double.valueOf(ViewHelper.sentenceCounterGUI(textString));
		double syllable=ViewHelper.syllableCounterLoop(textString);
		double words=Double.valueOf(ViewHelper.wordCounter(textString));
		DecimalFormat f = new DecimalFormat("###.##");
		double fleschScore=(206.835-(1.015*(words/sentenceCount))-(84.6*(syllable/words)));
		String fleschString=String.valueOf(f.format(fleschScore));
		return fleschString;
	}
	public static void fleschScore2(double words,double sentenceCount, double syllable) {
		double fleschScore=(206.835-(1.015*(words/sentenceCount))-(84.6*(syllable/words)));
		String fleschString=String.valueOf(fleschScore);
	}
}

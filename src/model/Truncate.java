package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import app.App;
import javafx.scene.control.TextArea;

public class Truncate {
	private double numberOfSubFiles;
	
	public Truncate(int numberOfSubFiles) {
		this.numberOfSubFiles=numberOfSubFiles;	
	}
	
	public LinkedList<Double> createFiles() {
		double increment=1.0/numberOfSubFiles;
		double start=1.0/numberOfSubFiles;
		double timeStart;
		double time;
		TextArea text=(TextArea) App.getRoot().getCenter();
		String te=text.getText();
		int length=te.length();
		LinkedList<Double> list= new LinkedList<>();
		for(int i=0;i<numberOfSubFiles;i++) {
			timeStart=java.lang.System.currentTimeMillis();
			saveTruncate("truncateFiles/truncate"+i,start,te,length);
			start+=increment;
			time=java.lang.System.currentTimeMillis()-timeStart;
			list.addLast(time);
			System.out.println(time);
		}
		return list;
	}
	
	private void saveTruncate(String fileLocation,double increment,String text,int length) {
		FileWriter fw;
		try {
			fw = new FileWriter(fileLocation);
		} catch (IOException e) {
			fw=null;
		}
		PrintWriter pw = new PrintWriter(fw);
		String te=text;
		int end=(int)(length*increment);
		te=te.substring(0, end);
		pw.println(te);
		pw.close();
	}
}

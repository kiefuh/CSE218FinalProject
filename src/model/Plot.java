package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import util.ViewHelper;

public class Plot {
	private LinkedList<Long> listSingle = new LinkedList<Long>();	
	private LinkedList<Long> listTriple= new LinkedList<Long>();
	private String fileName;
	private StringBuilder sb = new StringBuilder();
	
	public Plot(String fileName) {
		this.fileName=fileName;
	}
	
	private void calculateSingleLoop(){
		
		long timeStart;
		long time;
		String table= "		Single Loop\nFile					Time(nanoseconds)";
		sb.append(table+"\n");
		int percent=5;
		for (int i=0;i<20;i++ ) {
		String fileName=this.fileName+i;
		File file = new File(fileName);
		String string=ViewHelper.fileOpener(file);
		timeStart=java.lang.System.nanoTime();
		double wordCount=ViewHelper.wordCounterLoop(string);
		double sentenceCount=ViewHelper.sentenceCounterLoop(string);
		double syllableCount = ViewHelper.syllableCounterLoop(string);
		ViewHelper.fleschScore2(wordCount,sentenceCount,syllableCount);
		time=java.lang.System.nanoTime()-timeStart;
		listSingle.addLast(time);
		sb.append(String.valueOf(percent)+"	percent	"+"		"+time+"\n");
		percent+=5;
		System.out.println(time);
		}
	}
	
	private void calculateTripleLoop() {
		String table= "		Triple Loop\nFile					Time(nanoseconds)";
		sb.append(table+"\n");
		double wordCount = 0;
		double sentenceCount = 0;
		double syllableCount = 0;
		long timeStart;
		long time;
		int percent=5;
		for (int i=0;i<20;i++) {
			String fileName="truncateFiles/truncate"+i;
			File file = new File(fileName);
			String string=ViewHelper.fileOpener(file);
			timeStart=java.lang.System.nanoTime();
			for (int a=0;a<1;a++) {	
				wordCount=ViewHelper.wordCounterLoop(string);
			}
			for (int a=0;a<1;a++) {
				sentenceCount=ViewHelper.sentenceCounterLoop(string);
			}
			for (int a=0;a<1;a++) {
				syllableCount=ViewHelper.syllableCounterLoop(string);
			}
			ViewHelper.fleschScore2(wordCount, sentenceCount, syllableCount);
			time=java.lang.System.nanoTime()-timeStart;
			listTriple.addLast(time);
			sb.append(String.valueOf(percent)+"	percent	"+"		"+time+"\n");
			percent+=5;
			System.out.println(time);
		}
	}
	
	private void exportResults() {
		FileWriter fw;
		try {
			fw = new FileWriter("outsideSource/results.txt");
		} catch (IOException a) {
			fw=null;
		}
		PrintWriter pw = new PrintWriter(fw);
		String result=sb.toString();
		pw.println(result);
		pw.close();
	}
	
	private LineChart fillGraph() {
		final NumberAxis xAxis= new NumberAxis();
		final NumberAxis yAxis= new NumberAxis();
		xAxis.setLabel("Percent of Text Saved");
		yAxis.setLabel("Time Taken (ns)");
		final LineChart<Number,Number> lineChart= new LineChart<Number,Number>(xAxis,yAxis);
		lineChart.setTitle("Time Taken to Calculate Flesch Score");
		XYChart.Series series= new XYChart.Series();
		XYChart.Series series2= new XYChart.Series();
		double percentage=5;
		while(listSingle.isEmpty()!=true) {
			series.getData().add(new XYChart.Data(percentage,listSingle.pop()));
			percentage+=5;
		}
		percentage=5;
		while(listTriple.isEmpty()!=true) {
			series2.getData().add(new XYChart.Data(percentage,listTriple.pop()));
			percentage+=5;
		}
		series.setName("Single Loop");
		series2.setName("Triple Loop");
		lineChart.getData().addAll(series,series2);
		return lineChart;
	}
	
	public LineChart createGraph() {
		calculateSingleLoop();
		calculateTripleLoop();
		exportResults();
		return fillGraph();
	}
	
}

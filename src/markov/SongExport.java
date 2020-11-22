package markov;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SongExport {
	public static void exportSong(String song)  {
		FileWriter fw;
		try {
			fw = new FileWriter("data/blowingIn.txt");
		} catch (IOException e) {
			fw=null;
			ExceptionAlerts.fileNotFound();
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println(song);
		pw.close();
	}
}

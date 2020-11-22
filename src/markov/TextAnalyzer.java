package markov;

import java.io.File;
import markov.MasterLinkList.MasterLink;
import util.ViewHelper;

public class TextAnalyzer {

	
	public static MasterLinkList importMarkovNew() {
		File fileSong= new File("outsideSource/markov.txt");
		String text=ViewHelper.fileOpener(fileSong);
		String[] wordArray=text.split("\\s+|\\n|\\t");
		MasterLinkList mLL= new MasterLinkList();
		String current;
		String next;
		MasterLink link;
		for(int i=0;i<wordArray.length;i++) {
			if(i==wordArray.length-1) {
				mLL.insertFirst(wordArray[i]);
				break;
			}
			current=wordArray[i];
			next=wordArray[i+1];
			link=mLL.findLink(current);
			if(link==null){
				mLL.insertFirst(current);
				mLL.getFirst().getBabyList().insertFirst(next);
			}
			else {
			link.getBabyList().insertFirst(next);
			}
			double percent=(i/(double)wordArray.length)*100;
			System.out.println(percent);
		}
		System.out.println("import complete");
		return mLL;
	}
	
}

package markov;



import markov.BabyLinkedList.BabyLink;
import markov.MasterLinkList.MasterLink;

public class SongGenerator {
	private MasterLinkList list;
	
	public SongGenerator(MasterLinkList list) {
		this.list=list;
	}
	
	public String generate(int length,String word) {
		int songLength=length;
		String startingWord=word;
		StringBuilder sb = new StringBuilder();
		sb.append(word);
		for (int i=0;i<songLength-1;i++) {
			sb.append(" ");
			MasterLink mL=list.findLink(startingWord);
			if(mL==null) {
				sb.append(" does not exist in this work");
				break;
			}
			BabyLink bL=mL.getBabyList().randomLink();
			if(bL==null) {
				sb.append(word);	
				startingWord=word;
			}
			else {
				sb.append(bL.getword());	
				startingWord=bL.getword();
			}
			
		}
		String song=sb.toString();
		return song;
	}
}

package markov;

import markov.MasterLinkList.MasterLink;

public class BabyLinkedList {
	
	private BabyLink first;
	private int counter;
	public BabyLinkedList() {
		first=null;
		counter=0;
	}
	
	public boolean isEmpty() {
		return first==null;
	}
	
	public BabyLink getFirst() {
		return first;
	}
	
	public BabyLink randomLink() {
		BabyLink current=first;
		int randomNumber=(int)(Math.random()*counter);
		if (randomNumber==0) {
			return current;
		}
		for(int i=0;i<randomNumber;i++) {
			current=current.getNext();
			if (current==null) {
				current=first;
				break;
			}
		}
		return current;
	}
	
	public void insertFirst(String keyword) {
		BabyLink newLink= new BabyLink(keyword);
		newLink.setNext(first);
		first=newLink;
		counter++;
	}
	
	public BabyLink deleteFirst() {
		BabyLink temp=first;
		first=first.getNext();
		counter--;
		return temp;
	}
	
	public BabyLink delete(String key) {
		BabyLink current=first;
		BabyLink previous=first;
		while(current.getNext()!=null) {
			if (current.getNext()==null) {
				return null;
			}
			else {
				previous=current;
				current=current.getNext();
			}
			if(current.getword().equalsIgnoreCase(key)) {
				break;
			}
		}
		
		if (current==first) {
			first=first.getNext();
		}
		else {
			previous.setNext(current.getNext());
		}
		
		return current;
	}
	
	public boolean find(String key) {
		BabyLink current=first;
		while(current!=null) {
		if (current.getword()==key) {
			return true;
		}
		else {
			current=current.getNext();
		}}
		return false;
				}
	
	public BabyLinkedList findBabyLinks(String key,MasterLinkList mLL) {
		MasterLink current=mLL.getFirst().getNext();
		MasterLink previous=mLL.getFirst();
		BabyLinkedList bLL= new BabyLinkedList();
		while(current!=null) {
		if (current.getKeyword().equalsIgnoreCase(key)) {
			current=current.getNext();
			bLL.insertFirst(previous.getKeyword());
			previous=previous.getNext();
		}
		else {
			current=current.getNext();
			previous=previous.getNext();
		}}
		return bLL;
				}
	
	public void display() {
		BabyLink current= first;
		while(current!=null) {
			current.display();
			current=current.getNext();
		}
		System.out.println();
	}
	//nested private class
	public  class  BabyLink {
		private String word;
		private BabyLink next;
		
		public BabyLink(String nextWord) {
			this.word=nextWord;
			
		}

		public String getword() {
			return word;
		}

		public void setNextWord(String nextWord) {
			this.word = nextWord;
		}

		public BabyLink getNext() {
			return next;
		}

		public void setNext(BabyLink next) {
			this.next = next;
		}
		
		public void display() {
			System.out.print(word+", ");
		}
		
		
	}
}

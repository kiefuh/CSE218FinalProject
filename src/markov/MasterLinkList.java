package markov;


public class MasterLinkList {
	private MasterLink first;
	private int counter;
	public MasterLinkList() {
		first=null;
		counter=0;
	}
	
	public boolean isEmpty() {
		return first==null;
	}
	
	public MasterLink getFirst() {
		return first;
	}
	
	public int getCounter() {
		return counter;
	}
	
	public void insertFirst(String keyword) {
		BabyLinkedList bll= new BabyLinkedList();
		MasterLink newLink= new MasterLink(keyword,bll);
		newLink.setNext(first);
		first=newLink;
		counter++;
	}
	
	public MasterLink deleteFirst() {
		MasterLink temp=first;
		first=first.getNext();
		counter--;
		return temp;
	}
	
	public MasterLink delete(String key) {
		MasterLink current=first;
		MasterLink previous=first;
		while(current.getNext()!=null) {
			if (current.getNext()==null) {
				return null;
			}
			else {
				previous=current;
				current=current.getNext();
			}
			if(current.getKeyword().equalsIgnoreCase(key)) {
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
		MasterLink current=first;
		while(current!=null) {
		if (current.getKeyword().equalsIgnoreCase(key)) {
			return true;
		}
		else {
			current=current.getNext();
		}}
		return false;
				}
	
	public MasterLink findLink(String key) {
		MasterLink current=first;
		while(current!=null) {
		if (current.getKeyword().equalsIgnoreCase(key)) {
			return current;
		}
		else {
			current=current.getNext();
		}}
		return null;
				}
	
	
	
	public void display() {
		MasterLink current= first;
		while(current!=null) {
			current.display();
			current=current.getNext();
		}
		System.out.println();
	}
	//nested private class
	public class MasterLink {
		private String keyword;
		private BabyLinkedList babyList;
		private MasterLink next;
		
		public MasterLink(String keyword, BabyLinkedList bll) {
			this.keyword=keyword;
			this.babyList=bll;
		}
		
		public void setBabyList(BabyLinkedList bLL) {
			this.babyList=bLL;
		}
		
		public BabyLinkedList getBabyList() {
			return babyList;
		}

		public String getKeyword() {
			return keyword;
		}

		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}

		public MasterLink getNext() {
			return next;
		}

		public void setNext(MasterLink next) {
			this.next = next;
		}
		
		public void display() {
			System.out.print(keyword+" ");
		}
		
		
	}
}

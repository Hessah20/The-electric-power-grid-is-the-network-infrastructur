public class LinkedList<T> {
	private Node<T> head;
	private Node<T> current;

	private int size;
	public LinkedList() {
		head = current = null;
      size=0;}
	public  T  findhead(){
   return head.data;}
   
   
   public  T   retrieve(){
   return current.data;}

   public int length(){
   return size;}
	public boolean empty() {
		return head == null;}

	public boolean last() {
		return current.next == null;}

   public boolean first(){
		return current == head;
	}
public boolean full() {
		return false;}
	
	public void findFirst() {
		current = head;
	}
	public void findNext() {
		current = current.next;
	}
	
		public void update(T val) {
		current.data = val;
	}
   	public void insert(T val) {
     
      Node<T> tmp ;
		if(empty()) {
			current = head = new Node<T> (val);
         size++;
		}
		else {
			tmp = current.next;
			current.next = new Node<T> (val);
			current = current.next;
         current.next= tmp;
         size++;

		}
	}
   public void remove() {
		if(current == head) {
			head = head.next;
         size--;}
		else {
      Node<T> tmp = head;
      while(tmp.next!=current)
      tmp=tmp.next;
      tmp.next = current.next;
      size--;
					}

		if(current.next == null)
			current = head;
		else
			current = current.next;
	}
}
public class LinkedGT<T> implements GT<T>  {
private GTNode<T> root, current;



	// Return true if the tree is empty
	public boolean empty(){return root==null;}//empty




	// Return true if the tree is full
	public boolean full(){return false;}//full




	// Return the data of the current node
	public T retrieve(){
   
   return current.data;}     
   
   

	// Update the data of the current node
	public void update(T e){ current.data=e;}//update




	// If the tree is empty e is inserted as root. If the tree is not empty, e is added as a child of the current node. The new node is made current and true is returned.
	public boolean insert(T e){
   
   GTNode<T> newG = new GTNode<T> (e);
   if(full())
   return false;
   //case 1: tree empty()
   if(empty()){
   current=root = newG;
   return true;}
   //case 2: have child
   else{
   //Step1: attach parent
   newG.parent = current;
     //Step2: insert new node

   current.Cheldren.insert(newG);
   // step3: new node become a current
   current=newG;
   return true;}
   }//insert
   
   
   
	// Return the number of children of the current node.
	public int nbChildren(){
   if(empty())
   return 0;
   return current.Cheldren.length();}//nbChildren

	// Put current on the i-th child of the current node (starting from 0), if it exists, and return true. If the child does not exist, current is not changed and the method returns false.
	public boolean findChild(int i){
   if(empty())
   return false;
   int count =0;
   //case1: empty
   if (current.Cheldren.empty()){
   return false;}
   // case2: length<i 
  if(current.Cheldren.length()< i){
   return false;}
   //case3:i in range (exsist)
   current.Cheldren.findFirst();
    while(!current.Cheldren.last()){
    if(count == i){
    current =current.Cheldren.retrieve();
    return true;}
       current.Cheldren.findNext();
       count++;
      }//End while
      if(count==i){
      current =current.Cheldren.retrieve();
      return true;}
      return false;
      }//End  findChild(int i)

	// Put current on the parent of the current node. If the parent does not exist, current does not change and false is returned.
	public boolean findParent(){
   //exsist
   if (current.parent!= null){
   current = current.parent;
   return true;}
   //not exsist
   return false;
   }

	// Put current on the root. If the tree is empty nothing happens.
	public void findRoot(){
   if(!empty())
   current= root;}//findRoot

	// Remove the current subtree. The parent of current, if it exists, becomes the new current.
	public void remove(){
   GTNode<T> p = new GTNode<T>();
      GTNode<T> par = new GTNode<T>();

   GTNode<T> tmp ;
   
   //case 1: empty
   if(empty())
   return;
   //case 2: root = current 
   if(current==root)
   current=root = null;
   // case 3: subtree 
   else{
   
   p = current;
     current = current.parent;
   while(!current.Cheldren.last()){
   tmp =current.Cheldren.retrieve();
   if (tmp== p){
   current.Cheldren.remove();
   break;}
    current.Cheldren.findNext();
  
      }//End while
         
      if(current.Cheldren.retrieve()==p)
        current.Cheldren.remove();
  
  }
  }//End remove
}

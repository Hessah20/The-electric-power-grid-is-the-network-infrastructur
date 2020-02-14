public class GTNode <T>{
public T data;
public LinkedList<GTNode<T>> Cheldren;
public GTNode<T> parent;
public GTNode<T> next;
public GTNode (){
data = null;
parent = null;
next = null;
Cheldren = null;}

public GTNode (T val){
data = val;
parent = null;
next = null;
Cheldren =new LinkedList<GTNode<T>>();;}


}//end node 
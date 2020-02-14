public class PowerGridUtils {


//*****************************METHOD1***************
	// Return the IDs of all elements in the power grid pg in pre-order.
	public static Queue<Integer> collectPreorder(GT<PGElem> pg){
 Queue<Integer> ID = new LinkedQueue<Integer>();

   // Step1:  check is it empty

   //Step2: if no find root to start enqueue
   pg.findRoot();
   //Step3: use a recrsiv method to cover all elememts
   rec(pg,ID);
  
  // if empty return empty Queue
   return ID;
   }//End collectPreorder 
   
private static void rec (GT<PGElem> pg,Queue<Integer> Q){
if(pg.empty()||pg==null)
return;
Q.enqueue(pg.retrieve().getId());
if (pg.nbChildren()==0)
return ;
for(int i=0 ; i<pg.nbChildren();i++){
pg.findChild(i);
//System.out.println(i+""+pg.nbChildren()+""+pg.retrieve().getId());
 rec(pg,Q);
pg.findParent();
}

 } //End rec
//*****************************END METHOD1******************   
   
   

//*****************************METHOD2**********************
	// Searches the power grid pg for the element with ID id. If found, it is made current and true is returned, otherwise false is returned.
	public static boolean find(GT<PGElem> pg, int id){
    Queue<Integer> ID = new LinkedQueue<Integer>();
   if(pg.empty()||pg==null)
   return false;
   ID = collectPreorder(pg);
   pg.findRoot();
int length = ID.length();
for(int i = 0;i<length; i++){
int e = ID.serve();
if(e==id){
recf(pg,id);
return true;}
}//End for
return false;

 
     }//End find
static boolean flag = false;
private static boolean recf (GT<PGElem> pg,int id){
if(pg.retrieve().getId()==id){
return true ;}
for(int i=0 ; i<pg.nbChildren();i++){

pg.findChild(i);
flag = recf(pg,id);
if(flag)
return true;
pg.findParent();}
return false;
} //End recf
//*****************************END METHOD2***************


//*****************************METHOD3*******************

	// Add the generator element gen to the power grid pg. This can only be done if the grid is empty. If successful, the method returns true. If there is already a generator, or gen is not of type Generator, false is returned.
	public static boolean addGenerator(GT<PGElem> pg, PGElem gen){
   //Step1: check (is it  empty or not)
   if(!pg.empty())
   return false;
   //Step2: is it gerater
   switch(gen.getType()){
   case Generator:
   //if yes insert and return true
    pg.insert(gen);

    return true;
    default:return false;}}
    //if No return false
   // return false;}
   //*****************************END METHOD3***************



//*****************************METHOD4*****************

	// Attaches pgn to the element id and returns true if successful. Note that a consumer can only be attached to a transmitter, and no element can be be attached to it. The tree must not contain more than one generator located at the root. If id does not exist, or there is already aelement with the same ID as pgn, pgn is not attached, and the method retrurns false.
	public static boolean attach(GT<PGElem> pg, PGElem pgn, int id){
   if(find(pg,pgn.getId()))
   return false;
  
   if(find(pg,id))
   switch(pgn.getType()){
   case Generator:
   if(pg.empty()){
   pg.insert(pgn);
   return true;}
   return false;
   case Transmitter:
   switch(pg.retrieve().getType()){
   case Generator:
   pg.insert(pgn);
   return true;
   case Transmitter:
   pg.insert(pgn);
   return true;
       default:return false;}
  case Consumer:
  switch(pg.retrieve().getType()){
     case Transmitter:
   pg.insert(pgn);
   return true;
       default:return false;}

       default:return false;}
       return false;

   
      }
//*****************************END METHOD4***************



//*****************************METHOD5*******************

	// Removes element by ID, all corresponding subtree is removed. If removed, true is returned, false is returned otherwise.
	public static boolean remove(GT<PGElem> pg, int id){
   if(find(pg,id)){
   pg.remove();
   return true;}
   return false;}
//*****************************END METHOD5***************
   
//*****************************METHOD6****************
static double p = 0.0;
	// Computes total power that consumed by a element (and all its subtree). If id is incorrect, -1 is returned.
	public static double totalPower(GT<PGElem> pg, int id){
   p=0.0;
   if(pg==null)
   return -1;
   if(pg.empty())
   return -1;
    if(!find(pg,id))
    return -1;
    recTP(pg,id);
    return p;}
    

private static void recTP (GT<PGElem> pg,int id){
switch(pg.retrieve().getType()){
case Consumer:
p+=pg.retrieve().getPower();
}
if (pg.nbChildren()==0)
return ;
for(int i=0 ; i<pg.nbChildren();i++){
pg.findChild(i);
//System.out.println(i+""+pg.nbChildren()+""+pg.retrieve().getId());
 recTP(pg,id);
pg.findParent();
}

 } //End rec


//*****************************END METHOD6***************


//*****************************METHOD7******************

	// Checks if the power grid contains an overload. The method returns the ID of the first element preorder that has an overload and -1 if there is no overload.
	public static int findOverload(GT<PGElem> pg){
   if(pg==null)
   return -1;
     if(pg.empty())
   return -1;
   pg.findRoot();
   return recOver(pg,pg.retrieve().getPower());
   
   }
   
    private static int recOver (GT<PGElem> pg,double E){
    switch(pg.retrieve().getType()){
    case Consumer:
    return-1;
     }

    if (pg.nbChildren()==0)
      return-1 ;
    int Id=pg.retrieve().getId();

   double overload=totalPower(pg,Id);
   if(overload>E){
    return pg.retrieve().getId();}
   
   find(pg,Id);
   for(int i=0 ; i<pg.nbChildren();i++){
pg.findChild(i);
E=pg.retrieve().getPower();
int id =recOver(pg,E);
//System.out.println(i+""+pg.nbChildren()+""+pg.retrieve().getId());
if(id!=-1)
return id;
pg.findParent();
}
return -1;
    
    }
//*****************************END METHOD7***************

  }//End clss
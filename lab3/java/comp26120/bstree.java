package comp26120;

public class bstree implements set<String> {
    static int insert_comparisons = 0;
    static int search_comparisons = 0; 
    int verbose;

    String value;
    bstree left;
    bstree right;

    speller_config config;

    // TODO add fields for statistics
    
    int total_nodes = 0;
    int total_query_nodes = 0;
    
    public bstree(speller_config config) {
    verbose = config.verbose;
    this.config = config;
    }


    public int size(){
// This presumes that if value is not null then (possibly empty) left and right trees exist.
if(tree()){
   return 1 + left.size() + right.size();
}
return 0;
    }

    public void insert (String value)
    {
if(tree()){
   // TODO if tree is not NULL then insert into the correct sub-tree
        int result = this.value.compareToIgnoreCase(value);
        insert_comparisons++;
        if(result > 0){
            if(left != null){ left.insert(value);}
            else {left = new bstree(config); left.value = value;}
        }
        else if(result < 0){
            if(right != null){ right.insert(value);}
            else{right = new bstree(config); right.value = value;}
        }
}
else{
   // TODO otherwise create a new node containing the value and two sub-trees.
        this.value = value;
    }
    total_nodes++;
    }

    public boolean find (String value)
    {total_query_nodes++;
        if(tree()){
    //TODO complete the find function
            search_comparisons++;
            
            int result = this.value.compareToIgnoreCase(value);
            if(result == 0){return true;}
            else if(result > 0){return left != null && left.find(value);}
            else {return right != null && right.find(value);}
    }
// if tree is NULL then it contains no values
return false;
    }

    private boolean tree() {
return (value != null);
    }

    // You can update this if you want
    public void print_set ()
    {
// TODO update code to print binary tree.
       if(verbose > 2) 
       { if(this.left != null){this.left.print_set();}
        if(this.value != null){System.out.print(this.value+", ");}
        
        if(this.right != null)this.right.print_set();}
       

    }

    public void print_stats ()
    {
// TODO update code to record and print statistics
if(verbose >= 2){
    // System.out.print("[ ");
    // print_set();
    // System.out.print(" ]");

    System.out.println("The average comparisions made per insertion = "+(float)insert_comparisons/total_nodes);
    System.out.println("The average comparisions made per search = "+ (float)search_comparisons/total_query_nodes);
}   System.out.println("Total nodes = "+total_nodes);
    System.out.println("The final height is : "+this.height());
    
    }

    public int height(){
        int left = -1, right = -1;
        if(tree()){
            if(this.left != null){left = this.left.height();}
            if(this.right != null){right = this.right.height();}
            return 1 + Math.max(left, right);
        }
        else{
            return -1;
        }
    }
}


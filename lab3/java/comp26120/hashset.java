package comp26120;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.sound.midi.Soundbank;

public class hashset implements set<String> {
    int verbose;
    HashingModes mode;

    speller_config config;

    String[] cells;
    int size = 509;
    int num_entries; // number of cells in_use

    // TODO add any other fields that you need
    int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};
    int collisions = 0, lookup_collision = 0;
    int rehashes = 0;
    ArrayList<Integer> rehash_table = new ArrayList<Integer>();
    public hashset(speller_config config) {
    verbose = config.verbose;
    mode = HashingModes.getHashingMode(config.mode);

    // TODO: create initial hash table
    cells = new String[size];
    }

    // Helper functions for finding prime numbers
    public boolean isPrime (int n)
    {
    for (int i = 2; i*i <= n; i++)
        if (n % i == 0)
        return false;
    return true;
    }

    public int nextPrime(int n)
    {
    int i = n;
    while (!isPrime(i)) {
        i++;
    }
    return i;
    }

    public void insert (String value)
    {
    // TODO code for inserting into hash table
    int index;
    if(num_entries < (size*0.7) ){
        index  = hash1(value.toLowerCase(), false);
            cells[index] = value;
       
    }
    else {
        rehashes++;  
        rehash();
   
        index  = hash1(value.toLowerCase(), false);
       
        if(cells[index] == null){
            cells[index] = value;}
   
   
    }  
    // System.out.println(Arrays.toString(cells));
    // System.out.print("len = "+cells.length+"num_entries = "+num_entries);
}

    public boolean find (String value)
    {
    // TODO code for looking up in hash table
    int index = hash1(value, true);
    if(cells[index] != null && cells[index].equals(value)){
        return true;
    }
    
    return false;
    }

    public void print_set ()
    {
    // TODO code for printing hash table
    // System.out.println(Arrays.toString(cells));
       if(verbose > 2){ System.out.print("[ ");
        for(int i = 0; i < size; i++){
            if(cells[i] != null){
                System.out.print("("+cells[i]+" : "+i+"), ");}
        }
        System.out.println(" ]");}
    }

    public void print_stats ()
    {
    // TODO code for printing statistic
        
    if(verbose >= 2)    

    {  
        // if(verbose > 2) {print_set();} 
        System.out.println("Total collisions = "+ collisions);}
            
        System.out.println("Total rehashes = "+rehashes);
            System.out.println("Final Size = "+size);
    if(verbose >= 2){
            System.out.println("Average collisions per access = "+(float)lookup_collision/num_entries);
        }
   
        }

    // Hashing Modes

    public enum HashingModes {
    HASH_1_COLLISION_1, // =0 in mode flag
        HASH_1_COLLISION_2, // =1,
        HASH_1_COLLISION_3, //=2,
        HASH_2_COLLISION_1, // =3,
        HASH_2_COLLISION_2, // =4,
        HASH_2_COLLISION_3; // =5,

    public static HashingModes getHashingMode(int i) {
        switch (i) {
        case 1:
        return HASH_1_COLLISION_2;
        case 2:
        return HASH_1_COLLISION_3;
        case 3:
        return HASH_2_COLLISION_1;
        case 4:
        return HASH_2_COLLISION_2;
        case 5:
        return HASH_2_COLLISION_3;
        default:
        return HASH_1_COLLISION_1;
        }
    }
    }

    // Your code

   private int hash1(String s, boolean search){
    int len = s.length();
    int sum = 1;
    for  (int i = 0; i < len; i++){
        sum = (sum*47 + ((int)(s.charAt(i)))) % size;       //hashCode and Compression
    }
    if (cells[sum] != null && !cells[sum].equals(s)){
        if(!search){
            collisions++; lookup_collision++;
        }
       
        return hash2(sum,s, search);
    }
    if(cells[sum] == null && !search){
        num_entries++; rehash_table.add(sum);
        // System.out.println(rehash_table);
        }
    // System.out.println("at index 334 value = ''''' "+cells[334]+" '''''''");
    return sum;
   }

   private int hash2(int sum, String s, boolean search){
    int i = 0;
    while (cells[sum] != null && !cells[sum].equals(s)){
        sum = (sum*prime[i]+(size/53)) % size;                        //probing
       
        if(!search){
            collisions++; lookup_collision++;}
        i = (i+1) % 27;
    }  
    if(cells[sum] == null  && !search){
        num_entries++; rehash_table.add(sum);
       
    }
    return sum;
   }

   private void rehash(){
    lookup_collision = 0;
    String[] temp = cells;
    size = nextPrime(2*size);
    cells = new String[size];
   
    num_entries = 0;
    ArrayList<Integer> copy = new ArrayList<>(rehash_table);
    rehash_table.clear();
    for (int i : copy){
        cells[hash1(temp[i], false)] = temp[i];
       
       
    }
   
   
   }
}
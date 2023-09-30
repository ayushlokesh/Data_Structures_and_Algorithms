from enum import Enum
import config

class hashset:
    def __init__(self):
        # TODO: create initial hash table
        self.verbose = config.verbose
        self.mode = config.mode
        self.hash_table_size = config.init_size
                
    # Helper functions for finding prime numbers
    def isPrime(self, n):
        i = 2
        while (i * i <= n):
            if (n % i == 0):
                return False
            i = i + 1
        return True
        
    def nextPrime(self, n):
        while (not self.isPrime(n)):
            n = n + 1
        return n
        
    def insert(self, value):
        # TODO code for inserting into  hash table
        print("Placeholder")
        
    def find(self, value):
        # TODO code for looking up in hash table
        print("Placeholder")
        
    def print_set(self):
        # TODO code for printing hash table
        print("Placeholder")
        
    def print_stats(self):
        # TODO code for printing statistics
        print("Placeholder")
                        
# Hashing Modes
class HashingModes(Enum):
    HASH_1_COLLISION_1=0
    HASH_1_COLLISION_2=1
    HASH_1_COLLISION_3=2
    HASH_2_COLLISION_1=3
    HASH_2_COLLISION_2=4
    HASH_2_COLLISION_3=5
 

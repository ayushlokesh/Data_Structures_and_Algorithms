import os
import numpy as np
import sys

def generate_knapsack_instance(n, mean, std_dev, upper_bound):
    data = np.random.normal(mean, std_dev, n)

    data = np.clip(data, a_min=1, a_max=upper_bound)

    return np.round(data).astype(int)

def load_knapsack_instance(n, c, name, mean, data):
    folder_path = name
    file_name = os.path.join(folder_path, f"{mean}.txt")  
    os.makedirs(folder_path, exist_ok=True)
    with open(file_name, 'w') as fileObj:
        fileObj.write(str(n) + "\n")
        for i, weight in enumerate(data, start=1):
            profit = int(min(0.8 * weight + (mean + std_dev) / 5, (mean + std_dev)))
            fileObj.write(f"{i} {profit} {weight}\n")
        fileObj.write(f"{c}\n")

if __name__ == "__main__":
    n = int(sys.argv[1])
    upper_bound = int(sys.argv[2]) 
    std_dev = upper_bound / 50  # Set standard deviation
    name = sys.argv[3]

    # Iterate over different mean values
    means = range(int(upper_bound / 10), int(9 * upper_bound / 10) + 1, int(upper_bound / 20))
    for mean in means:
        data = generate_knapsack_instance(n, mean, std_dev, upper_bound)
        load_knapsack_instance(n, upper_bound, name, mean, data)

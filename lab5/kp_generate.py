# import os
# import random
# import sys

# def generate_uncorrelated_data(n, b):
#     data = []
#     for _ in range(n):
#         weight = random.randint(1, b)
#         profit = random.randint(1, b)
#         data.append((weight, profit))
#     return data

# def generate_strongly_correlated_data(n, b):
#     data = []
#     for i in range(1, n + 1):
#         weight = random.randint(1, b)
#         profit = int(min(0.8 * weight + b/5, b))
#         data.append((weight, profit))
#     return data

# def generate_weakly_correlated_data(n, b):
#     data = []
#     for _ in range(n):
#         weight = random.randint(1, b)
#         profit = min(b, max(weight + random.randint(-b/5, b/5), 1))
#         data.append((weight, profit))
#     return data



# def generate_knapsack_instances(n, c, b, name):
#     folder_name = str(n)
#     os.makedirs(folder_name, exist_ok=True)

#     uncorrelated_data = generate_uncorrelated_data(n, b)
#     strongly_correlated_data = generate_strongly_correlated_data(n, b)
#     weakly_correlated_data = generate_weakly_correlated_data(n, b)


#     generate_knapsack_instance(n, c, b, folder_name, name+"_uncorrelated.txt", uncorrelated_data)
#     generate_knapsack_instance(n, c, b, folder_name, name+"_strongly_correlated.txt", strongly_correlated_data)
#     generate_knapsack_instance(n, c, b, folder_name, name+"_weakly_correlated.txt", weakly_correlated_data)
# def generate_knapsack_instance(n, c, b, folder, name, data):
#     folder_path = os.path.join(folder, name)  # Corrected folder path
#     with open(folder_path, 'w') as fileObj:
#         fileObj.write(str(n) + "\n")
#         for i, (weight, profit) in enumerate(data, start=1):
#             fileObj.write(str(i) + " " + str(profit) + " " + str(weight) + "\n")
#         fileObj.write(str(c))

# if __name__ == "__main__":
#     n = int(sys.argv[1])
#     c = sys.argv[2]
#     b = int(sys.argv[3])
#     name = sys.argv[4]

#     generate_knapsack_instances(n, c, b, name)



import os
import numpy as np
import sys

def generate_normal_data_with_upper_bound(n, mean, std_dev, upper_bound):
    # Generate data with a normal distribution
    data = np.random.normal(mean, std_dev, n)

    # Clip the data to ensure it falls within the upper bound
    data = np.clip(data, a_min=1, a_max=upper_bound)

    return np.round(data).astype(int)

def generate_knapsack_instance(n, c, name, mean, data):
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
        data = generate_normal_data_with_upper_bound(n, mean, std_dev, upper_bound)
        generate_knapsack_instance(n, upper_bound, name, mean, data)

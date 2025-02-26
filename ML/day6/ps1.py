import numpy as np
class DataPoint:
    def __init__(self, monthly_income, total_spend, churn):
        self.monthly_income = monthly_income
        self.total_spend = total_spend
        self.churn = churn
import math

# Function to calculate Euclidean Distance
def euclidean_distance(p1, p2):
    return math.sqrt((p2.monthly_income - p1.monthly_income) ** 2 +
                     (p2.total_spend - p1.total_spend) ** 2)

# Function to calculate Manhattan Distance
def manhattan_distance(p1, p2):
    return abs(p2.monthly_income - p1.monthly_income) + abs(p2.total_spend - p1.total_spend)
from collections import Counter

# KNN Classification function (with class count printing)
def knn_classify(new_point, dataset, k, distance_metric):
    distances = []

    # Compute distance between new point and each data point
    for data in dataset:
        if distance_metric == "euclidean":
            dist = euclidean_distance(new_point, data)
        elif distance_metric == "manhattan":
            dist = manhattan_distance(new_point, data)
        else:
            raise ValueError("Invalid distance metric! Use 'euclidean' or 'manhattan'.")
        
        distances.append((dist, data.churn))  # Store distance and churn label

    # Sort distances in ascending order
    distances.sort()

    # Get the k nearest neighbors
    k_nearest = distances[:k]

    # Extract only the churn labels of k nearest neighbors
    churn_labels = [label for _, label in k_nearest]

    # Count occurrences of each class label
    class_counts = Counter(churn_labels)
    print(f"Class counts among {k} nearest neighbors: {dict(class_counts)}")

    # Get the most common class label
    majority_class = class_counts.most_common(1)[0][0]

    return majority_class
# Dataset
dataset = [
    DataPoint(50000, 48000, 0),
    DataPoint(70000, 70000, 0),
    DataPoint(30000, 29000, 1),
    DataPoint(60000, 51000, 0),
    DataPoint(55000, 54000, 1),
    DataPoint(75000, 65000, 0),
    DataPoint(40000, 37000, 1),
    DataPoint(62000, 61000, 0),
    DataPoint(48000, 38000, 0),
    DataPoint(56000, 49500, 1)
]

# New test points
test_points = [DataPoint(51000, 45000, None), DataPoint(51000, 50000, None)]

# Classify using Euclidean distance
for test_point in test_points:
    predicted_class = knn_classify(test_point, dataset, k=3, distance_metric="euclidean")
    print(f"Predicted class for ({test_point.monthly_income}, {test_point.total_spend}) using Euclidean: {predicted_class}")

# Classify using Manhattan distance
for test_point in test_points:
    predicted_class = knn_classify(test_point, dataset, k=3, distance_metric="manhattan")
    print(f"Predicted class for ({test_point.monthly_income}, {test_point.total_spend}) using Manhattan: {predicted_class}")
import matplotlib.pyplot as plt
import seaborn as sns

# Function to plot data points
def plot_knn_data(dataset, test_points):
    plt.figure(figsize=(8, 6))

    # Separate data based on churn class
    churn_0 = [(p.monthly_income, p.total_spend) for p in dataset if p.churn == 0]
    churn_1 = [(p.monthly_income, p.total_spend) for p in dataset if p.churn == 1]

    # Scatter plot for churn = 0
    if churn_0:
        plt.scatter(*zip(*churn_0), color='blue', label="Churn = 0", s=80, alpha=0.7)
    
    # Scatter plot for churn = 1
    if churn_1:
        plt.scatter(*zip(*churn_1), color='red', label="Churn = 1", s=80, alpha=0.7)
    
    # Scatter plot for test points (marked as green stars)
    for test in test_points:
        plt.scatter(test.monthly_income, test.total_spend, color='green', marker='*', s=200, label="New Point" if test == test_points[0] else "")

    # Labels and title
    plt.xlabel("Monthly Income")
    plt.ylabel("Total Spend")
    plt.title("Customer Churn Data & Test Points")
    plt.legend()
    plt.grid(True)

    # Show plot
    plt.show()

# Call the function
plot_knn_data(dataset, test_points)

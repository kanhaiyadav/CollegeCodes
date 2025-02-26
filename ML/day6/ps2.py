import pandas as pd

# Load dataset (Replace 'gene_expression.csv' with your actual file)
df = pd.read_csv('gene_expression.csv')

# Display first few rows
print(df.head())

# Check dataset shape
print("Dataset Shape:", df.shape)

# Check for missing values
print("Missing Values:\n", df.isnull().sum())
from sklearn.model_selection import train_test_split

X = df.iloc[:, :-1]  # Features (gene expression levels)
y = df.iloc[:, -1]   # Target (gene label)

# Split data
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

print("Training Data Shape:", X_train.shape)
print("Testing Data Shape:", X_test.shape)
from sklearn.neighbors import KNeighborsClassifier

# Initialize KNN with k=3 (you can change it)
knn = KNeighborsClassifier(n_neighbors=3)

# Train the model
knn.fit(X_train, y_train)

# Predict on test data
y_pred = knn.predict(X_test)

print("Predictions:", y_pred[:10])  # Print first 10 predictions
from sklearn.metrics import accuracy_score, confusion_matrix, classification_report

# Test different values of k
for k in [1, 3, 5, 7, 9]:
    knn = KNeighborsClassifier(n_neighbors=k)
    knn.fit(X_train, y_train)  # Train model
    y_pred = knn.predict(X_test)  # Predict
    
    # Calculate accuracy
    acc = accuracy_score(y_test, y_pred)
    print(f"Accuracy for k={k}: {acc:.4f}")

# Final model with best k (let's say k=5)
knn = KNeighborsClassifier(n_neighbors=5)
knn.fit(X_train, y_train)
y_pred = knn.predict(X_test)

# Confusion Matrix
print("\nConfusion Matrix:\n", confusion_matrix(y_test, y_pred))

# Classification Report
print("\nClassification Report:\n", classification_report(y_test, y_pred))
import matplotlib.pyplot as plt
import numpy as np
from sklearn.neighbors import KNeighborsClassifier
from matplotlib.colors import ListedColormap

# Function to plot decision boundary (Fixes multiple plots issue)
def plot_decision_boundary(X, y, k):
    plt.figure(figsize=(8, 6))  # Create a new figure to prevent multiple plots
    plt.clf()  # Clear previous plots

    knn = KNeighborsClassifier(n_neighbors=k)
    knn.fit(X, y)

    # Create mesh grid
    x_min, x_max = X[:, 0].min() - 1, X[:, 0].max() + 1
    y_min, y_max = X[:, 1].min() - 1, X[:, 1].max() + 1
    xx, yy = np.meshgrid(np.linspace(x_min, x_max, 200),
                         np.linspace(y_min, y_max, 200))

    # Predict for each point in the mesh grid
    Z = knn.predict(np.c_[xx.ravel(), yy.ravel()])
    Z = Z.reshape(xx.shape)

    # Plot decision boundary
    plt.contourf(xx, yy, Z, alpha=0.3, cmap=ListedColormap(["#FFAAAA", "#AAAAFF"]))

    # Scatter plot for data points
    for i, label in enumerate(np.unique(y)):
        plt.scatter(X[y == label, 0], X[y == label, 1], 
                    label=f"Class {label}", cmap=ListedColormap(["red", "blue"]), edgecolors="k")

    plt.title(f"Decision Boundary (k={k})")
    plt.xlabel("PCA Feature 1")
    plt.ylabel("PCA Feature 2")
    plt.legend()
    plt.grid(True)

    plt.show()  # Show only one plot at a time

# Plot decision boundaries for different k values
for k in [1, 3, 5, 7]:
    plot_decision_boundary(X_train_pca, y_train, k)
print(sns.__file__)
import numpy as np
from collections import Counter

# Function to compute Euclidean distance
def euclidean_distance(x1, x2):
    return np.sqrt(np.sum((x1 - x2) ** 2))

# KNN Classifier from Scratch
def knn_predict(X_train, y_train, X_test, k=5):
    y_pred = []
    
    for test_point in X_test:
        distances = []

        # Compute distance from each training point
        for i in range(len(X_train)):
            dist = euclidean_distance(test_point, X_train[i])
            distances.append((dist, y_train[i]))

        # Sort distances in ascending order
        distances.sort()
        
        # Get k nearest neighbors
        k_nearest = distances[:k]
        
        # Find majority class
        k_labels = [label for _, label in k_nearest]
        majority_class = Counter(k_labels).most_common(1)[0][0]
        
        y_pred.append(majority_class)
    
    return np.array(y_pred)

# Predict using custom KNN
y_pred_custom = knn_predict(X_train.values, y_train.values, X_test.values, k=5)

# Compare accuracy with sklearn's KNN
accuracy_custom = accuracy_score(y_test.values, y_pred_custom)
accuracy_sklearn = accuracy_score(y_test.values, y_pred)

print(f"Accuracy (Custom KNN): {accuracy_custom:.4f}")
print(f"Accuracy (Sklearn KNN): {accuracy_sklearn:.4f}")
from sklearn.neighbors import KNeighborsClassifier

# Train KNN using Manhattan distance
knn_manhattan = KNeighborsClassifier(n_neighbors=5, metric='manhattan')
knn_manhattan.fit(X_train, y_train)

# Predict on test data
y_pred_manhattan = knn_manhattan.predict(X_test)

# Compute accuracy
accuracy_manhattan = accuracy_score(y_test, y_pred_manhattan)
print(f"Accuracy (Sklearn KNN - Manhattan Distance): {accuracy_manhattan:.4f}")
# Train KNN using Euclidean distance
knn_euclidean = KNeighborsClassifier(n_neighbors=5, metric='euclidean')
knn_euclidean.fit(X_train, y_train)

# Predict on test data
y_pred_euclidean = knn_euclidean.predict(X_test)

# Compute accuracy
accuracy_euclidean = accuracy_score(y_test, y_pred_euclidean)

# Print comparison
print(f"Accuracy (Sklearn KNN - Euclidean Distance): {accuracy_euclidean:.4f}")
print(f"Accuracy (Sklearn KNN - Manhattan Distance): {accuracy_manhattan:.4f}")
# Train KNN with different algorithms
for algo in ['ball_tree', 'kd_tree', 'auto']:
    knn = KNeighborsClassifier(n_neighbors=5, metric='manhattan', algorithm=algo)
    knn.fit(X_train, y_train)  # Train model
    
    # Predict on test data
    y_pred = knn.predict(X_test)
    
    # Compute accuracy
    accuracy = accuracy_score(y_test, y_pred)
    
    print(f"Accuracy (KNN - Manhattan Distance, Algorithm: {algo}): {accuracy:}")

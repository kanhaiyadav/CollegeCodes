import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.cluster import KMeans, DBSCAN
from sklearn.metrics import silhouette_score, confusion_matrix, classification_report
#1
def generate_data():
    np.random.seed(42)
    cluster_1 = np.random.randn(100, 2) + np.array([3, 3])
    cluster_2 = np.random.randn(100, 2) + np.array([-3, -3])
    cluster_3 = np.random.randn(100, 2) + np.array([3, -3])
    data = np.vstack((cluster_1, cluster_2, cluster_3))
    return data

def initialize_centroids(data, k):
    indices = np.random.choice(data.shape[0], k, replace=False)
    return data[indices]

def compute_distance(a, b):
    return np.sqrt(np.sum((a - b) ** 2, axis=1))

def assign_clusters(data, centroids):
    clusters = []
    for point in data:
        distances = compute_distance(point, centroids)
        clusters.append(np.argmin(distances))
    return np.array(clusters)

def update_centroids(data, clusters, k):
    centroids = []
    for i in range(k):
        points = data[clusters == i]
        centroids.append(points.mean(axis=0))
    return np.array(centroids)

def k_means(data, k, max_iters=100):
    centroids = initialize_centroids(data, k)
    for i in range(max_iters):
        clusters = assign_clusters(data, centroids)
        new_centroids = update_centroids(data, clusters, k)
        if np.all(centroids == new_centroids):
            break
        centroids = new_centroids
    return centroids, clusters

def visualize_clusters(data, centroids, clusters, k):
    for i in range(k):
        plt.scatter(data[clusters == i][:, 0], data[clusters == i][:, 1], label=f'Cluster {i+1}')
    plt.scatter(centroids[:, 0], centroids[:, 1], color='black', marker='x', s=100, label='Centroids')
    plt.legend()
    plt.show()
data = generate_data()
for i in range(2,5):
    print(f"For k={i}:\n")
    centroids, clusters = k_means(data, i)
    visualize_clusters(data, centroids, clusters, i)
#2
df = pd.read_csv('iris.data.csv', header=None)
data = df.iloc[:, [0, 1]].values
def visualize_iris_clusters(data, clusters, centroids, k):
    plt.figure(figsize=(8, 6))
    for i in range(k):
        plt.scatter(data[clusters == i][:, 0], data[clusters == i][:, 1], label=f'Cluster {i+1}')
    plt.scatter(centroids[:, 0], centroids[:, 1], color='black', marker='x', s=100, label='Centroids')
    plt.xlabel('Sepal Length (cm)')
    plt.ylabel('Sepal Width (cm)')
    plt.title('K-Means Clustering on Iris Dataset')
    plt.legend()
    plt.show()
centroids, clusters = k_means(data, 3)
visualize_iris_clusters(data, clusters, centroids, 3)
def run_and_visualize_kmeans(data, k):
    centroids, clusters = k_means(data, k)
    visualize_iris_clusters(data, clusters, centroids, k)

run_and_visualize_kmeans(data, 2)
run_and_visualize_kmeans(data, 4)

for i in range(4):
    for j in range(i+1, 4):
        feature_data = df.iloc[:, [i, j]].values
        centroids, clusters = k_means(feature_data, 3)
        visualize_iris_clusters(feature_data, clusters, centroids, 3)

#3
datasets = ['cluster_blobs.csv', 'cluster_moons.csv', 'cluster_circles.csv', 'cluster_two_blobs.csv', 'cluster_two_blobs_outliers.csv']
dataframes = {name: pd.read_csv(name) for name in datasets}
def plot_clusters(data, labels, title):
    plt.scatter(data[:, 0], data[:, 1], c=labels, cmap='viridis')
    plt.title(title)
    plt.show()
two_blobs = dataframes['cluster_two_blobs.csv'].values
outliers = dataframes['cluster_two_blobs_outliers.csv'].values

plt.scatter(two_blobs[:, 0], two_blobs[:, 1], c='yellow', label='Two Blobs')
plt.title('Scatter Plot - Two Blobs')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.legend()
plt.show()

plt.scatter(outliers[:, 0], outliers[:, 1], c='pink', label='Two Blobs with Outliers')
plt.title('Scatter Plot - Two Blobs with Outliers')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.legend()
plt.show()

for name, df in dataframes.items():
    if 'two_blobs' not in name: 
        data = df.values
        print(f'\nDataset: {name}')

        kmeans = KMeans(n_clusters=3)
        kmeans_labels = kmeans.fit_predict(data)
        kmeans_score = silhouette_score(data, kmeans_labels)
        plot_clusters(data, kmeans_labels, f'KMeans Clustering - {name}')
        print(f'KMeans Silhouette Score: {kmeans_score:.3f}')

        dbscan = DBSCAN(eps=0.5, min_samples=5)
        dbscan_labels = dbscan.fit_predict(data)
        dbscan_score = silhouette_score(data, dbscan_labels) if len(set(dbscan_labels)) > 1 else -1
        plot_clusters(data, dbscan_labels, f'DBSCAN Clustering - {name}')
        print(f'DBSCAN Silhouette Score: {dbscan_score:.3f}')

        print(f'Better Clustering Method: {"KMeans" if kmeans_score > dbscan_score else "DBSCAN"}')

print("\nHyperparameter Tuning for DBSCAN on cluster_two_blobs_outliers.csv")
param_values = [0.5, 1, 7, 10, 0.001]
data_outliers = dataframes['cluster_two_blobs_outliers.csv'].values
for eps in param_values:
    dbscan = DBSCAN(eps=eps, min_samples=5)
    dbscan_labels = dbscan.fit_predict(data_outliers)
    plot_clusters(data_outliers, dbscan_labels, f'DBSCAN with eps={eps}')
    score = silhouette_score(data_outliers, dbscan_labels) if len(set(dbscan_labels)) > 1 else -1
    print(f'eps={eps} -> Silhouette Score: {score:.3f}')


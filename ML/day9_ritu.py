#!/usr/bin/env python
# coding: utf-8

# In[20]:


import numpy as np
import matplotlib.pyplot as plt
from sklearn import datasets


# In[51]:


#Part 1
class Perceptron:
    def __init__(self, learning_rate=0.1, max_iter=100):
        self.learning_rate = learning_rate
        self.max_iter = max_iter
        self.weights = None
        self.bias = None
        
    def activation(self, x):
        return 1 if x >= 0 else 0
    
    def train(self, X, y):
        n_samples, n_features = X.shape
        self.weights = np.zeros(n_features)
        self.bias = 0
        iterations = 0

        for _ in range(self.max_iter):
            error_count = 0
            for i in range(n_samples):
                linear_output = np.dot(X[i], self.weights) + self.bias
                y_pred = self.activation(linear_output)
                error = y[i] - y_pred
                
                self.weights -= self.learning_rate * (-error * X[i])
                self.bias -= self.learning_rate * (-error)
                
                if error != 0:
                    error_count += 1
            iterations += 1
            if error_count == 0:
                break
        return iterations
    
    def predict(self, X):
        linear_output = np.dot(X, self.weights) + self.bias
        return np.array([self.activation(x) for x in linear_output])


# In[52]:


data_and = np.array([[0, 0], [0, 1], [1, 0], [1, 1]])
labels_and = np.array([0, 0, 0, 1])

data_or = np.array([[0, 0], [0, 1], [1, 0], [1, 1]])
labels_or = np.array([0, 1, 1, 1])

perceptron = Perceptron(learning_rate=0.1, max_iter=100)
and_iterations = perceptron.train(data_and, labels_and)
print(f'AND Gate Weights: {perceptron.weights}, Bias: {perceptron.bias}, Iterations: {and_iterations}')
print(f'AND Predictions: {perceptron.predict(data_and)}')

perceptron = Perceptron(learning_rate=0.1, max_iter=100)
or_iterations = perceptron.train(data_or, labels_or)
print(f'OR Gate Weights: {perceptron.weights}, Bias: {perceptron.bias}, Iterations: {or_iterations}')
print(f'OR Predictions: {perceptron.predict(data_or)}')


# In[15]:


data_xor = np.array([[0, 0], [0, 1], [1, 0], [1, 1]])
labels_xor = np.array([0, 1, 1, 0])

perceptron = Perceptron(learning_rate=0.1, max_iter=100)
xor_iterations = perceptron.train(data_xor, labels_xor)
print(f'XOR Gate Weights: {perceptron.weights}, Bias: {perceptron.bias}, Iterations: {xor_iterations}')
print(f'XOR Predictions: {perceptron.predict(data_xor)}')


# In[16]:


#Part 2
dataset = np.array([
    [24.5, 120, 1], [26.9, 130, 1], [29.8, 140, 0], [31.2, 145, 0],
    [22.1, 110, 1], [23.8, 115, 1], [32.5, 150, 0], [28.6, 135, 0]
])

X_health = dataset[:, :2] 
y_health = dataset[:, 2]

perceptron = Perceptron(learning_rate=0.1, max_iter=100)
health_iterations = perceptron.train(X_health, y_health)
print(f'Health Classification Weights: {perceptron.weights}, Bias: {perceptron.bias}, Iterations: {health_iterations}')
print(f'Health Predictions: {perceptron.predict(X_health)}')


# In[41]:


learning_rates = [0.001, 0.01, 0.1, 1.0]
iterations_list = []

def plot_decision_boundary(X, y, perceptron,xlabel,ylabel,title,labe):
    x_min, x_max = X[:, 0].min() - 1, X[:, 0].max() + 1
    y_min, y_max = X[:, 1].min() - 1, X[:, 1].max() + 1
    xx, yy = np.meshgrid(np.linspace(x_min, x_max, 100),
                         np.linspace(y_min, y_max, 100))
    Z = perceptron.predict(np.c_[xx.ravel(), yy.ravel()]).reshape(xx.shape)
    plt.contourf(xx, yy, Z, alpha=0.3)
    scatter = plt.scatter(X[:, 0], X[:, 1], c=y, edgecolors='k', label=labe)
    plt.xlabel(xlabel)
    plt.ylabel(ylabel)
    plt.title(title)
    plt.legend(handles=scatter.legend_elements()[0], labels=labe)
    plt.show()
    
for lr in learning_rates:
    perceptron = Perceptron(learning_rate=lr, max_iter=100)
    iterations = perceptron.train(X_health, y_health)
    iterations_list.append(iterations)
    print(f'Learning Rate: {lr}, Iterations: {iterations}')
    print(f'Weights: {perceptron.weights}, Bias: {perceptron.bias}')
    plot_decision_boundary(X_health, y_health, perceptron,'BMI','Blood Pressure','Decision Boundary for Health Classification',['Unhealthy', 'Healthy'])

test_samples = np.array([[27.0, 125], [30, 135], [25.0, 140]])
print(f'Test Predictions for [27.0, 125], [30, 135], [25.0, 140]: {perceptron.predict(test_samples)}')


# In[18]:


plt.figure()
plt.plot(learning_rates, iterations_list, marker='o', linestyle='-')
plt.xscale('log')
plt.xlabel('Learning Rate')
plt.ylabel('Iteration Count')
plt.title('Learning Rate vs. Iteration Count')
plt.grid(True)
plt.show()

optimal_lr = learning_rates[np.argmin(iterations_list)]
print(f'Optimal Learning Rate: {optimal_lr}')


# In[40]:


#Part 3
iris = datasets.load_iris()
X = iris.data[:, [2, 3]] #taking petal length and width
y = (iris.target != 0) * 1
print(f"Actual Values: {y}")


# In[38]:


perceptron = Perceptron(learning_rate=0.1, max_iter=100)
iterations = perceptron.train(X, y)
y_pred = perceptron.predict(X)
accuracy = np.mean(y_pred == y) * 100
print(f"Predicted Values: {y_pred}")
print(f'Perceptron Accuracy with petal features: {accuracy:.2f}% , Iterations={iterations}')


# In[33]:


X_alternate = iris.data[:, [0, 1]]  # Sepal length and width
perceptron.train(X_alternate, y)
y_pred_alt = perceptron.predict(X_alternate)
accuracy_alt = np.mean(y_pred_alt == y) * 100


# In[35]:


print(f"Actual Values: {y}")
print(f"Predicted Values: {y_pred_alt}")
print(f'Accuracy with Sepal Features: {accuracy_alt:.2f}%')


# In[43]:


X_alternate = iris.data[:, [1, 3]] 
perceptron.train(X_alternate, y)
y_pred_alt = perceptron.predict(X_alternate)
accuracy_alt = np.mean(y_pred_alt == y) * 100
print(f"Actual Values: {y}")
print(f"Predicted Values: {y_pred_alt}")
print(f'Accuracy with Sepal and Petal features: {accuracy_alt:.2f}%')


# In[44]:


X_alternate = iris.data[:, [1, 2]] 
perceptron.train(X_alternate, y)
y_pred_alt = perceptron.predict(X_alternate)
accuracy_alt = np.mean(y_pred_alt == y) * 100
print(f"Actual Values: {y}")
print(f"Predicted Values: {y_pred_alt}")
print(f'Accuracy with Sepal and Petal features: {accuracy_alt:.2f}%')


# In[45]:


X_alternate = iris.data[:, [0, 3]] 
perceptron.train(X_alternate, y)
y_pred_alt = perceptron.predict(X_alternate)
accuracy_alt = np.mean(y_pred_alt == y) * 100
print(f"Actual Values: {y}")
print(f"Predicted Values: {y_pred_alt}")
print(f'Accuracy with Sepal and Petal features: {accuracy_alt:.2f}%')


# In[46]:


X_alternate = iris.data[:, [0, 2]] 
perceptron.train(X_alternate, y)
y_pred_alt = perceptron.predict(X_alternate)
accuracy_alt = np.mean(y_pred_alt == y) * 100
print(f"Actual Values: {y}")
print(f"Predicted Values: {y_pred_alt}")
print(f'Accuracy with Sepal and Petal features: {accuracy_alt:.2f}%')


# In[36]:


learning_rates = [0.001, 0.01, 0.1, 1.0]
iterations_list = []
for lr in learning_rates:
    perceptron = Perceptron(learning_rate=lr, max_iter=100)
    iterations = perceptron.train(X, y)
    iterations_list.append(iterations)


# In[37]:


plt.figure()
plt.plot(learning_rates, iterations_list, marker='o', linestyle='-')
plt.xscale('log')
plt.xlabel('Learning Rate')
plt.ylabel('Iteration Count')
plt.title('Learning Rate vs. Iteration Count (Iris)')
plt.grid(True)
plt.show()


# In[47]:


for lr in learning_rates:
    perceptron = Perceptron(learning_rate=lr, max_iter=100)
    iterations = perceptron.train(X, y)
    iterations_list.append(iterations)
    print(f'Learning Rate: {lr}, Iterations: {iterations}')
    print(f'Weights: {perceptron.weights}, Bias: {perceptron.bias}')
    plot_decision_boundary(X, y, perceptron,'Petal length and width','Species','Decision Boundary for Iris',['Iris-setosa', 'Iris-versicolor'])


# In[ ]:





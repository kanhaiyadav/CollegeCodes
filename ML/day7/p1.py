import numpy as np
import pandas as pd
from sklearn import datasets
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import LabelEncoder
from sklearn.naive_bayes import GaussianNB
from sklearn.metrics import accuracy_score, classification_report, confusion_matrix
import seaborn as sns
import matplotlib.pyplot as plt
#1
df = pd.read_csv("iris.data.csv", header=None)
df.columns = ["sepal_length", "sepal_width", "petal_length", "petal_width", "class"]
df.head()
label_encoder = LabelEncoder()
df['class'] = label_encoder.fit_transform(df['class'])
print("Class Encodings:")
for class_label, encoded_value in zip(label_encoder.classes_, range(len(label_encoder.classes_))):
    print(f"{encoded_value}: {class_label}")
X = df.iloc[:, :-1]
y = df.iloc[:, -1]

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)
df.head()
def gaussian_probability(x, mean, std):
    return (1 / (np.sqrt(2 * np.pi) * std)) * np.exp(-((x - mean) ** 2 / (2 * std ** 2)))
def calculate_class_probabilities(X, y):
    probabilities = {}
    for class_val in np.unique(y):
        class_data = X[y == class_val]
        probabilities[class_val] = {
            'prior': len(class_data) / len(y),
            'mean': class_data.mean(axis=0),
            'std': class_data.std(axis=0)
        }
    return probabilities
def predict_naive_bayes(X, probabilities):
    predictions = []
    for _, row in X.iterrows():
        class_probs = {}
        for class_val, params in probabilities.items():
            prior = params['prior']
            likelihood = np.prod(gaussian_probability(row, params['mean'], params['std']))
            class_probs[class_val] = prior * likelihood
        predictions.append(max(class_probs, key=class_probs.get))
    return np.array(predictions)
probabilities = calculate_class_probabilities(X_train, y_train)
y_pred_custom = predict_naive_bayes(X_test, probabilities)
nb_model = GaussianNB()
nb_model.fit(X_train, y_train)
y_pred_sklearn = nb_model.predict(X_test)
y_pred_sklearn

def evaluate_model(y_true, y_pred, model_name):
    print(f"\n{model_name} Evaluation:")
    print("Accuracy:", accuracy_score(y_true, y_pred))
    print("Confusion Matrix:\n", confusion_matrix(y_true, y_pred))
    print("Classification Report:\n", classification_report(y_true, y_pred))
evaluate_model(y_test, y_pred_custom, "Custom Naïve Bayes")
evaluate_model(y_test, y_pred_sklearn, "Sklearn GaussianNB")

sns.pairplot(df, hue='class')
plt.show()
print("\nEnter flower characteristics:")
sepal_length = float(input("Sepal Length (cm): "))
sepal_width = float(input("Sepal Width (cm): "))
petal_length = float(input("Petal Length (cm): "))
petal_width = float(input("Petal Width (cm): "))

user_features = np.array([[sepal_length, sepal_width, petal_length, petal_width]])

predicted_class = nb_model.predict(user_features)[0]
print("Predicted Class (sklearn model) :", label_encoder.inverse_transform([predicted_class])[0])
user_features = pd.DataFrame([[sepal_length, sepal_width, petal_length, petal_width]], 
                             columns=["sepal_length", "sepal_width", "petal_length", "petal_width"])

# Predict using custom Naïve Bayes model
y_pred_custom_user = predict_naive_bayes(user_features, probabilities)[0]
print("Predicted Class (Custom Naïve Bayes):", label_encoder.inverse_transform([y_pred_custom_user])[0])

#2

dat = pd.read_csv("predict_income.csv")
dat.head()
dat.isnull().sum()
label_encoders = {}
for col in dat.select_dtypes(include=['object']).columns:
    le = LabelEncoder()
    dat[col] = le.fit_transform(dat[col])
    label_encoders[col] = le
dat.head()
X = dat.iloc[:, :-1]
y = dat.iloc[:, -1]

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)
nb_model = GaussianNB()
nb_model.fit(X_train, y_train)
y_pred = nb_model.predict(X_test)
def evaluate_model(y_true, y_pred, model_name):
    print(f"\n{model_name} Evaluation:")
    print("Accuracy:", accuracy_score(y_true, y_pred))
    print("Confusion Matrix:\n", confusion_matrix(y_true, y_pred))
    print("Classification Report:\n", classification_report(y_true, y_pred))
evaluate_model(y_test, y_pred, "Gaussian Naïve Bayes")

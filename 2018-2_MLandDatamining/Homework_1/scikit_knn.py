# 201420907_homework1 scikit_knn.py
import numpy as np
from sklearn import metrics
from sklearn.neighbors import KNeighborsClassifier
from sklearn.model_selection import cross_val_score

train_data = np.loadtxt(fname='./cancer_data/cancer_train.csv', delimiter=',', dtype='int64')   # Training Set load
X_train = train_data[:, 1:]  # Features of Training set data
Y_train = train_data[:, 0]  # Labels of Training set data

test_data = np.loadtxt(fname='./cancer_data/cancer_test.csv', delimiter=',', dtype='int64')     # Test Set load
X_test = test_data[:, 1:]   # Features of Test set data
Y_test = test_data[:, 0]    # Labels of Test set data

# KNN Training (A)
knn = KNeighborsClassifier(n_neighbors=5)
knn.fit(X_train, Y_train)   # KNN(K=5) training
knn_Y_pred = knn.predict(X_test)

print("------------------------------------------------------------------")
print("KNN Algorithm's Accuracy")
print("트레이닝 셋의 Accuracy : {:.3f}".format(knn.score(X_train, Y_train)))
print("테스트 셋의 Accuracy   : {:.3f}".format(knn.score(X_test, Y_test)))
print("------------------------------------------------------------------")
print("KNN Algorithm's Confusion matrix")
print(metrics.confusion_matrix(Y_test, knn_Y_pred))  # Confusion Matrix
print("------------------------------------------------------------------")
print("KNN Algorithm's Precision, Recall, f1-score")
print(metrics.classification_report(Y_test, knn_Y_pred))  # precision & recall & f1-score
print("------------------------------------------------------------------")
print("KNN Algorithm's 5-fold Cross Validation")
knn_cv = np.array(cross_val_score(knn, X_train, Y_train, cv=5))
print(knn_cv, "mean : {:.3f}".format(knn_cv.mean()))
print("------------------------------------------------------------------")





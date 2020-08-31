# 201420907_homework1 scikit_DT.py
import numpy as np
from sklearn import metrics
from sklearn.tree import DecisionTreeClassifier
from sklearn.model_selection import cross_val_score

train_data = np.loadtxt(fname='./cancer_data/cancer_train.csv', delimiter=',', dtype='int64')   # Training Set load
X_train = train_data[:, 1:] # Features of Training set data
Y_train = train_data[:, 0]  # Labels of Training set data

test_data = np.loadtxt(fname='./cancer_data/cancer_test.csv', delimiter=',', dtype='int64')     # Test Set load
X_test = test_data[:, 1:]   # Features of Test set data
Y_test = test_data[:, 0]    # Labels of Test set data

# Decision Tree Training (B)
dtc = DecisionTreeClassifier(max_depth=4)
dtc.fit(X_train, Y_train)   # DecisionTree Training
dtc_Y_pred = dtc.predict(X_test)

print("------------------------------------------------------------------")
print("Decision Tree Algorithm's Accuracy")
print("트레이닝 셋의 Accuracy : {:.3f}".format(dtc.score(X_train, Y_train)))
print("테스트 셋의 Accuracy   : {:.3f}".format(dtc.score(X_test, Y_test)))
print("------------------------------------------------------------------")
print("Decision Tree Algorithm's Confusion matrix")
print(metrics.confusion_matrix(Y_test, dtc_Y_pred))  # Confusion Matrix
print("------------------------------------------------------------------")
print("Decision Tree Algorithm's Precision, Recall, f1-score")
print(metrics.classification_report(Y_test, dtc_Y_pred))  # precision & recall & f1-score
print("------------------------------------------------------------------")
print("Decision Tree Algorithm's 5-fold Cross Validation")
dtc_cv = np.array(cross_val_score(dtc, X_train, Y_train, cv=5))
print(dtc_cv, "mean : {:.3f}".format(dtc_cv.mean()))
print("------------------------------------------------------------------")





# 201420907_homework1 scikit_SVM.py
import numpy as np
from sklearn import metrics
from sklearn.svm import SVC
from sklearn.model_selection import cross_val_score

train_data = np.loadtxt(fname='./cancer_data/cancer_train.csv', delimiter=',', dtype='int64')   # Training Set load
X_train = train_data[:, 1:] # Features of Training set data
Y_train = train_data[:, 0]  # Labels of Training set data

test_data = np.loadtxt(fname='./cancer_data/cancer_test.csv', delimiter=',', dtype='int64')     # Test Set load
X_test = test_data[:, 1:]   # Features of Test set data
Y_test = test_data[:, 0]    # Labels of Test set data

# SVM training (C)
svm = SVC(gamma=0.01)
svm.fit(X_train, Y_train)
svm_Y_pred = svm.predict(X_test)

print("------------------------------------------------------------------")
print("SVM Algorithm's Accuracy")
print("트레이닝 셋의 Accuracy : {:.3f}".format(svm.score(X_train, Y_train)))
print("테스트 셋의 Accuracy   : {:.3f}".format(svm.score(X_test, Y_test)))
print("------------------------------------------------------------------")
print("SVM Algorithm's Confusion matrix")
print(metrics.confusion_matrix(Y_test, svm_Y_pred))  # Confusion Matrix
print("------------------------------------------------------------------")
print("SVM Algorithm's Precision, Recall, f1-score")
print(metrics.classification_report(Y_test, svm_Y_pred))  # precision & recall & f1-score
print("------------------------------------------------------------------")
print("SVM Algorithm's 5-fold Cross Validation")
svm_cv = np.array(cross_val_score(svm, X_train, Y_train, cv=5))
print(svm_cv, "mean : {:.3f}".format(svm_cv.mean()))
print("------------------------------------------------------------------")




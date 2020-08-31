# 5-fold cross validation 시에
#  Precision과 Recall 및 F1-score도 구할 수 있도록
# print_table을 구현하여 main.py를 modify한 code이다

import knn
import numpy as np

def print_table(y_test, y_pred):
    TP = np.zeros(10)  # True Positive (Correct Predicted target class)
    TN = np.zeros(10)  # True Negative (Correct Predicted not-target class)
    FP = np.zeros(10)  # False Positive (Incorrect Predicted target class)
    FN = np.zeros(10)  # False Negative (Incorrect Predicted  non-target class)

    Confusion_matrix = np.zeros((10, 10), dtype='int64')

    for idx in range(len(y_test)):
        Confusion_matrix[y_test[idx]][y_pred[idx]] += 1

    for i in range(10):
        TP[i] = Confusion_matrix[i][i]

        for j in range(10):
            if i != j:
                FN[i] += Confusion_matrix[i][j]
                TN[i] += Confusion_matrix[j][j]
                FP[i] += Confusion_matrix[j][i]

    # Calculation of accuracy, precision, recall, F1-measure
    Accuracy = np.zeros(10, dtype='float64')
    Precision = np.zeros(10, dtype='float64')
    Recall = np.zeros(10, dtype='float64')
    F1_score = np.zeros(10, dtype='float64')

    for i in range(10):
        Accuracy[i] = (TP[i]) / len(y_test)
        Precision[i] = (TP[i]) / (TP[i] + FP[i])
        Recall[i] = (TP[i]) / (TP[i] + FN[i])
        F1_score[i] = (2 * Precision[i] * Recall[i]) / (Precision[i] + Recall[i])

    print("Accuracy : {:.4f}".format(Accuracy.sum()))
    print("Precision : {:.4f}".format(Precision.mean()))
    print("Recall :{:.4f}".format(Recall.mean()))
    print("F1_score : {:.4f}".format(F1_score.mean()))


train_data = np.loadtxt(fname='./digits_data/digits_train.csv', delimiter=',', dtype='float64')   # Training Set load
X_train, Y_train = knn.train(train_data)  # KNN don't need training step. So, knn.train(data) just load train_data.

test_data = np.loadtxt(fname='./digits_data/digits_test.csv', delimiter=',', dtype='float64')     # Test Set load
X_test = np.array(test_data[:, 1:], dtype='float64')  # Features of Test set data
Y_test = np.array(test_data[:, 0], dtype='int64')  # Labels of Test set data

# knn.predict(train_x, train_y, test_x, k)
knn_Y_pred = knn.predict(X_train, Y_train, X_test, 5)  # k means n of neighbors

# Calculation of TP, TN, FP, FN
print_table(Y_test, knn_Y_pred)

# Implementation of 5-Fold cross-validation
K_fold_x1 = np.array(train_data[160:, 1:], dtype='float64')
K_fold_x2 = np.concatenate(
    (np.array(train_data[:160, 1:], dtype='float64'), np.array(train_data[320:, 1:], dtype='float64')), axis=0)
K_fold_x3 = np.concatenate(
    (np.array(train_data[:320, 1:], dtype='float64'), np.array(train_data[480:, 1:], dtype='float64')), axis=0)
K_fold_x4 = np.concatenate(
    (np.array(train_data[:480, 1:], dtype='float64'), np.array(train_data[640:, 1:], dtype='float64')), axis=0)
K_fold_x5 = np.array(train_data[:640, 1:], dtype='float64')

fold_train_x = []
fold_train_x.append(K_fold_x1)
fold_train_x.append(K_fold_x2)
fold_train_x.append(K_fold_x3)
fold_train_x.append(K_fold_x4)
fold_train_x.append(K_fold_x5)

fold_train_x = np.array(fold_train_x)

K_fold_y1 = np.array(train_data[160:, 0], dtype='int64')
K_fold_y2 = np.concatenate((np.array(train_data[:160, 0], dtype='int64'),
                            np.array(train_data[320:, 0], dtype='int64')), axis=0)
K_fold_y3 = np.concatenate((np.array(train_data[:320, 0], dtype='int64'),
                            np.array(train_data[480:, 0], dtype='int64')), axis=0)
K_fold_y4 = np.concatenate((np.array(train_data[:480, 0], dtype='int64'),
                            np.array(train_data[640:, 0], dtype='int64')), axis=0)
K_fold_y5 = np.array(train_data[:640, 0], dtype='int64')

fold_train_y = []
fold_train_y.append(K_fold_y1)
fold_train_y.append(K_fold_y2)
fold_train_y.append(K_fold_y3)
fold_train_y.append(K_fold_y4)
fold_train_y.append(K_fold_y5)

fold_train_y = np.array(fold_train_y)

fold_test_x = []
fold_test_x.append(np.array(train_data[:160, 1:], dtype='float64'))
fold_test_x.append(np.array(train_data[160:320, 1:], dtype='float64'))
fold_test_x.append(np.array(train_data[320:480, 1:], dtype='float64'))
fold_test_x.append(np.array(train_data[480:640, 1:], dtype='float64'))
fold_test_x.append(np.array(train_data[640:, 1:], dtype='float64'))

fold_test_x = np.array(fold_test_x)

fold_test_y = []
fold_test_y.append(np.array(train_data[:160, 0], dtype='int64'))
fold_test_y.append(np.array(train_data[160:320, 0], dtype='int64'))
fold_test_y.append(np.array(train_data[320:480, 0], dtype='int64'))
fold_test_y.append(np.array(train_data[480:640, 0], dtype='int64'))
fold_test_y.append(np.array(train_data[640:, 0], dtype='int64'))

fold_test_y = np.array(fold_test_y)

CV_Accuracy = np.zeros(5)

for i in range(5):
    print("--------------", i+1, "th cross-validation--------------")
    CV_train_x = fold_train_x[i]
    CV_train_y = fold_train_y[i]
    CV_test_x = fold_test_x[i]
    CV_test_y = fold_test_y[i]

    CV_pred_y = knn.predict(CV_train_x, CV_train_y, CV_test_x, 1)

    CV_TP = 0

    for k in range(len(CV_test_y)):
        if CV_test_y[k] == CV_pred_y[k]:
            CV_TP += 1

    print("Accuracy : {:.4f}".format(CV_TP / 160))
    CV_Accuracy[i] = CV_TP / 160
    print_table(CV_test_y, CV_pred_y)
print("--------------------------------------------------")

print("CV_accuracy.mean : {:.4f}".format(CV_Accuracy.mean()))


# 201420907_homework1 knn.py
import numpy as np


def train(train_data):
    train_x = np.array(train_data[:, 1:], dtype='float64')  # Features of Training set data
    train_y = np.array(train_data[:, 0], dtype='int64')  # Labels of Training set data

    return train_x, train_y


def get_distance(train_x, test_x, c):
    size = len(train_x)
    distTable = []

    if c == 'E':  # Euclidean distance
        for row in test_x:
            target = np.tile(row, (size, 1))

            sqDiff = (target - train_x) ** 2  # Square of 'diff'
            sqDiff_sum = sqDiff.sum(axis=1)  # Sum of 'sqDiff'
            distance = np.sqrt(sqDiff_sum)  # Square root of 'row_sum'

            distTable.append(distance)

    elif c == 'M':  # Manhattan distance
        for row in test_x:
            target = np.tile(row, (size, 1))

            diff = np.abs(target - train_x)
            distance = diff.sum(axis=1)

            distTable.append(distance)

    elif c == 'L':  # L-infinity distance
        for row in test_x:
            target = np.tile(row, (size, 1))

            diff = np.abs(target - train_x)
            distance = diff.max(axis=1)

            distTable.append(distance)

    return np.array(distTable)


def predict(train_x, train_y, test_x,  k):

    print(k, "knn Algorithm start!")

    class_result = []
    type = input("원하는 distance 방식을 입력해주세요.(E, M, L)  ")

    distTable = get_distance(train_x, test_x, type)
    sortDistTable = distTable.argsort()  # Sort Ascending

    for row in sortDistTable:   # loop for predicting label
        label = np.zeros(10, dtype='int64')

        for neighbor in range(k):
            label[train_y[row[neighbor]]] += 1

        class_result.append(label.argmax())  # append highest frequency element. (0 ~ 9)

    class_result = np.array(class_result)

    return class_result

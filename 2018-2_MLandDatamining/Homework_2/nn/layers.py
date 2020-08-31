import numpy as np
from nn.init import initialize

class Layer:
    """Base class for all neural network modules.
    You must implement forward and backward method to inherit this class.
    All the trainable parameters have to be stored in params and grads to be
    handled by the optimizer.
    """

    def __init__(self):
        self.params, self.grads = dict(), dict()

    def forward(self, *input):
        raise NotImplementedError

    def backward(self, *input):
        raise NotImplementedError


class Linear(Layer):
    """Linear (fully-connected) layer.

    Args:
        - in_dims (int): Input dimension of linear layer.
        - out_dims (int): Output dimension of linear layer.
        - init_mode (str): Weight initalize method. See `nn.init.py`.
          linear|normal|xavier|he are the possible options.
        - init_scale (float): Weight initalize scale for the normal init way.
          See `nn.init.py`.

    """

    def __init__(self, in_dims, out_dims, init_mode="linear", init_scale=1e-3):
        super().__init__()

        self.params["w"] = initialize((in_dims, out_dims), init_mode, init_scale)
        self.params["b"] = initialize(out_dims, "zero")

    def forward(self, x):
        """Calculate forward propagation.

        Returns:
            - out (numpy.ndarray): Output feature of this layer.
        """
        ######################################################################
        # TODO: Linear 레이어의 forward propagation 구현.
        ######################################################################
        # out = Wx + b
        out = np.dot(x, self.params["w"]) + self.params["b"]

        self.params["x"] = x
        ######################################################################
        #                          END OF YOUR CODE                          #
        ######################################################################
        return out

    def backward(self, dout):
        """Calculate backward propagation.

        Args:
            - dout (numpy.ndarray): Derivative of output `out` of this layer.

        Returns:
            - dx (numpy.ndarray): Derivative of input `x` of this layer.
        """
        ######################################################################
        # TODO: Linear 레이어의 backward propagation 구현.
        ######################################################################
        dx = np.dot(dout, self.params["w"].T)
        dw = np.dot(self.params["x"].T, dout)
        db = np.sum(dout, axis=0)
        ######################################################################
        #                          END OF YOUR CODE                          #
        ######################################################################
        self.grads["x"] = dx
        self.grads["w"] = dw
        self.grads["b"] = db
        return dx


class ReLU(Layer):
    def __init__(self):
        super().__init__()

    def forward(self, x):
        ######################################################################
        # TODO: ReLU 레이어의 forward propagation 구현.
        ######################################################################
        # out = max(0, x)
        out = np.maximum(0, x)

        self.info = dict()
        self.info["x"] = x
        ######################################################################
        #                          END OF YOUR CODE                          #
        ######################################################################
        return out

    def backward(self, dout):
        ######################################################################
        # TODO: ReLU 레이어의 backward propagation 구현.
        ######################################################################
        z_mask = (self.info["x"] <= 0)
        dout[z_mask] = 0
        dx = dout
        ######################################################################
        #                          END OF YOUR CODE                          #
        ######################################################################
        self.grads["x"] = dx

        return dx


class Sigmoid(Layer):
    def __init__(self):
        super().__init__()

    def forward(self, x):
        ######################################################################
        # TODO: Sigmoid 레이어의 forward propagation 구현.
        ######################################################################
        out = 1 / (1 + np.exp(-x))

        self.params["out"] = out
        ######################################################################
        #                          END OF YOUR CODE                          #
        ######################################################################
        return out

    def backward(self, dout):
        ######################################################################
        # TODO: Sigmoid 레이어의 backward propagation 구현.
        ######################################################################
        # derivative of sigmoid is sigmoid(x)(1 - sigmoid(x))
        dx = dout * self.params["out"] * (1 - self.params["out"])
        ######################################################################
        #                          END OF YOUR CODE                          #
        ######################################################################
        self.grads["x"] = dx

        return dx


class Tanh(Layer):
    def __init__(self):
        super().__init__()

    def forward(self, x):
        ######################################################################
        # TODO: Tanh 레이어의 forward propagation 구현.
        ######################################################################
        out = np.tanh(x)
        self.params["out"] = out
        ######################################################################
        #                          END OF YOUR CODE                          #
        ######################################################################
        return out

    def backward(self, dout):
        ######################################################################
        # TODO: Tanh 레이어의 backward propagation 구현.
        ######################################################################
        # derivative of sigmoid is 1-(tanh)*(tanh)
        dx = dout * (1 - np.square(self.params["out"]))
        ######################################################################
        #                          END OF YOUR CODE                          #
        ######################################################################
        self.grads["x"] = dx
        return dx


class SoftmaxCELoss(Layer):
    """Softmax and cross-entropy loss layer.
    """

    def __init__(self):
        super().__init__()

    def forward(self, x, y):
        """Calculate both forward and backward propagation.

        Args:
            - x (numpy.ndarray): Pre-softmax (score) matrix (or vector).
            - y (numpy.ndarray): Label of the current data feature.

        Returns:
            - loss (float): Loss of current data.
            - dx (numpy.ndarray): Derivative of pre-softmax matrix (or vector).
        """
        ######################################################################
        # TODO: Softmax cross-entropy 레이어의 구현.
        #
        # NOTE: 이 메소드에서 forward/backward를 모두 수행하고, loss와 gradient (dx)를
        # 리턴해야 함.
        ######################################################################
        x = x.T
        softmax = np.exp(x) / np.sum(np.exp(x), axis=0)
        softmax = softmax.T

        size = softmax.shape[0]

        # cross-entropy
        loss = -np.sum(np.log(softmax[np.arange(size), y])) / size

        dx = softmax.copy()
        dx[np.arange(size), y] -= 1
        dx = dx / size
        ######################################################################
        #                          END OF YOUR CODE                          #
        ######################################################################
        return loss, dx


class Conv2d(Layer):
    """Convolution layer.

    Args:
        - in_dims (int): Input dimension of conv layer.
        - out_dims (int): Output dimension of conv layer.
        - ksize (int): Kernel size of conv layer.
        - stride (int): Stride of conv layer.
        - pad (int): Number of padding of conv layer.
        - Other arguments are same as the Linear class.
    """

    def __init__(
            self,
            in_dims, out_dims,
            ksize, stride, pad,
            init_mode="linear",
            init_scale=1e-3
    ):
        super().__init__()

        self.params["w"] = initialize(
            (out_dims, in_dims, ksize, ksize),
            init_mode, init_scale)
        self.params["b"] = initialize(out_dims, "zero")

        self.in_dims = in_dims
        self.out_dims = out_dims
        self.ksize = ksize
        self.stride = stride
        self.pad = pad

    def forward(self, x):
        ######################################################################
        # TODO: Convolution 레이어의 forward propagation 구현.
        #
        # HINT: for-loop의 4-중첩으로 구현.
        ######################################################################
        self.params["x"] = x

        # N = image number, C = depth, H = height, W = width
        N, C, H, W = self.params["x"].shape

        # FN = filter number, C = depth, FH = filter height, FW = filter width
        FN, C, FH, FW = self.params["w"].shape

        # P = padding size, S = stride size
        P = self.pad
        S = self.stride

        # x matrix with padding (P = padding size)
        padding_x = np.zeros((N, C, H+(2*P), W+(2*P)))

        # for padding work
        for n in range(N):
            for c in range(C):
                padding_x[n, c] = np.lib.pad(x[n, c], P, 'constant', constant_values=0)

        # OH = output height, OW = ouput weight
        OH = int(((H + 2*P - FH) / S) + 1)
        OW = int(((W + 2*P - FW) / S) + 1)
        out = np.zeros((N, FN, OH, OW))

        # for convolution foward
        for n in range(N):
            for f in range(FN):
                for h in range(OH):
                    for w in range(OW):
                        # define height start point, end point
                        h_start = h*S
                        h_end = (h*S) + FH

                        # define width start point, end point
                        w_start = w*S
                        w_end = (w*S) + FW

                        filter_x = padding_x[n, :, h_start:h_end, w_start:w_end]
                        out[n, f, h, w] = np.dot(filter_x.ravel(), self.params["w"][f].ravel()) + self.params["b"][f]
        ######################################################################
        #                          END OF YOUR CODE                          #
        ######################################################################
        return out

    def backward(self, dout):
        ######################################################################
        # TODO: Convolution 레이어의 backward propagation 구현.
        #
        # HINT: for-loop의 4-중첩으로 구현.
        ######################################################################

        # N = image number, C = depth, H = height, W = width
        N, C, H, W = self.params["x"].shape

        # FN = filter number, C = depth, FH = filter height, FW = filter width
        FN, C, FH, FW = self.params["w"].shape

        # P = padding size, S = stride size
        P = self.pad
        S = self.stride

        # define dx, dw, db placeholder
        dx = np.zeros(self.params["x"].shape)
        dw = np.zeros(self.params["w"].shape)
        db = np.zeros(self.params["b"].shape)

        # OH = output height, OW = ouput weight
        OH = int(((H + 2 * P - FH) / S) + 1)
        OW = int(((W + 2 * P - FW) / S) + 1)

        # x matrix with padding (P = padding size)
        padding_x = np.zeros((N, C, H + (2 * P), W + (2 * P)))
        # dx matrix with padding (P = padding size)
        padding_dx = np.zeros((N, C, H + (2 * P), W + (2 * P)))

        for n in range(N):
            for c in range(C):
                arr_x = self.params["x"][n, c]
                padding_x[n, c] = np.lib.pad(arr_x, P, 'constant', constant_values=0)

        for f in range(FN):
            for n in range(N):
                for h in range(OH):
                    for w in range(OW):
                        # define height start point, end point
                        h_start = h*S
                        h_end = (h*S) + FH

                        # define width start point, end point
                        w_start = w*S
                        w_end = (w*S) + FW

                        filter_dx = padding_dx[n, :, h_start:h_end, w_start:w_end]
                        filter_x = padding_x[n, :, h_start:h_end, w_start:w_end]

                        filter_dx += dout[n, f, h, w] * self.params["w"][f]
                        dw[f] += dout[n, f, h, w] * filter_x
                        db[f] += dout[n, f, h, w]

        dx = padding_dx[:, :, P:(H+P), P:(W+P)]
        ######################################################################
        #                          END OF YOUR CODE                          #
        ######################################################################
        self.grads["x"] = dx
        self.grads["w"] = dw
        self.grads["b"] = db
        return dx


class MaxPool2d(Layer):
    """Max pooling layer.

    Args:
        - ksize (int): Kernel size of maxpool layer.
        - stride (int): Stride of maxpool layer.
    """

    def __init__(self, ksize, stride):
        super().__init__()

        self.ksize = ksize
        self.stride = stride

    def forward(self, x):
        ######################################################################
        # TODO: Max pooling 레이어의 forward propagation 구현.
        #
        # HINT: for-loop의 2-중첩으로 구현.
        ######################################################################
        self.info = dict()
        self.info["x"] = x

        # N = image number, C = depth, H = height, W = width
        N, C, H, W = self.info["x"].shape

        # K = kernel size, S = stride size
        K = self.ksize
        S = self.stride

        # OH = output height, OW = ouput weight
        OH = int(((H - K) / S) + 1)
        OW = int(((W - K) / S) + 1)

        # define out placeholder
        out = np.zeros((N, C, OH, OW))

        for h in range(OH):
            for w in range(OW):
                # define height start point, end point
                h_start = h * S
                h_end = (h * S) + K

                # define width start point, end point
                w_start = w * S
                w_end = (w * S) + K

                filter_x = x[:, :, h_start:h_end, w_start:w_end]
                out[:, :, h, w] = np.max(filter_x[:][:].ravel().reshape(N*C, K*S), axis=1).reshape(N, C)
        ######################################################################
        #                          END OF YOUR CODE                          #
        ######################################################################

        return out

    def backward(self, dout):
        ######################################################################
        # TODO: Max pooling 레이어의 backward propagation 구현.
        #
        # HINT: for-loop의 4-중첩으로 구현.
        ######################################################################

        # N = image number, C = depth, OH = output height, OW = output width
        N, C, OH, OW = dout.shape

        # K = kernel size, S = stride size
        K = self.ksize
        S = self.stride

        # define dx placeholder
        dx = np.zeros(self.info["x"].shape)

        for n in range(N):
            for c in range(C):
                for h in range(OH):
                    for w in range(OW):
                        # define height start point, end point
                        h_start = h * S
                        h_end = (h * S) + K

                        # define width start point, end point
                        w_start = w * S
                        w_end = (w * S) + K

                        filter_x = self.info["x"][n, c, h_start:h_end, w_start:w_end]
                        filter_dx = dx[n, c, h_start:h_end, w_start:w_end]

                        maxv_idx = np.unravel_index(filter_x.argmax(), filter_x.shape)
                        filter_dx[maxv_idx] = dout[n, c, h, w]
        ######################################################################
        #                          END OF YOUR CODE                          #
        ######################################################################

        self.grads["x"] = dx
        return dx

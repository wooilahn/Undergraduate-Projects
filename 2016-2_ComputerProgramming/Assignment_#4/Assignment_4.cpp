#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#define MAX(A,B) (A >= B ? A : B)
#define MIN(A,B) (A >= B ? B : A)

int A[255][255];
int B[255][255];
int C[255][255];
int dimA[2];
int dimB[2];
int dimC[2];

int sum = 0;
int end = 0;
int i = 0, j = 0, k = 0;

int printMatrix(int (* Mat)[255], int sy, int sx) 
{
	// write your code here
	for (int y = 0; y < sy; y++) {
		for (int x = 0; x < sx; x++) {
			printf("%d ", Mat[y][x]);
		}
		printf("\n");
	}
	printf("\n");
	return 1;
}

int readMatrix(int(*Mat)[255], int sy, int sx) 
{
	for (int y = 0; y < sy; y++) {
		for (int x = 0; x < sx; x++) {
			scanf("%d", &Mat[y][x]);
		}		
	}

	return 1;
}

int main(void)
{
	FILE *fp = freopen("input.txt", "rt", stdin);
	if (fp == NULL) {
		fprintf(stderr, "File not found\n");
		return 0;
	}

	scanf("%d %d", dimA, dimA+1); // Height x Width
	readMatrix(A, dimA[0], dimA[1]);
	printf("Matrix A\n");
	printMatrix(A, dimA[0], dimA[1]);

	scanf("%d %d", dimB, dimB + 1);
	readMatrix(B, dimB[0], dimB[1]);
	printf("Matrix B\n");
	printMatrix(B, dimB[0], dimB[1]);

	printf("Matrix C=A*B\n");

	// write your code here

		dimC[0] = MIN(dimA[0], dimB[0]);
		dimC[1] = MIN(dimA[1], dimB[1]);
		end     = MAX(dimA[0], dimB[0]);

		if (dimA[1] >= dimB[1])
		{
			for (i = 0; i < dimC[0]; i++)
			{
				for (j = 0; j < dimC[1]; j++)
				{
					sum = 0;
					for (k = 0; k < end; k++)
						sum += A[i][k] * B[k][j];
					C[i][j] = sum;
				}

			}
		}
		
		else
		{
			for (i = 0; i < dimC[0]; i++)
			{
				for (j = 0; j < dimC[1]; j++)
				{
					sum = 0;
					for (k = 0; k < end; k++)
						sum += B[i][k] * A[k][j];
					C[i][j] = sum;
				}

			}
		}

	printMatrix(C, dimC[0], dimC[1]);
	fclose(fp);

	return 1;
}
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <algorithm>

using namespace std;

int checkPalindrome(char* buff)
{
	int ret = 0;
	// write code here
	char twice_buff[511] = { 0 }; // 길이가 (2n+1)인 배열 
	int rad[510] = { 0 }; // 반지름의 길이를 저장할 배열
	int maxR = 0, p = 0;
	int n = strlen(buff); // 문자열의 길이

	// 짝수의 회문도 구하기 위해 사이사이에 '#' 추가함
	for (int i = 0; i <= strlen(buff); i++)
	{
		twice_buff[2 * i] = '#';
		twice_buff[2 * i + 1] = buff[i];
	}

	// '#'을 추가한 문자열의 길이
	int N = strlen(twice_buff);

	// rad[i] 배열의 값을 각각 구하는 반복문
	for (int i = 1; i <= N; i++)
	{
		if (i <= maxR)
			rad[i] = min(rad[2 * p - i], maxR - i);
		else
			rad[i] = 0;

		while (i - rad[i] - 1 >= 0 && i + rad[i] + 1 <= N && twice_buff[i - rad[i] - 1] == twice_buff[i + rad[i] + 1])
			rad[i]++;

		if (maxR < i + rad[i])
			maxR = i + rad[i], p = i;
	}

	// 실제 문자열의 rad값을 구해주기 위해 2로 나눠줌
	for (int i = 0; i <= N; i++)
	{
		rad[i] /= 2;
	}

	// 0~(N-1)의 rad[i]값을 모두 더해줌
	for (int k = 0; k < N; k++)
		ret += rad[k];

	printf("\n%d\n", ret);
	return ret;
}

int main(void)
{
	char buff[255];
	int nTest;

	FILE *fp = freopen("input.txt", "rt", stdin);
	if (fp == NULL) {
		fprintf(stderr, "File not found\n");
		return 0;
	}

	scanf("%d", &nTest);

	for (int i = 0; i < nTest; i++) {
		scanf("%s", buff);
		checkPalindrome(buff);
	}
	printf("\n");
	fclose(fp);
	return 1;
}
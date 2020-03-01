
/* ----------------------------------------------------------------------------------------------------
*  파일: assignment_2_1.c
*  기능	: 10~100사이에 숫자로 숫자맞추기 게임을 통해 맞출시 2점, 틀릴시에 -1점 획득.(초기점수 10점)
          point가 20점 이상일 때 게임에서 승리했다는 문장 출력 or 0점일 때 게임에서 졌다는 문장출력 후에 프로그램 종료
*  개발자: 201420907 안우일
*  날짜	: 1차 개발 : 2016년 10월 9일
          2차 개발 : 2016년 10월 12일
*----------------------------------------------------------------------------------------------------*/
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(void)
{
	int target = 0, guess = 0; //난수는 target, 입력받는 숫자 guess
	int point = 10; //초기점수 10점
	int trial;

	while (1)
	{

		srand((unsigned int)time(NULL));
		target = rand() % 89 + 11; //10과 100사이(11~99)의 난수 생성
		trial = 0; //시도횟수 0회로 초기화

		printf("10~100사이의 정수 맞추기 게임(20점이상 승리 / 0점시 패배)\n");
		printf("현재점수는 %d점이고, 시도횟수가 6회가 넘어가면 새 게임이 시작됩니다.\n\n",point);

		while (trial != 6 && point > 0 && point < 20) 
		{
			while (1)
			{
				printf("10~100사이의 정수를 입력하세요 : "); 
				scanf_s("%d", &guess);  //guess값을 입력받음 
				getchar();

				if (guess >= 100 || guess <= 10) //0<guess<100 인지 판별
					printf("10-100사이 정수가 아닙니다. 다시 입력해주세요.\n");
				else
					break;
			}
			trial++; //시도횟수 1증가

			if (guess > target)
			{
				point--; //point -1 
				printf("target은 입력한 숫자보다 작습니다(%d번째 시도, point:%d)\n", trial, point);
			}
			else if (guess < target)
			{
				point--; //point -1 
				printf("target은 입력한 숫자보다 큽니다(%d번째 시도, point:%d)\n", trial, point);
			}
			else if (guess == target)
			{
				point += 2; //point +2
				printf("target을 맞추셨습니다!(%d번째 시도, point:%d)\n\n", trial, point);
				break; //게임 탈출
			}
		}

		if (point == 0)
		{
			printf("\npoint가 0점이 되어 게임에서 지셨습니다.\n");
			break; 
		}
		else if (point >= 20)
		{
			printf("\npoint가 20점이상이 되어 게임에서 이기셨습니다.\n");
			break; 
		}
		else if (trial == 6)
		{
			printf("제한시도횟수 %d회를 초과하였습니다.\n\n게임을 다시 시작합니다.\n", trial);
			continue; //새 게임 생성
		}
		
	}
	return 0;
}
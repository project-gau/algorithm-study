import sys
sys.stdin=open("input.txt", "rt")
input=sys.stdin.readline

n, k=map(int, input().split())
p=1000000000

# n이 고정되어 있고 k에 따른 개수를 저장하는 dp[k]를 만들면
# dp=[0]*(k+1)
# dp[1]=1
# k=2일 때
# (0,20) (1,19) (2,18) ... (10,10) ... (19,1) (20, 0) - dp[2]=21
# k=3일 때
# (0,*,*)-dp[2] (*,0,*)-dp[2] (*,*,0)-dp[2] 이지만 중복 발생..
# 0 0 20  (0 1 19  1 0 19)-2 (0 2 18  1 1 18  2 0 18)-3 (0 3 17  1 2 17  2 1 17  3 0 17)-4 ... (0 19 1 ...)-20 (0 20 0 ...)-21
# dp[k]=dp[k-1]+dp[k-1]-1 + dp[k-1]-2 + ... + 1 인가? - 아님
# 0 0 6  (0 1 5  1 0 5)  (0 2 4  1 1 4  2 1 4)  (0 3 3  1 2 3  2 1 3  3 0 3)  0 4 2  1 3 2  2 2 2  3 1 2  4 0 2  (0 5 1  1 4 1  2 3 1  3 2 1  4 1 1  5 0 1)  0 6 0  1 5 0  2 4 0  3 3 0
# 0 0 0 6  (0 0 1 5  0 1 0 5  1 0 0 5)  (0 0 2 4  0 2 0 4  2 0 0 4  0 1 1 4  1 0 1 4  1 1 0 4) (0 0 3 3  0 3 0 3  3 0 0 3  0 1 2 3  0 2 1 3  1 0 2 3  2 0 1 3  1 2 0 3  2 1 0 3  )
# dp의 정의를 고쳐야할것 같다.
# dp를 (n,k)일 때의 가지수로 정의하고 생각하면
# dp[1][x]는 x값에 상관없이 모두 1
# dp[x][1]은 x값에 따라 모두 x
# dp[n][k]는 (0 + n을 k-1개로 나눈것) + (1 + (n-1)을 k-1개로 나눈것) + ... + (19 + 1을 k-1개로 나눈것) + (20 + 0을 k-1개로 나눈것)
# dp[n][k] = dp[0][k-1] + dp[1][k-1] + ... + dp[n-1][k-1] + dp[n][k-1]
# dp[n-1][k] = dp[0][k-1] + dp[1][k-1] + ... + dp[n-1][k-1]
# 따라서 dp[n][k] = dp[n-1][k] + dp[n][k-1
dp=[[0]*(n+1) for _ in range(k+1)]
dp[1][1:n+1]=[1]*n
for x in range(1, k+1):
    dp[x][1]=x
for i in range(2, k+1):
    for j in range(2, n+1):
        dp[i][j]=dp[i-1][j]+dp[i][j-1]

print(dp[k][n]%p)

#확인용
# for x in dp:
#     print(x)
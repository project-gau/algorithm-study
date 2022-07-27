# 22.07.21
# 양쪽에 2개씩 붙는다. 그래서 x4 인데, 중복이 된다.
# 아래로 내려가게만 세면 중복되지 않는다. 그래서 x2를 해주고 안되는 거(-1)를 뺴준다고 생각했는데, 틀렸다.

import sys

sys.stdin = open("input.txt")

n = int(input())

dp = [0] * (n+1)

dp[0] = 1
dp[1] = 9

for i in range(2,n+1):
    dp[i] = (dp[i-1] * 2 - 1) % 1000000000

print(dp[n])
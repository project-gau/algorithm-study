# 22.07.22

import sys

sys.stdin = open("input.txt")

n = int(input())

arr = list(map(int,input().split()))
arr.insert(0,0)
dp = [0] * (n+1)

for i in range(1,n+1):
    for j in range(1,n+1):
        if i - j >= 0:
            dp[i] = max(dp[i],dp[i-j] + arr[j])

print(dp[n])
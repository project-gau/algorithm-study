import sys

# 22.07.21

sys.stdin = open("input.txt")

T = int(input())

def recurse(n: int, m: int, dp):
    if n == m:
        return 1
    elif n == 1:
        return m
    if dp[n][m] != 0:
        return dp[n][m]

    dp[n][m] = recurse(n,m-1,dp) + recurse(n-1,m-1,dp)
    return dp[n][m]


for _ in range(T):
    n, m = map(int, input().split())

    dp = [[0]*(m+1) for _ in range(n+1)]

    print(recurse(n,m,dp))
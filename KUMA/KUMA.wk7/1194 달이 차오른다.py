import sys
from collections import deque
sys.stdin=open("input.txt", "rt")
input=sys.stdin.readline

# 빈 칸: 언제나 이동할 수 있다. ('.')
# 벽: 절대 이동할 수 없다. ('#')
# 열쇠: 언제나 이동할 수 있다. 이 곳에 처음 들어가면 열쇠를 집는다. ('a', 'b', 'c', 'd', 'e', 'f')
# 문: 대응하는 열쇠가 있을 때만 이동할 수 있다. ('A', 'B', 'C', 'D', 'E', 'F')
# 민식이의 현재 위치: 빈 곳이고, 민식이가 현재 서 있는 곳이다. ('0')
# 출구: 달이 차오르기 때문에, 민식이가 가야하는 곳이다. 이 곳에 오면 미로를 탈출한다. ('1')

# 0. min(열쇠를 얻고 출구까지 가는 최소 통행 길이, 열쇠를 얻지 않고 출구까지 가는 최소 통행 길이)
# 1. 출발위치 0부터 1까지 '.'으로만 이어져 있는 경우: 1의 x, y 좌표 - 0의 x, y 좌표
# 2. 출발위치 0부터 1까지 사이에 알파벳으로 문이 막혀있는 경우: 해당 열쇠의 idx값까지 간 후 진행
# 3. 열쇠까지 도달할 수 없거나, 열쇠가 없거나, '#'-벽으로 사방이 모두 막힌 경우
# 열쇠와 문은 a~f 6 종류까지 존재할 수 있다

# bfs로 길이 모두 막히거나 출구에 닿을때까지 탐색을 하고 출구에 도착하면 이동거리 출력후 종료
# 길이 없을 경우 -> 막힌 곳중에 문이 있고 그 키가 존재하는 경우, 키까지 최단거리로 이동, 이후 다시 bfs 호출

n, m = map(int, input().split()) #1 ≤ N, M ≤ 50
board = [list(input().rstrip()) for _ in range(n)]
ch = [[[0]*64 for _ in range(m)] for _ in range(n)]
dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]
dq=deque()

for i in range(n):
    for j in range(m):
        if board[i][j]=='0':
            dq.append((i, j, 0, 0))
            board[i][j]='.'
            ch[i][j][0]=1
def BFS():
    while dq:
        x, y, key, cnt = dq.popleft()
        for i in range(4):
            xx=x+dx[i]
            yy=y+dy[i]
            if 0<=xx<n and 0<=yy<m and board[xx][yy]!='#' and not ch[xx][yy][key]:
                if board[xx][yy]=='.':
                    ch[xx][yy][key]=1
                    dq.append((xx, yy, key, cnt+1))
                elif board[xx][yy] == "1":
                    return cnt + 1
                else:
                    if board[xx][yy].isupper():
                        if key & 1 << (ord(board[xx][yy]) - 65):
                            ch[xx][yy][key] = 1
                            dq.append([xx, yy, key, cnt + 1])
                    elif board[xx][yy].islower():
                        nc = key | (1 << ord(board[xx][yy]) - 97)
                        if ch[xx][yy][nc] == 0:
                            ch[xx][yy][nc] = 1
                            dq.append([xx, yy, nc, cnt + 1])
    return -1
print(BFS())

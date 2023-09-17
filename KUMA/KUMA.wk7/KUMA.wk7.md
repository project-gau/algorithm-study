# 7주차 자료구조 & DFS

## 1194 달이 차오른다, 가자.
* 수면제 이빠이 먹고 풀어보려 해서 그런가 당연히 못풀었고 구글링해서 나온 풀이를 보는데 이해도 안됐다.
* 정신좀 차리고 보니 이해는 갔다. 핵심은 기존 방문여부 확인 배열인 `ch를 3차원 리스트로 구성해서 key의 상태에 따른 방문여부`를 각자 구성하는 개념으로 접근했다는 점과 더불어 `bfs를 돌 때 dq에 key와 거리를 나타내는 cnt 변수를 추가로 저장해서 활용`한다는 점이다.
* 다음으로 이동할 곳의 정보에 따라 분기가 나뉜다.
  1. `'1'` 이거나 `'.'`인 경우: `cnt를 +1` 해주고 `'1'인 경우는 cnt값 리턴`
  2. 알파벳 `소문자`인 경우: `현재 key와 or연산`을 통해 `key를 획득`
  3. 알파벳 `대문자`인 경우: `현재 key와 and연산`을 통해 `문의 통과 여부`를 확인
* 수면제가 아니었어도 자력으로 못풀었을 것 같다.
    ```python
    n, m = map(int, input().split()) #1 ≤ N, M ≤ 50
    board = [list(input().rstrip()) for _ in range(n)]
    ch = [[[0]*64 for _ in range(m)] for _ in range(n)]
    dx = [-1, 0, 1, 0]
    dy = [0, -1, 0, 1]
    dq=deque()

    for i in range(n):
        for j in range(m):
            if board[i][j]=='0': # 시작지점 0을 .으로 바꿔주고 dq에 정보 저장
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

    ``` 
## 1525 퍼즐
* 이 문제도 발상의 전환이 필요했다. `방문여부`와 `이동 횟수를 저장`하는 요소가 하나 필요한데 보통은 이 요소를 리스트로 구성해왔다.
* 이 문제에서 시간초과를 피하려면 `위의 요소를 문자열`로 `딕셔너리`에 저장해야한다.
* 뿐만 아니라 초기에 입력받은 board 리스트는 바로 문자열로 치환해서 `bfs도 문자열을 대상으로 가상의 3*3 리스트를 탐색`하도록 한다.
    ```python
    board=[list(map(int, input().split())) for _ in range(3)]
    dq=deque()
    dx=[-1, 0, 1, 0]
    dy=[0, -1, 0, 1]

    def BFS():
        while dq:
            now=dq.popleft()
            # 차례로 정렬되었으면 이동 횟수 출력
            if now == "123456780": 
                print(dt[now])
                return
            # 0의 인덱스를 찾고 그 인덱스에 해당하는 3*3 리스트의 인덱스를 x, y에 저장
            idx=now.find('0') 
            x, y = idx//3, idx%3

            for i in range(4):
                xx = x + dx[i]
                yy = y + dy[i]
                if 0 <= xx < 3 and 0 <= yy < 3:
                    # 4방 탐색한 인덱스를 다시 문자열에 맞는 인덱스로 치환
                    target_idx = 3 * xx + yy  
                    # now를 리스트화하고 숫자를 바꾼뒤 다시 문자열화 한다.
                    nextNum=list(now) 
                    nextNum[target_idx], nextNum[idx] = nextNum[idx], nextNum[target_idx]
                    nextNum = ''.join(nextNum)
                    # 딕셔너리 dt에 다음 방문할 위치의 정보가 없다면 이동거리 갱신, dq에 방문할 곳 추가
                    if not dt.get(nextNum):
                        dt[nextNum]=dt[now]+1
                        dq.append(nextNum)
        # 더이상 탐색할 곳이 없고 정렬된 형태로 되지 않을 때 -1 출력
        print(-1)

    dt={}
    start = ""
    for i in range(3):
        for j in range(3):
            start += str(board[i][j])
    dq.append(start)
    dt[start]=0
    BFS() 
    ```

## 1939 중량제한
* 이것도 구글링해서 풀었다. bfs도 버거운데 간단하다지만 이런 이분탐색같은 다른 알고리즘을 접합하는건 어려운 것 같다.
```python
n, m = map(int, input().split()) # 2 ≤ N ≤ 10,000, 1 ≤ M ≤ 100,000
bridge=[[] for _ in range(n+1)]
for _ in range(m):
    a, b, c = map(int, input().split()) # 1 ≤ A, B ≤ N, 1 ≤ C ≤ 1,000,000,000, 서로 같은 두 섬 사이에 여러 개의 다리가 있을 수도 있다.
    bridge[a].append((b, c))
    bridge[b].append((a, c))
startIsland, endIsland = map(int, input().split())

def BFS(mid):
    dq=deque()
    dq.append(startIsland)
    ch[startIsland] = True
    while dq:
        now = dq.popleft()
        if now == endIsland:
            return True
        for nnow, ncost in bridge[now]:
            if not ch[nnow]:
                if mid <= ncost:
                    ch[nnow] = True
                    dq.append(nnow)
res=0
start, end = 1, 1000000000
while start <= end:
    ch = [0] * (n+1)
    mid = (start + end) // 2
    if BFS(mid):
        res = mid
        start = mid +1
    else:
        end = mid -1
print(res)
```
## 2812 크게 만들기
* 최대한 큰 숫자를 만들어야 하므로 숫자 앞자리부터 스택에 넣고 스택의 최상단 수보다 더 큰수를 읽을 때마다 총 k값만큼 스택에서 pop시키면 된다.
* 입력이 652653 , 3 처럼 k값만큼 스택에서 pop시키지 못한 채로 끝나는 경우의 예외가 등장하는데 이는 앞에서부터 n-k만큼 슬라이싱해서 출력하면 된다.
    ```python
    for i in range(n):
        while K>0 and stack and stack[-1] < num[i]:
            stack.pop()
            K-=1
        stack.append(num[i])

    print(''.join(stack[:n-k]))
    ```
## 4195 친구 네트워크
* 처음엔 다음과 같이 단순하게 친구관계를 입력 받는대로 딕셔너리에 추가해서 그 길이를 활용해서 풀으려했다.
    ```python
    friend={}
    fr1, fr2 = input.split()
    try:
        friend[fr1].append(fr2)
    except:
        friend[fr1]=[fr2]
    ...
    print(len(friend[fr1])+ ... + ...)
    ```
* 근데 출력부를 보면 알듯이 `친구의 친구식으로 타고 올라가야할 필요`가 있기도 하고 그 숫자를 파악해야 해서 관계를 타고 올라가 중복되는 문자열을 검사해서 그값을 뺀 나머지의 숫자만 매번 더해주기엔 시간초과를 피할 수 없을 것 같고 `그 인물의 친구관계수를 누적해서 더해주는 식으로 구현`해야한다는 생각이 들었고 굳이 이름을 저장할 필요없이 부모관계를 정립해주면 될 것이라는 생각이 들었다.
* 부모를 찾는 함수 `findParent(x)`: x의 뿌리노드를 찾아주는 함수
    ```python
    def findParent(x):
        if parent[x] != x:
            parent[x] = findParent(parent[x])
        return parent[x]
    ``` 
* `부모관계를 저장하는 parent`와 `친구네트워크 수를 나타내는 cnt` 딕셔너리형을 각자 두어 입력 받은 `두 친구의 부모가 같은 경우(이미 이어져 있는 상태)` 따로 `cnt를 더하지않고 부모노드의 cnt값을 출력`하고 `부모가 같지 않은 경우(관계가 없던 상태)에는 부모노드로 설정`하고 `자식노드의 cnt값을 부모노드의 cnt값에 더해준 뒤 출력`했다.
    ```python
    for i in range(t):
        f=int(input()) # 100000이하
        cnt={}
        parent={}
        for j in range(f):
            fr1, fr2 = input().split()
            if fr1 not in parent:
                parent[fr1]=fr1
                cnt[fr1]=1
            if fr2 not in parent:
                parent[fr2]=fr2
                cnt[fr2]=1
            a=findParent(fr1)
            b=findParent(fr2)
            if a!=b:
                parent[a] = b
                cnt[b] += cnt[a]
            print(cnt[b])
    ```
## 7662 이중 우선순위 큐
* 처음엔 음수와 양수를 다른 힙으로 각각 추가해서 해보려고도 해봤는데 결국 조건문만 더 붙고 비효율적이라는 생각이 들어서 튜플로 정렬 기준이 아닌 뒤에다가 인덱스를 넣고 방문여부를 나타내는 리스트 visited를 따로 만들어 해결했다.

## 17299 오등큰수
* 저번에 풀었던 오큰수랑 제목부터 비슷한 문제였다.
* 시간복잡도가 n^2여도 딕셔너리를 활용하면 해당 인덱스 오른쪽부터만 탐색하면 시간초과에 걸리지 않을거 같아서 그냥 반복문으로 풀어봤는데 채점 70%정도에서 시간초과가 뜬다. 
    ```python
    n=int(input())
    A=list(map(int, input().split()))
    NGF=[-1]*n
    cnt={}
    cnt_A=[]
    for x in A:
        if x in cnt:
            cnt[x]+=1
        else:
            cnt[x]=1

    for i in range(n):
        cnt_A.append(cnt[A[i]])

    for i in range(n):
        for j in range(i+1, n):
            if cnt_A[i]<cnt_A[j]:
                NGF[i]=A[j]
                break
    print(*NGF)
    ```

* 스택을 활용하는 코드로 통과했다. 가장 다른점은 스택을 활용함으로써 `NGF값을 기준`으로 반복문이 돌아가지 않고 `cnt_A 값을 기준`으로 더 작은 빈도수를 가지는 값을 일괄적으로 적용 해줬다는 점이다.
    ```python
    n=int(input())
    A=list(map(int, input().split()))
    cnt_A=[]
    NGF=[-1]*n
    cnt={}
    stack=[]

    for x in A:
        try:
            cnt[x]+=1
        except:
            cnt[x]=1
    for i in range(n):
        cnt_A.append(cnt[A[i]])

    for i in range(n):
        while stack and cnt_A[stack[-1]]<cnt_A[i]:
            NGF[stack.pop()]=A[i]
        stack.append(i)
    print(*NGF)
    ```
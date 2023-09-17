import sys
sys.stdin=open("input.txt", "rt")
input=sys.stdin.readline

# Ai가 수열 A에서 등장한 횟수를 F(Ai)라고 했을 때,
# Ai의 오등큰수는 오른쪽에 있으면서 수열 A에서 등장한 횟수가 F(Ai)보다 큰 수 중에서 가장 왼쪽에 있는 수를 의미한다.
# 예전에 풀었던 오큰수랑 비슷한데 스택없이 풀수 있을까 해서 해봤는데 시간초과 뜬다 ㅠ
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

# 스택 풀이
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
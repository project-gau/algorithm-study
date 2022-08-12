# 5주차 6주차

## 문제링크

* [1.로또](https://www.acmicpc.net/problem/6603)
* [2.괄호제거](https://www.acmicpc.net/problem/2800)
* [3.욕심쟁이 판다](https://www.acmicpc.net/problem/1937)
* [4.텀 프로젝트](https://www.acmicpc.net/problem/9466)
* [5.빙산](https://www.acmicpc.net/problem/2573)
* [6.0만들기](https://www.acmicpc.net/problem/7490)
* [7.ABCDE](https://www.acmicpc.net/problem/13023)
* [8.암호만들기](https://www.acmicpc.net/problem/1759)
* [9.야구](https://www.acmicpc.net/problem/17281)
* [10.히스토그램에서 가장 큰 직사각형](https://www.acmicpc.net/problem/6549)
* [11.불끄기](https://www.acmicpc.net/problem/14939)

## 1. 로또

* N개의 조합을 구하는 문제
  * 그냥 조합구하면 되는 문제였다.

## 2. 괄호제거

* 괄호의 쌍이 많아야 10개인게 포인트이다.
* `[여는 쌍 index - 닫는 쌍 index]`를 저장해놓고, 조합으로 선택하는 경우의 수를 구한다.

ex)
```
(1+(2*(3+4))) 의 경우
{0=12, 3=11, 6=10}
```
* 이 때, map의 경우 index의 증가로 조합을 구하기 어려우므로 따로 array - map.key를 매칭시켜서 조합을 구해주자.
  * `arr[index++] = map.key() <- iter`

* 헷갈렸던 점

```java
if(i==temp[j] || (map.containsKey(temp[j]) && i == map.get(temp[j]))) continue out;
```

* 풀고, 나중에 해설을 달 때 위 식이 헷갈렸다.
* `i == temp[j]` -> 여는 괄호의 index를 만났을 때 continue
* `(map.containsKey(temp[j]) && i == map.get(temp[j]))`
  * map이 해당 값을 포함할 때(값이 없을수도 있으므로 그냥 `map.get(temp[j])` 하면 에러남)
    * 닫는 괄호의 index라면 continue

## 3. 욕심쟁이 판다

* 처음에는 [내리막길](https://www.acmicpc.net/problem/1520) 문제의 풀이와 거의 똑같이 생각했다.
* 그런데 시간초과가 떴다.
* 풀이는 거의 똑같은 것 같은데, 시간 초과가 나는 이유는 잘 모르겠다...

## 4. 텀 프로젝트

* 사이클이 있는 수를 세는 문제였다.
  * 이 때의 아이디어는 2개의 visited배열을 선언해놓는 것이다.
    * 재 방문했는데 끝나는 방문체크가 안돼있다면 사이클이 생기는 것이다.
  * visited, isEnd 배열 2개가 있다고 해보자.
  * `visited[i] = true` 인데 `isEnd[i] = false` 면 사이클이 생긴것이다.

* 방문이 됐다면, 사이클 체크를 해준다.
* `isEnd[cur] = false` 라면, 이어져있는 값들을 전부 true처리 해주고 해당 count를 세준다.
* 만약 방문이 되지 않았다면, 방문처리를 해주고 다음 값으로 보낸다.
  * 다음 값으로 보내놓고, 현재의 값에 대해서 `isEnd[cur]=true` 체크를 해준다.
    * 사이클이 없는 값들에 대해서 cycleCount가 증가하지 않게 하기 위함이다.

```java
if (visited[cur]) {
    // 사이클 체크
    if (!isEnd[cur]) {
        int p = next;
        isEnd[cur] = true;
        cycleCount++;
        while (p != cur) {
            cycleCount++;
            isEnd[p] = true;
            p = arr[p];
        }
    }
} else {
    // 방문 안했으면 다음거 보내주고, 다음 dfs에서 사이클 체크를 해줬기 때문에 isEnd = true해줘야한다.
    visited[cur] = true;
    dfs(next);
    // 다음거 보냈으면 isEnd 체크 해줘야한다.
    isEnd[cur] = true;
}
```

## 5. 빙산

* 삼성 기출문제인 것 같다.
* 그냥 구현하면 되는 문제인데, 실수를 많이 했다.
* 실수한 것에 대해서는 코드에 주석을 달아놨다.

## 6. 0만들기

* 파이썬에서는 eval함수를 이용해서 문자열의 수식값을 계산할 수 있다.
* 자바에서는 그냥 계산해야 한다.

* op과 num을 분리해서 queue에 담았다.
  * 왠지모르게 stack을 사용해야 한다는 착각에 빠져서 헤맸었다.

```java
// 공백제거 4 3 -> 43
s = s.replace(" ","");
// op, num 분리 후 queue에 담기
for(int i=0;i<s.length();i++){
    char c = s.charAt(i);
    if(c == '+' || c == '-' ){
        op.add(c);
    }else{
        int p = i;
        while(p < s.length() && s.charAt(p) != '+' && s.charAt(p) != '-'){
            p++;
        }
        String iv = s.substring(i,p);
        i = p-1;
        num.add(Integer.valueOf(iv));
    }
}
```

* 그 다음에 숫자 2개 꺼내고, op에서 연산하나 꺼내서 계산해준다.
  * 숫자가 하나 남으면 멈춘다.
  * 이 때, 계산한값은 `offerFirst`로 가장 앞에 넣어주어야 한다.

## 7. ABCDE

* 처음에 문제가 잘 이해가 안됐다.
* depth가 4인 지점이 존재하는 지 묻는 문제였다.
  * 이 때, 방문체크를 해줘야 했는데 그러면 매번 넘길 때마다 `new boolean[N]`으로 visited배열을 생성해야 했다.
  * ex) `1(방문 가정), 2, 3, 4` -> 1에서 2,3,4로 넘겨줄 때 각각 다른 visited배열을 생성해주어야 함
    * 혹은 방문이 끝나고 `visited[cur] = false` 처리를 해주어야 함
  * 그게 싫어서 비트 마스킹을 통해 방문체크를 진행했다. 
  * N=2000이긴 하지만, 어차피 depth가 4면 끝나므로 비트마스킹을 활용할 수 있다..라고 생각했는데 답은 맞았지만 틀린 풀이다.

* 32단위로 차이가 나는 값이 주어진다고 해보자.

```
130 4(N,M)
1 33
33 65
65 97
97 129
```

* 이러면 1 - 33 - 65 - 97 - 129 여서 1이 나와야 하지만, 비트마스킹으로 체크를 했기 때문에 모두 체킹이 돼서 다음 값으로 넘어가지 않는다.
(어케맞았지?)

* `visited[i] = false`를 해주는 방법으로 방문처리를 해주어서 다시 풀어봐야겠다.

```java
if(depth == 4){
    res = true;
    System.out.println(1);
    System.exit(0);
    return ;
}
for(int connect : list[index]) {
    if ((flag & 1 << connect) != 0) continue;
    dfs(connect, depth+1, flag | 1 << connect);
}
```

## 8. 암호만들기

* 그냥 조합 돌리면 되는 문제인데, 조건이 2개 붙는다.
* `[자음 2개 이상, 모음1개 이상]`, `증가하는 부분으로만 구성`이다.
* 증가하는 부분은 아래처럼 마지막 index의 값과 대입하는 i의 값을 비교해줌으로써 가능하다.
  * 해당 부분이 없으면 timeout이 발생한다.

```java
if( cnt != 0 && temp[cnt-1] > i ) continue;
```

## 9. 야구

* 1번 타자는 4번에 고정이어야 하는 조건이 있다.

```java
if(cnt >= 4 && temp[3] != 0) continue;
```

* 해당 조건 처리는 위와 같이 했다.
* 안타, 2루타, 3루타, 홈런 처리는 visited 배열 `[4]` 짜리를 만들어서 조건대로 진행했다.
  * 자세한 구현은 코드를 보는게 빠를 것 같다.

## 10. 히스토그램에서 가장 큰 직사각형

* [블로그에 정리](https://camel-man-ims.tistory.com/56)

* stack을 사용해서 푼다.
* array를 n+2크기 만큼 설정한다.

## 11. 불끄기

* [블로그에 정리](https://camel-man-ims.tistory.com/52)

import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();	//트리 정보 저장 리스트
    static int answer = 0;		//리프 노드 개수 변수
    public static void main(String[] args) throws IOException {
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());	//노드의 개수 입력값 저장
        int root = 0;		//루트 노드 기본값 설정
        //트리에 대한 ArrayList 초기화
        for(int i=0;i<=N;i++)
            tree.add(new ArrayList<>());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        //트리의 정보 저장
        for(int i=0;i<N;i++){
            int node = Integer.parseInt(st.nextToken());
            //루트 노드의 값 저장
            if(node == -1){
                root = i;
                continue;
            }
            tree.get(node).add(i);	//일반 노드의 값들 저장
        }
        //삭제할 노드 입력값 저장
        int remove = Integer.parseInt(br.readLine());
        //삭제할 노드와 루트 노드가 같으면 트리 전체가 삭제!
        if(remove == root)
            answer = 0;		//트리가 없으면 리프 노드도 0개
        else		//루트 노드가 아닌 다른 노드 삭제시 DFS 탐색!
            search(remove, root);
        bw.write(answer + "");		//리프 노드 개수 BufferedWriter 저장
        bw.flush();		//결과 출력
        bw.close();
        br.close();
    }
    //루트 노드부터 DFS탐색으로 리프 노드를 찾는 재귀 함수
    static void search(int remove, int node){
        //현재 노드의 삭제할 노드 포함시 삭제!
        if(tree.get(node).contains(remove))
            tree.get(node).remove(Integer.valueOf(remove));
 
        //현재 노드가 리프 노드일 때
        if(tree.get(node).isEmpty()){
            answer++;
            return;
        }
        //자식 노드가 존재할 때 DFS 탐색!
        for(int next : tree.get(node)){
            search(remove, next);
        }
    }
}
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int shortestTime = Integer.MAX_VALUE;
    private static int dst;
    private static List<List<int[]>> adj;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nSrcDstK = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nSrcDstK[0];
        int src = nSrcDstK[1];
        dst = nSrcDstK[2];
        int k = nSrcDstK[3];

        adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        while (sc.hasNext()) {
            int[] edge = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adj.get(u).add(new int[]{v, w});
        }

        dfs(src, k, 0);
        System.out.println(shortestTime == Integer.MAX_VALUE ? -1 : shortestTime);
    }

    private static void dfs(int node, int jump, int currentTime) {
        if (jump < 0) {
            return;
        }
        if (node == dst) {
            shortestTime = Math.min(shortestTime, currentTime);
            return;
        }
        for (int[] edge : adj.get(node)) {
            int nextNode = edge[0];
            int weight = edge[1];
            dfs(nextNode, jump - 1, currentTime + weight);
        }
    }
}
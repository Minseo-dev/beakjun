package bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class mazeExploration2178 {
    static int[][] maze;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, 1, -1, 0};
    static int width;
    static int height;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
      String[] input = br.readLine().split(" ");
      height = Integer.parseInt(input[0]);
      width = Integer.parseInt(input[1]);
      maze = new int[width][height];

      for (int h = 0; h < height; h++) {
        String inputLine = br.readLine();
        for (int w = 0; w < width; w++) {
          maze[w][h] = inputLine.charAt(w) - '0';
        }
      }
      bfs(0, 0);
      bw.flush();
      bw.close();
      br.close();
    }


    static void bfs(int x, int y) throws IOException {
      //BFS 위한 큐 객체
      Queue<PointXY> queue = new LinkedList<>();
      //초기 0.0좌표 삽입
      queue.add(new PointXY(x, y));

      while (!queue.isEmpty()) { // 주변에 1이 없을 때까지
        PointXY check = queue.poll();

        if (check.x == width - 1 && check.y == height - 1) {
          break;
        }
        //상, 하, 좌, 우 탐색 반복문
        for (int i = 0; i < dx.length; i++) {
          int nx = check.x + dx[i];
          int ny = check.y + dy[i];

          //0보다 크고 행,열보다 작을때 진입
          if (nx >= 0 && ny >= 0 && nx <width && ny < height) {

            if (maze[nx][ny] == 1) {
              maze[nx][ny] = maze[check.x][check.y] + 1;
              queue.add(new PointXY(nx, ny));
            }
          }
        }
      }
      bw.write(maze[width-1][height-1] + "");
    }

    static class PointXY {
      int x;
      int y;

      PointXY(int x, int y) {
        this.x = x;
        this.y = y;
      }
    }
}

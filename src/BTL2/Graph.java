package BTL2;

import java.io.*;
import java.util.*;

public class Graph {

	ArrayList<Integer>[] links;
	private static int v, e;
	private int[] deg, a;

	public Graph(int v, int e) {
		this.e = e;
		this.v = v;
		a = new int[v];
		deg = new int[v];
		links = new ArrayList[v];
		for (int i = 0; i < v; i++) {
			links[i] = new ArrayList<Integer>();
		}
	}

	public void addEdge(int x, int y) {
		links[x].add(y);
		links[y].add(x);
		deg[x]++;
		deg[y]++;
	}

	public void insertA() {
		boolean[] check = new boolean[v];
		Arrays.fill(check, true);
		for (int i = 0; i < v; i++) {
			int max = -1;
			for (int j = 0; j < v; j++) {
				if (check[j]) {
					max = j;
					break;
				}
			}
			for (int j = 0; j < v; j++) {
				if (deg[max] < deg[j] && check[j] == true) {
					max = j;
				}
			}
			a[i] = max;
			check[max] = false;
		}
	}

	public void GreedyAlgorithm() {
		int[] color = new int[v];
		String cl[] = { "red", "yellow", "green", "blue", "pink", "white", "purple", "grey", "grown", "orange" };
		boolean[] available = new boolean[v];
		Arrays.fill(color, -1);
		color[a[0]] = 0;
		for (int i = 1; i < v; i++) {
			Arrays.fill(available, true);
			for (int j = 0; j < links[a[i]].size(); j++)
				if (color[links[a[i]].get(j)] != -1)
					available[color[links[a[i]].get(j)]] = false;
			int c;
			for (c = 0; c < v; c++)
				if (available[c])
					break;
			color[a[i]] = c;
		}
		try {
			File file = new File("C:\\Users\\Anh Bui\\Desktop\\graph\\dothitomau.dot");
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			String s = "graph dothi {\n";
			for (int i = 0; i < v; i++) {
				s = s + "\t" + (i + 1) + " [fillcolor = " + cl[color[i]] + ", style = filled];\n";
			}
			File f = new File("F:\\Workspace\\Java-eclipse\\ToanRoiRac\\dothi.txt");
			Scanner sc = new Scanner(f);
			sc.nextInt();
			sc.nextInt();
			for (int i = 0; i < e; i++) {
				s = s + "\t" + sc.nextInt() + " -- " + sc.nextInt() + ";\n";
			}
			s = s + "}";
			bw.write(s);
			bw.close();
		} catch (IOException e) {
			e.getMessage();
		}

	}

	public static void main(String[] args) {
		try {
			File file = new File("F:\\Workspace\\Java-eclipse\\ToanRoiRac\\dothi.txt");
			Scanner sc = new Scanner(file);
			v = sc.nextInt();
			e = sc.nextInt();
			Graph g = new Graph(v, e);
			while (sc.hasNextInt()) {
				int v1 = sc.nextInt();
				int v2 = sc.nextInt();
				g.addEdge(v1 - 1, v2 - 1);
			}
			g.insertA();
			g.GreedyAlgorithm();
		} catch (IOException e) {
			e.getMessage();
		}
	}
}

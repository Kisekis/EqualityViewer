package com.nime.eqviewer.util;

public class UnionFind {
    public int[] parent;
    public int count;

    public UnionFind(int n) {
        count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
         return p == parent[p] ? p : (parent[p] = find(parent[p]));
    }

    public void union(int p, int q) {
        if (find(p) == find(q)) {
            return;
        }
        int proot = find(p);
        int qroot = find(q);
        parent[proot] = qroot;
    }

}

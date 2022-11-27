package com.nime.eqviewer.util;

public class UnionFind {
    private int[] id;
    private int count;

    public UnionFind(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        while (p != id[p])
            p = id[p];
        return p;
    }

    public void union(int p, int q) {
        if (find(p) == find(q)) {
            return;
        }
        int proot = find(p);
        int qroot = find(q);
        id[proot] = qroot;
        count--;
    }

}

package sw1;

import java.io.*;

public class MatrixUDG {

    private char[] mVexs;
    private int[][] mMatrix;
    private static final int INF = Integer.MAX_VALUE;
    public MatrixUDG(char[] vexs, int[][] matrix) {
        int vlen = vexs.length;
        mVexs = new char[vlen];
        for (int i = 0; i < mVexs.length; i++)
            mVexs[i] = vexs[i];

        mMatrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++)
            for (int j = 0; j < vlen; j++)
                mMatrix[i][j] = matrix[i][j];
    }
    private int getPosition(char ch) {
        for(int i=0; i<mVexs.length; i++)
            if(mVexs[i]==ch)
                return i;
        return -1;
    }
    public void print() {
        System.out.print("Martix Graph:\n");
        for (int i = 0; i < mVexs.length; i++) {
            System.out.print("         " + mVexs[i]);
        }
        System.out.println();
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++) {
                if (mMatrix[i][j] == INF) {
                    System.out.print("       INF ");
                } else {
                    System.out.printf("%10d ", mMatrix[i][j]);
                }
            }
            System.out.print("\n");
        }
    }
    public void prim(int start) {
        int iterations_count = 0;
        int num = mVexs.length;
        int index = 0;
        char[] prims  = new char[num];
        int[] weights = new int[num];
        prims[index++] = mVexs[start];
        for (int i = 0; i < num; i++ )
            weights[i] = mMatrix[start][i];
        weights[start] = 0;
        for (int i = 0; i < num; i++) {
            if(start == i)
                continue;
            int j = 0;
            int k = 0;
            int min = INF;
            while (j < num) {
                if (weights[j] != 0 && weights[j] < min) {
                    min = weights[j];
                    k = j;
                }
                j++;
            }
            prims[index++] = mVexs[k];
            weights[k] = 0;
            for (j = 0 ; j < num; j++) {
                if (weights[j] != 0 && mMatrix[k][j] < weights[j])
                    weights[j] = mMatrix[k][j];
            }
        }
        int sum = 0;
        for (int i = 1; i < index; i++) {
            int min = INF;
            int n = getPosition(prims[i]);
            for (int j = 0; j < i; j++) {
                int m = getPosition(prims[j]);
                if (mMatrix[m][n]<min) {
                    min = mMatrix[m][n];
                    iterations_count++;
                }
            }
            sum += min;
        }
        System.out.printf("PRIM(%c)=%d: ", mVexs[start], sum);
        for (int i = 0; i < index; i++)
            System.out.printf("%c ", prims[i]);
        System.out.println();
        System.out.println("Количество итераций: " + iterations_count);
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        try {
            FileInputStream fis = new FileInputStream(new File("test.txt"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            int[][] matrix = (int[][]) ois.readObject();
            char[] vexs = new char[matrix.length];
            for (int i = 0; i < vexs.length; i++) {
                vexs[i] = (char)(i + 65);
            }
            MatrixUDG pG;
            pG = new MatrixUDG(vexs, matrix);
            pG.print();
            pG.prim(0);
            fis.close();
            ois.close();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("Прошло времени, мс: " + elapsed);
    }
}

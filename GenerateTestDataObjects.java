package sw1;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class GenerateTestDataObjects {
    public static void main(String[] args) {
        int n = 26;
        int rand = 1000;
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    Random r = new Random();
                    int x = r.nextInt(2);
                    if (x == 0) {
                        matrix[i][j] = Integer.MAX_VALUE;
                        matrix[j][i] = Integer.MAX_VALUE;
                    } else {
                        int a = r.nextInt(rand) + 1;
                        matrix[i][j] = a;
                        matrix[j][i] = a;
                    }
                }
            }
        }
//        for (int[] elem:
//             matrix) {
//            System.out.println(Arrays.toString(elem));
//        }
        try {
            FileOutputStream fos = new FileOutputStream(new File("test.txt"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // Запись объектов в файл
            oos.writeObject(matrix);
            oos.close();
            fos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}

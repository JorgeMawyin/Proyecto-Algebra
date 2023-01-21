/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoAlgebra;

/**
 *
 * @author Jorge Daniel
 */
import java.util.Arrays;

public class HillCipher {

    private int[][] encodingMatrix;
    private int[][] decodingMatrix;
    private boolean mapNumbers;

    public HillCipher(int[][] encodingMatrix, int[][] decodingMatrix, boolean mapNumbers) {
        this.encodingMatrix = encodingMatrix;
        this.decodingMatrix = decodingMatrix;
        this.mapNumbers = mapNumbers;
    }

    public String encryptText(String plainText) {
        int[][] plainMatrix = getMatrixFromText(plainText);
        int[][] cipherMatrix = multiplyMatrices(encodingMatrix, plainMatrix);
        String cipherText = getNumbersFromMatrix(cipherMatrix);
        if (mapNumbers) {
            cipherText = numberToChar(cipherText);
        }
        return cipherText;
    }

    public String decryptText(String cipherText) {
        if (mapNumbers) {
            cipherText = charToNumber(cipherText);
        }
        int[][] cipherMatrix = getMatrixFromNumbers(cipherText);
        int[][] plainMatrix = multiplyMatrices(decodingMatrix, cipherMatrix);
        return getTextFromMatrix(plainMatrix);
    }

    private static String getTextFromMatrix(int[][] matrix) {
        StringBuilder text = new StringBuilder();
        for (int j = 0; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                text.append(matrix[i][j] > 0 ? Character.toString((char) matrix[i][j]) : "");
            }
        }
        return text.toString();
    }

    private static String getNumbersFromMatrix(int[][] matrix) {
        StringBuilder text = new StringBuilder();
        for (int j = 0; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                text.append(matrix[i][j]).append(" ");
            }
        }
        return text.toString();
    }

    private int[][] getMatrixFromArray(int[] arr) {
        int rows = encodingMatrix.length;
        int columns = arr.length % rows == 0 ? arr.length / rows : (arr.length / rows) + 1;
        int[][] matrix = new int[rows][columns];

        for (int i = 0; i < arr.length; i++) {
            matrix[i % rows][i / rows] = arr[i];
        }

        if (arr.length % rows != 0) {
            for (int i = arr.length % rows; i < rows; i++) {
                matrix[i][(arr.length - 1) / rows] = 0;
            }
        }

        return matrix;
    }

    private int[][] getMatrixFromText(String text) {
        int[] arr = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            arr[i] = text.charAt(i);
        }
        return getMatrixFromArray(arr);
    }

    private int[][] getMatrixFromNumbers(String text) {
        String[] numbers = text.split(" ");
        int[] arr = Arrays.stream(numbers).mapToInt(Integer::parseInt).toArray();
        return getMatrixFromArray(arr);
    }

    private int[][] multiplyMatrices(int[][] m1, int[][] m2) {
        int rows = m1.length;
        int columns = m2[0].length;
        int[][] matrix = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                for (int k = 0; k < m1[0].length; k++) {
                    matrix[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }

        return matrix;
    }

    private String numberToChar(String text) {
        char[] result = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            result[i] = (char) (text.charAt(i) + (text.charAt(i) == ' ' ? 33 : 21));
        }
        return new String(result);
    }

    private String charToNumber(String text) {
        char[] result = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            result[i] = (char) (text.charAt(i) - (text.charAt(i) == 'A' ? 33 : 21));
        }
        return new String(result);
    }
}

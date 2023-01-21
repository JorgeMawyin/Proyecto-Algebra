/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoAlgebra;

/**
 *
 * @author Jorge Daniel
 */
public class inicio {

    public void testHillCipher() {
        int[][] encodingMatrix = new int[][]{{1, -1, -1, 1}, {2, -3, -5, 4}, {-2, -1, -2, 2}, {3, -3, -1, 2}};
        int[][] decodingMatrix = new int[][]{{6, -1, 0, -1}, {22, -4, 1, -4}, {14, -3, 1, -2}, {31, -6, 2, -5}};
        HillCipher hillCipher = new HillCipher(encodingMatrix, decodingMatrix, true);
        String plainText = "❞❝۩ ๑ ۞  ஐ • @ ღ ○ ₪ √ ™ № ╬";
        String cipherText = hillCipher.encryptText(plainText);
        System.out.println("Texto encriptado: "+cipherText);
        String decryptedText = hillCipher.decryptText(cipherText);
        System.out.println("Texto desencriptado: " +decryptedText);
    }

    public static void main(String[] args) {
        inicio i = new inicio();
        i.testHillCipher();
    }
}

import java.util.Scanner;

public class Lab_1 {
    public static void main(String[] args){
        System.out.println("Задайте промежуток:");
        Scanner input = new Scanner(System.in);
        int numBegin = 0;
        int numEnd = 0;
        try {
            numBegin = input.nextInt();
            numEnd = input.nextInt();
        }
        catch (Exception e) {
            throw new IllegalArgumentException ("Промежуток должен состоять из чисел!");
        }
        if ((numBegin < 0)||(numEnd < 0)) {
            throw new IllegalArgumentException ("В промежутке не должно быть отрицательных чисел!");
        }
        if (numEnd < numBegin) {
            throw new IllegalArgumentException ("Некорректный промежуток");
        }
        System.out.println("Ваш промежуток - [" + numBegin + ", " + numEnd + "]");
        System.out.print("Простые числа из вашего промежутка: ");
        for (int i = numBegin; i <= numEnd; i++) {
            if ((i == 0) | (i == 1)) {
                continue;
            }
            if (isPrime(i)){
                System.out.print(i + " ");
            }
        }

    }
    private static boolean isPrime(int num) {
        int count = 0;
        int k = 2;
        while ((k * k <= num) && (count == 0)) {
            if (num % k == 0)
                count++;
            k++;
        }
        return (count == 0);
    }
}

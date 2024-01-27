import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.LongAdder;

public class PA1 {
  private static final BlockingDeque<Integer> primes = new LinkedBlockingDeque<>(10);
  private static final LongAdder totalSum = new LongAdder();
  private static final LongAdder primeCount = new LongAdder();

  private static void isPrime(int start, int end) {
    boolean primeArray[] = new boolean[end - start + 1];
    long threadSum = 0;
    long threadCounter = 0;
    Arrays.fill(primeArray, true);

    for (int p = 2; p * p <= end; p++) {
      for (int i = Math.max(p * p, (start + p - 1) / p * p); i <= end; i += p) {
          primeArray[i - start] = false;
      }
    }

    for (int i = Math.max(2, start); i <= end; i++) {
      if (primeArray[i - start]) {
        if (!primes.offerLast(i)) {
          primes.pollFirst();
          primes.offerLast(i);
        }
        threadSum += i;
        threadCounter++;
      }
    }

    totalSum.add(threadSum);
    primeCount.add(threadCounter);
  }

  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();

    for (int i = 0; i < 8; i++) {
      int start = (i * 12500000) + 1;
      int end = (i + 1) * 12500000;
      Thread thread = new Thread(() -> isPrime(start, end));
      thread.start();
      try {
        thread.join();
      } catch (Exception e) {
        System.out.println(e);
      }
    }

    try (BufferedWriter File = new BufferedWriter(new FileWriter("primes.txt"))) {
      File.write((System.currentTimeMillis() - startTime) + " MS " + primeCount + " " + totalSum + "\n");
      for (int prime : primes) {
        File.write(prime + " ");
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
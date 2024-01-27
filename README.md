# COP4520-HW1

## Compile the Java program
```bash
$ javac PA1.java
```

## Run the Java program
```bash
$ java PA1
```

## Summary of Project
To quickly summarize this program creates 8 threads, splits up the 10 $^8$ ceiling into 8 12500000 sized chunks (1 for each thread), assigns them each to a thread, and then each thread runs the Sieve of Eratosthenes to efficiently calculate the primes in the thread. I used BlockingDeque, LinkedBlockingDeque, and LongAdder as a way to keep those variables thread safe without having to deal with any synchronization. What im curious about is if setting the threads dynamically instead of ranges (since the first range would finish considerable quicker then the last), along with calculating only odd numbers since every even after 2 is not prime, and comparing dividing said numbers by only odd numbers to check if they are prime since any number divisible by an even number must be even according to the fundamental property of even numbers would improve my runtime. A file should be created called primes.txt or it should be there when you pull, and then check the answer.
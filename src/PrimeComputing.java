// Description: PrimeComputing class defines three queues and calculates the 
// prime numbers up to an integer given by the user, as well as the largest 
// prime number up to the integer and the number of primes up to the integer. 

// LinkedList as a Queue
import java.util.LinkedList;

public class PrimeComputing
{
    private int n, lastPrime, primeCount;
    private LinkedList<Integer> originalQueue;
    private LinkedList<Integer> primeQueue;
    private LinkedList<Integer> backupQueue;

    // Constructor to initialize all queues and instance variables
    public PrimeComputing(int enteredNum)
    {
        n = enteredNum; 
        primeQueue = new LinkedList<Integer>(); 
        backupQueue = new LinkedList<Integer>(); 
        originalQueue = new LinkedList<Integer>();
        for (int i = 2; i <= n; i++) // adds all of the integers up to the enteredNum
        {
            originalQueue.add(i); 
        }
        lastPrime = 2; // guaranteed at least 2 as a prime number in the queue
        primeCount = 0; 
    }

    // Computes all prime numbers up to some integer n
    public void computePrimesUpToN()
    {
        int nextPrime = 2;
        lastPrime = 2;

        System.out.println("\nProcessing......");
        
        do 
        {
            // 1) Removes first element in original queue and sets next prime to that number
            nextPrime = originalQueue.remove(); 

            System.out.println("The next prime to divide: " + nextPrime);

            // 2) Enqueues next prime into primeQueue
            primeQueue.add(nextPrime);

            // 3) Goes through ints in original queue and elimienates any int divisible by next prime
            while (!originalQueue.isEmpty())
            {
                int element = originalQueue.remove(); // removes each int from original queue
                if (element % nextPrime != 0)  
                { 
                    backupQueue.add(element); // puts back ints not divisible by next prime 
                }
            }
            lastPrime = nextPrime; // updates last prime 
            
            while (!backupQueue.isEmpty())
            {
                originalQueue.add(backupQueue.remove()); // transfers backup queue data to original queue
            }
        } while (nextPrime <= Math.sqrt(n));

        //4) Transfers all remaining ints in original queue to prime queue
        while (!originalQueue.isEmpty())
        {
            primeQueue.add(originalQueue.remove()); 
        }
        primeCount = primeQueue.size(); // keeps track of prime count 
    }

    //Prints out all primes up to N, by displaying 10 prime numbers in each line
    public void printResults()
    {
        System.out.println("\n-----------------------");
        System.out.println("The prime(s) up to " + n);
        System.out.println("-----------------------");

        int count = 0;
        primeCount = 0;

        while (primeQueue.isEmpty() == false)
        {
            int primeNum = primeQueue.remove();
            System.out.print(primeNum + "\t");

            count++;
            // Displaying 10 primes in each line
            if (count%10 == 0)
                System.out.print("\n");
        }
        System.out.println("\n-----------------------");
        primeCount = count;
    }
     
    // Returns largest prime that was used to divide other integers in the original queue. 
    // Note that this is not the largest prime found.
    public int getMaxPrime()
    {
        return lastPrime;
    }
    
    // Returns count of prime numbers up to n
    public int getCount()
    {
        return primeCount;
    }
}
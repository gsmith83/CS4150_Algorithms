import java.math.BigInteger;
import java.util.Random;

public class PrimeProbability {
    
    static int NUM_BITS = 128;
    static int ITERATIONS = 10000000;
    
    public static void main(String[] args) {
        Random rand = new Random();
               
        // prime array: 0 is prime; 1 is composite that fools; 2 is composite that doesn't fool
        double primeArray[] = {0.0f, 0.0f, 0.0f};
        
        for(int i = 0; i < ITERATIONS; i++){
            // create a random integer consisting of exactly 64 bits (must have leading 1)
            BigInteger randomInteger = new BigInteger(NUM_BITS, rand).setBit(NUM_BITS - 1);

            if(randomInteger.isProbablePrime(100))
                primeArray[0]++;
            else if(probabilityTest(randomInteger))
                primeArray[1]++;
            else
                primeArray[2]++;
            
        }

        System.out.printf("\nProbability of a randomly chosen BigInteger being prime = %f\n", primeArray[0]/(primeArray[0] + primeArray[1] + primeArray[2]));
        System.out.printf("Probability of being composite and fooling test = %.10f\n", primeArray[1]/(primeArray[0] + primeArray[1] + primeArray[2]));
        System.out.printf("Probability of being composite and not fooling test = %f\n", primeArray[2]/(primeArray[0] + primeArray[1] + primeArray[2]));
        System.out.printf("Sanity check: 0 + 1 + 2 = %f", (primeArray[0] + primeArray[1] + primeArray[2])/ITERATIONS);
    }
    
    private static boolean probabilityTest(BigInteger n){
        // create a = 2
        BigInteger base = BigInteger.ONE.add(BigInteger.ONE); // a = 2
        BigInteger exp = n.subtract(BigInteger.ONE);    // n - 1
        
        if(base.modPow(exp, n).compareTo(BigInteger.ONE) == 0)
            return true;
        else
            return false;
    }
    
    
    /*
    private static boolean isPrime(BigInteger num){
        if(num.intValue() == 1){
            return false;
        }
        
        if(num.mod(new BigInteger("2")).equals(BigInteger.ZERO)){
            return false;
        }
        
        long sqrtNum = bigIntSqRootFloor(num).longValue() + 1;
        
        BigInteger b1;
        
        for(long i = 3; i < sqrtNum; i+=2){
            b1 = BigInteger.valueOf(i);
            
            if(num.gcd(b1).longValue() != 1){
                return false;
            }
        }
        
        return true;
    }

    
    public static BigInteger bigIntSqRootFloor(BigInteger x)
            throws IllegalArgumentException {
        if (x.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Negative argument.");
        }
        // square roots of 0 and 1 are trivial and
        // y == 0 will cause a divide-by-zero exception
        if (x .equals(BigInteger.ZERO) || x.equals(BigInteger.ONE)) {
            return x;
        } // end if
        BigInteger two = BigInteger.valueOf(2L);
        BigInteger y;
        // starting with y = x / 2 avoids magnitude issues with x squared
        for (y = x.divide(two);
                y.compareTo(x.divide(y)) > 0;
                y = ((x.divide(y)).add(y)).divide(two));
        return y;
    }
    */
}

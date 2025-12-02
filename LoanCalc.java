public class LoanCalc {

    static double epsilon = 0.001;
    static int iterationCounter;

    public static void main(String[] args) {		
        if (args.length < 3) {
            System.out.println("Usage: java LoanCalc <loan> <rate> <numPeriods>");
            return;
        }

        double loan = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);
        System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

        System.out.print("\nPeriodical payment, using brute force: ");
        System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);

        System.out.print("\nPeriodical payment, using bi-section search: ");
        System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);
    }

    private static double endBalance(double loan, double rate, int n, double payment) {	
        for (int i = 0; i < n; i++) {
            loan = loan * (1 + rate / 100.0) - payment;
        }
        return loan;
    }

    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
        double payment = 0.0;
        double balance = endBalance(loan, rate, n, payment);

        while (balance > 0 && iterationCounter < 1_000_000) {
            payment += epsilon;
            iterationCounter++;
            balance = endBalance(loan, rate, n, payment);
        }

        return payment;
    }

    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
        iterationCounter = 0;

        double lo = 0.0;
        double hi = loan;

        while (endBalance(loan, rate, n, hi) > 0) {
            hi *= 2.0;
        }

        while ((hi - lo) > epsilon && iterationCounter < 10000) {
            double mid = (lo + hi) / 2.0;
            double bal = endBalance(loan, rate, n, mid);

            if (bal > 0) {
                lo = mid;
            } else {
                hi = mid;
            }

            iterationCounter++;
        }

        return (lo + hi) / 2.0;
    }
}
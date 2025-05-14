import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Programming Assignment 1: Amortized Mortgage Calculator; create an amortized mortgage schedule.
 *
 * @Author John Ryder and Jelal Kaufman
 * @Version 2/13/25
 *
 *          Sources Used:
 *          -Used https://www.investopedia.com/terms/a/amortization.asp for a
 *          definition/formula for an amortization schedule.
 *          -We asked ChatGPT to explain amortization of loans to us. Our exact
 *          prompt was "What is an amortized mortgage, and how do I calculate
 *          it?"
 *          -We asked ChatGPT to explain making the schedule to us. Our exact
 *          prompt was "How can I create the amortization schedule?"
 *          -We used ChatGPT to isolate the issue to an improper calculation of amounted owned
 *           (we were subtracting full monthly payment, not just the principle.
 *
 *          Known Issues:
 *          -Our computations have some minor rounding issues due to our use of doubles.
 *          For the time being, we haven't made a fix to this until we get more input
 *          on the issue from Dr. Ezell (should we even be using floating-point numbers, etc).
 *
 *
 */

public class AmortizedLoan {

    /**
     * Take user input to pass to the loan-calculating portion of our code.
     */
    public static void gatherInput() {
        boolean promptForInput = true;
        Scanner input = new Scanner(System.in);
        while (promptForInput) {
            System.out.print("Input Initial Amount Borrowed: ");
            double initAmntBorwd = 0;
            try {
                initAmntBorwd = input.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid initial amount. Input should be a decimal number.");
                exit(-1);
            }
            System.out.print("Input Initial Interest Rate: ");
            double initIntRt = 0.0;
            try {
                initIntRt = input.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid initial interest. Input should be a decimal number.");
                exit(-1);
            }
            System.out.print("Input Desired Down Payment: ");
            int desiredDownPayment = 0;
            try {
                desiredDownPayment = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid desired down payment. Input should be an integer.");
                exit(-1);
            }
            System.out.print("Input Borrower Name: ");
            String borrowerName = input.next();
            printLoan15(initAmntBorwd, initIntRt, desiredDownPayment, borrowerName);
            printLoan30(initAmntBorwd, initIntRt, desiredDownPayment, borrowerName);
            System.out.print("Calculate another loan (Yes/No)? ");
            String calculateAnother = input.next();
            if (!calculateAnother.equals("Yes")) {
                promptForInput = false;
            }
        }
        input.close();
    }

    /**
     * Print an amortized mortgage schedule for a 15-year loan.
     *
     * @param amountBorrowed the amount loaned to the borrower.
     * @param initialInterestRate interest rate for the loan.
     * @param desiredDownPayment down payment the borrower is prepared to pay.
     * @param name the name of the borrower.
     */
    public static void printLoan15(double amountBorrowed, double initialInterestRate,
            double desiredDownPayment, String name) {
        System.out.print("Amortization Schedule for: ");
        System.out.println(name);

        double monthlyInterestRate = (initialInterestRate / 100) / 12; // Convert to decimal
        double amountOwed = amountBorrowed - desiredDownPayment;

        // Monthly Payment Calculation
        double monthlyPayment = amountOwed * ((monthlyInterestRate * Math.pow(1 + monthlyInterestRate, 180))
                / (Math.pow(1 + monthlyInterestRate, 180) - 1));

        double totalPrincipal = 0;
        double totalInterest = 0;

        for (int i = 0; i <= 180; i++) {
            double monthlyInterest = amountOwed * monthlyInterestRate;
            totalInterest += monthlyInterest;

            double monthlyPrincipal = monthlyPayment - monthlyInterest;
            totalPrincipal += monthlyPrincipal;

            System.out.printf(
                    "Month: %d | Monthly Payment: %.2f | Principal: %.2f | Interest: %.2f | Remaining: %.2f\n",
                    i, monthlyPayment, monthlyPrincipal, monthlyInterest, amountOwed);

            amountOwed -= monthlyPrincipal; // Fix: subtract principal, not full payment

            // Prevent negative rounding issues
            if (amountOwed < 0) {
                amountOwed = 0;
            }
        }
        System.out.printf("Total Principle Paid: %f | Total Interest Paid: %f\n", totalPrincipal, totalInterest);
    }

    /**
     * Print an amortized mortgage schedule for a 30-year loan.
     *
     * @param amountBorrowed the amount loaned to the borrower.
     * @param initialInterestRate the interest rate on the loan.
     * @param desiredDownPayment the down payment the borrower is prepared to pay.
     * @param name the name of the borrower.
     */
    public static void printLoan30(double amountBorrowed, double initialInterestRate,
            double desiredDownPayment, String name) {
        System.out.print("Amortization Schedule for: ");
        System.out.println(name);

        double monthlyInterestRate = (initialInterestRate / 100) / 12; // Convert to decimal
        double amountOwed = amountBorrowed - desiredDownPayment;

        // Monthly Payment Calculation
        double monthlyPayment = amountOwed * ((monthlyInterestRate * Math.pow(1 + monthlyInterestRate, 360))
                / (Math.pow(1 + monthlyInterestRate, 360) - 1));

        double totalPrincipal = 0;
        double totalInterest = 0;

        for (int i = 0; i <= 360; i++) {
            double monthlyInterest = amountOwed * monthlyInterestRate;
            totalInterest += monthlyInterest;

            double monthlyPrincipal = monthlyPayment - monthlyInterest;
            totalPrincipal += monthlyPrincipal;

            System.out.printf(
                    "Month: %d | Monthly Payment: %.2f | Principal: %.2f | Interest: %.2f | Remaining: %.2f\n",
                    i, monthlyPayment, monthlyPrincipal, monthlyInterest, amountOwed);

            amountOwed -= monthlyPrincipal; // Fix: subtract principal, not full payment

            // Prevent negative rounding issues
            if (amountOwed < 0) {
                amountOwed = 0;
            }
        }
        System.out.printf("Total Principle Paid: %f | Total Interest Paid: %f\n", totalPrincipal, totalInterest);
    }

    public static void main(String[] args) {
        gatherInput();
    }
}
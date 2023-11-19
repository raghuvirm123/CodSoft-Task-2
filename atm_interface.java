package codSoft;

import java.util.Scanner;


public class atm_interface {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the initial balance of your account: ");
        double initialBalance = input.nextDouble();
        input.nextLine(); // Consume the newline character

        BankAccount userAccount = new BankAccount(initialBalance);
        ATM atm = new ATM(userAccount);
        atm.run();
    }
}


class BankAccount {
    
	private double amount;

    public BankAccount(double balance) {
        this.amount = balance;
    }

   
    public boolean deposit(double amount) {
        if (amount > 0) {
            this.amount += amount;
            return true;
        } else {
            System.out.println("Invalid deposit amount.");
            return false;
        }
    }

   
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= this.amount) {
            this.amount -= amount;
            return true;
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
            return false;
        }
    }

    public double checkBalance() {
        return this.amount;
    }
}

class ATM {
    private BankAccount userAccount;
    private Scanner scanner;

    public ATM(BankAccount userAccount) {
        this.userAccount = userAccount;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void run() {
        while (true) {
            displayMenu();
            System.out.print("Enter your choice (1-4): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character
                    if (userAccount.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful. Remaining balance: " + userAccount.checkBalance());
                    } else {
                        System.out.println("Withdrawal failed.");
                    }
                    break;

                case 2:
                    System.out.print("Enter the deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character
                    if (userAccount.deposit(depositAmount)) {
                        System.out.println("Deposit successful. Updated balance: " + userAccount.checkBalance());
                    } else {
                        System.out.println("Deposit failed.");
                    }
                    break;

                case 3:
                    System.out.println("Your current balance: " + userAccount.checkBalance());
                    break;

                case 4:
                    System.out.println("Exiting the ATM. Thank you!");
                    scanner.close(); // Close the scanner before exiting
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }
}





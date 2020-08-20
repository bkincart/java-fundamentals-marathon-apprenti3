import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class CashRegister {
  public static final String REGISTER_PATH = "./outstandingReceipts.txt";

  public static void main(String[] args) throws IOException {
    double cashRegisterBalance = getMoneyValue("How much money is in the cash register?");

    File register = new File(REGISTER_PATH);
    Scanner dataIn = new Scanner(register);
    while(dataIn.hasNextLine()) {
      cashRegisterBalance += dataIn.nextDouble();
    }
    System.out.println("Our updated balance is $" + cashRegisterBalance);

    double orderBalance = getMoneyValue("How much is the customer's order?");
    double cashProvided = 0;
    while (cashProvided < orderBalance) {
      cashProvided = getMoneyValue("How much cash did the customer provide? Make sure it's more than the order balance.");
    }
//    double cashProvided = getMoneyValue("How much cash did the customer provide?");;
//    while (cashProvided < orderBalance) {
//      cashProvided = getMoneyValue("Please try again. Have the customer pay more than the order balance. How much total money did the customer give you?");
//    }

    //pseudocode start
    // double changeToReturn = (customer payment) - (item cost)
    //check if customer payment > item cost
    //if ^ yes:
      // return changeToReturn
    // else:
      // tell them it was exact change
    double changeToReturn = cashProvided - orderBalance;
    while (changeToReturn > cashRegisterBalance) {
      cashProvided = getMoneyValue("I don't have that much change available, could you please provide cash closer to your total?");
      changeToReturn = cashProvided - orderBalance;
    }

    if (changeToReturn > 0) {
      System.out.println("Pay change in the amount of:" + changeToReturn);
    } else {
      System.out.println("exact change woot");
    }
  }

  public static double getMoneyValue(String prompt) {
    System.out.println(prompt);
    Scanner inputScanner = new Scanner(System.in);
    String moneyValueString = inputScanner.nextLine();
    while (moneyValueString.isEmpty()) {
      System.out.println("Please enter a value");
      moneyValueString = inputScanner.nextLine();
    }
    double moneyValue = Double.parseDouble(moneyValueString);
    System.out.println("You entered a value of $" + moneyValue);
    return moneyValue;
  }
}

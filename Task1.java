import java.util.InputMismatchException;
import java.util.Scanner;

public class Task1 { // RGU ID : 2237953
    public static void main(String[] args) {
        Task1 noOfTransactionQueue = new Task1();   // creating the object noOfTransactionQueue
        Scanner input = new Scanner(System.in);

        int noOfTransaction;
        while (true) {

            try {
                System.out.println("Enter The number of transactions you made : ");
                noOfTransaction = input.nextInt();  // Taking the input for The number of transactions you made
                if (noOfTransaction <= 0) {
                    throw new Exception();
                }
                break;
            } catch (InputMismatchException f) {
                System.out.println("Please Enter a Integer value....");
                input.nextLine();
            } catch (Exception e) {
                System.out.println("Please Enter a Positive value....");
                input.nextLine();
            }

        }

//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        noOfTransactionQueue.Queue(noOfTransaction); // calling the method queue and creating a queue size of the taken input
        for (int i = 0; i < noOfTransaction; i++) {

            int x ;
            while (true) {

                try {
                    System.out.println("Enter the number of shares you bought on Day " + (i + 1) + " :");
                    x = input.nextInt(); // Taking the input for the price of shares you bought.
                    if (x <= 0) {
                        throw new Exception();
                    }
                    break;
                } catch (InputMismatchException f) {
                    System.out.println("Please Enter a Integer value....");
                    input.nextLine();
                }catch (Exception e) {
                    System.out.println("Please Enter a Positive value....");
                    input.nextLine();
                }

            }
            noOfTransactionQueue.enqueue(x); // inserting a value
        }


//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        Task1 priceOfSharesQueue = new Task1();
        priceOfSharesQueue.Queue(noOfTransaction);
        for (int i = 0; i < noOfTransaction; i++) {
            double y;
            while (true) {

                try {
                    System.out.println("Enter the price of shares you bought on Day " + (i + 1) + " :");
                    y = input.nextDouble(); // Taking the input for the price of shares you bought.
                    if (y <= 0) {
                        throw new Exception();
                    }
                    break;
                }catch (Exception e) {
                    System.out.println("Please Enter a Positive value....");
                    input.nextLine();
                }

            }
            priceOfSharesQueue.doubleEnqueue(y);
        }


//--------------------------------------------------------------------------------------------------------------------------------------------------------------

        int noOfSellingShares;
        int noOfSharesYouHave = 0;
        for(int i = 0;i<noOfTransaction;i++) {
            noOfSharesYouHave = noOfSharesYouHave + noOfTransactionQueue.queueArray[i];
            // Calculating the shares you have
        }
        while (true) {

            try {
                System.out.println("Enter the number of selling shares : ");
                noOfSellingShares = input.nextInt();
                if (noOfSellingShares <= 0) {
                    throw new Exception();
                }
                if (noOfSharesYouHave<noOfSellingShares){
                    throw new Exception();
                }
                break;
            } catch (InputMismatchException f) {
                System.out.println("Please Enter an Integer value....");
                input.nextLine();
            }catch (Exception e) {
                System.out.println("Given Input is greater than the shares you have or it is not a Positive value...");
                input.nextLine();
            }

        }

        Task1 soldShares = new Task1();
        soldShares.Queue(noOfTransaction); // creating a new queue for the soldShares to make the calculating process easy.

        for(int i = 0;i<noOfTransaction;i++) {
            if (noOfSellingShares > noOfTransactionQueue.queueArray[i]) {
                noOfTransactionQueue.dequeue();
                soldShares.enqueue(noOfTransactionQueue.queueArray[i]);
                /* if the number of selling shares are greater than number of shares bought on any days those shares will be removed from the noOfTransactionQueue
                and will be added to the sold share  queue to make the calculation easy.
                 */
                noOfSellingShares=noOfSellingShares-noOfTransactionQueue.queueArray[i]; // to replace the value of noOfSellingShares to compare the next bought shares.


            }
            else {
                noOfTransactionQueue.dequeue();
                soldShares.enqueue(noOfSellingShares); // to add the remaining shares to be sold.
                break;
            }
        }

//--------------------------------------------------------------------------------------------------------------------------------------------------------------

        double priceOfSellingShares;
        while (true) {

            try {
                System.out.println("Enter the price of selling shares : ");
                priceOfSellingShares = input.nextDouble(); // Taking the input for the price of shares you bought.
                if (priceOfSellingShares <= 0) {
                    throw new Exception();
                }
                break;
            }catch (Exception e) {
                System.out.println("Please Enter a Positive value....");
                input.nextLine();
            }

        }
        Task1 soldPrice = new Task1();
        soldPrice.Queue(noOfTransaction); // creating a new queue for the soldPrice to make the calculating process easy.

        for(int i = 0;i<noOfTransaction;i++) {
            double profit = priceOfSellingShares-priceOfSharesQueue.doubleQueueuArray[i];
            /* substracting the price of bought shares from the price of the selling share and find the profit
            to make the calculation easier
                 */
            soldPrice.doubleEnqueue(profit);

        }

//--------------------------------------------------------------------------------------------------------------------------------------------------------------

        Task1 oneDayCapitalGainQueue = new Task1();
        oneDayCapitalGainQueue.Queue(noOfTransaction); // creating a new queue for the oneDayCapitalGain to make the calculating process easy.

        for(int i = 0;i<noOfTransaction;i++) {
            double oneDayCapitalGain = soldShares.queueArray[i]*soldPrice.doubleQueueuArray[i];
            // multiplying the profit and sold share to calculate the one day's capital gain
            oneDayCapitalGainQueue.doubleEnqueue(oneDayCapitalGain);

        }

//--------------------------------------------------------------------------------------------------------------------------------------------------------------
        double capitalGain = 0;
        for(int i = 0;i<noOfTransaction;i++) {
            capitalGain = capitalGain + oneDayCapitalGainQueue.doubleQueueuArray[i];
            // adding all one day capital gain to find the whole capital gain.
        }

        System.out.println("The Total Capital Gain is " + (String.format("%.2f",capitalGain)) + " Dollar/Dollars");

    }

//--------------------------------------------------------------------------------------------------------------------------------------------------------------

    //References got from lecture slides and I added a double value queue and created another enqueue and dequeue methods for double values
    private int front;
    private int rear;
    private int noOfItems;
    private int maxSize;
    private int queueArray[];
    private double doubleQueueuArray[];//Added by me

    public void Queue(int size){
        maxSize = size;
        front = 0;
        rear = -1;
        noOfItems = 0;
        queueArray = new int[maxSize];
        doubleQueueuArray = new double[maxSize]; //Added by me
    }

    public boolean isFull(){
        return (rear == maxSize-1);
    }

    public boolean isEmpty(){
        return (noOfItems == 0);
    }

    public void enqueue(int item){
        if(isFull()){
            System.out.println("The Queue is Full");
        }
        else{
            queueArray[++rear] = item;
            noOfItems++;
        }
    }

    public int dequeue(){
        if(isEmpty()){
            System.out.println("The Queue is Empty");
            return -1;
        }
        else{
            noOfItems--;
            return queueArray[front++];
        }

    }
//------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Created for Queue with double values
    public void doubleEnqueue(double item){
        if(isFull()){
            System.out.println("The Queue is Full");
        }
        else{
            doubleQueueuArray[++rear] = item;
            noOfItems++;
        }
    }

    public double doubleDequeue(){
        if(isEmpty()){
            System.out.println("The Queue is Empty");
            return -1;
        }
        else{
            noOfItems--;
            return doubleQueueuArray[front++];
        }

    }

}
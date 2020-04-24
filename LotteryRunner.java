/*****************************************************************
 class LotteryRunner
 LotteryRunner represents a lottery application that enables
 users to buy either a PowerBall ticket or a MegaMillions ticket.
 It enables users to get a quick pick ticket or to enter their own
 numbers. Also, it enables users to check their winnings. 
 Author: James Hembree
 Last modified: 4/24/2020
 jshembree88@gmail.com
 ****************************************************************/

import java.util.Scanner;

public class LotteryRunner {
    static Scanner keyboard = new Scanner(System.in);
    static LotteryGame[] lotteryGames = {new PowerBall(), new MegaMillions()};
    static final int QUIT = 3;

    public static void main(String[] args) {
        runLotteryRunner();
    }

    static void runLotteryRunner() {
        int userChoice = 0;

        do {
            displayUserMenu();
            userChoice = getUserChoice();

            switch (userChoice) {
                case 1:
                    ticketChoice();
                    break;
                case 2:
                    getWinnings();
                    break;
                case QUIT:
                    System.out.println("\nThank you for playing!");
                    break;
                default:
                    System.out.println("\nINVALID OPTION! Please enter 1, 2, or " + QUIT + ".");
            }
        } while (userChoice != QUIT);
    }

    static void displayUserMenu() {
        System.out.println("\n\tWelcome to the Lottery!");
        System.out.println("\n  Press(1)\t\tTo buy a lottery ticket.");
        System.out.println("  Press(2)\t\tTo check how much you won.");
        System.out.println("  Press(" + QUIT + ")\t\tTo quit.");
    }

    static void ticketChoice() {
        int choice = 0;

        do {
            buyTicketMenu();
            choice = getUserChoice();

            switch (choice) {
                case 1:
                    gameTicket(choice - 1);
                    break;
                case 2:
                    gameTicket(choice - 1);
                    break;
                case QUIT:
                    break;
                default:
                    System.out.println("\nINVALID OPTION! Please enter 1, 2, or " + QUIT + ".");
            }
        } while (choice != QUIT);
    }

    static void buyTicketMenu() {
        System.out.println("\n\tWhat kind of ticket?");
        System.out.println("\n  Press(1)\t\tFor a Powerball ticket.");
        System.out.println("  Press(2)\t\tFor a Mega Millions ticket.");
        System.out.println("  Press(" + QUIT + ")\t\tTo go to the main menu.");
    }

    static void gameTicket(int gameIndex) {
        int ticketType;
        int[] userPicks;
        int userSpecialPick;
        int numberOfPicks;
        String ticketInfo;
        
        do {
            ticketTypeMenu();
            ticketType = getUserChoice();

            switch (ticketType) {
                case 1:
                    ticketInfo = lotteryGames[gameIndex].getQuickPickTicket();
                    System.out.println(ticketInfo);
                    break;
                case 2:
                    userPicks = getUserPicks(lotteryGames[gameIndex].getNumberOfPicks(), lotteryGames[gameIndex].getMinimumPick(), 
                                                lotteryGames[gameIndex].getMaximumPick());
                    userSpecialPick = getUserSpecialPick(lotteryGames[gameIndex].getMinimumSpecialPick(), lotteryGames[gameIndex].getMaximumSpecialPick());
                    ticketInfo = lotteryGames[gameIndex].createUserPickTicket(userPicks, userSpecialPick);
                    System.out.println(ticketInfo);
                    break;
                case QUIT:
                    break;
                default:
                    System.out.println("\nINVALID OPTION! Please enter 1, 2, or " + QUIT + ".");
            }
        } while (ticketType != QUIT);
        
    }

    static void ticketTypeMenu() {
        System.out.println("\n\t  Choose what type");
        System.out.println("\n  Press(1)\t\tFor a quick pick ticket.");
        System.out.println("  Press(2)\t\tTo enter your own numbers.");
        System.out.println("  Press(" + QUIT + ")\t\tTo go to the previous menu.");
    }

    static int[] getUserPicks(int numberOfPicks, int minimumPick, int maximumPick) {
        int count = 0;
        int pick = 0;
        int[] pickArray = new int[numberOfPicks];

        System.out.print("\nPlease enter " + numberOfPicks + " numbers you want, between " + 
                            minimumPick + " and " + maximumPick + ":\n");

        for (int i = 0;i < numberOfPicks;i++) {
            count++;
            System.out.print("\n#" + count + ": ");
            pick = keyboard.nextInt();
            keyboard.nextLine();

            while (pick < minimumPick || pick > maximumPick) {
                System.out.println("\nINVALID NUMBER! Please enter a number between " + minimumPick + " and " + maximumPick + ":\n ");
                System.out.print("#" + count + ": ");
                pick = keyboard.nextInt();
                keyboard.nextLine(); 
            }

            pickArray[i] = pick;
        }

        return pickArray;
    }

    static int getUserSpecialPick(int minimumSpecialPick, int maximumSpecialPick) {
        int specialPick;

        System.out.print("\nPlease enter the special pick number you want between " + minimumSpecialPick + " and " + maximumSpecialPick + ": ");
        specialPick = keyboard.nextInt();
        keyboard.nextLine();

        while (specialPick < minimumSpecialPick || specialPick > maximumSpecialPick) {
            System.out.println("INVALID NUMBER! Please enter a number between " + minimumSpecialPick + " and " + maximumSpecialPick + ": ");
            specialPick = keyboard.nextInt();
            keyboard.nextLine(); 
        }

        return specialPick;
    }

    static void getWinnings() {
        int ticketNumber;
        int gameIndex;

        System.out.println("\n\tWhat kind of ticket?");
        System.out.println("\n   Press(1)\t\tFor Powerball.");
        System.out.println("\n   Press(2)\t\tFor Mega Millions.");
        gameIndex = getUserChoice();

        while (gameIndex < 1 || gameIndex > 2) {
            System.out.println("\nINVALID OPTION! Please enter either 1 (for Powerball) or 2 (for Mega Millions): ");
            gameIndex = getUserChoice();
        }

        System.out.print("\nPlease enter your ticket number: ");
        ticketNumber = keyboard.nextInt();
        keyboard.nextLine();

        System.out.println(lotteryGames[gameIndex - 1].getAmountWon(ticketNumber));
    }

    static int getUserChoice() {
        int choice;

        System.out.print("\nPlease enter your choice: ");
        choice = keyboard.nextInt();

        keyboard.nextLine();

        return choice;
    }

}

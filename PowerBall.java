/***************************************************************
 class PowerBall
 PowerBall is a subclass of class LotteryGame, and represents
 a PowerBall lottery game. It inherits methods, and instance 
 variables from LotteryGame and implements all abstract methods.
 Author: James Hembree
 Last modified: 2/13/2020
 jhembree0023@kctcs.edu
 ***************************************************************/

import java.text.NumberFormat;
import java.util.Arrays;

public class PowerBall extends LotteryGame {
    public static final int NUMBER_OF_PICKS = 5;
    public static final int MINIMUM_PICK = 1;
    public static final int MAXIMUM_PICK = 69;
    public static final int MINIMUM_SPECIAL_PICK = 1;
    public static final int MAXIMUM_SPECIAL_PICK = 26;
    private static LotteryTicket winningTicket;
    private static int ticketCount;

    public PowerBall() {
        super(NUMBER_OF_PICKS, MINIMUM_PICK, MAXIMUM_PICK, 
                MINIMUM_SPECIAL_PICK, MAXIMUM_SPECIAL_PICK);
        
		int[] winningPicks = generatePicks();
		int winningSpecialPick = generateSpecialPick();
		Arrays.sort(winningPicks);
        winningTicket = new LotteryTicket(winningPicks, winningSpecialPick);
        // ticket[ticketCount] = winningTicket;
        // ticketCount++;

		prize = new int[9];
        prize[0] = 4;
        prize[1] = 4;
        prize[2] = 7;
        prize[3] = 7;
        prize[4] = 100;
        prize[5] = 100;
        prize[6] = 5000;
        prize[7] = 1000000;
        prize[8] = 300000000;
    }

    public int generateSpecialPick() {
        int specialPick;
        double randomNumber;

        randomNumber = Math.random();
        specialPick = MINIMUM_SPECIAL_PICK + (int)(randomNumber * ((MAXIMUM_SPECIAL_PICK - MINIMUM_SPECIAL_PICK) + 1));

        return specialPick;
    }

    public String getAmountWon(int ticketNumber) {
        boolean isFound;
        int foundIndex;
        int matchCount;
        int amountWon;
        
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        isFound = false;
        foundIndex = -1;

        for (int i = 0;i < ticketCount;i++) {
            if (ticketNumber == ticket[i].getTicketNumber()) {
                isFound = true;
                foundIndex = i;

                matchCount = countMatches(ticketNumber);

                if (ticket[foundIndex].getSpecialPick() == winningTicket.getSpecialPick()) {
                    switch (matchCount) {
                        case 1:
                            amountWon = prize[1];
                            break;
                        case 2:
                            amountWon = prize[2];
                            break;
                        case 3:
                            amountWon = prize[4];
                            break;
                        case 4:
                            amountWon = prize[6];
                            break;
                        case 5:
                            amountWon = prize[8];
                            break;
                        default:
                            amountWon = prize[0];
                    } 
                }
                else {
                    switch (matchCount) {
                        case 3:
                            amountWon = prize[3];
                            break;
                        case 4:
                            amountWon = prize[5];
                            break;
                        case 5:
                            amountWon = prize[7];
                            break;
                        default:
                            amountWon = 0;
                    }
                }

                if (amountWon == 0) {
                    return "\nSorry, better luck next time! : " + formatter.format(amountWon);
                }
                else {
                    return "\nCongratulations, you won! : " + formatter.format(amountWon);
                }
            }
        }

        return "\nTicket does not exist!";
        
    }

    public String getQuickPickTicket() {
        pick = new int[NUMBER_OF_PICKS];
        int specialPick;
        String ticketInfo;

        pick = generatePicks();
        specialPick = generateSpecialPick();

        Arrays.sort(pick);
        LotteryTicket newTicket = new LotteryTicket(pick, specialPick);
        ticket[ticketCount] = newTicket;

        ticketInfo = "\n   Powerball:" + ticket[ticketCount].toString(); 

        ticketCount++;

        return ticketInfo;
    }

    public String createUserPickTicket(int[] pick, int specialPick) {
        String ticketInfo;
        boolean isEquals;

        Arrays.sort(pick);
        LotteryTicket newTicket = new LotteryTicket(pick, specialPick);

        isEquals = false;
        for (int i = 0;i < ticketCount;i++) {
            isEquals = ticket[i].equals(newTicket);
        }

        if (isEquals) {
            newTicket = null;
            LotteryTicket.nextTicketNumber--;

            return "\n###That ticket already exists! Please choose different numbers.###";
        }

        ticket[ticketCount] = newTicket;

        ticketInfo = "\n   Powerball:" + ticket[ticketCount].toString();

        ticketCount++;

        return ticketInfo;
    }
}
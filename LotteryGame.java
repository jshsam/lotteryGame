/****************************************************************
 class LotteryGame
 This class is an abstract class so that LotteryGame objects 
 cannot be made, but instead objects of its subclasses PowerBall 
 & MegaMillions will be made and forced to implement any abstract 
 methods they inherit from class LotteryGame.
 Author: James Hembree
 Last modified: 2/13/2020
 jhembree0023@kctcs.edu
 ****************************************************************/

public abstract class LotteryGame {
    protected int numberOfPicks;
    protected int minimumPick;
    protected int maximumPick;
    protected int minimumSpecialPick;
    protected int maximumSpecialPick;
    protected LotteryTicket[] ticket;
    protected LotteryTicket winningTicket;
    protected int[] prize;
    protected int[] pick;
    protected int ticketCount = 0;

    public LotteryGame(int numberOfPicks, int minimumPick, int maximumPick, int minimumSpecialPick, int maximumSpecialPick) {
        this.numberOfPicks = numberOfPicks;
        this.minimumPick = minimumPick;
        this.maximumPick = maximumPick;
        this.minimumSpecialPick = minimumSpecialPick;
        this.maximumSpecialPick = maximumSpecialPick;

        pick = new int[numberOfPicks];
        ticket = new LotteryTicket[1000000];

        winningTicket = new LotteryTicket(generatePicks(), generateSpecialPick());
    }

    public int getNumberOfPicks() {
        return this.numberOfPicks;
    }

    public int getMinimumPick() {
        return this.minimumPick;
    }

    public int getMaximumPick() {
        return this.maximumPick;
    }

    public int getMinimumSpecialPick() {
        return this.minimumSpecialPick;
    }

    public int getMaximumSpecialPick() {
        return this.maximumSpecialPick;
    }

    protected int[] generatePicks() {
        double generatedNumber;
        int pickNumber;

        for (int i = 0;i < this.pick.length;i++) {
            do {
                generatedNumber = Math.random();
                pickNumber = this.minimumPick + (int)(generatedNumber * ((this.maximumPick - this.minimumPick) + 1));
            } while (isRepeat(pickNumber));
                
            this.pick[i] = pickNumber;
        }

        return this.pick;
    }

    protected abstract int generateSpecialPick();

    protected boolean isRepeat(int pickNumber) {
        boolean isRepeat;

        isRepeat = false;

        for (int i = 0;!isRepeat && i < this.pick.length;i++) {
            if (this.pick[i] == pickNumber) {
                isRepeat = true;
            }
        }

        return isRepeat;
    }

    protected abstract String getQuickPickTicket();

    protected abstract String createUserPickTicket(int[] pick, int specialPick);

    protected abstract String getAmountWon(int ticketNumber);

    protected int countMatches(int ticketNumber) {
        boolean found;
        int foundIndex;
        int count;

        found = false;
        foundIndex = -1;
        count = 0;

        for (int i = 0;!found && i < ticket.length;i++) {
            if (ticket[i].getTicketNumber() == ticketNumber) {
                found = true;
                foundIndex = i;
            }
        }

        for (int i = 0;i < this.pick.length;i++) {
            if (ticket[foundIndex].getPicks()[i] == winningTicket.getPicks()[i]){
                count++;
            }
        }

        return count;
    }
}
/*************************************************************
 class LotteryTicket
 This class creates object of type LotteryTicket. 
 Each object will contain the details of the ticket purchased 
 including id number and the numbers for that ticket.
 Author: James Hembree
 Last modified: 4/24/2020
 jshembree88@gmail.com
 *************************************************************/

 public class LotteryTicket {

    static int nextTicketNumber = 100000;
    private int ticketNumber;
    private int[] pick = new int[5];
    private int specialPick;

    public LotteryTicket(int[] pick, int specialPick) {
        
        for (int i = 0;i < pick.length;i++) {
            this.pick[i] = pick[i];
        }

        this.specialPick = specialPick;

        this.ticketNumber = nextTicketNumber++;
    }

    public int getTicketNumber() {
        return this.ticketNumber;
    }

    public int[] getPicks() {
        int[] ticketPicks;

        ticketPicks = new int[5];

        for (int i = 0;i < this.pick.length;i++) {
            ticketPicks[i] = this.pick[i];
        }

        return ticketPicks;
    }

    public int getSpecialPick() {
        return this.specialPick;
    }

    public String toString() {
        String ticketInfo;
        String pickString;

        pickString = "";

        for (int i = 0;i < pick.length;i++) {
            pickString += (this.getPicks()[i] + " ");
        }

        ticketInfo = "\nTicket number: " + this.ticketNumber + 
                    "\nPicks: " + pickString + "\nSpecial pick: " + this.specialPick;

        return ticketInfo;
    }

    public boolean equals(Object obj) {
        boolean equalTickets;
        LotteryTicket otherTicket;
        int notEqualIndex;

        equalTickets = true;
        otherTicket = null;
        notEqualIndex = 0;

        if (obj == null) {
			throw new NullPointerException();
		}
		else if (this == obj) {
			equalTickets = true;
		}
		else {
			if (obj instanceof LotteryTicket) {
                otherTicket = (LotteryTicket)obj;
                
				for (int i = 0;i < pick.length;i++) {
                    if (this.pick[i] != otherTicket.pick[i]) {
                        equalTickets = false;
                    }
                }
                
			}
        }

        return equalTickets;
    }
 }

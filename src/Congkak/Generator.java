package Congkak;

/*
    [7][7][7][7][7][7][7]
[p1]                     [p2]
    [7][7][7][7][7][7][7]

INDEX
    [ 8][ 9][10][11][12][13][14]
[ 7]                            [15]
    [ 6][ 5][ 4][ 3][ 2][ 1][ 0]

*/

public class Generator {
    
    final static int setHole = 7;
    final static int setMarbleEachHole = 7;
    final static boolean captureMode = true;
    static int[] house = new int[(setHole*2)+2];
    final static int p1Store = house.length/2 - 1;
    final static int p2Store = house.length-1;
    
    public static void main(String[] args) {
        newGame();
        display();
        
        while(!gameOver()){
//        P1 starts first
            System.out.println("Player 1");
            while(p1GetFreeTurn() != -1) {
                p1Deposit(p1GetFreeTurn());
            }
            if(p1GetFreeTurn() == -1) p1Deposit(p1ChooseNext());
            System.out.println();
            
//        P2 starts next
            System.out.println("Player 2");
            while(p2GetFreeTurn() != -1) {
                p2Deposit(p2GetFreeTurn());
            }
            if(p2GetFreeTurn() == -1) p2Deposit(p2ChooseNext());
            System.out.println();

        }
        
    }
    
    public static void newGame(){
        // Assigning marbles for each houses except the storehouses
        for(int i = 0; i < house.length; i++) if(i != p1Store && i != p2Store) house[i] = setHole;
    }
    
    public static int p1GetFreeTurn(){
        for(int i = 0; i < p1Store; i++) if(house[i] == (p1Store - i)) return i;
        return -1; // return -1 if no possible way to get free turn
    }
    
    public static int p1ChooseNext(){
        for(int i = 0; i < p1Store; i++) if(house[i] != 0) return i;
        return -1; // return -1 if game whole rows is empty
    }
    
    public static int p2GetFreeTurn(){
        for(int i = p1Store+1; i < p2Store; i++) if(house[i] == (p2Store - i)) return i;
        return -1; // return -1 if no possible way to get free turn
    }
    
    public static int p2ChooseNext(){
        for(int i = p1Store+1; i < p2Store; i++) if(house[i] != 0) return i;
        return -1; // return -1 if game whole rows is empty
    }
    
    /**
     * @param n
     * @return true if last deposit marble in on p1store
     */
    public static boolean p1Deposit(int n){
        int holds = house[n];
        house[n] = 0;
            
        while(holds > 0){
            n++;
            if(n == p2Store) n = 0;
            house[n]++;
            holds--;
            
            // When depositing the last marble
            if(holds == 0){
                if(n == p1Store) break;
                else {
                    if(house[n] > 1) {
                        display();
                        holds = house[n];
                        house[n] = 0;
                    }
                }
            }
        }
        display();
        
        if(n == p1Store){
            System.out.println("Player 1 gets free turn");
            return true;
        }
        return false;
    }
    
    /**
     * @param n
     * @return true if last deposit marble in on p2store
     */
    public static boolean p2Deposit(int n){
        int holds = house[n];
        house[n] = 0;
            
        while(holds > 0){
            n++;
            if(n == p1Store) n++;
            if(n == p2Store+1) n = 0;
            house[n]++;
            holds--;
            
            // When depositing the last marble
            if(holds == 0){
                if(n == p2Store) break;
                else {
                    if(house[n] > 1) {
                        display();
                        holds = house[n];
                        house[n] = 0;
                    }
                }
            }
        }
        display();
        
        if(n == p2Store){
            System.out.println("Player 2 gets free turn");
            return true;
        }
        return false;
    }
    
    public static boolean checkCapture(){
        return false;
    }
    
    
    
    public static boolean gameOver(){
        int total = 0;
        for(int i = 0; i < house.length; i++) if(i != 7 && i != 15) total += house[i];
        return total == 0;
    }
    
    public static void display(){
        System.out.print("    ");
        for(int i = 8; i < 15; i++) System.out.printf("[%2d]", house[i]);
        System.out.printf("\n[%2d]\t\t\t\t[%2d]\n", house[7], house[15]);
        System.out.print("    ");
        for(int i = 6; i >= 0; i--) System.out.printf("[%2d]", house[i]);
        System.out.println("\n");
    }
}

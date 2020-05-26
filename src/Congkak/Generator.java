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
    
    public static void main(String[] args) {
        
        int[] house = new int[(7*2)+2];
        // P1 STOREHOUSE = house[7]
        // P2 STOREHOUSE = house[15]
        
        newGame(house);
        display(house);
        
        // P1 starts first
//        while(!gameOver(house)){
//            
//        }
        
    }
    
    public static void newGame(int[] house){
        // Assigning marbles for each houses except the storehouses
        for(int i = 0; i < house.length; i++) if(i != 7 && i != 15) house[i] = 7;
    }
    
    public static int p1GetFreeTurn(){
        return -1;
    }
    
    public static int p2GetFreeTurn(){
        return -1;
    }
    
    public static boolean checkLastMarbleOnStore(){
        return false;
    }
    
    public static boolean checkCapture(){
        return false;
    }
    
    public static boolean gameOver(int[] house){
        int total = 0;
        for(int i = 0; i < house.length; i++) if(i != 7 && i != 15) total += house[i];
        return total == 0;
    }
    
    public static void display(int[] house){
        System.out.print("    ");
        for(int i = 8; i < 15; i++) System.out.printf("[%2d]", house[i]);
        System.out.printf("\n[%2d]\t\t\t\t[%2d]\n", house[7], house[15]);
        System.out.print("    ");
        for(int i = 6; i >= 0; i--) System.out.printf("[%2d]", house[i]);
        System.out.println();
    }
}

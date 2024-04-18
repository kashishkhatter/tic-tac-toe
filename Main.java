import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Main{

    static ArrayList<Integer>playerpositions=new ArrayList<>();
    static ArrayList<Integer>cpupositions=new ArrayList<>();
    public static void main(String[] args) {
        char[][] gameBoard = { //structure of gameboard(2d array)
            {' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' '},
            {'-', '-', '-', '+', '-', '-', '-', '+', '-', '-', '-'},
            {' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' '},
            {'-', '-', '-', '+', '-', '-', '-', '+', '-', '-', '-'},
            {' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' '}
        };
       
        printGameBoard(gameBoard);
       
    while(true){
        Scanner sc=new Scanner(System.in); //take input from user ki kis box ma place kre
        System.out.println("Enter your placement(1-9)");
        int playerPos=sc.nextInt(); //user input for deciding pos of X
        while (playerpositions.contains(playerPos)||cpupositions.contains(playerPos)){
           System.out.println("Position taken!Enter a correct position");
           playerPos=sc.nextInt();
        } 
        placePiece(gameBoard,playerPos,"player"); //fun to put symbol in choosen position(player is playong(X))
       
       String res=checkWinner();
       if(!res.equals("")){
        System.out.println(res);
        break;
       }
        Random rand=new Random(); //for cpu input generate a random pos betw 1-9 and place symbol at that pos
        int cpuPos=rand.nextInt(9)+1; //rand pos btw 1-9
       
        placePiece(gameBoard,cpuPos,"cpu"); //fun to put symbol in O in cpus choosen pos(randomly)
        printGameBoard(gameBoard); //fun printing board
        res=checkWinner();
        if(!res.equals("")){
            System.out.println(res);
            break;
        }
        }
    }
       
        public static void printGameBoard(char[][]gameBoard){ //fun to print 2d array aka gameboard
            for(char[]row:gameBoard){
                for(char c:row){
                    System.out.print(c);
                }
                System.out.println();
            }
        }
        public static void placePiece(char[][]gameBoard,int pos,String user){ //fun to replace the ' ' with symbol in the box choosen
         char symbol=' '; 
            if(user.equals("player")){ //if user is the player then X symbol
            symbol='X';
            playerpositions.add(pos);
           }
           else{ //if cpu playing then O symbol
            symbol='O';
            cpupositions.add(pos);
           }
            switch(pos) {
                case 1: //agr box 1 hai to place symbol in this coordinate
                  gameBoard[0][1]=symbol;
                  break;
                case 2:
                  gameBoard[0][5]=symbol; //coordinate of box 2 pr symbol
                  break;
                case 3:
                  gameBoard[0][9]=symbol; //coordinate of box 3 pr symbol
                  break;
                case 4:
                  gameBoard[2][1]=symbol;
                  break;
                case 5:
                  gameBoard[2][5]=symbol;
                  break;
                case 6:
                  gameBoard[2][9]=symbol;
                  break;
                case 7:
                  gameBoard[4][1]=symbol;
                  break;
                case 8:
                  gameBoard[4][5]=symbol;
                  break;
                case 9:
                  gameBoard[4][9]=symbol;
                  break;
                default:
                 break;
            }
        }
        public static String checkWinner(){
            List topRow=Arrays.asList(1,2,3);
            List midRow=Arrays.asList(4,5,6);
            List bottomRow=Arrays.asList(7,8,9);
            List leftCol=Arrays.asList(1,4,7);
            List midCol=Arrays.asList(2,5,8);
            List rightCol=Arrays.asList(3,6,9);
            List cross1=Arrays.asList(1,5,9);
            List cross2=Arrays.asList(7,5,3);

            List<List>winning=new ArrayList<List>();
            winning.add(topRow);
            winning.add(midRow);
            winning.add(bottomRow);
            winning.add(leftCol);
            winning.add(midCol);
            winning.add(rightCol);
            winning.add(cross1);
            winning.add(cross2);

            for(List l:winning){
                if(playerpositions.containsAll(l)){
                    return "Congratulations,You Won!";
                }else if(cpupositions.containsAll(l)){
                    return "CPU wins";
                }
              
            }
            if (playerpositions.size() + cpupositions.size() == 9) {
                return "Tie!";
            }
            return "";

        }
    }

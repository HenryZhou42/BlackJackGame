package zyh.main;
import zyh.deck.Card;
import zyh.deck.Deck;

import java.util.Random;
import java.util.Scanner;

public class Main {

    /**
     * 输出文本并暂停一段时间让用户阅读
     *@Param text 要输出的文本
     *@Param pauseInMillis 暂停的时间（单位：毫秒）
     *
     */
    public static void printLineWithPause(String text, long pauseInMillis) {
        System.out.println(text);
        try {
            Thread.sleep(pauseInMillis); // 暂停
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 重新设置中断标志
            e.printStackTrace();
        }
    }
    static int base = 0;
    static Deck deck = new Deck();
    static int dealers = 0;
    private static Scanner scanner; // 将Scanner声明为静态成员变量
    public static void main(String[] args) {
        Random rand = new Random();
        scanner = new Scanner(System.in); // 在主方法开始时初始化Scanner
            do{
                printLineWithPause("Welcome to Black Jack Game!",3000);
                deck.shuffle(); //洗牌
                System.out.println("Deck after shuffling:");
                System.out.println(deck);//展示牌库
                dealers = rand.nextInt(12) + 10;//庄家的点数简化成选择10到21中的一个随机整数，实际上可以设计庄家的游戏逻辑，即激进或者稳健。
                System.out.println("Dealer`s point: " + dealers);
                draw();//抽牌
            }while (playAgain());//判定玩家是否再进行游戏
        scanner.close();//确保在所有操作完成后关闭Scanner
        System.out.println("Thanks for playing!");
    }

    public static void draw(){

        while(base<21){
            System.out.println("Draw a card? Y|N");
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("Y")){
                Card drawnCard = deck.drawCard(); // 发一张牌
                System.out.println("Drawn card: " + drawnCard);
                System.out.println("Remaining cards in the deck: " + deck.remainingCards());
                System.out.println(drawnCard.getPointValue());
                base+=drawnCard.getPointValue();
                System.out.println("Your sum: " + base);
            } else if (input.equalsIgnoreCase("N")) {
                break; //不再抽牌，进入胜负判定
            }else {
                System.out.println("Invalid input, please try again");
            }
        }
        if (base>21){
            System.out.println("You Lose");//玩家点数高于21点，直接判负
        }else {
            String result = base > dealers ? "You Win" : base == dealers?"Draw" : "You Lose";//根据庄家的点数判断胜负
            System.out.println(result);
        }
    }

    public static boolean playAgain() {
        System.out.println("Another Game? Y|N");
        String answerAG = scanner.nextLine();
        while (true){
            if (answerAG.equalsIgnoreCase("Y")) { //玩家选择再进行一次游戏
                base = 0;
                deck.shuffle();
                return true;
            } else if (answerAG.equalsIgnoreCase("N")) {//玩家决定不再进行游戏
                return false;
            }
            else {
                System.out.println("Invalid input, please try again");
                answerAG = scanner.nextLine();
            }
        }
    }
}

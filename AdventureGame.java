import java.util.Scanner;
import java.util.Random;

class Player{
    int Health =100;
    boolean Treasure=false;
    public void HasDamage(int damage ){
        Health-=damage;
        System.out.println("You Took "+damage+" damage" +"\n Your Health Now : "+ Health);
    }
    public boolean isAlive(){
        return Health>0;
    }
}
 public class AdventureGame {
 
    public static void main(String[] args) {
        Scanner input =new Scanner(System.in);
        Random random = new Random();
        Player player =new Player();
        System.out.println("==== WELCOME TO ADVENTURE GAME ====");
        System.out.println("\nYou Entered to a dark cave searcing for a treasure");
        System.out.println("You see to paths : ");
        System.out.println("1.Go Left ");
        System.out.println("2.Go Right ");
        System.out.print("Your choice : ");
        int c1 = input.nextInt();
         if (c1==1) {
            System.out.println(" \nA wild Monster appears ");
            int damage=random.nextInt(90)+10;
            player.HasDamage(damage);
            if (!player.isAlive()) {
                System.out.println("\n=== You Died ... Game Over ===");
                return;
            }
                System.out.println(" \nGreat! ... You Defeated the Monster");
            }
            else{
                System.out.println(" You found a safe path");
            }
        System.out.println("You found a treasure box : ");
        System.out.println("1.Open It ");
        System.out.println("2.Ignore It ");
        System.out.print("Your choice : ");
        int c2 = input.nextInt();
        if (c2==1) {
            if (random.nextBoolean()) {
                System.out.println(" \nGreat! .. You found the treasure ");
                player.Treasure=true;
            }
            else{
                System.out.println(" \nOps ... It's a trap ! ");
                player.HasDamage(50);
                if (!player.isAlive()) {
                System.out.println("\n=== You Died ... Game Over ===");
                return;
            }
            }  
        }
        else{System.out.println("You walk away safely ");}
        System.out.println("You reach the cave exit !");
        if (player.Treasure && player.isAlive()) {
            System.out.println("Congratulations!.. You escaped with the treasure $ ");
        }else if(player.isAlive())
        {
            System.out.println(" You survived, but empty-handed");
        }
        else{
            System.out.println("You didn't make it ");
        }

    }

 }
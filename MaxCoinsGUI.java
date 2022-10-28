
import java.util.Scanner;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;

class MaxCoinsGUI extends Frame {
    static int MaxCoins;
    static int NoBalloons;

    MaxCoinsGUI(String s) {
        super(s);
        this.setVisible(true);
        this.setSize(700, 500);
        this.setBackground(Color.BLACK);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public static int MaxCoins(int[] input) {
        int array[] = new int[input.length + 2]; //declaration of array left 1 +right 1=2
        array[0] = 1;
        array[array.length - 1] = 1;
        for (int i = 0; i < input.length; i++) {
            array[i + 1] = input[i];
        }

        // Initialize array , 1 indexed based
        int dp[][] = new int[array.length][array.length];
		//read the array at memoization 
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                dp[i][j] = 0;
            }
        }

        for (int i = 1; i < array.length - 1; i++) {
            for (int j = i; j >= 1; j--) {
                // k means that a balloon burst in  last
//				if k==i left part of array becomes is zero
				//k==j means that right part become zero
                for (int k = j; k <= i; k++) {
                    dp[j][i] = Math.max(array[j - 1] * array[k] * array[i + 1] + dp[j][k - 1] + dp[k + 1][i], dp[j][i]);
					//in the above line  is the main formula or logic of code left*arr[i]*right+condition of rightbound +condition of left bound
					//or already present dp[][]array.
			   }
            }
        }
        return dp[1][array.length - 2]; 
		//at the end we can calculate left * k * right using memoization we got max coins .
    }

    public void paint(Graphics g) {
        Graphics label_01 = g.create();
        label_01.setColor(Color.WHITE);
        label_01.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        label_01.drawString("For " + NoBalloons + " number(s) of balloon(s),", 100, 100);

        Graphics Oval = g.create();
        Oval.setColor(Color.YELLOW);
        Oval.fillOval(200, 200, 50, 50);
        Oval.drawOval(200, 200, 50, 50);

        Graphics Image = g.create();
        Image file = new ImageIcon("C:\\Users\\232338\\OneDrive\\Pictures\\balloon.png").getImage();
        Image.drawImage(file, 300, 100, 100, 200, null);

        Graphics label_02 = g.create();
        label_02.setColor(Color.WHITE);
        label_02.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        label_02.drawString("After Bursting a Balloon", 100, 400);
        label_02.drawString("Maximum Coins is : " + MaxCoins, 100, 450);

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter Number of Balloons : ");
        NoBalloons = scan.nextInt();
//Decalaration of array or initalize it 
        int[] arr = new int[NoBalloons];
		//reading all element of array or balloon with cost
        for (int i = 0; i < NoBalloons; i++) {
            System.out.printf("\t Balloon number %02d : ", i + 1);
            arr[i] = scan.nextInt();
        }

        MaxCoins = MaxCoins(arr);
        System.out.println("Maximum Coin(s) is : " + MaxCoins);// function call
        new MaxCoinsGUI("GUI");
    }
}

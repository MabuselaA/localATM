import java.util.*;
import javax.swing.*;

public class ATM {

	public static void main(String[] args) {
		try{
	Machine swipe = new Machine(0);
		}catch(RuntimeException e){
		System.out.println("Wrong Input");
		System.out.println("Home page");
		Machine swipe = new Machine(0);
		}
	}
}
class Machine{
	static Scanner in = new Scanner(System.in);
	
	static int Ans = 0;
	static int count;
	int ser = 0;
	int aPIN =0, zama = 2;
	
	static ArrayList<Long> ID_No = new ArrayList<>();
	static ArrayList<Integer> Account_No = new ArrayList<>();
	static ArrayList<Integer> Pin = new ArrayList<>();
	static ArrayList<Double> Balans = new ArrayList<>();
	
	Machine(){
	}
	Machine(int ok){
		check(ok);
	}
	public void check(int ok){
		String star = null;
		
		System.out.print("Create an account or sign up? C = create, S = Sign up");
		star = in.next();
		switch(star){
		case "S":
		case "s":
			sign_in();
			break;
		case "c":
		case "C":
			new_account();
			break;
			default: System.out.println("Your're directed to the start page");
			ATM.main(null);
		}
	}
	private void new_account(){
		double Balance = 50000.00;
		int counts=0;
		Long minID = 1000000000000l, ID = null;
		Long maxID = 9999999999999l;
		int minAcc = 10000000, Acc = 0;
		int maxAcc = 99999999;
		int minpin = 1000, pin = 0;
		int maxpin = 9999;
		int a = 0, b = 0, c = 0, d= 0;
		
		for(int account1: Machine.Account_No)
			counts++;
			
		System.out.println("Enter ID number:");
		ID = in.nextLong();
		while((Long.valueOf(ID) <= Long.valueOf(minID))||(Long.valueOf(ID) >= Long.valueOf(maxID))){
			System.out.println("Incorrect input, please enter 13 digits of your ID number");	
			ID = in.nextLong();
		}
		for(; a < counts; a++){
			while(ID.equals(ID_No.get(a))){
				System.out.println("ID number already exists in our system, please enter own ID number");
				ID = in.nextLong();	
				while(ID.equals(ID_No.get(b))){
					System.out.println("ID number already exists in our system, please enter own ID number");
					ID = in.nextLong();	
					b++;
				}
			}
		}
		ID_No.add(ID);
		System.out.println("Enter given account number:");
		Acc = in.nextInt();
		while((Acc<=minAcc)||(Acc>=maxAcc)){
			System.out.println("Incorrect input, please enter 8 digits account number");
			Acc = in.nextInt();
		}
		for(; c < counts; c++){
			while(Acc==Account_No.get(c)){
				System.out.println("ID number already exists in our system, please enter own ID number");
				Acc = in.nextInt();	
				while(Acc==Account_No.get(d)){
					System.out.println("ID number already exists in our system, please enter own ID number");
					Acc = in.nextInt();	
					d++;
				}
			}
		}
		Account_No.add(Acc);
		System.out.println("Create Pin");
		pin = in.nextInt();
		while((pin<=minpin)||(pin>=maxpin)){
			System.out.println("Incorrect input, please enter correct pin number");
			pin = in.nextInt();
		}
		Pin.add(pin);
		Balans.add(Balance);
		
		JOptionPane.showMessageDialog(null, ID_No);
		sign_in();
	}
	private void sign_in(){
		int counter=0;
		System.out.println("Enter account number:");
		ser = in.nextInt();
		try{
		while(!(ser==Account_No.get(counter)))
			counter++;
		}catch(RuntimeException e){
			System.out.println("No record of " + ser);
			System.out.println("Session Terminated");
			ATM.main(null);
		}
		count = counter;
		System.out.println("Enter PIN:");
		
		aPIN = in.nextInt();
		while(aPIN!=Pin.get(count)){
			JOptionPane.showMessageDialog(null, "Attemps left : " + zama);
			if(zama==0){
				System.out.println("Closed!");
				ATM.main(null);
			}
			System.out.println("Enter PIN number:");
			aPIN = in.nextInt();
			zama--;
		}
		System.out.println("Welcome");
		sa(0);
	}
	public static void sa(int answer){
		System.out.println("Select Transaction");
		System.out.println(" ");
		System.out.println("1. Withdraw");
		System.out.println("2. Deposit");
		System.out.println("3. Balance");
		
		answer = in.nextInt();
		
		switch(answer){
		case 1:
			Withdraw draw = new Withdraw();
			break;
		case 2:
			Deposit dep = new Deposit();
			break;
		case 3:
			Balance bal = new Balance();
			break;
		default: System.out.println("Your're directed to the start page");
		ATM.main(null);
		}
		Ans = answer;
	}
	public static int count(int c){
		c = count;
		return c;
	}
}
class Withdraw{
	
	Machine Mach = new Machine();
	int DrawLimit = 5000;
	double bal = 0.0, drawA = 0.0;
	int attemps = 2, count = Machine.count(0);
	int accN = 0;
	
	Withdraw(){
		bal = Machine.Balans.get(count);
		newBalance();
	}
	private double insert_amount(double draw, int atp){
		JOptionPane.showMessageDialog(null,"Current Balance: R" + bal);
		atp = attemps;
		draw = Integer.parseInt(JOptionPane.showInputDialog("Enter amount"));
		while((draw>bal)||(draw>DrawLimit)){
			System.out.println("You either have exceeded your daily withdraw limit or funds are insufficiate.");
			System.out.println("Attemps left : " + atp);
			draw = Integer.parseInt(JOptionPane.showInputDialog("Enter amount"));
			atp--;
			if(atp==0){
				System.out.println("Failed to Co-operate");
				ATM.main(null);
			}
		}
		drawA = draw;
		return draw;
	}
	private double Deduct(double draw, double remain){
		draw = insert_amount(0, 0);
		remain = bal;
		
		return remain - draw;
	}
	void newBalance(){

		String end = null;
		bal = Deduct(0.0, 0.0);
		Machine.Balans.set( count,bal);
		JOptionPane.showMessageDialog(null, "R " +drawA + " was withdrawn from account: "+ Machine.Account_No.get(count) + "\n"
				+ "New Balance: R " + bal);
		System.out.println("End session?");
		end = Machine.in.next();
		switch(end){
		case "Yes":
		case "yes":
		case "Y":
		case "y":
			System.out.println(" ");
			System.out.println("Session Terminated");
			ATM.main(null);
			break;
		case "No":
		case "no":
		case "N" :
		case "n" :
			System.out.println(" ");
			System.out.println("Menu");
			Machine.sa(0);
			break;
		default: System.out.println("Termination Taking place");
		System.exit(0);
		}
	}
}
class Deposit{
	Machine Mach = new Machine();
	
	static int choose = 0, other = 0, x =0, y = 0;
	static int count = Machine.count;
	static int count2 = 1;
	static int selectA;
	static int minAcc = 10000000, Acc = 0;
	static int maxAcc = 99999999;
	static double Amount;
	static double deposit_Limit = 10000.0;

	Deposit(){
		end();
	}
	private static double Dep(double amount, int account){
		count2=1;
		System.out.println("Insert amount of money to be deposited");
		amount = Machine.in.nextDouble();
		if(amount>deposit_Limit){
			System.out.println("Cannot deposit more than R" + deposit_Limit);
			Dep(0.0,0);
		}
		System.out.println("select account");
		for(int account1: Machine.Account_No){
		System.out.println(count2 + ". "+ account1);
		count2++;
		}
		other = count2;
		System.out.println(other +". other account");
		System.out.println("0. to exit");
		choose = Machine.in.nextInt();
		if(choose ==0)
			Machine.sa(0);
		else if(choose == other){
			System.out.println("Enter given account number:");
			Acc = Machine.in.nextInt();
			while((Acc<=minAcc)||(Acc>=maxAcc)){
				System.out.println("Incorrect input, please enter 8 digits account number");
				Acc = Machine.in.nextInt();
			}
		}else{
		choose = choose - 1;
		while(choose==count){
			System.out.println("Cannot deposit to the same account you are logged in with");
			System.out.println("select account");
			choose = Machine.in.nextInt();
			choose = choose - 1;
		}
		account = Machine.Account_No.get(choose);
		
		selectA = account;
		}
		return amount;
	}
	public void Depo(){
		
		Amount = Dep(0.0, 0);
		if(choose == other){
			Machine.Balans.set(count, Machine.Balans.get(count)- Amount);
			JOptionPane.showMessageDialog(null, "account: "+ Machine.Account_No.get(count) + " R " + Machine.Balans.get(count) + "\n"
					+	"account: "+ Acc + " has recieved R " + Amount);	
		}else{
		Machine.Balans.set(count, Machine.Balans.get(count)- Amount);
		Machine.Balans.set(choose, Machine.Balans.get(choose)+ Amount);
		JOptionPane.showMessageDialog(null, "account: "+ Machine.Account_No.get(count) + " R " + Machine.Balans.get(count) + "\n"
			+	"account: "+ Machine.Account_No.get(choose) + " has recieved R " + Amount);	
		}
	}
	private void end(){
		Depo();
		String end = null;
		System.out.println("End session?"); 
		end = Machine.in.next();
		switch(end){
		case "Yes":
		case "yes":
		case "Y":
		case "y":
			System.out.println(" ");
			System.out.println("Session Terminated");
			ATM.main(null);
			break;
		case "No":
		case "no":
		case "N" :
		case "n" :
			System.out.println(" ");
			System.out.println("Menu");
			Machine.sa(0);
			break;
		default: System.out.println("Termination Taking place");
		System.exit(0);
		}
	}
}
class Balance{
	Machine Mach = new Machine();
	double bal = 0.0;
	int count = Machine.count, count2 = 0;
	
	Balance(){
	Balanse();
	}
	void Balanse(){
		String end = null;
		JOptionPane.showMessageDialog(null,"Account: "+ Machine.Account_No.get(count) + "\n" +"Current Balance: R" + Machine.Balans.get(count));
		System.out.println("End session?"); 
		end = Machine.in.next();
		switch(end){
		case "Yes":
		case "yes":
		case "Y":
		case "y":
			System.out.println(" ");
			System.out.println("Session Terminated");
			ATM.main(null);
			break;
		case "No":
		case "no":
		case "N" :
		case "n" :
			System.out.println(" ");
			System.out.println("Menu");
			Machine.sa(0);
			break;
			default: System.out.println("Termination Taking place");
			System.exit(0);
		}
	}
}
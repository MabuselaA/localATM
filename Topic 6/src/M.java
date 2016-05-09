import javax.swing.JOptionPane;//import statements
import java.util.Scanner;

public class M{
    
    //Main class
    public static void main(String[] args) {
        
        //Object created to access classes and constructors
       BMW_Dealership Power = new BMW_Dealership();
       M_Package Total = new M_Package();
   }

}

class BMW_Dealership {
    
    //static variables = class variables
    static int model = 0;
    static String[] mod = {"18i", "20i", "20d", "25i", "28i", "30i", "35i", "40i", "50i", "60li"};//Model type in arrays
    static String[] range = {"1-Series", "2-Series", "3-Series", "4-Series", "5-Series", "6-Series", "7-Series"};//Range type in arrays
    static Scanner in = new Scanner(System.in);
    
        //Instance variables
        int Doors = 0;
        double cprice = 0.0;
        String r = null;
        
        String MP = null;
        String c = null;
        
        New nnew = new New();
    
        //User-Defined constructor to handle inputs of the user
    BMW_Dealership(){
        
      int Doors = 0, counter2=1;//local variables
      double cprice = 0.0;
      int r = 0;
        
      String MP = null;
      String c = null;
        
      New nnew = new New();
        
      System.out.println("BMW Range");
        
      //for-loop to display the array car range
      for(String R:range){
        System.out.println(counter2 + ". " + R);
            counter2++;
        }
        
      //inputs the chosen Range type
      System.out.println("Choose Range");
      r = in.nextInt();
      New.Range = r;
        
      //selects numbers of doors
      Doors = Integer.parseInt(JOptionPane.showInputDialog("How many doors? 3 or 5"));
      New.doors(Doors);
        
      //user enters car model, 
      System.out.println("Select car model");
      New.setModels();//model method called to enter details
        
      //enters color of desired car
      c = JOptionPane.showInputDialog("Color?");
      nnew.C = c;//object reference/ answer sent to the method in a different class
      New nnew1 = new New(null, c, cprice);//overload constructor recieves values for color
        
      //Enters whether they want M package or not
      MP = JOptionPane.showInputDialog("M-Performance Package Included? Y = Yes or N = No");
      nnew.Decide = MP;
    
    }  
}

 class New{
    
    //Global and Instance Variables declared 
    static int Range;
    String C = null;
    static String ra, mo;
    
    static int min, max, Select, count = 1;
    static int Model = 0, co = 0;
    static String Colour = null;
    static String Decide = null;
    
    double CPrice = 0.0;
    protected static double CarPrice;//encapsulated variable
        
    //default constructor used to access this class, without this constructor, compilation errors will be reached
    New(){
    }
    
    //Overload constructor used to call the color method
    New(String type, String color, double price){
            model(type, color, price);
    }
    
    //encapsulated method known as information hiding, only access from this class
    private static double getRangePrice(int range){
        
        Range = range;//method arguments
        
        //switch statement to determine amount based on selected car range
        switch(Range){
            case 1:
                CarPrice = 250000.00;
                break;
            case 2:
                CarPrice = 300000.00;
                break;
            case 3:
                CarPrice = 350000.00;
                break;
            case 4:
                CarPrice = 400000.00;
                break;
            case 5:
                CarPrice = 450000.00;
                break;
            case 6:
                CarPrice = 500000.00;
                break;
            case 7:
                CarPrice = 550000.00;
                break;
        }
        
        //Selected range passed into the variable
        ra = BMW_Dealership.range[range-1];
        
        //return statement
        return CarPrice;
    }
    
    //method with parameters
    static void doors(int doors){
        
        getRangePrice(Range);
        
        if(doors==3)
            CarPrice = CarPrice + 20000;
        
        else if(doors==5)
            CarPrice = CarPrice + 40000;
        
        else{
            System.out.println("Invalid Option");
            System.exit(0);
        }

    }
    
    //method without parameters
        public static void setModels(){
            
            //local variables
        int count = 1;
        int sel = 0;
        
        //if statement used to determine which range should be displayed from the array
         if((Range>=1)&&(Range<=4)){
             
            min = 0;
            max = 7;
            
        }else if((Range>=5)&&(Range<=6)){
            
            min = 1;
            max = 8;
            Select = 1;
            
        } else if(Range == 7){
            
            min = 7;
            max = 10;
            Select = 7;
            
        }
        
        //Displays selected values from the arrays
         for(;min<max;min++){
             
             System.out.println(count + ". " + BMW_Dealership.mod[min]);
             count++;
                
        }
         
        Model = Integer.parseInt(JOptionPane.showInputDialog("Select Size"));
        
        //if and while statements to align the model with the correct initialisation value accord to the array
         if (Select==0)
           while(Model>max)
                Model = Integer.parseInt(JOptionPane.showInputDialog("incorret input, Select Size"));
                
        else if (Select==1)
            while((Model<Select)||(Model>max))  
              Model = Integer.parseInt(JOptionPane.showInputDialog("incorret input, Select Size"));
              
        else if (Select==7){
            
            sel = Select + Model;
            
           while((sel<Select)||(sel>max)){
               
               Model = Integer.parseInt(JOptionPane.showInputDialog("incorret input, Select Size"));
               sel = 0;
               sel = Select + Model;
               
           }
           
        }

        model(Model, 0);//value sent and call the method
    }
    
    //overloaded methods for the select of models, with return value
    public static double model(int model, double cost){
        
        //Arguments used to calculate the correct model as well as to get correct values from array
        model = Select + model;
        co = model -1;
        Model = model;
    
         switch(model){
              case 1:
                  cost = 10000;
                  break;
              case 2 :
                  cost = 12000;
                  break;
              case 3 :
                  cost = 14000;
                  break;
              case 4 :
                  cost = 16000;
                  break;
              case 5 :
                  cost = 18000;
                  break;
              case 6 :
                  cost = 20000;     
                  break;
              case 7 :
                  cost = 22000;
                  break;
              case 8 :
                  cost = 28000;
                  break;
              case 9 :
                  cost = 30000;
                  break;
              case 10 :
                  cost = 34000;
                  break;
                  default: System.out.println("Invalid option");
                  System.exit(0);
              }
                
                //Model sent from array to the variable
                mo = BMW_Dealership.mod[co];
                CarPrice = CarPrice + cost;     
    
                return CarPrice;
    }

    //overloaded method with no return type
    public void model(String type, String color, double price){
    
        if((color.equals("red"))||(color.equals("black"))||(color.equals("blue"))||(color.equals("grey")))
            price = 2100.00;
        else if(color.equals("white"))
            price = 1000.00;
        else
            System.exit(0);
    
         Colour = color;
         CPrice = price;
    
         CarPrice = CarPrice + CPrice;
        }

}
 
 class M_Package{
	    
	 
	 	
	    //encapsulated principal used and varaib;es declared
	    protected double M_Package = 70000, standard = 55000;
	    private static String Decide = New.Decide;
	    //user defined to call print method
	    M_Package(){
	        
	        print();
	    
	    }
	    
	    //access modifier, method with parameters and include return type
	    public double Grand_Total(double M, double CarP){
	        String Decision = null;
	        
	        setDecide(Decision);
	    	
	        CarP = New.CarPrice + standard;
	        
	        switch(Decision){
	        case "Yes":
	        case "yes":
	        case "Y":
	        case "y":
	            M = M_Package;
	            System.out.println("M-Performance Package added");
	        break;
	        }
	        
	        return M + CarP;
	        
	    }
	    
	    //method to print all results
	    void print(){
	        
	        System.out.println("\n");
	        System.out.println(New.ra);
	        System.out.println(car(null));
	        System.out.println("Color: " + New.Colour);
	        System.out.println("R " + Grand_Total(0, 0));
	        
	    }
	    
	    //method to produce type of car
	    private String car(String Ride){
	        
	        int range = New.Range;
	        String Mod = New.mo;//object reference
	        Ride = range + Mod;
	        
	        return Ride;//return type of car
	    }
	    public String getDecide(){
	        return Decide;
	    }
	    public String setDecide(String deci){
	       deci =  getDecide();
	       return deci;
	    }
	       
	}

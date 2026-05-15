import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.Random;

class GachaGirl{
	public String name;
	protected int rate;
	public String image;
	public String getName(){
		return name;
	}
	public int getRate(){
		return rate;
	}	
};

class Inventory extends GachaGirl {

	public int shards = 0;
	public HashMap<Integer, GachaGirl> invGirls = new HashMap<>();
	//public int count = 0;
	public void add (GachaGirl g, int key){
		//iGachaGirl g = new GachaGirl();
		//g.name = name;
		//g.rate = rate;
		invGirls.put(key, g);
		//count++;
	}
	public void printInv() {
		System.out.println("Girls:");
		if (invGirls.size() > 0) {
			for (int i = 0; i < 4; i++) {
				if (invGirls.containsKey(i)) {
					System.out.println(invGirls.get(i).name);
				}
			}
			System.out.println("Shards: " + shards);
		}
	}

};


//TODO: add in the girls
class GachaGirlPool extends Inventory {
	//hashmap of int keys and girl values
	//we're gonna roll a number and see who it lands on
	protected HashMap<Integer, GachaGirl> girls = new HashMap<>(); 	
	//protected int size = 1; //i have it set to 1 rn for testing
	public int getSize() {
		return girls.size();
	}
	//public Inventory inv = new Inventory();
	//high key we don't need an add function we need a remove one 
	//er actually i think we would actually want an inventory and a chack duplicate system 
	//yeah cuz a remove function would eventually garuntee every character
	// ... unless we want that
	//ehhh maybe we do want that? so that your ability to date the girls isnt solely based on luck
	//ok made an add function afterall because java is stupid 
	public void addGirl(String name, int rate, String img){
		GachaGirl g = new GachaGirl(); 
		g.name = name;
		g.rate = rate;
		g.image = img;
		girls.put(rate, g);
	}
	public void printOne(int key) {
		System.out.println(girls.get(key).name);
	}
	/*public void obtainGirl(int key) {
	  if (invGirls.containsKey(key)) {
	  inv.shards++;
	  }
	  else  {
	  inv.add(girls.get(key), key);
	  }
	}
	public void hanging_myself () {
	inv.printInv();
	}*/
};

class gacha extends GachaGirlPool { //this class runs main automatically because its *the* gacha class, which gets called when you do java gacha 
	public static void main(String[] args) {
		GachaGirlPool girls = new GachaGirlPool();
		girls.addGirl("Sylver Sharpe", 0, "Sylver Sharpe.png");
		girls.addGirl("Kona Cupp", 1, "Kona Cupp.png");
		girls.addGirl("Brioche Baker", 2, "Brioche Baker.png");
		girls.addGirl("Earl Potter", 3, "Earl Potter.png");
		Random rand = new Random();
		int randInt; //rn each girl has an equal chance 
													 //but if we want to change the drop rate we could do like rand.nextInt(100) and then say that the femboy has the values 0-20, kettle 20-30, etc.
		//System.out.println("Random Integer: " + randInt);
		//girls.printOne(randInt);
		//System.out.println("yayyyy");
		Inventory inv = new Inventory();
		Scanner keyboard = new Scanner(System.in);
		while (true) {
			System.out.println("press 1 to gamble🔥🎰🎲👩");
			int myint = keyboard.nextInt();
			randInt = rand.nextInt(girls.getSize());
			if (myint == 1) {
				System.out.println("You got: " + girls.girls.get(randInt).name);
				if (inv.invGirls.containsKey(randInt)) {
					inv.shards++;
				}
				else  {
					//System.out.println(girls.girls.get(randInt));
					inv.add((girls.girls.get(randInt)), randInt);
				}
				inv.printInv();
			}
			else {
				break;
			}
		}
		//inv.printInv();
	}
}

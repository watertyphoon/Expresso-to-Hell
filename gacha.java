import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.Random;

class GachaGirl{
	public String name;
	protected int rate;
	public String getName(){
		return name;
	}
	public int getRate(){
		return rate;
	}	
};

class Inventory extends GachaGirl {

	public int shards;
	protected HashMap<Integer, GachaGirl> invGirls = new HashMap<>();
	public int count = 0;
	public void add (GachaGirl g){
		//iGachaGirl g = new GachaGirl();
		//g.name = name;
		//g.rate = rate;
		invGirls.put(count, g);
		count++;
	}
	public void printInv() {
		System.out.println("Girls:");
		for (int i = 0; i < count; i++) {
			System.out.println(invGirls.get(i));
		}
		System.out.println("Shards: " + shards);
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
	public Inventory inv = new Inventory();
	//high key we don't need an add function we need a remove one 
	//er actually i think we would actually want an inventory and a chack duplicate system 
	//yeah cuz a remove function would eventually garuntee every character
	// ... unless we want that
	//ehhh maybe we do want that? so that your ability to date the girls isnt solely based on luck
	//ok made an add function afterall because java is stupid 
	public void addGirl(String name, int rate){
		GachaGirl g = new GachaGirl(); 
		g.name = name;
		g.rate = rate;	
		girls.put(rate, g);
	}
	public void printOne(int key) {
		System.out.println(girls.get(key).name);
	}
	public void obtainGirl(int key) {
		if (invGirls.containsKey(key)) {
			shards++;
		}
		else  {
			inv.add(girls.get(key));
		}
	}
	public void hanging_myself () {
		inv.printInv();
	}
};

class gacha extends GachaGirlPool { //this class runs main automatically because its *the* gacha class, which gets called when you do java gacha 
	public static void main(String[] args) {
		GachaGirlPool girls = new GachaGirlPool();
		girls.addGirl("Blender", 0);
		girls.addGirl("Espresso Machine", 1);
		girls.addGirl("Matcha Whisk", 2);
		girls.addGirl("Dishwasher", 3);
		girls.addGirl("Toaster Oven", 4);
		girls.addGirl("Kettle", 5);
		Random rand = new Random();
		int randInt = rand.nextInt(girls.getSize()); //rn each girl has an equal chance 
		//but if we want to change the drop rate we could do like rand.nextInt(100) and then say that the femboy has the values 0-20, kettle 20-30, etc.
		System.out.println("Random Integer: " + randInt);
		girls.printOne(randInt);
		System.out.println("yayyyy");
		girls.hanging_myself();
	}
}

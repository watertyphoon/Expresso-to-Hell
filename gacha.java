import java.util.Scanner;
import java.io.File;

	class GachaGirl{
		protected String name;
		protected int rate;
	}
	public final String getName(){
		return name;
	}
	public final int getRate(){
		return rate;
	};	

	class GachaGirlPool{
		protected HashMap<GachaGirl, Integer> girls = new HashMap<>(); 
		protected int size = 0;

		public void addGirl(String name, int rate){
		GachaGirl g = new GachaGirl(); 
			g.name = name;
			g.rate = rate;	
			girls.put(name, rate);
			size++;
		}	
	};





class Everything {
	//class Coord {
		public int x = 0;
		public int y = 0;
	//}
	//class Hitbox {
		public int width = 0;
		public int height = 0;
	//}

	//Coord coord = new Coord();
	protected String name;
	protected int velocity = 0;
	protected boolean stationary = false;
	public String sprite;

	public void set_name(String s) {
		name = s;
	}
	public String get_name() {
		return name;
	}
	public void set_velocity(int v) {
		velocity = v;
	}
	public int get_velocity() {
		return velocity;
	}
	public void set_stationary(boolean b) {
		stationary = b;
	}
	public boolean get_stationary() {
		return stationary;
	} 
	public void set_coord (int new_x, int new_y) {
		x = new_x;
		y = new_y;
	}
	//TODO: add all the thingies and stop being lazy and fix this one
	public int get_x () {
		return x;
	}
	public int get_y () {
		return y;
	}
}

class Player extends Everything {
	public Player(String str, int v, boolean b, String rts, int new_x, int new_y) {
		name = str;
		velocity = v;
		boolean stationary = b;
		sprite = rts;
		x = new_x;
		y = new_y;
	}
}

class Projectile extends Everything {
	protected int damage = 0;
	public Projectile(String str, int v, boolean b, int d, int new_x, int new_y) {
		name = str;
		velocity = v;
		stationary = b;
		damage = d;
		x = new_x;
		y = new_y;
	}
	public void set_damage(int d) {
		damage = d;
	}
	public int get_damage() {
		return damage;
	}
}

class Expresso_Shot extends Projectile {
	public Expresso_Shot() {
		super("Expresso Shot", 5, false, 2, 0, 0);
	}
}

class Expresso_Machine extends Expresso_Shot {
	protected Expresso_Shot expresso;
}


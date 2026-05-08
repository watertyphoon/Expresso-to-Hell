 

class Everything {
	class coord {
		public int x = 0;
		public int y = 0;
	}
	class hitbox {
		public int width = 0;
		public int height = 0;
	}
	protected String name;
	protected int velocity = 0;
	protected bool stationary = false;

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
	public void set_stationary(bool b) {
		stationary = b;
	}
	public int get_stationary() {
		return stationary;
	}
	public void set_coord (int x, int y) {
		coord.x = x;
		coord.y = y;
	}
	//TODO: add all the thingies and stop being lazy and fix this one
	public int get_coord () {
		return {coord.x, coord.y};
	}
}

class Projectile extends Everything {
	protected int damage = 0;
	public Projectile(String str, int v, bool b, int d) {
		name = str;
		velocity = v;
		stationary = b;
		damage = d;
	}
}

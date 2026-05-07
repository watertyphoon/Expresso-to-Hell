 

class Everything {
	class coord {
		public int x = 0;
		public int y = 0;
	}
	protected String name;
	protected int velocity = 0;
	protected bool stationary = false;

	public void set_name(String s) {
		name = s;
	}
	public String get_name() const {
		return name;
	}
	public void set_velocity(int v) {
		velocity = v;
	}
	public int get_velocity() const {
		return velocity;
	}
}

class Projectile extends Everything {
	public Projectile(String str, int v, bool b) {
		name = str;
		velocity = v;
		stationary = b;
	}
}

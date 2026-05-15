import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;

public class visuals extends JPanel implements KeyListener/*implements WindowListener*/, ActionListener {
	public Player player = new Player("Expresso", 25, false, "back stand.png", 6000, 4500);  
	public boolean is_fighting = false;
	public ArrayList<Projectile> enemy_projectiles = new ArrayList<>();
	public ArrayList<Projectile> bullets = new ArrayList<>();
	//multiplayer variable
	public ArrayList<String> chat = new ArrayList<>();
	public dialogue d = new dialogue();
	private PrintWriter message;
	private String playername = "Player" + new Random().nextInt(100);

	public boolean right = false;
	public boolean left = false;
	public boolean up = true;
	public boolean down = false;
	public boolean next_frame = true;
	public boolean max2 = false;

	public boolean max = true;
	public boolean crust = false;
	public boolean lobby = false;

	public int index = 0;
	public int b_hp = 5;
	public int b_x = 6000;
	public int b_y = 0;
	public String b_sprite = "max p 1.png";

	public Timer timer;

	public void chit_chat() {
		new Thread(() -> {
			try {
				Socket socket = new Socket("host", 6769);
				message = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader inbox = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				message.println(playername);

				String line;
				while((line = inbox.readLine()) != null) {
					chat.add(line);
					if(chat.size() > 5) {
						chat.remove(0);
					}
					repaint();
				}
			} catch (Exception e) {
				System.out.println("Ya GOt tHe WroNg NuMBeR"); 
			}
		}).start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < bullets.size(); i++) {
				//bullets.get(i).x += bullets.get(i).get_velocity();
				bullets.get(i).y -= bullets.get(i).get_velocity();
				if(bullets.get(i).y == b_y && bullets.get(i).x == b_x) {
					bullets.remove(i);
					b_hp -= 1;
				}
			}
			repaint();
	}

	public visuals() {
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(this);
		timer = new Timer(16, this);
        timer.start();
		chit_chat();
	}

	public String text = "i hate periods";
	public String eemage = "max performative(shrunk).png";
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		// Turn on anti-aliasing for smooth circles
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Image rom_bg = Toolkit.getDefaultToolkit().getImage("bg.png");
		Image arena = Toolkit.getDefaultToolkit().getImage("arena.png");

		// Draw background
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(0, 0, getWidth(), getHeight());

		//start of my code
		//Image img1 = Toolkit.getDefaultToolkit().getImage(eemage); //idk what all this does but you put the name of your image in here
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
		//g2d.scale(0.85, 0.85); //this controls how big or small the image will be
							   //its important to do scale() BEFORE drawing the image 
							   //if you do it after it'll scale the next thing that gets drawn instead of the image 
		//g2d.drawImage(img1, 1, 1, null);
		//font stuff
		//i high key dont know what most of this does i got it off stack overflow
		g2d.setFont(new Font("Arial", 0, 60));
		//g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		//g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
		//protected String text = "i hate periods"; //sets the text 
		//more font stuff
		Image boss = Toolkit.getDefaultToolkit().getImage(b_sprite);
		if(max) {
			Image perf = Toolkit.getDefaultToolkit().getImage(eemage);
			g2d.drawImage(rom_bg, 0, 0, getWidth(), getHeight(), null);
			//g2d.scale(0.85,0.85);
			g2d.drawImage(perf, 0, getHeight() - 250, 150, 250, null);
			if(next_frame) {
				setText(d.MaxPerf1.get(index));
				if((index + 1) == d.MaxPerf1.size()) {
					is_fighting = true;
					max = false;
				}
				else {
					index += 1;
					next_frame = false;
				}
			}
		}
		FontMetrics fm = new FontMetrics(g2d.getFont()) {};
		g2d.translate((600 - g2d.getFontMetrics(g2d.getFont()).stringWidth(text)) / 2, (450 - fm.getHeight()) / 2); //you can play with these values to see what happens
		g2d.setColor(Color.WHITE); //set color of text (also goes BEFORE you draw the text) 
		g2d.scale(.9, .9); //sets scale of text 
		g2d.drawString(text, 600f, 850f); //draws text on the screen 
		
		Image img2 = Toolkit.getDefaultToolkit().getImage(player.sprite);

		Image bull = Toolkit.getDefaultToolkit().getImage("coffee bean.png");
		//g2d.drawImage(arena, (getWidth()/2), (getHeight()/2), null); 
		if(is_fighting) {
			g2d.scale(0.2,0.2);
			//g2d.setColor(Color.DARK_GRAY);
			//g2d.fillRect(0,0,getWidth()*10,getHeight()*10);
			g2d.drawImage(img2, player.x, player.y, null);
			if(up) {
				if(player.sprite == "back stand.png") {
					player.sprite = "back walk 1.png";
					up = false;
				}
				else if(player.sprite == "back walk 1.png") {
					player.sprite = "back walk 2.png";
					up = false;
				}
				else {
					player.sprite = "back stand.png";
					up = false;
				}
			}
			if(down) {
				if(player.sprite == "front stand.png") {
					player.sprite = "front walk 1.png";
					down = false;
				}
				else if(player.sprite == "front walk 1.png") {
					player.sprite = "front walk 2.png";
					down = false;
				}
				else {
					player.sprite = "front stand.png";
					down = false;
				}
			}
			if(right) {
				if(player.sprite == "right_stand.png") {
					player.sprite = "right_walk.png";
					right = false;
				}
				else {
					player.sprite = "right_stand.png";
					right = false;
				}
			}
			if(left) {
				if(player.sprite == "side stand.png") {
					player.sprite = "side walk 2.png";
					left = false;
				}
				else {
					player.sprite = "side stand.png";
					left = false;
				}
			}
			g2d.drawImage(boss, b_x, b_y, null);
			for(int i = 0; i < bullets.size(); i++) {
				g2d.drawImage(bull, bullets.get(i).x, bullets.get(i).y, null);
			}
		}
		g2d.scale(1,1);
		g2d.setFont(new Font("Arial", 0, 12));
		g2d.setColor(Color.BLUE);
		g2d.drawString("Chat: ", 10, 20);

		for(int i = 0; i < chat.size(); i++) {
			g2d.drawString(chat.get(i), 10, 40 + (i * 20));
		}
	}	

	public void setText(String newText) {
		this.text = newText;
	}
	public void setImage(String newImage)  {
		this.eemage = newImage;
	}
	@Override 
	public void keyPressed(KeyEvent e) {
		if(is_fighting) {
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				player.y -= player.get_velocity();
				up = true;
				down = false;
				right = false;
				left = false;
			}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				player.y += player.get_velocity();
				up = false;
				down = true;
				right = false;
				left = false;
			}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				player.x -= player.get_velocity();
				up = false;
				down = false;
				right = false;
				left = true;
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				player.x += player.get_velocity();
				up = false;
				down = false;
				right = true;
				left = false;
			}
			else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				bullets.add(new Projectile("bullet", 10, false, 2, (player.x), (player.y-5)));
			}
			if(b_hp <= 0) {
				if(max) {
					max = false;
					max2 = true;
				}
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_T) {
			String s = JOptionPane.showInputDialog(this, "{:");
			if(s != null && message != null) {
				message.println(s);
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				next_frame = true;
		}
		repaint();
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	// Simple container class for ball properties
	public static void main(String[] args) {
		// Ensure GUI creation is done on the Event Dispatch Thread
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("X11 Tunnel Test: bullet cafe");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(600, 600);
			frame.add(new visuals());
			frame.setLocationRelativeTo(null); // Center on screen
			frame.setVisible(true);
		});
	}
}

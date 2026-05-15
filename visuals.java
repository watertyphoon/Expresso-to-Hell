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

public class visuals extends JPanel implements KeyListener/*implements WindowListener*/, ActionListener {
	public Player player = new Player("Expresso", 25, false, "heart.png", 6000, 4500);  
	public boolean is_fighting = false;
	public ArrayList<Projectile> enemy_projectiles = new ArrayList<>();
	public ArrayList<Projectile> bullets = new ArrayList<>();
	public Timer timer;

	@Override
	public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < bullets.size(); i++) {
				bullets.get(i).x += bullets.get(i).get_velocity();
				bullets.get(i).y -= bullets.get(i).get_velocity();
			}
			repaint();
	}

	public visuals() {
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(this);
		timer = new Timer(16, this);
        timer.start();
	}
	//private final int BALL_COUNT = 10;
	//private final ArrayList<Ball> balls = new ArrayList<>();
	//private final Timer timer;
	//kerney's Bouncing ball code 
	public String text = "i hate periods";
	public String eemage = "max performative(shrunk).png";
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		/*public String getText() {
		  return text;
		}
		public void setText(String newText) {
		this.text = newText;
		}*/
		// Turn on anti-aliasing for smooth circles
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Draw background
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(0, 0, getWidth(), getHeight());

		//start of my code
		Image img1 = Toolkit.getDefaultToolkit().getImage(eemage); //idk what all this does but you put the name of your image in here
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
		g2d.scale(0.85, 0.85); //this controls how big or small the image will be
							   //its important to do scale() BEFORE drawing the image 
							   //if you do it after it'll scale the next thing that gets drawn instead of the image 
		g2d.drawImage(img1, 1, 1, null);
		//font stuff
		//i high key dont know what most of this does i got it off stack overflow
		g2d.setFont(new Font("Arial", 0, 60));
		//g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		//g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
		//protected String text = "i hate periods"; //sets the text 
		//more font stuff 
		FontMetrics fm = new FontMetrics(g2d.getFont()) {};
		g2d.translate((600 - g2d.getFontMetrics(g2d.getFont()).stringWidth(text)) / 2, (450 - fm.getHeight()) / 2); //you can play with these values to see what happens
		g2d.setColor(Color.WHITE); //set color of text (also goes BEFORE you draw the text) 
		g2d.scale(.9, .9); //sets scale of text 
		g2d.drawString(text, 600f, 850f); //draws text on the screen 
		
		//g2d.setColor(new Color(128,0,128));
		//g2d.fillRect(520,745,10,300);//x,y,length of obj, height of obj

		/*g2d.setColor(new Color(128,0,128));
		g2d.fillRect(525,745,1400,10);//x,y,length of obj, height of obj

		g2d.setColor(new Color(128,0,128));
		g2d.fillRect(1923,745,10,300);//x,y,length of obj, height of obj*/
		
		Image img2 = Toolkit.getDefaultToolkit().getImage(player.sprite);
		Image boss = Toolkit.getDefaultToolkit().getImage("omniman.png");
		Image bull = Toolkit.getDefaultToolkit().getImage("bullet.png");


		//player.x = 6000;
		//player.y = 4500;
		//player.set_velocity(25);		
		if(is_fighting) {
			//removeAll();	
			g2d.scale(0.2,0.2);
			g2d.setColor(Color.DARK_GRAY);
			g2d.fillRect(0,0,getWidth()*10,getHeight()*10);
			g2d.drawImage(img2, player.x, player.y, null);
			g2d.drawImage(boss, 6000, 0, null);
			for(int i = 0; i < bullets.size(); i++) {
				g2d.drawImage(bull, bullets.get(i).x, bullets.get(i).y, null);
			}
			/*for(int i = 0; i < bullets.size(); i++) {
				bullets.get(i).x += bullets.get(i).get_velocity();
				bullets.get(i).y -= bullets.get(i).get_velocity();
			}*/
		}
	}	
	/*public String getText() {
		return text;
	}*/
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
			}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				player.y += player.get_velocity();
			}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				player.x -= player.get_velocity();
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				player.x += player.get_velocity();
			}
			else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				bullets.add(new Projectile("bullet", 10, false, 2, (player.x), (player.y-5)));
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			//setText("balls");
			//setImage("alysa liu(shrunk).png");
				is_fighting = true;
		}
		repaint();
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}

	/*Button b;
	public visuals(String title) {
		super(title);
		setLayout(new FlowLayout());
		addWindowListener(this);
		b = new Button("Click me");
		add(b);
		add(text);
		b.addActionListener(this);
	}*/

	/*Action up = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.scale(0.2,0.2);
			g2d.drawImage(img2, player.x, player.y - player.get_velocity(), null);
		}
	};
	component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("w"), "up");
	component.getActionMap().put("up", up);*/
	
/*	@Override
	public void actionPerformed(ActionEvent e) {
		int width = getWidth();
		int height = getHeight();

		//Inputmap input = gamepanel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		//Actionmap act = gamepanel.getActionMap();
		//input.put(KeyStroke.getKeyStroke("W"), "move_up");

		public void actionPerformed(ActionEvent e) {
			text.setText("hanging myself frong");
        }*/
		// Update positions and check for wall collisions


	/*	for (Ball ball : balls) {
		  ball.x += ball.dx;
		  ball.y += ball.dy;

		// X-axis collision
		if (ball.x - ball.radius < 0 || ball.x + ball.radius > width) {
		ball.dx = -ball.dx;
		// Correct position so they don't get stuck in the wall
		ball.x = Math.max(ball.radius, Math.min(ball.x, width - ball.radius));
		}
		// Y-axis collision
		if (ball.y - ball.radius < 0 || ball.y + ball.radius > height) {
		ball.dy = -ball.dy;
		// Correct position
		ball.y = Math.max(ball.radius, Math.min(ball.y, height - ball.radius));
		}
	}

		// Tell Swing to redraw the window
		repaint();
}*/

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

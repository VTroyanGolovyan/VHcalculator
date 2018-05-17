import java.math.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
class Main{
  public static void main(String[] args){
	  double k = 1.5;
     new CalculatorFrame("VHcalc",100,100,(int)(200*k),(int)(330*k)-22).animate();
  }
}
class DisplayButton extends MyButton{
	String text;
	public DisplayButton(String text,int x, int y, int width, int height){
		super(text, x, y, width, height);
		this.text = "0";
		this.operator = "";
		this.num1 = 0;
		this.num2 = 0;
	}
	
	int num1;
	int num2;
	int rez;
	String operator;
	public void calc(){
	  try{
		switch(operator){
		     case "+" :
		          rez = num1 + num2;
		        break;
		     case "-" :
		          rez = num1 - num2;
		        break;
		     case "/" :
		          rez = num1 / num2;
		        break;
		     case "*" :
		          rez = num1 * num2;
		        break;
		   }
		   operator = "";
		   setText("" + rez);
		   num1 = rez;
		   num2 = 0;
		}catch(Exception e){
		   operator = "";
		   setText("Error / zerro");
		   num1 = 0;
		   num2 = 0;
		}
	}
	public void command(String s){
		String t = "+-*/";
		if (s.equals("C")){
		   if (operator.equals("")){
		      num1 /= 10;
		      this.setLabel(""+num1);
		   }
		   else {
			   num2 /= 10;
			    this.setLabel(""+num2);
		   }
		}else if (s.equals("=")){
		   calc();
		   operator = "";
		   num1 = 0;
		   num2 = 0;
		}else if (t.indexOf(s) >= 0){
			if (!operator.equals("")){
			   if (num2 != 0)
			     calc();
               operator = s;
			   
			} else operator = s;
		}else{
		   if (operator.equals("")){
		      num1 = num1*10 + (int)s.charAt(0) - 48;
		      this.setLabel(""+num1);
		   }
		   else {
			   num2 = num2*10 + (int)s.charAt(0) - 48;
			    this.setLabel(""+num2);
		   }
		}
	}
	public void setText(String s){
	  this.text = s;
	  setLabel(text);
	}
	public void actionPerformed(ActionEvent e){
		this.text = "";
        setLabel("0");
        num1 = 0;
		num2 = 0;
    }
}

class InputButton extends MyButton{
	DisplayButton display;
	public InputButton(String text,int x, int y, int width, int height,DisplayButton display){
		super(text, x, y, width, height);
		this.display = display;
               
	}
	public void actionPerformed(ActionEvent e){
		
        display.command(getLabel());
    }
}
class CalculatorFrame extends MyFrame{
	InputButton ar[];
	DisplayButton display;
	ChangeModeButton  cm;
	public void animate(){
		double k = 1.5;
		display.changeLocation(0,0);
		changeTheme(0,0,0);
		ar[0].changeLocation(0,(int)(50*k));
		ar[1].changeLocation((int)(50*k),(int)(50*k));
		ar[2].changeLocation((int)(100*k),(int)(50*k));
		ar[3].changeLocation(0,(int)(100*k));
		ar[4].changeLocation((int)(50*k),(int)(100*k));
		ar[5].changeLocation((int)(100*k),(int)(100*k));
		ar[6].changeLocation(0,(int)(150*k));
		ar[7].changeLocation((int)(50*k),(int)(150*k));
		ar[8].changeLocation((int)(100*k),(int)(150*k));
		ar[9].changeLocation(0,(int)(200*k));
		ar[10].changeLocation((int)(50*k),(int)(200*k));
		ar[11].changeLocation((int)(100*k),(int)(200*k));
		ar[12].changeLocation((int)(150*k),(int)(150*k));
		ar[13].changeLocation((int)(150*k),(int)(200*k));
		ar[14].changeLocation((int)(150*k),(int)(100*k));	
		ar[15].changeLocation((int)(150*k),(int)(50*k));
		cm.changeLocation(0,(int)(250*k));
	}
	public void changeTheme(int r, int g, int b){
		display.changeColor(r,g,b);
		for (int i = 0; i < 16; i++)
		   ar[i].changeColor(r,g,b);
		cm.changeColor(r,g,b);
	}
	public void randomTheme(){
		display.changeColor((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
		for (int i = 0; i < 16; i++)
		   ar[i].changeColor((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
		cm.changeColor((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
	}
	public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        GradientPaint blackToGray = new GradientPaint(0, 0, Color.BLACK,
            500, 0, Color.LIGHT_GRAY);
        g2.setPaint(blackToGray);
        g2.fill(new Rectangle2D.Double(0, 0, 300, 800));


    }
    public CalculatorFrame(String title,int x,int y,int width,int height){
		super(title,x,y,width,height);
		double k = 1.5;
		display = new DisplayButton("0",0,getHeight(),(int)(200*k),(int)(50*k));
		add(display);
		setBackground(Color.BLACK);
        setForeground(Color.WHITE);
		ar = new InputButton[100];
		
		add(ar[0] = new InputButton("1",(int)(-50*k),(int)(50*k),(int)(50*k),(int)(50*k),display));
		
		add(ar[1] = new InputButton("2",0,0,(int)(50*k),(int)(50*k),display));
		
		add(ar[2] = new InputButton("3",getWidth(),(int)(50*k),(int)(50*k),(int)(50*k),display));
		
		add(ar[3] = new InputButton("4",(int)(-50*k),(int)(100*k),(int)(50*k),(int)(50*k),display));
		
		add(ar[4] = new InputButton("5",0,0,(int)(50*k),(int)(50*k),display));
		
		add(ar[5] = new InputButton("6",getWidth(),0,(int)(50*k),(int)(50*k),display));
		
		add(ar[6] = new InputButton("7",(int)(-50*k),(int)(150*k),(int)(50*k),(int)(50*k),display));
		
		add(ar[7] = new InputButton("8",0,getHeight(),(int)(50*k),(int)(50*k),display));
		
		add(ar[8] = new InputButton("9",getWidth(),getHeight(),(int)(50*k),(int)(50*k),display));
		
		add(ar[9] = new InputButton("+",(int)(-50*k),(int)(200*k),(int)(50*k),(int)(50*k),display));
		
		add(ar[10] = new InputButton("0",(int)(50*k),getHeight(),(int)(50*k),(int)(50*k),display));
		
		add(ar[11] = new InputButton("-",(int)(100*k),getHeight(),(int)(50*k),(int)(50*k),display));
		
		add(ar[12] = new InputButton("*",getWidth(),(int)(150*k),(int)(50*k),(int)(50*k),display));
		
		add(ar[13] = new InputButton("/",getWidth(),(int)(200*k),(int)(50*k),(int)(50*k),display));
		
		add(ar[14] = new InputButton("C",getWidth(),(int)(100*k),(int)(50*k),(int)(50*k),display));
		
		add(ar[15] = new InputButton("=",getWidth(),(int)(50*k),(int)(50*k),(int)(50*k),display));
		
		add(cm = new ChangeModeButton ("Change colors",0,0,(int)(200*k), (int)(50*k),this));
	}
}
class AnimationThread extends Thread{
	public int x;
	public int y;
	MyButton b;
    public AnimationThread(MyButton b,int x,int y){
		super();
		this.b = b;
		this.x = x;
		this.y = y;
	}
	public void run(){
		b.setAnimationAllow(false);
		int tx = b.getX();
		int ty = b.getY();
		int dx = 5;
		int dy = 5;
		while((tx != x || ty != y) && (Math.abs(tx-x) > dx || Math.abs(ty-y) > dy)){                 
		    if (tx < x)
		      tx+=dx;
		    if (tx > x)
		      tx-=dx;
		    if (ty < y)
		      ty+=dx;
		    if (ty > y)
		      ty-=dx;
		    b.setLocation(tx,ty);
		    try{
				sleep(30);
			}catch(Exception e){
			  
			}
		}
		b.setLocation(x,y);
		b.setAnimationAllow(true);
	}
}
class AnimationColorThread extends Thread{
	public int x;
	public int y;
	public int z;
	MyButton b;
    public AnimationColorThread(MyButton b,int red,int green, int blue){
		super();
		this.b = b;
		this.x= red;
		this.y = green;
		this.z = blue;
	}
	public void run(){
		b.setAnimationAllow(false);
		
		Color color = b.getBackground();
		int tx,ty,tz;
		try{
		  tx = color.getRed();
		  ty = color.getGreen();
		  tz = color.getBlue();
		}catch(Exception e){
			tx = 0;
			ty = 0;
			tz = 0;
		}
		int dx = 4;
		int dy = 4;
		int dz = 4;
		
		while((tx != x || ty != y || tz != z) && (Math.abs(tx-x) > dx || Math.abs(ty-y) > dy || Math.abs(tz-z) > dz)){                 
		    if (tx < x)
		      tx+=dx;
		    if (tx > x)
		      tx-=dx;
		    if (ty < y)
		      ty+=dy;
		    if (ty > y)
		      ty-=dy;
		    if (tz < z)
		      tz+=dz;
		    if (tz > z)
		      tz-=dz;
		    if (tx > 255)
		      tx = 255;
		    if (ty > 255)
		      ty = 255;
		    if (tz > 255)
		      tz = 255;
		    if (tx < 0)
		      tx = 0;
		    if (ty < 0 )
		      ty = 255;
		    if (tz < 0)
		      tz = 0;
		    b.setBackground(new Color(tx,ty,tz));
		    b.setForeground(new Color(255 - tx,255 - ty, 255 - tz));
		    try{
				sleep(30);
			}catch(Exception e){
			  
			}
		}
		b.setBackground(new Color(x,y,z));
		
		b.setAnimationAllow(true);
	}
}
class MyFrame extends JFrame implements WindowListener{
	public MyFrame(String title,int x,int y,int width,int height){
		super(title);
		setSize(width,height);
		setLocation(x,y);
		setLayout(null);
		addWindowListener(this);
		setResizable(false);
		setVisible(true);
		setBackground(new Color(0,0,0));
		
	}
    public void windowActivated(WindowEvent e) {
		
	}
	public void windowClosed(WindowEvent e) {
		
	}
	public void windowClosing(WindowEvent e) {
		setVisible(false);
        dispose();
	}
	public void windowDeactivated(WindowEvent e) {
		
	}
	public void windowIconified(WindowEvent e)  {
		
	}
	public void windowOpened(WindowEvent e) {
		
	}
	public void windowDeiconified(WindowEvent e) {
		
	}
}
class ChangeModeButton extends MyButton{
  CalculatorFrame f;
  public ChangeModeButton(String text,int x, int y, int width, int height, CalculatorFrame f){
		super(text, x, y, width, height);
        this.f = f;
  }
   public void actionPerformed(ActionEvent e){
       f.changeTheme((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
      
       //f.randomTheme();
   }
}
class MyButton extends Button implements ActionListener{
   AnimationThread anim;
   AnimationColorThread anim1;
   public static Font myFont = new Font("Arial", Font.ITALIC,24);
   public MyButton(String text, int x,int y, int width, int height){
	   super(text);
	   setLocation(x,y);
	   setSize(width,height);
	   setBackground(Color.WHITE);
       setForeground(Color.BLACK);
	   this.animationFlag = true;
	   
	   setFont(myFont);
	   addActionListener(this);
   }
   boolean animationFlag;
   public void setAnimationAllow(boolean f){
     this.animationFlag = f;
   }
   public void changeLocation(int x,int y){
        anim = new AnimationThread(this,x,y);
        anim.start();     
   }
   public void changeColor(int r, int g, int b){
	   if (animationFlag){  
         anim1 = new AnimationColorThread(this,r,g,b);
         anim1.start();   
	   }  
   }
   public void actionPerformed(ActionEvent e){
       
   }
}

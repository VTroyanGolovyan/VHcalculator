import java.math.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class Main{
  public static void main(String[] args){
     new CalculatorFrame("VHcalc",100,100,205,280);
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
		setBackground(Color.BLACK);
        setForeground(Color.WHITE);
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
		  // setText("0");
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
    }
}

class InputButton extends MyButton{
	DisplayButton display;
	public InputButton(String text,int x, int y, int width, int height,DisplayButton display){
		super(text, x, y, width, height);
		this.display = display;
		setBackground(Color.BLACK);
        setForeground(Color.WHITE);
               
	}
	public void actionPerformed(ActionEvent e){
		
        display.command(getLabel());
    }
}
class CalculatorFrame extends MyFrame{
    public CalculatorFrame(String title,int x,int y,int width,int height){
		super(title,x,y,width,height);
		DisplayButton display = new DisplayButton("0",0,0,200,50);
		add(display);
		setBackground(Color.BLACK);
        setForeground(Color.WHITE);
		display.changeLocation(0,0);
		InputButton t;
		add(t = new InputButton("1",0,0,50,50,display));
		t.changeLocation(0,50);
		add(t = new InputButton("2",0,0,50,50,display));
		t.changeLocation(50,50);
		add(t = new InputButton("3",0,0,50,50,display));
		t.changeLocation(100,50);
		add(t = new InputButton("4",0,0,50,50,display));
		t.changeLocation(0,100);
		add(t = new InputButton("5",0,0,50,50,display));
		t.changeLocation(50,100);
		add(t = new InputButton("6",0,0,50,50,display));
		t.changeLocation(100,100);
		add(t = new InputButton("7",0,0,50,50,display));
		t.changeLocation(0,150);
		add(t = new InputButton("8",0,0,50,50,display));
		t.changeLocation(50,150);
		add(t = new InputButton("9",0,0,50,50,display));
		t.changeLocation(100,150);
		add(t = new InputButton("+",0,0,50,50,display));
		t.changeLocation(0,200);
		add(t = new InputButton("0",0,0,50,50,display));
		t.changeLocation(50,200);
		add(t = new InputButton("-",0,0,50,50,display));
		t.changeLocation(100,200);
		add(t = new InputButton("*",0,0,50,50,display));
		t.changeLocation(150,150);
		add(t = new InputButton("/",0,0,50,50,display));
		t.changeLocation(150,200);
		InputButton t2;
		add(t2 = new InputButton("C",0,0,50,50,display));
		t2.changeLocation(150,100);
		InputButton t3;
		add(t3 = new InputButton("=",0,0,50,50,display));
		t3.changeLocation(150,50);
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
class MyButton extends Button implements ActionListener{
   AnimationThread anim;
   public MyButton(String text, int x,int y, int width, int height){
	   super(text);
	   setLocation(x,y);
	   setSize(width,height);
	   this.animationFlag = true;
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
   public void actionPerformed(ActionEvent e){
       
   }
}

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class music
{
	public Clip sound=loadClip("sherlock_theme.wav");
	public  Clip loadClip(String filename) 
   	{       
		Clip clip=null;
        try
       	{
			AudioInputStream audioIn=AudioSystem.getAudioInputStream(getClass().getResource(filename));
			clip=AudioSystem.getClip();
			clip.open(audioIn);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
      	}
       	catch (Exception e)
		{
			e.printStackTrace(); 
		}
		return clip;
    }
	public void playClip(Clip clip)
	{
		clip.start();
	}
}
class frame extends JFrame implements ActionListener
{
	static int count=1,playerCheckValue=0,set_positionValue=10,cpuCheckValue=0,ifdoit=0,playerScore=0,cpuScore=0,checkWinnerValue=0,checkWinnerPlayer=0,checkWinnerCpu=0,vsCpu=1,Player1Cross=1,crossSwap=1,crissSwap=0;
	int hit=11;
	static char pos[]={'a','b','c','d','e','f','g','h','i'},flag='j',search='n',player_move='j',player_move1='j',doItValue='j',randomValue='j',tempd='j';
	String Player1Name="PLAYER",Player2Name="CPU";
	
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	ImageIcon criss=new ImageIcon("blackO2.jpg");
	ImageIcon cross=new ImageIcon("redX2.png");
	ImageIcon ttt2=new ImageIcon("ttt2.jpg");
	ImageIcon ttt7=new ImageIcon("ttt7.jpg");
	ImageIcon ttt=new ImageIcon("ttt.jpg");
	
	
	JMenuBar mb=new JMenuBar();
	JMenu menuA=new JMenu("Game");
	JRadioButtonMenuItem rbMenuItem1=new JRadioButtonMenuItem("Play against computer",true);
	JRadioButtonMenuItem rbMenuItem2=new JRadioButtonMenuItem("Play against human");
	ButtonGroup bg=new ButtonGroup();
	JMenu subMenu=new JMenu("Player Symbol");
	JRadioButtonMenuItem rbMenuItem3=new JRadioButtonMenuItem("Cross",true);
	JRadioButtonMenuItem rbMenuItem4=new JRadioButtonMenuItem("Criss");
	ButtonGroup bg1=new ButtonGroup();
	JMenuItem MenuItem=new JMenuItem("Register",'R');
	
	JButton b1=new JButton();
	JButton b2=new JButton();
	JButton b3=new JButton();
	JButton b4=new JButton();
	JButton b5=new JButton();
	JButton b6=new JButton();
	JButton b7=new JButton();
	JButton b8=new JButton();
	JButton b9=new JButton();
	JButton button1 = new JButton("Yes, Please"); 
	JButton button2 = new JButton("No, thanks"); 
	JButton button3 = new JButton("Done");
	
	JPanel p=new JPanel();
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JPanel p3=new JPanel();
	JPanel p4=new JPanel();
	JPanel p5=new JPanel();
	JPanel temp=new JPanel();
	JPanel temp1=new JPanel();
	JPanel temp2=new JPanel();
	JPanel temp3=new JPanel();
	JPanel temp4=new JPanel();
	JPanel temp5=new JPanel();
	JPanel temp6=new JPanel();
	JPanel temp7=new JPanel();
	JLabel l=new JLabel("SCORE");
	JLabel l1=new JLabel(Player1Name+": 0");
	JLabel l2=new JLabel(Player2Name+": 0");
	JLabel l3=new JLabel("");
	JLabel l4=new JLabel(ttt7);
	
	JTextField tf1 =new JTextField("PLAYER 1",20);
	JTextField tf2 =new JTextField("PLAYER 2",20);
	
	JDialog d;
	JDialog d1;
	
	frame()
	{
		this.setLocation(dim.width/8-this.getSize().width/2, dim.height/20-this.getSize().height/2);
		setLayout(null);
		setIconImage(ttt.getImage());
		setResizable(false);
		setJMenuBar(mb);
		

		p.setLayout(null);
		p1.setLayout(new GridLayout(3,3));
		
		bg.add(rbMenuItem1);
		bg.add(rbMenuItem2);
		bg1.add(rbMenuItem3);
		bg1.add(rbMenuItem4);
		menuA.add(rbMenuItem1);
		menuA.add(rbMenuItem2);
		menuA.addSeparator();
		menuA.add(subMenu);
		subMenu.setToolTipText("In \"aginst human\" mode it will be assigned to first Player");
		subMenu.add(rbMenuItem3);
		subMenu.add(rbMenuItem4);
		menuA.addSeparator();
		menuA.add(MenuItem);
		mb.add(menuA);
		menuA.setMnemonic('G');
	


		add(p);
		
		
		p.add(l);
		p.add(l1);
		p.add(l2);
		p.add(p1);
		p.add(l3);
		p.add(l4);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p1.add(b5);
		p1.add(b6);
		p1.add(b7);
		p1.add(b8);
		p1.add(b9);
		p.setBounds(0,0,1000,500);
		p1.setBounds(366,200,287,130);
		l.setBounds(480,0,100,25);
		l1.setBounds(8,35,100,25);
		l2.setBounds(910,35,100,25);
		l3.setBounds(420,429,500,25);
		l4.setBounds(0,0,1000,455);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		rbMenuItem1.addActionListener(this);
		rbMenuItem2.addActionListener(this);
		rbMenuItem3.addActionListener(this);
		rbMenuItem4.addActionListener(this);
		MenuItem.addActionListener(this);
		
	}
	public void random()
	{
		Random rand = new Random();
		int randomNum = rand.nextInt(9)+ 97;
		char search='n',tempe;
		tempe=(char)randomNum;
		for(int h=0;h<9;h++)
		{
			if(tempe==pos[h])
			{
				search=tempe;
			}
		}
		if(search==tempe)
		{
			randomValue=tempe;
		}
		else
		{	
			random();	
		}
	}
	public void cpuCheck()
	{
		char tempe='w';
		if (pos[1]==pos[2]||pos[3]==pos[6]||pos[4]==pos[8])
		tempe='a';
		else if (pos[0]==pos[2]||pos[4]==pos[7])
		tempe='b';
		else if (pos[0]==pos[1]||pos[5]==pos[8]||pos[4]==pos[6])
		tempe='c';
		else if (pos[4]==pos[5]||pos[0]==pos[6])
		tempe='d';
		else if (pos[3]==pos[5]||pos[1]==pos[7]||pos[0]==pos[8]||pos[2]==pos[6])
		tempe='e';
		else if (pos[3]==pos[4]||pos[2]==pos[8])
		tempe='f';
		else if (pos[7]==pos[8]||pos[0]==pos[3]||pos[2]==pos[4])
		tempe='g';
		else if (pos[6]==pos[8]||pos[1]==pos[4])
		tempe='h';
		else if (pos[6]==pos[7]||pos[2]==pos[5]||pos[0]==pos[4])
		tempe='i';
		for(int h=0;h<9;h++)
					{
						if(tempe!='w')
						{
							cpuCheckValue=1;
						}
					}
	}
	public void doit()
	{
		v:
		{
		if (pos[1]==pos[2]&&pos[2]=='O'||pos[3]==pos[6]&&pos[6]=='O'||pos[4]==pos[8]&&pos[8]=='O')
		{
		tempd='a';
		for(int h=0;h<9;h++)
					{
						if(tempd==pos[h])
						{
							break v;
						}
					}
		
		}
		if (pos[0]==pos[2]&&pos[2]=='O'||pos[4]==pos[7]&&pos[7]=='O')
		{
		tempd='b';
		for(int h=0;h<9;h++)
					{
						if(tempd==pos[h])
						{
							break v;
						}
					}
		}
		if (pos[0]==pos[1]&&pos[1]=='O'||pos[5]==pos[8]&&pos[8]=='O'||pos[4]==pos[6]&&pos[6]=='O')
		{
		tempd='c';
		for(int h=0;h<9;h++)
						{
							if(tempd==pos[h])
							{
								break v;
							}
						}
		}
		if (pos[4]==pos[5]&&pos[5]=='O'||pos[0]==pos[6]&&pos[6]=='O')
		{
		tempd='d';
		for(int h=0;h<9;h++)
					{
						if(tempd==pos[h])
						{
							break v;
						}
					}
		}
		if (pos[3]==pos[5]&&pos[5]=='O'||pos[1]==pos[7]&&pos[7]=='O'||pos[0]==pos[8]&&pos[8]=='O'||pos[2]==pos[6]&&pos[6]=='O')
		{
		tempd='e';
		for(int h=0;h<9;h++)
					{
						if(tempd==pos[h])
						{
							break v;
						}
					}
		}
		if (pos[3]==pos[4]&&pos[4]=='O'||pos[2]==pos[8]&&pos[8]=='O')
		{
		tempd='f';
		for(int h=0;h<9;h++)
					{
						if(tempd==pos[h])
						{
							break v;
						}
					}
		}
		if (pos[7]==pos[8]&&pos[8]=='O'||pos[0]==pos[3]&&pos[3]=='O'||pos[2]==pos[4]&&pos[4]=='O')
		{
		tempd='g';
		for(int h=0;h<9;h++)
					{
						if(tempd==pos[h])
						{
							break v;
						}
					}
		}
		if (pos[6]==pos[8]&&pos[8]=='O'||pos[1]==pos[4]&&pos[4]=='O')
		{
		tempd='h';
		for(int h=0;h<9;h++)
					{
						if(tempd==pos[h])
						{
							break v;
						}
					}
		}
		if (pos[6]==pos[7]&&pos[7]=='O'||pos[2]==pos[5]&&pos[5]=='O'||pos[0]==pos[4]&&pos[4]=='O')
		{
		tempd='i';
		for(int h=0;h<9;h++)
					{
						if(tempd==pos[h])
						{
							break v;
						}
					}
		}
		if (pos[1]==pos[2]||pos[3]==pos[6]||pos[4]==pos[8])
		{
		tempd='a';
		for(int h=0;h<9;h++)
					{
						if(tempd==pos[h])
						{
							break v;
						}
					}
		}
		if (pos[0]==pos[2]||pos[4]==pos[7])
		{
		tempd='b';
		for(int h=0;h<9;h++)
					{
						if(tempd==pos[h])
						{
							break v;
						}
					}
		}
		if (pos[0]==pos[1]||pos[5]==pos[8]||pos[4]==pos[6])
		{
		tempd='c';
		for(int h=0;h<9;h++)
					{
						if(tempd==pos[h])
						{
							break v;
						}
					}
		}
		if (pos[4]==pos[5]||pos[0]==pos[6])
		{
		tempd='d';
		for(int h=0;h<9;h++)
					{
						if(tempd==pos[h])
						{
							break v;
						}
					}
		}
		if (pos[3]==pos[5]||pos[1]==pos[7]||pos[0]==pos[8]||pos[2]==pos[6])
		{
		tempd='e';
		for(int h=0;h<9;h++)
					{
						if(tempd==pos[h])
						{
							break v;
						}
					}
		}
		if (pos[3]==pos[4]||pos[2]==pos[8])
		{
		tempd='f';
		for(int h=0;h<9;h++)
					{
						if(tempd==pos[h])
						{
							break v;
						}
					}
		}
		if (pos[7]==pos[8]||pos[0]==pos[3]||pos[2]==pos[4])
		{
		tempd='g';
		for(int h=0;h<9;h++)
					{
						if(tempd==pos[h])
						{
							break v;
						}
					}
		}
		if (pos[6]==pos[8]||pos[1]==pos[4])
		{
		tempd='h';
		for(int h=0;h<9;h++)
					{
						if(tempd==pos[h])
						{
							break v;
						}
					}
		}
		if (pos[6]==pos[7]||pos[2]==pos[5]||pos[0]==pos[4])
		{
		tempd='i';
		for(int h=0;h<9;h++)
					{
						if(tempd==pos[h])
						{
							break v;
						}
					}
		}
		}
		for(int h=0;h<9;h++)
					{
						if(tempd==pos[h])
						{
							ifdoit=1;
							doItValue=tempd;
						}
					}
	}
	public void cpuMove()
	{
		int tempe;
		cpuCheck();
		tempe=cpuCheckValue;
		if(tempe==1)
		{
			doit();
			flag=doItValue;
			
		}
		if(count==2&&(player_move=='a'||player_move=='c'||player_move=='g'||player_move=='i'))
		{
			flag='e';
		}
		else if(count==2&&player_move=='e')
		{
			do 
			{
				random();
				flag=randomValue;
			}
			while(flag=='b'||flag=='d'||flag=='e'||flag=='f'||flag=='h');
		}
		else if(count==4&&ifdoit!=1&&(player_move1=='a'||player_move1=='c'||player_move1=='g'||player_move1=='i'))
		{
			do 
			{
				random();
				flag=randomValue;
			}
			while(flag=='a'||flag=='c'||flag=='g'||flag=='i');
		}
		else if(count==4&&ifdoit!=1&&player_move1=='e')
		{
			do 
			{
				random();
				flag=randomValue;
			}
			while(flag=='b'||flag=='d'||flag=='f'||flag=='h'||flag=='e');
		}
		else if (count>1&&ifdoit!=1&&count<9)
		{
				random();
				flag=randomValue;
		}
		set_position();
		pos[set_positionValue]='O';
		ifdoit=0;
	}
	public void playerCheck()
	{
		for(int h=0;h<9;h++)
		{
			if(flag==pos[h])
			{
				search=flag;
			}
		}
		if(search==flag)
			{
				search='n';
				set_position();
				if(vsCpu==1||count%2!=0)
				{
					pos[set_positionValue]='X';
				}
				else if(count%2==0)
				{
					pos[set_positionValue]='O';
				}
				if(count==1)
				{
					player_move1=flag;
				}
				player_move=flag;
				playerCheckValue=1;
			}
	}
	public void set_position()
	{
		if(flag=='a')
		set_positionValue=0;
		else if(flag=='b')
		set_positionValue=1;
		else if(flag=='c')
		set_positionValue=2;
		else if(flag=='d')
		set_positionValue=3;
		else if(flag=='e')
		set_positionValue=4;
		else if(flag=='f')
		set_positionValue=5;
		else if(flag=='g')
		set_positionValue=6;
		else if(flag=='h')
		set_positionValue=7;
		else if(flag=='i')
		set_positionValue=8;
	}
	public void checkWinner()
	{
		if(pos[0]=='X'&&pos[1]=='X'&&pos[2]=='X'||pos[3]=='X'&&pos[4]=='X'&&pos[5]=='X'||pos[6]=='X'&&pos[7]=='X'&&pos[8]=='X'||pos[0]=='X'&&pos[3]=='X'&&pos[6]=='X'||pos[1]=='X'&&pos[4]=='X'&&pos[7]=='X'||pos[2]=='X'&&pos[5]=='X'&&pos[8]=='X'||pos[0]=='X'&&pos[4]=='X'&&pos[8]=='X'||pos[2]=='X'&&pos[4]=='X'&&pos[6]=='X')
		{
			checkWinnerPlayer=1;
			l1.setText(Player1Name+": "+playerScore);
			l3.setText("Congratulations! "+Player1Name+", You win the game :).");
			checkWinnerValue=1;
		}
		else if(pos[0]=='O'&&pos[1]=='O'&&pos[2]=='O'||pos[3]=='O'&&pos[4]=='O'&&pos[5]=='O'||pos[6]=='O'&&pos[7]=='O'&&pos[8]=='O'||pos[0]=='O'&&pos[3]=='O'&&pos[6]=='O'||pos[1]=='O'&&pos[4]=='O'&&pos[7]=='O'||pos[2]=='O'&&pos[5]=='O'&&pos[8]=='O'||pos[0]=='O'&&pos[4]=='O'&&pos[8]=='O'||pos[2]=='O'&&pos[4]=='O'&&pos[6]=='O')
		{
			checkWinnerCpu=1;
			l2.setText(Player2Name+": "+cpuScore);
			if(vsCpu==1)
			{
				l3.setText("CPU win the game.");
			}
			else
			{
				l3.setText("Congratulations! "+Player2Name+", You win the game :).");
			}
			checkWinnerValue=1;
		}
		else if(count>9)
		{
			l3.setText("Game draw.");
			checkWinnerValue=1;
		}
	}
	public void dialogs()
	{
		JDialog.setDefaultLookAndFeelDecorated(true);
		d=new JDialog(this,"Message",true);
		d.setLocation(dim.width/3-d.getSize().width/3, dim.height/2-d.getSize().height/6);
		d.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		d.setSize(400,100);
		d.setLayout(new FlowLayout());
		d.add(new JLabel("Would you like to play again?"));
		d.add(button1);
		d.add(button2);
		d.setVisible(true);
	}
	public void reset()
	{
		count=1;
		playerCheckValue=0;
		set_positionValue=10;
		cpuCheckValue=0;
		ifdoit=0;
		hit=11;
		for(int h=0;h<9;h++)
		{
			pos[h]=(char)(h+97);
		}
		flag='j';
		search='n';
		player_move='j';
		player_move1='j';
		doItValue='j';
		randomValue='j';
		tempd='j';
		b1.setIcon(null);
		b2.setIcon(null);
		b3.setIcon(null);
		b4.setIcon(null);
		b5.setIcon(null);
		b6.setIcon(null);
		b7.setIcon(null);
		b8.setIcon(null);
		b9.setIcon(null);
	}
	public void registerDialog()
	{
		JDialog.setDefaultLookAndFeelDecorated(true);
		d1=new JDialog(this,"Register your name",true);
		d1.setLocation(dim.width/3-d1.getSize().width/3, dim.height/2-d1.getSize().height/6);
		d1.setSize(350,150);
		d1.setLayout(new FlowLayout());
		if(vsCpu==1)
		{
			d1.add(new JLabel("Player name:"));
			tf1.setText("PLAYER");
			d1.add(tf1);
			tf2.setText("CPU");
		}
		else
		{
			d1.add(new JLabel("Player 1 name:"));
			tf1.setText("PLAYER 1");
			d1.add(tf1);
			d1.add(new JLabel("Player 2 name:"));
			tf2.setText("PLAYER 2");
			d1.add(tf2);
		}
		Player1Name=tf1.getText();
		d1.add(button3);
		d1.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{		
		if(e.getSource()==button1)
		{
			d.setVisible(false);
			reset();
		}
		else if(e.getSource()==button2)
		{
			d.setVisible(false);
			dispose();
			System.exit(0);
		}	
		else if(e.getSource()==button3)
		{
			Player1Name=tf1.getText();
			Player2Name=tf2.getText();
			l1.setText(Player1Name+": "+playerScore);
			if(vsCpu==0)
			{
				l2.setText(Player2Name+": "+cpuScore);
			}
			d1.setVisible(false);
		}
		
		if(vsCpu==1)
		{
			if(e.getSource()==b1&&count%2!=0)
			{
				flag='a';
				playerCheck();
				if(playerCheckValue==1)
				{
					playerCheckValue=0;
					b1.setIcon(cross);
					count++;
					checkWinner();
					if(checkWinnerValue!=1)
					{
						cpuMove();
					}
				}	
			}
			else if(e.getSource()==b2&&count%2!=0)
			{
				flag='b';
				playerCheck();
				if(playerCheckValue==1)
				{
					playerCheckValue=0;
					b2.setIcon(cross);
					count++;
					checkWinner();
					if(checkWinnerValue!=1)
					{
						cpuMove();
					}
				}
			}
			else if(e.getSource()==b3&&count%2!=0)
			{
				flag='c';
				playerCheck();
				if(playerCheckValue==1)
				{
					playerCheckValue=0;
					b3.setIcon(cross);
					count++;
					checkWinner();
					if(checkWinnerValue!=1)
					{
						cpuMove();
					}
				}
			}
			else if(e.getSource()==b4&&count%2!=0)
			{
				flag='d';
				playerCheck();
				if(playerCheckValue==1)
				{
					playerCheckValue=0;
					b4.setIcon(cross);
					count++;
					checkWinner();
					if(checkWinnerValue!=1)
					{
						cpuMove();
					}
				}
			}
			else if(e.getSource()==b5&&count%2!=0)
			{
				flag='e';
				playerCheck();
				if(playerCheckValue==1)
				{
					playerCheckValue=0;
					b5.setIcon(cross);
					count++;
					checkWinner();
					if(checkWinnerValue!=1)
					{
						cpuMove();
					}	
				}
			}
			else if(e.getSource()==b6&&count%2!=0)
			{
				flag='f';
				playerCheck();
				if(playerCheckValue==1)
				{
					playerCheckValue=0;
					b6.setIcon(cross);
					count++;
					checkWinner();
					if(checkWinnerValue!=1)
					{
						cpuMove();
					}
				}
			}
			else if(e.getSource()==b7&&count%2!=0)
			{
				flag='g';
				playerCheck();
				if(playerCheckValue==1)
				{
					playerCheckValue=0;
					b7.setIcon(cross);
					count++;
					checkWinner();
					if(checkWinnerValue!=1)
					{
						cpuMove();
					}
				}
			}
			else if(e.getSource()==b8&&count%2!=0)
			{
				flag='h';
				playerCheck();
				if(playerCheckValue==1)
				{
					playerCheckValue=0;
					b8.setIcon(cross);
					count++;
					checkWinner();
					if(checkWinnerValue!=1)
					{
						cpuMove();
					}
				}
			}
			else if(e.getSource()==b9&&count%2!=0)
			{
				flag='i';
				playerCheck();
				if(playerCheckValue==1)
				{
					playerCheckValue=0;
					b9.setIcon(cross);
					count++;
					checkWinner();
					if(checkWinnerValue!=1)
					{
						cpuMove();
					}	
				}
			}
			if(set_positionValue==0&&count%2==0)
			{
				if(checkWinnerValue!=1)
				{
					b1.setIcon(criss);	
					count++;
				}
			}
			else if(set_positionValue==1&&count%2==0)
			{
				if(checkWinnerValue!=1)
				{
					b2.setIcon(criss);	
					count++;
				}
			}
			else if(set_positionValue==2&&count%2==0)
			{
				if(checkWinnerValue!=1)
				{
					b3.setIcon(criss);	
					count++;
				}
			}
			else if(set_positionValue==3&&count%2==0)
			{
				if(checkWinnerValue!=1)
				{
					b4.setIcon(criss);	
					count++;
				}
			}
			else if(set_positionValue==4&&count%2==0)
			{
				if(checkWinnerValue!=1)
				{
					b5.setIcon(criss);	
					count++;
				}
			}
			else if(set_positionValue==5&&count%2==0)
			{
				if(checkWinnerValue!=1)
				{
					b6.setIcon(criss);	
					count++;
				}
			}
			else if(set_positionValue==6&&count%2==0)
			{
				if(checkWinnerValue!=1)
				{
					b7.setIcon(criss);	
					count++;
				}
			}
			else if(set_positionValue==7&&count%2==0)
			{
				if(checkWinnerValue!=1)
				{
					b8.setIcon(criss);	
					count++;
				}
			}
			else if(set_positionValue==8&&count%2==0)
			{
				if(checkWinnerValue!=1)
				{
					b9.setIcon(criss);	
					count++;
				}
			}
		}
		else 
		{
			if(e.getSource()==b1)
			{
				flag='a';
				playerCheck();
				if(playerCheckValue==1)
				{
					playerCheckValue=0;
					if(count%2!=0)
					{
						b1.setIcon(cross);
						count++;
					}
					else
					{
						b1.setIcon(criss);
						count++;
					}
				}	
			}
			if(e.getSource()==b2)
			{
				flag='b';
				playerCheck();
				if(playerCheckValue==1)
				{
					playerCheckValue=0;
					if(count%2!=0)
					{
						b2.setIcon(cross);
						count++;
					}
					else
					{
						b2.setIcon(criss);
						count++;
					}
				}
			}
			if(e.getSource()==b3)
			{
				flag='c';
				playerCheck();
				if(playerCheckValue==1)
				{
					playerCheckValue=0;
					if(count%2!=0)
					{
						b3.setIcon(cross);
						count++;
					}
					else
					{
						b3.setIcon(criss);
						count++;
					}
				}
			}
			if(e.getSource()==b4)
			{
				flag='d';
				playerCheck();
				if(playerCheckValue==1)
				{
					playerCheckValue=0;
					if(count%2!=0)
					{
						b4.setIcon(cross);
						count++;
					}
					else
					{
						b4.setIcon(criss);
						count++;
					}
				}
			}
			if(e.getSource()==b5)
			{
				flag='e';
				playerCheck();
				if(playerCheckValue==1)
				{
					playerCheckValue=0;
					if(count%2!=0)
					{
						b5.setIcon(cross);
						count++;
					}
					else
					{
						b5.setIcon(criss);
						count++;
					}
				}
			}
			if(e.getSource()==b6)
			{
				flag='f';
				playerCheck();
				if(playerCheckValue==1)
				{
					playerCheckValue=0;
					if(count%2!=0)
					{
						b6.setIcon(cross);
						count++;
					}
					else
					{
						b6.setIcon(criss);
						count++;
					}
				}
			}
			if(e.getSource()==b7)
			{
				flag='g';
				playerCheck();
				if(playerCheckValue==1)
				{
					playerCheckValue=0;
					if(count%2!=0)
					{
						b7.setIcon(cross);
						count++;
					}
					else
					{
						b7.setIcon(criss);
						count++;
					}
				}
			}
			if(e.getSource()==b8)
			{
				flag='h';
				playerCheck();
				if(playerCheckValue==1)
				{
					playerCheckValue=0;
					if(count%2!=0)
					{
						b8.setIcon(cross);
						count++;
					}
					else
					{
						b8.setIcon(criss);
						count++;
					}
				}
			}
			if(e.getSource()==b9)
			{
				flag='i';
				playerCheck();
				if(playerCheckValue==1)
				{
					playerCheckValue=0;
					if(count%2!=0)
					{
						b9.setIcon(cross);
						count++;
					}
					else
					{
						b9.setIcon(criss);
						count++;
					}
				}
			}
		}
		if(count==1)
		{
			l3.setText("");
		}
		if(count>4)
		{
			checkWinner();
		}
		if(checkWinnerValue==1)
		{
			if(checkWinnerPlayer==1)
			{
				playerScore++;
				l1.setText(Player1Name+": "+playerScore);
				checkWinnerPlayer=0;
			}
			else if(checkWinnerCpu==1)
			{
				cpuScore++;
				l2.setText(Player2Name+": "+cpuScore);
				checkWinnerCpu=0;
			}
			checkWinnerValue=0;
			dialogs();
		}
		JMenuItem m1button = (JMenuItem) e.getSource();
		if(m1button.getText().equals("Register"))
		{
			registerDialog();
			reset();
			playerScore=0;
			cpuScore=0;
			l1.setText(Player1Name+": "+playerScore);
			l2.setText(Player2Name+": "+cpuScore);
		}
		JRadioButtonMenuItem mbutton = (JRadioButtonMenuItem) e.getSource();
		if(mbutton.getText().equals("Play against computer"))
		{
			vsCpu=1;
			reset();
			playerScore=0;
			cpuScore=0;
			tf1.setText("PLAYER");
			tf2.setText("CPU");
			Player1Name=tf1.getText();
			Player2Name=tf2.getText();
			l1.setText(Player1Name+": "+playerScore);
			l2.setText(Player2Name+": "+cpuScore);
			l4.setIcon(ttt7);
		}
		else if(mbutton.getText().equals("Play against human"))
		{
			vsCpu=0;
			reset();
			playerScore=0;
			cpuScore=0;
			tf1.setText("PLAYER 1");
			tf2.setText("PLAYER 2");
			Player1Name=tf1.getText();
			Player2Name=tf2.getText();
			l1.setText(Player1Name+": "+playerScore);
			l2.setText(Player2Name+": "+cpuScore);
			l4.setIcon(ttt2);
		}
		else if(mbutton.getText().equals("Cross"))
		{
			if(crossSwap==0)
			{
				ImageIcon newi=criss;
				criss=cross;
				cross=newi;
				crossSwap=1;
				crissSwap=0;
				reset();
			}
		}
		else if(mbutton.getText().equals("Criss"))
		{
			if(crissSwap==0)
			{
				ImageIcon newi=criss;
				criss=cross;
				cross=newi;
				crossSwap=0;
				crissSwap=1;
				reset();
			}
		}	
	}
}
class Main
{
	public static void main(String[] args)
	{
		music m=new music();
		frame f=new frame();
		f.setTitle("Tic Tac Toe Game");
		f.setSize(1000,500);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.playClip(m.sound);
	}
}

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

abstract class Loaninfo implements Serializable
{
	static ObjectOutputStream oos;
	static FileOutputStream fos;
	static
	{
		 try{
		    	fos=new FileOutputStream("D://hi.txt");
		    	oos=new ObjectOutputStream(fos);
		        }
		        catch(Exception e){}
	}
	public int loanamount,bankbalance, property, gold, age,flag=1;
	public String no,name;
	void Amount()
	{
		if(loanamount<1000)
		{
			JOptionPane optionPane = new JOptionPane("Loan Amount Exception\nLoan Denied",JOptionPane.ERROR_MESSAGE);
			JDialog dialog = optionPane.createDialog("Loan Status");
			dialog.setAlwaysOnTop(true); // to show top of all other application
			dialog.setVisible(true);
			flag=0;
		}
	}
	void assetinfo()
	{
		//checks for adequate assets
			if(bankbalance<30000||property<5||gold<5)
			{
			JOptionPane optionPane = new JOptionPane("Asset Exception\nLoan Denied",JOptionPane.ERROR_MESSAGE);
			JDialog dialog = optionPane.createDialog("Loan Status");
			dialog.setAlwaysOnTop(true); // to show top of all other application
			dialog.setVisible(true);
			flag=0;
			}
	}
	void personalinfo()
	{
			int x=no.length();
			if(x!=10)
			{
			JOptionPane optionPane = new JOptionPane("Phone Number Exception\nLoan Denied",JOptionPane.ERROR_MESSAGE);
			JDialog dialog = optionPane.createDialog("Loan Status");
			dialog.setAlwaysOnTop(true); // to show top of all other application
			dialog.setVisible(true);
			flag=0;
			}
	
		//checks age
			
			if(age<18||age>60)
			{
				JOptionPane optionPane = new JOptionPane("Age Exception\nLoan Denied",JOptionPane.ERROR_MESSAGE);
				JDialog dialog = optionPane.createDialog("Loan Status");
				dialog.setAlwaysOnTop(true); // to show top of all other application
				dialog.setVisible(true);
				flag=0;
			}
	}
	public String toString()
	{
		return (getClass().getName() + '@' + Integer.toHexString(hashCode())+
				":\n\tName:"+name+"\n\tLoanamount:"+loanamount+"\n\tBank Balanace:"+bankbalance+"\n\tProperty:"+property+
				"\n\tGold:"+gold+"\n\tAge:"+age+"\n------------------------");
	}
	abstract boolean proofRequired();	
	abstract void dostuff(Loaninfo r);//for calling finance and bank manager threads
}
class Bikeloan extends Loaninfo
{
	public Bikeloan(){}
	boolean proofRequired()
	{
		return true;
	}
	public void dostuff(Loaninfo r)
		{
			Financemanager fm=new Financemanager();
			Bankmanager bm=new Bankmanager();
			fm.test(this);
			bm.test(this);
			if(fm.check&&bm.check)
			{
				if(flag==1)
				{
					JOptionPane optionPane = new JOptionPane("Loan Approved!",JOptionPane.PLAIN_MESSAGE);
					JDialog dialog = optionPane.createDialog("Loan Status");
					dialog.setAlwaysOnTop(true); // to show top of all other application
					dialog.setVisible(true);
					try{oos.writeObject(r);}catch(Exception e){}
				}
			}
		}
}
class Carloan extends Loaninfo
{
	public Carloan(){}
	boolean proofRequired()
	{
		return true;
	}
	public void dostuff(Loaninfo r)
		{
			Financemanager fm=new Financemanager();
			Bankmanager bm=new Bankmanager();
			fm.test(this);
			bm.test(this);
			if(fm.check&&bm.check)
			{
				if(flag==1)
				{
					JOptionPane optionPane = new JOptionPane("Loan Approved!",JOptionPane.PLAIN_MESSAGE);
					JDialog dialog = optionPane.createDialog("Loan Status");
					dialog.setAlwaysOnTop(true); // to show top of all other application
					dialog.setVisible(true);
					try{oos.writeObject(r);}catch(Exception e){}
				}
			}
		}
	}
	
	
class Houseloan extends Loaninfo
{
	public Houseloan(){}
	boolean proofRequired()
	{
		return true;
	}
	public void dostuff(Loaninfo r)
		{
			Financemanager fm=new Financemanager();
			Bankmanager bm=new Bankmanager();
			fm.test(this);
			bm.test(this);
			if(fm.check&&bm.check)
			{
				if(flag==1)
				{
					JOptionPane optionPane = new JOptionPane("Loan Approved!",JOptionPane.PLAIN_MESSAGE);
					JDialog dialog = optionPane.createDialog("Loan Status");
					dialog.setAlwaysOnTop(true); // to show top of all other application
					dialog.setVisible(true); 
					try{oos.writeObject(r);}catch(Exception e){}
 	           }
			}
		}
}
	
class Financemanager implements Runnable
{
	boolean check=false;
	Loaninfo b;
	void test(Loaninfo b)
	{
		Thread tf=new Thread(this,"fm");
		this.b=b;
		tf.start();
		try//making main wait
		{	
			tf.join();
		}
		catch(InterruptedException e)
		{
		}
		
	}
	public void run()
	{	
		if(b.proofRequired())
		{
			check=true;
		}
	}
}
class Bankmanager implements Runnable
{
	boolean check=false;
	Loaninfo b;
	void test(Loaninfo b)
	{
		Thread tb=new Thread(this,"bm");
		this.b=b;
		tb.start();
		try//making main wait
		{	
			tb.join();
		}
		catch(InterruptedException e)
		{
		}

	}
	public void run()
	{
		if(b.proofRequired())
		{
			check=true;
		}
	}
}


public class Japp extends JFrame implements WindowListener
{
    public Japp() 
    {
    	JLabel j=new JLabel("                                     Welcome!Choose your loan");	
    	add(j);
        setTitle("Loan");
        setSize(400, 500);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {

        	public void windowClosing(WindowEvent e)
            {
            	try{
            		FileInputStream fis=new FileInputStream("d://hi.txt");
		       		ObjectInputStream ois=new ObjectInputStream(fis);
		       		Loaninfo k=(Loaninfo)ois.readObject();
		       		while(k!=null)
		       			{
		       				System.out.println(k);
		       				k=(Loaninfo)ois.readObject();
		       			}
		       		ois.close();
		       			
		       		}
            	catch(Exception q){}
            	dispose();
            	System.exit(0);
            }
        });
        JMenuBar mbar = new JMenuBar();
        JMenu loantype= new JMenu("Loan Type");
        JMenuItem m1= new JMenuItem("Bikeloan");
        JMenuItem m2= new JMenuItem("Carloan");
        JMenuItem m3= new JMenuItem("Houseloan");
        loantype.add(m1); loantype.add(m2); loantype.add(m3);
        mbar.add(loantype);
        setJMenuBar(mbar);
        m1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	Info i=new Info("Bikeloan");
            	i.setTitle("Bikeloan");
            }
        });
        m2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	Info i=new Info("Carloan");
            	i.setTitle("Carloan");
            }
        });
        m3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	Info i=new Info("Houseloan");
            	i.setTitle("Houseloan");
            }
        });
    }
    public static void main(String[] args)
    {

        SwingUtilities.invokeLater(new Runnable()
							        {
							            public void run()
							            {
							                Japp ex = new Japp();
							                ex.setVisible(true);
							            }
							        }
        							);
    }
}
class Info extends JFrame implements ActionListener
{
	JTextField t1,t2,t3,t4,t5,t6,t7; JPanel myPanel;
	JButton b;
	JComboBox cb,cb1;
	String s;
    	    public Info(String s)
    	    {
    	    	this.s=s;
    	    	 myPanel = new JPanel();
     	        add(myPanel);
     	        myPanel.setLayout(new GridLayout(10,2));
     	        
    	    	JLabel name = new JLabel("Name");
    	    	myPanel.add(name);
    	    	t1 = new JTextField();
      	        myPanel.add(t1);
      	        
    	    	JLabel loanamount = new JLabel("Loanamount");
    	    	myPanel.add(loanamount);
    	    	t2 = new JTextField();
       	        myPanel.add(t2);
       	        
       	        JLabel bal = new JLabel("Bank Balance");
       	        myPanel.add(bal);
 	    		t6 = new JTextField();
    	        myPanel.add(t6);
    	        
    	        JLabel property = new JLabel("Property");
    	    	myPanel.add(property);
    	    	t4 = new JTextField();
       	        myPanel.add(t4);
       	     
    	    	JLabel gold = new JLabel("Gold");
    	    	myPanel.add(gold);
    	    	t5 = new JTextField();
       	        myPanel.add(t5);
       	        
    	    	JLabel age = new JLabel("Age");
    	    	myPanel.add(age);
    	    	t3 = new JTextField();
       	        myPanel.add(t3);
       	        
       	        JLabel no = new JLabel("Phone no");
       	     	myPanel.add(no);
 	    		t7 = new JTextField();
    	        myPanel.add(t7);
    	        
       	        JLabel p=new JLabel("Proofs");
       	        myPanel.add(p);
       	        String proofs[]={"Id","Bank statements","Income"};
       	        cb=new JComboBox(proofs);
       	        myPanel.add(cb);
       	        
       	        JLabel p1=new JLabel("Assets");
    	        myPanel.add(p1);
    	        String asset[]={"House proof","Account proof"};
    	        cb1=new JComboBox(asset);
    	        myPanel.add(cb1);
       	        
       	        b=new JButton("Apply Loan");
       	        myPanel.add(b);
       	        b.addActionListener(this);
    	        setSize(350, 450);
    	        setVisible(true);
    	        setLocationRelativeTo(null);
    	    }

    	    public void actionPerformed(ActionEvent e)
    	    {
    	            
    	           try{
    	    
    	        	Class y=Class.forName(s);
    				Loaninfo r=(Loaninfo) y.newInstance();
    				r.name=t1.getText();
    				r.loanamount=Integer.parseInt(t2.getText());
     	            r.bankbalance=Integer.parseInt(t6.getText());
     	            r.property=Integer.parseInt(t4.getText());
     	            r.gold=Integer.parseInt(t5.getText());
     	            r.age=Integer.parseInt(t3.getText());
     	            r.no=t7.getText();
     	            r.Amount();
    				r.assetinfo();
    				r.personalinfo();
    				r.dostuff(r);
    	           }
    	           catch(Exception z)
    	           {
    	        	   System.out.println(z.toString());
    	        	   JOptionPane optionPane = new JOptionPane("All data not entered",JOptionPane.WARNING_MESSAGE);
        	           JDialog dialog = optionPane.createDialog("Warning!");
        	           dialog.setAlwaysOnTop(true); // to show top of all other application
        	           dialog.setVisible(true); 
    	           }  
    
    	    }
    	}
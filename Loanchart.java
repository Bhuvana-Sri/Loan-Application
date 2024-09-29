import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

abstract class Loaninfo implements Comparable<Loaninfo>
{
	public int loanamount,bankbalance, property, gold, age;
	public String no;
	public int compareTo(Loaninfo o)
	{
		if(this.loanamount>o.loanamount)
			return 1;
		else if(this.loanamount<o.loanamount)
			return -1;
		else return 0;
	}
}

class Amountcomp implements Comparator<Loaninfo>
{
    public int compare(Loaninfo  obj1, Loaninfo obj2)
	{
        return Integer.compare(obj2.loanamount,obj1.loanamount);
    }
}
class Agecomp implements Comparator<Loaninfo>
{
    public int compare(Loaninfo  obj1, Loaninfo obj2)
	{
        return Integer.compare(obj1.age,obj2.age);
    }
}
class Balancecomp implements Comparator<Loaninfo>
{
    public int compare(Loaninfo  obj1, Loaninfo obj2)
	{
        return Integer.compare(obj2.bankbalance,obj1.bankbalance);
    }
}


class Bikeloan extends Loaninfo
{	
	Bikeloan(int l,int b,int p,int g,int a,String n)
	{
		loanamount=l;bankbalance=b;property=p;gold=g;age=a;no=n;
	}
	
}
class Carloan extends Loaninfo
	{
	Carloan(int l,int b,int p,int g,int a,String n)
	{
		loanamount=l;bankbalance=b;property=p;gold=g;age=a;no=n;
	}
	
	}
	
	
class Houseloan extends Loaninfo
	{
	
	Houseloan(int l,int b,int p,int g,int a,String n)
	{
		loanamount=l;bankbalance=b;property=p;gold=g;age=a;no=n;
	}
	
	}
	

public class Loan extends Applet implements ItemListener
{
	Choice c;
	Label u;
	List<Loaninfo>lm;
	public int a[]=new int[3];
	public int sum=1,w=50,i=0;
        public void init()
        {
                c=new Choice();
                Panel p1=new Panel();
                u=new Label("Sort by");
                p1.add(u);
                c.addItem("loanamount");c.addItem("age");c.addItem("bankbalance");
                p1.add(c);
                c.addItemListener(this);
                add(p1,BorderLayout.NORTH);
                lm=new ArrayList<Loaninfo>();
               
    			Bikeloan b=new Bikeloan(50000,50000,40000,90,45,"8885909841");
    			Carloan c=new Carloan(20000,60000,60000,60,40,"8885906841");
    			Houseloan h=new Houseloan(30000,30500,4000,70,35,"8885905841");
    				lm.add(b);lm.add(c);lm.add(h);
        }
        public void itemStateChanged(ItemEvent e)
        {
        	
        	if(e.getItem()==   "loanamount")
        		{
        			Collections.sort(lm,new Amountcomp());
        			showStatus("Sorting on loanamounts");
        			System.out.println("\nProcessing according to loanamounts");
              
        		}
        		else if(e.getItem()=="age")
        		{
        			Collections.sort(lm,new Agecomp());
        			showStatus("Sorting by age");
        			System.out.println("\nProcessing according to age");
              
        		}
        		else if(e.getItem()=="bankbalance")
        		{
        			Collections.sort(lm,new Balancecomp());
        			showStatus("Sorting by bankbalance");
        			System.out.println("\nProcessing according to bankbalance");
              
        		}
        	Iterator u=lm.iterator();
    		while(u.hasNext())
    		{
    			Loaninfo k=(Loaninfo)u.next();
    			sum=sum+k.loanamount;
    			a[i]=k.loanamount;i++;
    			System.out.println("Processing loan: "+k.toString()+"  Loanamount:"+k.loanamount+" loan granted");
    		}
        	repaint();
        }
        public void paint(Graphics g)
        {
        	int x=10,y=310,t;
        	g.drawLine(10, 310, 310, 310);
        	g.drawLine(10,110,10,310);
        	setForeground(Color.gray);
        	for(int m=0;m<3;m++)
        	{
        		x=x+50;
        		t=((a[m]*200)/sum);
        		g.fillRect(x,y-t , w,t);
        		x=x+10;
        	}
        	if(i==3){i=i-3;sum=0;}
        }
}


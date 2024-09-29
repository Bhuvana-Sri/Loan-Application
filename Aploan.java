import pck.AssetException;
import pck.InvalidphoneException;
import pck.AgeException;
import pck.ITException;
import java.util.*;

abstract class Loaninfo implements Comparable<Loaninfo>
{
	public int loanamount,bankbalance, property, gold, age;
	public String no;
	
	void assetinfo()
	{
		//checks for adequate assets
		try
		{	
			if(bankbalance<=30000||property<5||gold<500)
			{
				throw new AssetException();
			}
		}
		catch(AssetException e)
		{
			System.out.println(e+"\nloan denied");
			System.exit(0);
		}
	}
	void personalinfo()
	{
		//checks phone number
		try
		{
			int x=no.length();
			if(x!=10)
			{
				throw new InvalidphoneException();
			}
		}
		catch(InvalidphoneException e)
		{
			System.out.println(e+"\nloan denied");
			System.exit(0);
		} 
		//checks age
		try
		{		
			if(age<18&&age>60)
			{
				throw new AgeException();
			}
		}
		catch(AgeException e)
		{
			System.out.println(e+"\nloan denied");
			
		}
	}
	abstract boolean proofRequired();	
	abstract void dostuff();//for calling finance and bank manager threads
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
	
	boolean proofRequired()
	{
		System.out.println("Proofs for bikeloan verified");
		return true;
	}
	public void dostuff()
		{
			Financemanager fm=new Financemanager();
			Bankmanager bm=new Bankmanager();
			fm.test(this);
			bm.test(this);
			if(fm.check&&bm.check)
			{
				
				System.out.println("	Loan approved!!\n");
			}
		}
}
class Carloan extends Loaninfo
	{
		
	boolean proofRequired()
	{
		System.out.println("Proofs for carloan verified");
		return true;
	}
	public void dostuff()
		{
			Financemanager fm=new Financemanager();
			Bankmanager bm=new Bankmanager();
			fm.test(this);
			bm.test(this);
			if(fm.check&&bm.check)
			{
				System.out.println("	Loan approved!!\n");
			}
		}
	}
	
	
class Houseloan extends Loaninfo
	{
	
	boolean proofRequired()
	{
		System.out.println("Proofs for houseloan verified");
		return true;
	}
	public void dostuff()
		{
			Financemanager fm=new Financemanager();
			Bankmanager bm=new Bankmanager();
			fm.test(this);
			bm.test(this);
			if(fm.check&&bm.check)
			{
				System.out.println("	Loan approved!!\n-----------------------------------");
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
			System.out.println("join is interrupted");
		}
		
	}
	public void run()
	{	
		if(b.proofRequired())
		{
			System.out.println("        by financemanager");
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
			System.out.println("join is interrupted");
		}

	}
	public void run()
	{
		if(b.proofRequired())
		{
			System.out.println("        by bankmanager");
			check=true;
		}
	}
}

class Bank
{
	public static void main(String args[])throws Exception
	{
		List<Loaninfo>lm=new ArrayList<Loaninfo>();
		Scanner s=new Scanner(System.in);
		String choice;
		//takes into account all waiting customers
		do{
				System.out.println("Welcome! Enter the type of loan\nBikeloan, Carloan or Houseloan");
				String x=s.next();
				Class y=Class.forName(x);
				Loaninfo r=(Loaninfo) y.newInstance();
				System.out.println("\nEnter loanamount,bankbalance,property,gold");
				r.loanamount=s.nextInt();r.bankbalance=s.nextInt();r.property=s.nextInt();r.gold=s.nextInt();
				r.assetinfo();
				System.out.println("entre age and phone no");
				r.age=s.nextInt();r.no=s.next();
				r.personalinfo();
				lm.add(r);
				System.out.println("Next customer?(y/n):");
				choice=s.next();
		}while ((choice.equals("y"))||(choice.equals("Y")) );
		
	System.out.println("Enter creiteria of search:loanamount,age or bankbalance");
	String ch=s.next();
	if(ch.equals("loanamount"))
		{
			Collections.sort(lm,new Amountcomp());
		}
		else if(ch.equals("age"))
		{
			Collections.sort(lm,new Agecomp());
		}
		else if(ch.equals("bankbalance"))
		{
			Collections.sort(lm,new Balancecomp());
		}
		
		Iterator u=lm.iterator();
		while(u.hasNext())
		{
			Loaninfo k=(Loaninfo)u.next();
			System.out.println("Processing loan: "+k.toString()+"\n");
			k.dostuff();
		}
	s.close();
}
}

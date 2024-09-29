import pck.AssetException;
import pck.InvalidphoneException;
import pck.AgeException;
import pck.ITException;
import java.util.*;

abstract class Loaninfo
{
	void assetinfo(int bankbalance,int property,int gold)
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
	void personalinfo(int age,String no)
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
				System.out.println("	Loan approved!!\n");
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
			System.out.println("by financemanager");
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
			System.out.println("by bankmanager");
			check=true;
		}
	}
}

class Validate
{
	public static void main(String args[])throws Exception
	{
	Queue q=new LinkedList();
	Scanner s=new Scanner(System.in);String enter;
	//takes into account all waiting customers
	do{
		System.out.println("Welcome! Enter the type of loan\nBikeloan, Carloan or Houseloan");
		Class y=Class.forName(s.next());
		Loaninfo r=(Loaninfo) y.newInstance();
		q.offer(r);
		System.out.println("Next customer?(y/n):");
		enter=s.next();
	}while(enter.equals("y"));
	//starts validating their data
	while(q.peek()!=null)
	{
	
	    Loaninfo o=(Loaninfo)q.poll();
		System.out.println(o.toString()+"\nEnter bankbalance,property,gold:");
		o.assetinfo(s.nextInt(),s.nextInt(),s.nextInt());
		System.out.println("enter age and phone no:");
		o.personalinfo(s.nextInt(),s.next());
		o.dostuff();
	}
	s.close();
	}
}
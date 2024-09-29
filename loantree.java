import pck.AssetException;
import pck.InvalidphoneException;
import pck.AgeException;
import pck.ITException;
import java.util.*;

abstract class Loaninfo
{
	public int loanamount;
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
class MyComp implements Comparator<Integer>
{
    public int compare(Integer  obj1, Integer obj2)
	{
        return Integer.compare(obj2,obj1);
    }
}

class Tree
{
	public static void main(String args[])throws Exception
	{
		LinkedHashMap<String,Loaninfo>lm=new LinkedHashMap<String,Loaninfo>();
		Scanner s=new Scanner(System.in);
		String enter;
	//takes into account all waiting customers
	do{
		System.out.println("Welcome! Enter the type of loan\nBikeloan, Carloan or Houseloan");
		String x=s.next();
		Class y=Class.forName(x);
		Loaninfo r=(Loaninfo) y.newInstance();
		lm.put(x,r);
		System.out.println("Next customer?(y/n):");
		enter=s.next();
	}while( ((enter.equals("y"))||(enter.equals("Y")) ));
		
	System.out.println("Enter creiteria of search");
	s.next();
	Set<Map.Entry<String,Loaninfo>> set=lm.entrySet();
	TreeSet<Integer> tree=new TreeSet<Integer>(new MyComp());
	for(Map.Entry<String,Loaninfo> m:set)
	{
		System.out.println("Enter loan amount for"+m.getValue().toString()+":");
		int a=s.nextInt();
		(m.getValue()).loanamount=a;
		tree.add(a);
	}
	Iterator<Integer> iterator = tree.iterator();
		while (iterator.hasNext())
		{
			int p=iterator.next();
			for(Map.Entry<String,Loaninfo> m:set)
			{
				if(p==(m.getValue()).loanamount)
				{
				Loaninfo o=(Loaninfo)m.getValue();
				System.out.println(o.toString()+"\nEnter bankbalance,property,gold:");
				o.assetinfo(s.nextInt(),s.nextInt(),s.nextInt());
				System.out.println("enter age and phone no:");
				o.personalinfo(s.nextInt(),s.next());
				o.dostuff();
				}
			}
		}
	s.close();

	}
}

import pck.AssetException;
import pck.InvalidphoneException;
import pck.AgeException;
import pck.ITException;



class Financemanager implements Runnable
{
	boolean check=false;
	Bikeloan b;
	void test(Bikeloan b)
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
			check=true;
		}
	}
}
class Bankmanager implements Runnable
{
	boolean check=false;
	Bikeloan b;
	void test(Bikeloan b)
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
			check=true;
		}
	}
}


class Bikeloan extends Thread 
	{
	void assetinfo(int bankbalance,int property,int gold)
	{
		try
		{	
			if(bankbalance<=30000||property<5||gold<500)
			{
				throw new AssetException();
			}
		}
		catch(AssetException e)
		{
			System.out.println(e);
		}
	}
	void personalinfo(int age,String no)
	{
		try
		{
			int c=no.length();
			if(c!=10)
			{
				throw new InvalidphoneException();
			}
		}
		catch(InvalidphoneException e)
		{
			System.out.println(e);
		} 
		try
		{
			if(age<18)
			{
				throw new AgeException();
			}
		}
		catch(AgeException e)
		{
			System.out.println(e);
		}
	}
	boolean proofRequired()
	{
		assetinfo(50000,6,700);
		personalinfo(25,"8885909841");
		return true;
	}
		Financemanager fm=new Financemanager();
		Bankmanager bm=new Bankmanager();
		Bikeloan()
		{
			Thread t=new Thread(this,"t");
			fm.test(this);
			bm.test(this);
			if(fm.check&&bm.check)
			{
				System.out.println("loan approved");
			}
		}
		
		public static void main(String args[])
		{	
			Bikeloan b=new Bikeloan();
		}
		
	}
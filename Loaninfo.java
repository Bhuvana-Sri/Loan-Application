import pck.AssetException;
import pck.InvalidphoneException;
import pck.AgeException;
import pck.ITException;


abstract class Loaninfo
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
			if(c!=9)
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
	abstract void proofRequired(boolean It);	
}

class Bikeloan extends Loaninfo
	{
		void proofRequired(boolean It)
		{
			
			try
			{
				if(!It)
				{
					throw new ITException();
				}	
			}
			catch(ITException e)
			{
				System.out.println(e);
			}
		}
	}
	
	
class Carloan extends Loaninfo
	{
		void proofRequired(boolean It)
		{
			
			try
			{
				if(!It)
				{
					throw new ITException();
				}	
			}
			catch(ITException e)
			{
				System.out.println(e);
			}
		}
	}
	
	
class Houseloan extends Loaninfo
	{
		void proofRequired(boolean It)
		{
			
			try
			{
				if(!It)
				{
					throw new ITException();
				}	
			}
			catch(ITException e)
			{
				System.out.println(e);
			}
		}
	}
	
	
class Personalloan extends Loaninfo
	{
		void proofRequired(boolean It)
		{
			
			try
			{
				if(!It)
				{
					throw new ITException();
				}	
			}
			catch(ITException e)
			{
				System.out.println(e);
			}
		}
	}
	
	
class Demo
{
	public static void main(String args[])throws Exception
	{
		Class y=Class.forName(args[0]);
		Loaninfo r=(Loaninfo) y.newInstance();
		r.assetinfo(50000,6,700);
		r.personalinfo(25,args[1]);
		r.proofRequired(true);
	}
}


public class Day implements Cloneable {

	private int year;
	private int month;
	private int day;
	private static final String MonthNames = "JanFebMarAprMayJunJulAugSepOctNovDec";


	// Constructor
	public Day(int y, int m, int d) {
		this.year = y;
		this.month = m;
		this.day = d;
	}

	// Constructor, simply call set(sDay)
	public Day(String sDay) {
		set(sDay);
	}

	// Set year,month,day based on a string like 01-Mar-2020
	public void set(String sDay) {
		String[] sDayParts = sDay.split("-");
		this.day = Integer.parseInt(sDayParts[0]); // Apply Integer.parseInt for sDayParts[0];
		this.year = Integer.parseInt(sDayParts[2]);
		this.month = MonthNames.indexOf(sDayParts[1]) / 3 + 1;
	}

	@Override
	public String toString() {
		return day + "-" + MonthNames.substring((month - 1) * 3, (month) * 3) + "-" + year; // (month-1)*3,(month)*3
	}

	// check if a given year is a leap year
	static public boolean isLeapYear(int y) {
		if (y % 400 == 0)
			return true;
		else if (y % 100 == 0)
			return false;
		else if (y % 4 == 0)
			return true;
		else
			return false;
	}

	@Override
	public Day clone() {
		Day copy = null;
		try {
			copy = (Day) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return copy;
	}
	
	// check if y,m,d valid
	static public boolean valid(int y, int m, int d)
	{
		if (m<1 || m>12 || d<1) return false;
		switch(m){
			case 1: case 3: case 5: case 7:
			case 8: case 10: case 12:
					 return d<=31; 
			case 4: case 6: case 9: case 11:
					 return d<=30; 
			case 2:
					 if (isLeapYear(y))
						 return d<=29; 
					 else
						 return d<=28; 
		}
		return false;
	}

    public boolean isEndOfAMonth(Day d) {
        if (valid(d.getYear(), d.getMonth(), d.getDay() + 1)) 
            return false;
        else
            return true;
    }

	public Day next(Day d) 
	{
		int month = d.getMonth();
		int year = d.getYear();
		int day = d.getDay();
        if (isEndOfAMonth(d))
            if (month == 12)
                return new Day(year + 1, 1, 1); 
            else
                return new Day(year, month + 1, 1);
        else
            return new Day(year, month, day + 1);
    }

	public int getDay()
	{
		return this.day;
	}

	public int getMonth()
	{
		return this.month;
	}

	public int getYear()
	{
		return this.year;
	}

	public Boolean checkMonth(String s)
	{
		Boolean flag = false;
		String monname = "Jan-Feb-Mar-Apr-May-Jun-Jul-Aug-Sep-Oct-Nov-Dec";
		String[] names = monname.split("-");

		for(int i=0; i<names.length; i++)
		{
			if(s.equals(names[i]))
				flag = true;
		}

		return flag;
	}
	public boolean isStartofMonth(Day d) {
        if(valid(d.getYear(), d.getMonth(), d.getDay()-1))
            return false;
        else 
            return true;
    }

	public Day previous(Day d) 
	{
        if (isStartofMonth(d)) {
            if (d.getMonth() == 1)
                return new Day(d.getYear() - 1, 12, 31);
            else if (isLeapYear(d.getYear())&&d.getMonth()==3)
                return new Day(d.getYear(), 2, 29);
            else if (d.getMonth()==3)
                return new Day(d.getYear(), 2, 28);
            else if(d.getMonth()==2||d.getMonth()==4||d.getMonth()==6||d.getMonth()==9||d.getMonth()==11)
                return new Day(d.getYear(), d.getMonth()-1, 31);
            else
                return new Day(d.getYear(), d.getMonth()-1, 30);
        }
        else
            return new Day(d.getYear(), d.getMonth(), d.getDay()-1);
    }


	public Day getEndDay(Day d, int n)
	{	
		
		int newmonth = 0;
		int newday = 0;
		int newyear = d.getYear();

		int sum = d.getDay();
		Boolean leapyear = isLeapYear(d.getYear());

		if(d.getMonth()==2)
		{
			sum += 31;
		}

		if(leapyear)
		{
			if(d.getMonth()==3) 
			{
				sum += 60;
			}

			else if(d.getMonth()==4)
			{
				sum += 91;
			}

			else if(d.getMonth()==5)
			{
				sum += 121;
			}

			else if(d.getMonth()==6)
			{
				sum += 152;
			}

			else if(d.getMonth()==7)
			{
				sum += 182;
			}

			else if(d.getMonth()==8)
			{
				sum += 213;
			}

			else if(d.getMonth()==9)
			{
				sum += 244;
			}

			else if(d.getMonth()==10)
			{
				sum += 274;
			}

			else if(d.getMonth()==11)
			{
				sum += 305;
			}

			else if(d.getMonth()==12)
			{
				sum += 335;
			}
		}

		else
		{
			if(d.getMonth()==3) 
			{
				sum += 59;
			}

			else if(d.getMonth()==4)
			{
				sum += 90;
			}

			else if(d.getMonth()==5)
			{
				sum += 120;
			}

			else if(d.getMonth()==6)
			{
				sum += 151;
			}

			else if(d.getMonth()==7)
			{
				sum += 181;
			}

			else if(d.getMonth()==8)
			{
				sum += 212;
			}

			else if(d.getMonth()==9)
			{
				sum += 243;
			}

			else if(d.getMonth()==10)
			{
				sum += 273;
			}

			else if(d.getMonth()==11)
			{
				sum += 304;
			}

			else if(d.getMonth()==12)
			{
				sum += 334;
			}
		}

		int nextd = sum + n - 1;

		if(nextd/365>=1)
		{
			newyear = nextd/365;
		}

		nextd = nextd%365;


		if(nextd == 0)
		{
			newmonth = 12;
			newday = 1;
		}

		else if(nextd <= 31 && nextd!=0)
		{
			newmonth = 1;
			newday = nextd;
		}

		if(leapyear)
		{
			if(nextd <= 60)
			{
				newmonth = 2;
				newday = (nextd - 31);
			}
			else if(nextd <= 91)
			{
				newmonth = 3;
				newday = (nextd - 60);
			}

			else if(nextd <= 121)
			{
				newmonth = 4;
				newday = (nextd - 91);
			}
			
			else if(nextd <= 152)
			{
				newmonth = 5;
				newday = (nextd - 121);
			}

			else if(nextd <= 182)
			{
				newmonth = 6;
				newday = (nextd - 152);
			}

			else if(nextd <= 213)
			{
				newmonth = 7;
				newday = (nextd - 182);
			}
		
			else if(nextd <= 244)
			{
				newmonth = 8;
				newday = (nextd - 213);
			}

			else if(nextd <= 274)
			{
				newmonth = 9;
				newday = (nextd - 244);
			}

			else if(nextd <= 305)
			{
				newmonth = 10;
				newday = (nextd - 274);
			}

			else if(nextd <= 335)
			{
				newmonth = 11;
				newday = (nextd - 305);
			}

			else if(nextd <= 365)
			{
				newmonth = 12;
				newday = (nextd - 335);
			}
		}

		else
		{
			if(nextd <= 59)
			{
				newmonth = 2;
				newday = (nextd - 31);
			}
			else if(nextd <= 90)
			{
				newmonth = 3;
				newday = (nextd - 59);
			}

			else if(nextd <= 120)
			{
				newmonth = 4;
				newday = (nextd - 90);
			}
			
			else if(nextd <= 151)
			{
				newmonth = 5;
				newday = (nextd - 120);
			}

			else if(nextd <= 181)
			{
				newmonth = 6;
				newday = (nextd - 151);
			}

			else if(nextd <= 212)
			{
				newmonth = 7;
				newday = (nextd - 181);
			}
		
			else if(nextd <= 243)
			{
				newmonth = 8;
				newday = (nextd - 212);
			}

			else if(nextd <= 273)
			{
				newmonth = 9;
				newday = (nextd - 243);
			}

			else if(nextd <= 304)
			{
				newmonth = 10;
				newday = (nextd - 273);
			}

			else if(nextd <= 334)
			{
				newmonth = 11;
				newday = (nextd - 304);
			}

			else if(nextd <= 365)
			{
				newmonth = 12;
				newday = (nextd - 335);
			}
		}

		Day newDay = new Day(newyear, newmonth, newday);

		return newDay;
	}

	public int getLen(Day d)
	{
		int sum = d.getDay();
		Boolean leapyear = isLeapYear(d.getYear());
		if(d.getMonth()==2)
		{
			sum += 31;
		}

		if(leapyear)
		{
			if(d.getMonth()==3) 
			{
				sum += 60;
			}

			else if(d.getMonth()==4)
			{
				sum += 91;
			}

			else if(d.getMonth()==5)
			{
				sum += 121;
			}

			else if(d.getMonth()==6)
			{
				sum += 152;
			}

			else if(d.getMonth()==7)
			{
				sum += 182;
			}

			else if(d.getMonth()==8)
			{
				sum += 213;
			}

			else if(d.getMonth()==9)
			{
				sum += 244;
			}

			else if(d.getMonth()==10)
			{
				sum += 274;
			}

			else if(d.getMonth()==11)
			{
				sum += 305;
			}

			else if(d.getMonth()==12)
			{
				sum += 335;
			}
		}

		else
		{
			if(d.getMonth()==3) 
			{
				sum += 59;
			}

			else if(d.getMonth()==4)
			{
				sum += 90;
			}

			else if(d.getMonth()==5)
			{
				sum += 120;
			}

			else if(d.getMonth()==6)
			{
				sum += 151;
			}

			else if(d.getMonth()==7)
			{
				sum += 181;
			}

			else if(d.getMonth()==8)
			{
				sum += 212;
			}

			else if(d.getMonth()==9)
			{
				sum += 243;
			}

			else if(d.getMonth()==10)
			{
				sum += 273;
			}

			else if(d.getMonth()==11)
			{
				sum += 304;
			}

			else if(d.getMonth()==12)
			{
				sum += 334;
			}
		}

		return sum;
	}

	public double checklen(Day d)
	{
		double year = d.getYear();
		double month = d.getMonth();
		double day = d.getDay();
		double sum = 0;
		sum = sum + year*100;
		sum = sum + month;
		sum = sum + day/100;
		return sum;
	}

}

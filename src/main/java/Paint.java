
    // Making it comparable is a simple way of allowing it to be stored in TreeSet and TreeMap
	public class Paint implements Comparable<Paint>
	{
		private double liters;
		private int red;
		private int green;
		private int blue;
	
		public Paint(double d, int red, int green, int blue)
		{
			this.liters = d;
			this.red = red;
			this.green = green;
			this.blue = blue;
		}

		// Note that because default equals is not consistent with this method, TreeMap<Paint>/TreeSet<Paint> breaks
		// the contract for Set/Map in that it considers Paint x and Paint y equal so long as
		// 0==x.compareTo(y), regardless of whether x.equals(y)
		public int compareTo(Paint other) { return Double.compare(liters, other.liters);
		}
	}


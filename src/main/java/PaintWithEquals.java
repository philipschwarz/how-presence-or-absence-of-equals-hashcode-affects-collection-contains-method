import org.apache.commons.lang.builder.EqualsBuilder;

	// Making it comparable is a simple way of allowing it to be stored in TreeSet and TreeMap
	public class PaintWithEquals implements Comparable<PaintWithEquals>
	{
		private double liters;
		private int red;
		private int green;
		private int blue;
	
		public PaintWithEquals(double liters, int red, int green, int blue)
		{
			this.liters = liters;
			this.red = red;
			this.green = green;
			this.blue = blue;
		}
		
		@Override
		public boolean equals(Object obj)
		{
		   if (obj == null) { return false; }
		   if (obj == this) { return true; }
		   if (obj.getClass() != getClass()) { return false; }
		   PaintWithEquals other = (PaintWithEquals) obj;
		   return new EqualsBuilder()
						.append(liters,other.liters)
						.append(red,other.red)
						.append(green,other.green)
						.append(blue,other.blue)
						.isEquals();
		}
		
		public int compareTo(PaintWithEquals other)
		{
			return Double.compare(liters, other.liters); 
		}	
	}


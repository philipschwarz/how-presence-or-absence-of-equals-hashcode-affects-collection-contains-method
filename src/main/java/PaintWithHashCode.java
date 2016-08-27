import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

	// Making it comparable is a simple way of allowing it to be stored in TreeSet and TreeMap
	public class PaintWithHashCode implements Comparable<PaintWithHashCode>
	{
		private double liters;
		private int red;
		private int green;
		private int blue;
	
		public PaintWithHashCode(double liters, int red, int green, int blue)
		{
			this.liters = liters;
			this.red = red;
			this.green = green;
			this.blue = blue;
		}
		
		@Override public int hashCode()
		{
			return new HashCodeBuilder(17, 37)
						.append(liters)
						.append(red)
						.append(green)
						.append(blue)
						.toHashCode();
		}
		
		public int compareTo(PaintWithHashCode other)
		{
			return Double.compare(liters, other.liters); 
		}	
	}

	
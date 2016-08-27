import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

	// Making it comparable is a simple way of allowing it to be stored in TreeSet and TreeMap
	public class PaintWithEqualsAndInvalidHashCode implements Comparable<PaintWithEqualsAndInvalidHashCode>
	{
		private double liters;
		private int red;
		private int green;
		private int blue;
		private static int hashCode;
	
		public PaintWithEqualsAndInvalidHashCode(double liters, int red, int green, int blue)
		{
			this.liters = liters;
			this.red = red;
			this.green = green;
			this.blue = blue;
		}
		
		@Override public boolean equals(Object obj)
		{
		   if (obj == null) { return false; }
		   if (obj == this) { return true; }
		   if (obj.getClass() != getClass()) { return false; }
		   PaintWithEqualsAndInvalidHashCode other = (PaintWithEqualsAndInvalidHashCode) obj;
			return new EqualsBuilder()
						.append(liters,other.liters)
						.append(red,other.red)
						.append(green,other.green)
						.append(blue,other.blue)
						.isEquals();
		}
		
		@Override public int hashCode()
		{
			// This hash code function violates both of the first two provisions of the Object.hashCode() contract
			// See javadoc of same method in superclass
			return hashCode++;
		}
		
		public int compareTo(PaintWithEqualsAndInvalidHashCode other)
		{
			return Double.compare(liters, other.liters); 
		}	
	}

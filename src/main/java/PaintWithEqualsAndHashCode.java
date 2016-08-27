import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


	// Making it comparable is a simple way of allowing it to be stored in TreeSet and TreeMap
	public class PaintWithEqualsAndHashCode implements Comparable<PaintWithEqualsAndHashCode>
	{
		private double liters;
		private int red;
		private int green;
		private int blue;
	
		public PaintWithEqualsAndHashCode(double liters, int red, int green, int blue)
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
		   PaintWithEqualsAndHashCode other = (PaintWithEqualsAndHashCode) obj;
			return new EqualsBuilder()
						.append(liters,other.liters)
						.append(red,other.red)
						.append(green,other.green)
						.append(blue,other.blue)
						.isEquals();
		}
		
		@Override public int hashCode(){
			return new HashCodeBuilder(17, 37)
						.append(liters)
						.append(red)
						.append(green)
						.append(blue)
						.toHashCode();
		}
		
		public int compareTo(PaintWithEqualsAndHashCode other){
			return Double.compare(liters, other.liters); 
		}	
	}

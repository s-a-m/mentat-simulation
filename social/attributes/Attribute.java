/**
 * 
 */
package social.attributes;


/**
 * @author Samer
 *
 */
public interface Attribute extends Comparable<Object>{

	public void useDefaultValue();
	
	public void generateRandomValue();
	
	public int compareTo(Object arg0);
	
	public String toString();

	public double getNormalized();
}

/**
 * 
 */
package social.indiv.strats;

import social.indiv.extensions.IndivMainAttributes;

/**
 * @author samer
 *
 */
public interface IStrategySimilarity {
	
	public double similarity(IndivMainAttributes own, IndivMainAttributes other);

	public double getSimilarLimit();
}

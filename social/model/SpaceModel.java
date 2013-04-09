/**
 * 
 */
package social.model;

import uchicago.src.sim.analysis.DataSource;
import uchicago.src.sim.analysis.Sequence;

/**
 * @author samer
 * 
 * Container class for the *Att*InSpace classes, needed for the sequence graphs. It must be 
 * a BasicModel as the classes use its functions.
 * 
 */
public abstract class SpaceModel extends ComplexModel {

	
	class numAgentsInSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			double numAgs = (double) getAgentList().size();
			return numAgs;
		}
	}

	class ideologyInSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			double id = getStats().meanIdeology();
			return id;
		}
	}

	class educationInSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			double ed = getStats().meanEducation();
			return ed;
		}
	}

	class religionInSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			double rl = getStats().meanReligion();
			/*
			 * if(is1stValidationYear())
			 * stats.saveStat1stValidationYear("meanReligion", rl); else
			 * if(is2ndValidationYear())
			 * stats.saveStat2ndValidationYear("meanReligion", rl);
			 */
			return rl;
		}
	}

	class economyInSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			double ec = getStats().meanEconomy();
			return ec;
		}
	}

	class religClassifEcInSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			double rlEc = getStats().religClassif("EC");
			return rlEc;
		}
	}

	class religClassifLowInSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			double rlLow = getStats().religClassif("LOW");
			return rlLow;
		}
	}

	class religClassifAltInSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			double rlAlt = getStats().religClassif("ALT");
			return rlAlt;
		}
	}

	class religClassifNonInSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			double rlNon = getStats().religClassif("NON");
			return rlNon;
		}
	}

	class homosexAcc1InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().homosexAccPorc(1);
		}
	}

	class homosexAcc2InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().homosexAccPorc(2);
		}
	}

	class homosexAcc3InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().homosexAccPorc(3);
		}
	}

	class homosexAcc4InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().homosexAccPorc(4);
		}
	}

	class homosexAcc5InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().homosexAccPorc(5);
		}
	}

	class homosexAcc6InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().homosexAccPorc(6);
		}
	}

	class homosexAcc7InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().homosexAccPorc(7);
		}
	}

	class homosexAcc8InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().homosexAccPorc(8);
		}
	}

	class homosexAcc9InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().homosexAccPorc(9);
		}
	}

	class homosexAcc10InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().homosexAccPorc(10);
		}
	}

	class homosexAcc99InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().homosexAccPorc(99);
		}
	}

	class prosAcc1InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().prosAccPorc(1);
		}
	}

	class prosAcc2InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().prosAccPorc(2);
		}
	}

	class prosAcc3InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().prosAccPorc(3);
		}
	}

	class prosAcc4InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().prosAccPorc(4);
		}
	}

	class prosAcc5InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().prosAccPorc(5);
		}
	}

	class prosAcc6InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().prosAccPorc(6);
		}
	}

	class prosAcc7InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().prosAccPorc(7);
		}
	}

	class prosAcc8InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().prosAccPorc(8);
		}
	}

	class prosAcc9InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().prosAccPorc(9);
		}
	}

	class prosAcc10InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().prosAccPorc(10);
		}
	}

	class prosAcc99InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().prosAccPorc(99);
		}
	}

	class abortAcc1InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().abortAccPorc(1);
		}
	}

	class abortAcc2InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().abortAccPorc(2);
		}
	}

	class abortAcc3InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().abortAccPorc(3);
		}
	}

	class abortAcc4InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().abortAccPorc(4);
		}
	}

	class abortAcc5InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().abortAccPorc(5);
		}
	}

	class abortAcc6InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().abortAccPorc(6);
		}
	}

	class abortAcc7InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().abortAccPorc(7);
		}
	}

	class abortAcc8InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().abortAccPorc(8);
		}
	}

	class abortAcc9InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().abortAccPorc(9);
		}
	}

	class abortAcc10InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().abortAccPorc(10);
		}
	}

	class abortAcc99InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().abortAccPorc(99);
		}
	}

	class divorAcc1InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().divorAccPorc(1);
		}
	}

	class divorAcc2InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().divorAccPorc(2);
		}
	}

	class divorAcc3InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().divorAccPorc(3);
		}
	}

	class divorAcc4InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().divorAccPorc(4);
		}
	}

	class divorAcc5InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().divorAccPorc(5);
		}
	}

	class divorAcc6InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().divorAccPorc(6);
		}
	}

	class divorAcc7InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().divorAccPorc(7);
		}
	}

	class divorAcc8InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().divorAccPorc(8);
		}
	}

	class divorAcc9InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().divorAccPorc(9);
		}
	}

	class divorAcc10InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().divorAccPorc(10);
		}
	}

	class divorAcc99InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().divorAccPorc(99);
		}
	}

	class euthaAcc1InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().euthaAccPorc(1);
		}
	}

	class euthaAcc2InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().euthaAccPorc(2);
		}
	}

	class euthaAcc3InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().euthaAccPorc(3);
		}
	}

	class euthaAcc4InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().euthaAccPorc(4);
		}
	}

	class euthaAcc5InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().euthaAccPorc(5);
		}
	}

	class euthaAcc6InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().euthaAccPorc(6);
		}
	}

	class euthaAcc7InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().euthaAccPorc(7);
		}
	}

	class euthaAcc8InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().euthaAccPorc(8);
		}
	}

	class euthaAcc9InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().euthaAccPorc(9);
		}
	}

	class euthaAcc10InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().euthaAccPorc(10);
		}
	}

	class euthaAcc99InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().euthaAccPorc(99);
		}
	}

	class suiciAcc1InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().suiciAccPorc(1);
		}
	}

	class suiciAcc2InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().suiciAccPorc(2);
		}
	}

	class suiciAcc3InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().suiciAccPorc(3);
		}
	}

	class suiciAcc4InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().suiciAccPorc(4);
		}
	}

	class suiciAcc5InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().suiciAccPorc(5);
		}
	}

	class suiciAcc6InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().suiciAccPorc(6);
		}
	}

	class suiciAcc7InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().suiciAccPorc(7);
		}
	}

	class suiciAcc8InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().suiciAccPorc(8);
		}
	}

	class suiciAcc9InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().suiciAccPorc(9);
		}
	}

	class suiciAcc10InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().suiciAccPorc(10);
		}
	}

	class suiciAcc99InSpace implements DataSource, Sequence {

		public Object execute() {
			return new Double(getSValue());
		}

		public double getSValue() {
			return getStats().suiciAccPorc(99);
		}
	}
}


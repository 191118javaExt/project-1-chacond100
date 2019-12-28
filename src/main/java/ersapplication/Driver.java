package ersapplication;
import org.apache.log4j.Logger;

import static java.lang.System.out;

public class Driver {
	private static Logger logger = Logger.getLogger(Driver.class);
	public static void main(String[] args) {
		System system = new System();
		Employee employee = system.getReimbursement(1);
	}
}

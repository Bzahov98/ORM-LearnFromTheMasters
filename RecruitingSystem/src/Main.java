
import com.bzahov.Utils.DemoClass;
import com.bzahov.Utils.SessionHolder;


public class Main {

	public static void main(final String[] args) throws Exception {
		try {
			//DemoClass.objectDemoCreationWithoutReferences();
			//DemoClass.objectDemoCreationWithReferences();
			//DemoClass.demoMaxJobAdsPerEmployerError();
			//DemoClass.demoMultipleRecordsOnOneJobAddError();
			DemoClass.queriesDemoCreation();

		} finally {
			SessionHolder.closeSession();
		}
	}
}
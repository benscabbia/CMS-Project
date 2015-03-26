import javax.swing.SwingUtilities;

/**
 * TesterClass is a carefully assembled test driver providing a full-demostration on the functionality
 * of the system. For this class, please run the main method
 * 
 * @author Benjamin Scabbia
 * @version Task4
 */
public class TesterClass {
    public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new CopierSystemGui();
			}
		});		
	}
}
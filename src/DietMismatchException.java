/**Exception that is thrown when there is a mismatch in the diet of the predator and the type of the prey
 * @author Santhosh Senthil, 115871889, R01
 */
public class DietMismatchException extends Exception {

    public DietMismatchException(){
        System.out.println("ERROR: This prey cannot be added as it does not match the diet of the predator");
    }
}

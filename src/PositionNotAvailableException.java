/**Exception thrown when an OrganismNode object is inserted into a node with no space for it
 * @author Santhosh Senthil, 115871889, R01
 */
public class PositionNotAvailableException extends Exception {
    public PositionNotAvailableException(){
        System.out.println("ERROR: There is no more room for more prey for this predator");
    }
}

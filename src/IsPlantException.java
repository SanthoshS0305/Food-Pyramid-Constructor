/**Exception that is thrown when a plant is called in a situation meant for an animal
 * @author Santhosh Senthil, 115871889, R01
 */
public class IsPlantException extends Exception {
    public IsPlantException(){
        System.out.println("ERROR: The cursor is at a plant node. Plants cannot be predators");
    }
}

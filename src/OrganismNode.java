/** Tree node that contains information about the organism and its children
 * @author Santhosh Senthil, 115871889, R01
 */
public class OrganismNode {
    private String name;
    private boolean isPlant;
    private boolean isHerbivore;
    private boolean isCarnivore;
    private OrganismNode left;
    private OrganismNode right;
    private OrganismNode middle;

    /**
     * default constructor for OrganismNode
     */
    public OrganismNode(){
        left=null;
        middle=null;
        right=null;
    }

    /**
     * Constructor for OrganismNode with parameters
     * @param name Name of the organism being created
     * @param isPlant boolean for whether the organism is a plant
     * @param isHerbivore assuming isPlant is false, boolean for whether the organism is a herbivore
     * @param isCarnivore assuming isPlant is false, boolean for whether the organism is a carnivore
     */
    public OrganismNode(String name, boolean isPlant, boolean isHerbivore, boolean isCarnivore){
        this.name=name;
        this.isPlant=isPlant;
        this.isHerbivore=isHerbivore;
        this.isCarnivore=isCarnivore;
        left=null;
        right=null;
        middle=null;
    }

    /**
     * getter method for name variable
     * @return String name of organism
     */
    public String getName() {
        return name;
    }

    /**
     * getter method for isPlant
     * @return boolean value of isPlant
     */
    public boolean isPlant() {
        return isPlant;
    }

    /**
     * getter method for isHerbivore
     * @return boolean value of isHerbivore
     */
    public boolean isHerbivore() {
        return isHerbivore;
    }

    /**
     * getter method for isCarnivore
     * @return boolean value of isCarnivore
     */
    public boolean isCarnivore() {
        return isCarnivore;
    }

    /**
     * getter method for left prey of organism
     * @return value of left prey of organism
     */
    public OrganismNode getLeft() {
        return left;
    }

    /**
     * getter method for right prey of organism
     * @return value of right prey of organism
     */
    public OrganismNode getRight() {
        return right;
    }

    /**
     * getter method for middle prey of organism
     * @return value of middle prey of organism
     */
    public OrganismNode getMiddle() {
        return middle;
    }

    /**
     * setter method for left prey of organism
     * @param left left prey of organism
     */
    public void setLeft(OrganismNode left) {
        this.left = left;
    }

    /**
     * setter method for right prey of organism
     * @param right right prey of organism
     */
    public void setRight(OrganismNode right) {
        this.right = right;
    }

    /**
     * setter method for middle prey of organism
     * @param middle middle prey of organism
     */
    public void setMiddle(OrganismNode middle) {
        this.middle = middle;
    }

    /**
     * Method that adds prey to the selected organism
     * @param preyNode the prey object to be added
     * @throws PositionNotAvailableException predator has no space for more prey
     * @throws IsPlantException organism is a plant, and cannot be prey
     * @throws DietMismatchException diet of the predator does not match the type of the prey
     */
    public void addPrey(OrganismNode preyNode) throws PositionNotAvailableException, IsPlantException, DietMismatchException{
            if (preyNode.isPlant)
                throw new IsPlantException();
            if (!isCarnivore)
                throw new DietMismatchException();
            if (right!=null)
                throw new PositionNotAvailableException();
            if (left!=null){
                if (middle!=null){
                    right = preyNode;
                }
                else middle=preyNode;
            }
            else left = preyNode;
    }
}

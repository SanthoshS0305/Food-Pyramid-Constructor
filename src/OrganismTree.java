/**OrganismTree object contains references and methods for the tree of OrganismNodes
 * @author Santhosh Senthil, 115871889, R01
 */
public class OrganismTree {
    private OrganismNode root;
    private OrganismNode cursor;

    /**
     * Constructor for OrganismTree object
     * @param apexPredator the OrganismNode for the Apex Predator that is the root of the tree
     */
    public OrganismTree(OrganismNode apexPredator){
        if (apexPredator.isPlant())
            return;
        root=apexPredator;
        cursor=root;
    }

    /**
     * method that resets the cursor to the root
     */
    public void cursorReset(){
        cursor=root;
    }

    /**
     * getter method for cursor
     * @return cursor reference
     */
    public OrganismNode getCursor(){
        return cursor;
    }

    /**
     * method that moves cursor to one of its children
     * @param name name of child that cursor will move to
     * @throws IllegalArgumentException thrown if name does not match the name of any children
     */
    public void moveCursor(String name) throws IllegalArgumentException{
        try{
            if (cursor.getLeft()==null)
                throw new IllegalArgumentException("ERROR: This prey does not exist for this predator");
            if (cursor.getLeft().getName().equalsIgnoreCase(name)) {
                cursor = cursor.getLeft();
                return;
            }
            if (cursor.getMiddle()==null)
                throw new IllegalArgumentException("ERROR: This prey does not exist for this predator");
            if (cursor.getMiddle().getName().equalsIgnoreCase(name)) {
                cursor = cursor.getMiddle();
                return;
            }
            if (cursor.getRight()==null)
                throw new IllegalArgumentException("ERROR: This prey does not exist for this predator");
            if (cursor.getRight().getName().equalsIgnoreCase(name)) {
                cursor = cursor.getRight();
                return;
            }
            throw new IllegalArgumentException("ERROR: This prey does not exist for this predator");
        } catch (IllegalArgumentException e){
            System.out.println(e);
        }
    }

    /**
     * lists the prey of an organism as a string
     * @return formatted string that lists the organism as well as its direct prey
     * @throws IsPlantException thrown if cursor is a plant
     */
    public String listPrey() throws IsPlantException{
        if (cursor.isPlant())
            throw new IsPlantException();
        if (cursor.getLeft()==null)
            return cursor.getName();
        return cursor.getName()+" -> "+cursor.getLeft().getName()+ (cursor.getMiddle()==null?"":(", "+cursor.getMiddle().getName())) + (cursor.getRight()==null?"":(", "+cursor.getRight().getName()));
    }

    /**
     * lists path from root to cursor as a string
     * @return string that contains formatted path from root to cursor
     */
    public String listFoodChain(){
        OrganismNode tempCursor = root;
        String[] path = new String[30];
        inPath(tempCursor, path, 0, cursor.getName());
        String out=listPath(path, 0);
        return out.substring(0,out.length()-4);
    }

    /**
     * recursive helper method that converts string[] of organisms in the path into a formatted string
     * @param path
     * @param size
     * @return
     */
    private String listPath(String[] path, int size){
        if (path[size]!=null)
            return path[size++]+" -> "+listPath(path, size);
        return "";
    }

    /**
     * recursive helper method that fills a String[] with the names of the organisms in the path
     * @param cursor temporary cursor node that traverses from root to cursor
     * @param container String[] that contains the path names
     * @param containerSize size of the portion of the container that is not null. Increments during iteration
     * @param target string that contains name of cursor, that denotes the destination of path
     * @return boolean that shows whether target is in path
     */
    private boolean inPath(OrganismNode cursor, String[] container, int containerSize, String target){
        if (cursor==null)
            return false;
        container[containerSize++]=cursor.getName();
        if (cursor.getName().equalsIgnoreCase(target))
            return true;
        if (inPath(cursor.getLeft(), container, containerSize, target)||inPath(cursor.getMiddle(), container, containerSize, target)
                ||inPath(cursor.getRight(), container, containerSize, target))
            return true;
        container[containerSize--]=null;
        return false;
    }

    /**
     * method that prints the tree from the cursor onwards
     */
    public void printOrganismTree(){
        OrganismNode tempCursor = cursor;
        printTreeHelper(tempCursor);
    }

    /**
     * helper method for printOrganismTree object
     * @param cursor Iterative temporary cursor that updates with each recursive call
     */
    private void printTreeHelper(OrganismNode cursor){
        if (cursor!=null){
            String[] path = new String[30];
            OrganismNode tempRoot=root;
            inPath(tempRoot, path, 0, cursor.getName());
            System.out.print("\t".repeat(listPath(path, 0).split(" -> ").length));
            System.out.print((cursor.isPlant()?"":"|")+"- ");
            System.out.print(cursor.getName()+"\n");
            printTreeHelper(cursor.getLeft());
            printTreeHelper(cursor.getMiddle());
            printTreeHelper(cursor.getRight());
        }
    }

    /**
     * method that lists all plants from the subtree formed from the cursor
     * @return formatted string containing all plants from the cursor onwards
     */
    public String listAllPlants(){
        String[] plants = new String[30];
        OrganismNode tempCursor = cursor;
        leafFinder(tempCursor, plants);
        String out = listPath(plants, 0);
        return out.substring(0,out.length()-4).replace(" -> ", ", ");
    }

    /**
     * helper method that finds the plant and inserts it into a string[]
     * @param cursor starting point of the traversal that updates with each recursive call
     * @param plants String[] that contains all the plant organisms in the scope.
     */
    private void leafFinder(OrganismNode cursor, String[] plants){
        if (cursor==null)
            return;
        if (cursor.isPlant()){
            int i = 0;
            while (plants[i]!=null)
                i++;
            plants[i]=cursor.getName();
        }

        leafFinder(cursor.getLeft(), plants);
        leafFinder(cursor.getMiddle(), plants);
        leafFinder(cursor.getRight(), plants);
    }

    /**
     * Method that adds an Animal Child to the tree
     * @param name name of animal
     * @param isHerbivore boolean for whether animal is a herbivore
     * @param isCarnivore boolean for whether animal is a carnivore. if isHerbivore is also true, then animal is an omnivore
     * @throws IllegalArgumentException thrown when predator diet does not match prey type
     */
    public void addAnimalChild(String name, boolean isHerbivore, boolean isCarnivore) throws IllegalArgumentException, DietMismatchException, PositionNotAvailableException, IsPlantException {
            if ((cursor.getMiddle()!=null&&cursor.getMiddle().getName().equalsIgnoreCase(name))||(cursor.getLeft()!=null&&cursor.getLeft().getName().equalsIgnoreCase(name)))
                throw new IllegalArgumentException("ERROR: This prey already exists for this predator");
            cursor.addPrey(new OrganismNode(name, false, isHerbivore, isCarnivore));
    }

    /**
     * Method that adds a Plant Child to the tree
     * @param name name of plant
     * @throws IllegalArgumentException thrown when predator diet does not match prey type
     * @throws PositionNotAvailableException predator has no space for more prey
     */
    public void addPlantChild(String name) throws IllegalArgumentException, PositionNotAvailableException, IsPlantException {
            if (cursor.getRight()!=null)
                throw new PositionNotAvailableException();
            if (cursor.isPlant())
                throw new IsPlantException();
            if (!cursor.isHerbivore())
                throw new IllegalArgumentException("ERROR: This prey cannot be added as it does not match the diet of the predator");
            if ((cursor.getMiddle()!=null&&cursor.getMiddle().getName().equalsIgnoreCase(name))||(cursor.getLeft()!=null&&cursor.getLeft().getName().equalsIgnoreCase(name)))
                throw new IllegalArgumentException("ERROR: This prey already exists for this predator");
            OrganismNode plantNode = new OrganismNode(name, true, false, false);
            if (cursor.getLeft()!=null){
                if (cursor.getMiddle()!=null){
                    cursor.setRight(plantNode);
                }
                else cursor.setMiddle(plantNode);
            }
            else cursor.setLeft(plantNode);
    }

    /**
     * Method that removes child from predator node
     * @param name name of the child to be removed
     * @throws IllegalArgumentException thrown if child to be removed does not exist
     */
    public void removeChild(String name) throws IllegalArgumentException{
        if (cursor.getLeft()==null)
            throw new IllegalArgumentException();
        if (!cursor.getLeft().getName().equalsIgnoreCase(name)){
            if (cursor.getMiddle()==null)
                throw new IllegalArgumentException();
            if (!cursor.getMiddle().getName().equalsIgnoreCase(name)){
                if (cursor.getRight()==null)
                    throw new IllegalArgumentException();
                if (!cursor.getRight().getName().equalsIgnoreCase(name)){
                    throw new IllegalArgumentException();
                }else cursor.setRight(null);
            }
            else {
                cursor.setMiddle(cursor.getRight());
                cursor.setRight(null);
            }
        }
        else {
            cursor.setLeft(cursor.getMiddle());
            cursor.setMiddle(cursor.getRight());
            cursor.setRight(null);
        }
    }
}

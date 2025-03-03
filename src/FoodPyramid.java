/** Main method for OrganismTree and OrganismNode objects
 * @author Santhosh Senthil, 115871889, R01
 */
import java.util.Scanner;

public class FoodPyramid {
    private static OrganismTree tree;

    /**
     * default constructor for FoodPyramid Object
     */
    public FoodPyramid(){}

    public static void main(String[] args) throws PositionNotAvailableException, IsPlantException, DietMismatchException {
        String test = "bald eagle\n" +
                "C\n" +
                "AC\n" +
                "python\n" +
                "C\n" +
                "AC\n" +
                "carp\n" +
                "H\n" +
                "AC\n" +
                "prairie dog\n" +
                "H\n" +
                "RC\n" +
                "prairie dog\n" +
                "AC\n" +
                "raccoon\n" +
                "O\n" +
                "p\n" +
                "F\n" +
                "M\n" +
                "carp\n" +
                "PC\n" +
                "pondweed\n" +
                "PC\n" +
                "kelp\n" +
                "LP\n" +
                "M\n" +
                "pondweed\n" +
                "C\n" +
                "R\n" +
                "M\n" +
                "python\n" +
                "AC\n" +
                "lizard\n" +
                "O\n" +
                "AC\n" +
                "mallard duck\n" +
                "O\n" +
                "M\n" +
                "lizard\n" +
                "AC\n" +
                "fly\n" +
                "O\n" +
                "C\n" +
                "AC\n" +
                "cricket\n" +
                "O\n" +
                "P\n" +
                "F\n" +
                "M\n" +
                "cricket\n" +
                "PC\n" +
                "ragweed\n" +
                "PC\n" +
                "crabgrass\n" +
                "PC\n" +
                "leaves\n" +
                "F\n" +
                "R\n" +
                "M\n" +
                "python\n" +
                "M\n" +
                "lizard\n" +
                "M\n" +
                "fly\n" +
                "PC\n" +
                "grass\n" +
                "PC\n" +
                "nectar\n" +
                "R\n" +
                "M\n" +
                "python\n" +
                "M\n" +
                "mallard duck\n" +
                "PC\n" +
                "seeds\n" +
                "R\n" +
                "F\n" +
                "M\n" +
                "raccoon\n" +
                "AC\n" +
                "crayfish\n" +
                "O\n" +
                "PC\n" +
                "berries\n" +
                "AC\n" +
                "clams\n" +
                "O\n" +
                "M\n" +
                "crayfish\n" +
                "C\n" +
                "PC\n" +
                "java moss\n" +
                "R\n" +
                "M\n" +
                "raccoon\n" +
                "M\n" +
                "clams\n" +
                "PC\n" +
                "algae\n" +
                "PC\n" +
                "phytoplankton\n" +
                "PC\n" +
                "cyanobacteria\n" +
                "P\n" +
                "M\n" +
                "cyanobacteria\n" +
                "C\n" +
                "R\n" +
                "F\n" +
                "LP\n" +
                "RC\n" +
                "raccoon\n" +
                "F\n" +
                "R\n" +
                "M\n" +
                "carp\n" +
                "PC\n" +
                "kelp\n" +
                "Q";
        Scanner in = new Scanner(System.in);
        System.out.println("What is the name of the apex predator?: ");
        String apexName = in.nextLine();
        System.out.println("Is the organism an herbivore / a carnivore / an omnivore? (H / C / O): ");
        String apexType = in.nextLine();
        if (apexType.equalsIgnoreCase("H")){
            tree = new OrganismTree(new OrganismNode(apexName, false, true, false));
        }
        else if (apexType.equalsIgnoreCase("C")){
            tree = new OrganismTree(new OrganismNode(apexName, false, false, true));
        }
        else if (apexType.equalsIgnoreCase("O")){
            tree = new OrganismTree(new OrganismNode(apexName, false, true, true));
        }
        System.out.print("Constructing food pyramid\n");

        System.out.println("(PC) - Create New Plant Child\n" +
                "(AC) - Create New Animal Child\n" +
                "(RC) - Remove Child\n" +
                "(P) - Print Out Cursor’s Prey\n" +
                "(C) - Print Out Food Chain\n" +
                "(F) - Print Out Food Pyramid at Cursor\n" +
                "(LP) - List All Plants Supporting Cursor\n" +
                "(R) - Reset Cursor to Root\n" +
                "(M) - Move Cursor to Child\n" +
                "(Q) - Quit");
        String option;
        do{
            System.out.println("Please select an option: ");
            option = in.nextLine();
            if (option.equalsIgnoreCase("PC")){
                try{
                    if (tree.getCursor().isPlant())
                        throw new IsPlantException();
                    if (tree.getCursor().getRight()!=null)
                        throw new PositionNotAvailableException();
                    if (!tree.getCursor().isHerbivore())
                        throw new DietMismatchException();
                    System.out.println("What is the name of the organism?: ");
                    String name = in.nextLine();
                    tree.addPlantChild(name);
                    System.out.println(name+" has successfully been added as prey for the "+tree.getCursor().getName());
                } catch (DietMismatchException | PositionNotAvailableException | IsPlantException | IllegalArgumentException e){
                    System.out.println(e);
                }

            }

            else if (option.equalsIgnoreCase("AC")){
                try{
                    if (tree.getCursor().getRight()!=null)
                        throw new PositionNotAvailableException();
                    if (!tree.getCursor().isCarnivore())
                        throw new DietMismatchException();
                    System.out.println("What is the name of the organism?: ");
                    String name = in.nextLine();
                    System.out.println("Is the organism an herbivore / a carnivore / an omnivore? (H / C / O): ");
                    String type = in.nextLine();
                    if (type.equalsIgnoreCase("H")){
                        tree.addAnimalChild(name, true, false);
                    }
                    else if (type.equalsIgnoreCase("C")){
                        tree.addAnimalChild(name, false, true);
                    }
                    else if (type.equalsIgnoreCase("O")){
                        tree.addAnimalChild(name, true, true);
                    }
                    else System.out.println("Illegal Argument, Try Again");
                    System.out.println("A(n) "+name+" has successfully been added as prey for the "+tree.getCursor().getName()+"!");
                } catch(DietMismatchException | PositionNotAvailableException e){
                    System.out.println(e);
                }
            }

            else if (option.equalsIgnoreCase("RC")){
                System.out.println("What is the name of the organism to be removed?: ");
                String name = in.nextLine();
                tree.removeChild(name);
                System.out.println("A(n) "+name+" has been successfully removed as prey for the "+tree.getCursor().getName()+"!");
            }

            else if (option.equalsIgnoreCase("P")){
                System.out.println(tree.listPrey());
            }

            else if (option.equalsIgnoreCase("C")){
                System.out.println(tree.listFoodChain());
            }

            else if (option.equalsIgnoreCase("F")){
                tree.printOrganismTree();
            }

            else if (option.equalsIgnoreCase("LP")){
                System.out.println(tree.listAllPlants());
            }

            else if (option.equalsIgnoreCase("R")){
                tree.cursorReset();
                System.out.println("cursor successfully reset to root");
            }

            else if (option.equalsIgnoreCase("M")){
                System.out.println("What is the name of the child?");
                tree.moveCursor(in.nextLine());
                System.out.println("Cursor successfully moved to "+tree.getCursor().getName());
            }
            else if (!option.equalsIgnoreCase("Q"))
                System.out.println("Illegal Argument, Try Again");
        }while (!option.equalsIgnoreCase("Q"));
        System.out.println("Make like a tree and leave!");
        in.close();
    }
}

package pathPlot;

import java.util.Random;
import java.util.Scanner;

public class Simulation {

    public static void GridSetter(){
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        // Setting the grid size
        System.out.println("No of colomns in the grid: ");
        int cols = scanner.nextInt();
        System.out.println("No of rows in the grid: ");
        int rows = scanner.nextInt();
        Grid grid = new Grid(cols,rows);

        // Set Start and Goal Node
        scanner.nextLine();
        System.out.println("Do you want to randomly Generate start and goal Node(y/n)? ");
        String decision = scanner.nextLine();

        if(decision.equals("y")){ // Randomly Set Start and Goal Node
            grid.setStartNode(random.nextInt(cols),random.nextInt(rows));
            grid.setGoalNode(random.nextInt(cols),random.nextInt(rows));
            if (grid.startNode==grid.goalNode){
                while(grid.startNode==grid.goalNode){
                    grid.setStartNode(random.nextInt(cols),random.nextInt(rows));
                    grid.setGoalNode(random.nextInt(cols),random.nextInt(rows));
                }
            }
        }
        else{ // Manually Set Start and Goal node
            System.out.println("Column no of the start node: ");
            int num1 = scanner.nextInt();
            System.out.println("Row no of the start node: ");
            int num2 = scanner.nextInt();
            grid.setStartNode(num1,num2);
            System.out.println();
            System.out.println("Column no of the goal node: ");
            num1 = scanner.nextInt();
            System.out.println("Row no of the goal node: ");
            num2 = scanner.nextInt();
            grid.setGoalNode(num1,num2);
            scanner.nextLine();
        }

        // Setting Obstacles
        System.out.println("Do you want to randomly add obstacles(y/n)?  ");
        decision = scanner.nextLine();

        if (decision.equals("y")){ // Randomly Setting Obstacles
            System.out.println("How many obstacles do you want to generate: ");
            int noOfObstacles = scanner.nextInt();
            for (int i=0; i<noOfObstacles; i++){
                grid.setObstacleNode(random.nextInt(cols),random.nextInt(rows));
            }
        }
        else{ // Manually Setting Obstacles
            boolean looping= true;
            while (looping) {
                System.out.println("In what column do you want to set your obstacle");
                cols = scanner.nextInt();
                System.out.println("In what row do you want to set your obstacle");
                rows = scanner.nextInt();
                grid.setObstacleNode(cols,rows);

                scanner.nextLine();
                System.out.println("Do you want to add another obstacle(y/n)? ");
                decision = scanner.nextLine();
                if (decision.equals("n")){
                    looping=false;
                }
            }
        }
        grid.Astar();
        grid.GridGenerator();

    }


    public static void main(String[] args) {
        // Some example Grid Layouts

        // initializing Grid
        Grid grid1 = new Grid(15,9);

        // Set start and goal Node
        grid1.setStartNode(3, 6);
        grid1.setGoalNode(11, 3);

        // placing obstacle nodes
        grid1.setObstacleNode(10, 2);
        grid1.setObstacleNode(10, 3);
        grid1.setObstacleNode(10, 4);
        grid1.setObstacleNode(10, 5);
        grid1.setObstacleNode(10, 6);
        grid1.setObstacleNode(10, 7);
        grid1.setObstacleNode(6, 2);
        grid1.setObstacleNode(7, 2);
        grid1.setObstacleNode(8, 2);
        grid1.setObstacleNode(9, 2);
        grid1.setObstacleNode(11, 7);
        grid1.setObstacleNode(12, 7);
        grid1.setObstacleNode(6, 1);

        // executing the algorithm
        grid1.Astar();
        grid1.GridGenerator(); //displaying the Grid after the algorithm has been executed

        System.out.println();

        // initializing Grid
        Grid grid2= new Grid(5,7);

        // Set start and goal Node
        grid2.setStartNode(0,3);
        grid2.setGoalNode(4,3);

        // placing obstacle nodes
        grid2.setObstacleNode(1,2);
        grid2.setObstacleNode(1,4);
        grid2.setObstacleNode(3,1);
        grid2.setObstacleNode(3,2);
        grid2.setObstacleNode(3,3);
        grid2.setObstacleNode(3,4);
        grid2.setObstacleNode(3,5);

        // executing the algorithm
        grid2.Astar();
        grid2.GridGenerator(); //displaying the Grid after the algorithm has been executed

        System.out.println();

        // Method to customly generate a Grid and find the shortest path
        GridSetter();

    }
}

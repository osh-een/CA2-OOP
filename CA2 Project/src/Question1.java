import java.util.List;

/**
 *  Your Name: OLUWADAMILARE DAVID ADEKEYE
 *  Class Group: SD2B
 */
public class Question1 {
    public static void main(String[] args) {

        System.out.println("Question 1");

        // Instantiate ContainerManager and container objects
        ContainerManager manager = new ContainerManager();

        Box box1 = new Box(3.0, 4.0, 5.0, 10.0);
        Cylinder cylinder1 = new Cylinder(6.0, 3.0, 12.0);
        Pyramid pyramid1 = new Pyramid(4.0, 6.0, 15.0);

        // Add containers to manager
        manager.add(box1);
        manager.add(cylinder1);
        manager.add(pyramid1);

        // Output total weight and total rectangular volume
        System.out.println("Total Weight: " + manager.totalWeight());
        System.out.println("Total Rectangular Volume: " + manager.totalRectangularVolume());

        // Get all containers and print details
        List<IMeasurableContainer> allContainers = manager.getAllContainers();
        for (IMeasurableContainer container : allContainers) {
            if (container instanceof Box box) {
                System.out.println("Box - Length: " + box.getLength() + ", Width: " + box.getWidth() +
                        ", Depth: " + box.getDepth() + ", Weight: " + box.getWeight());
            } else if (container instanceof Cylinder cylinder) {
                System.out.println("Cylinder - Height: " + cylinder.getHeight() + ", Diameter: " + cylinder.getDiameter() +
                        ", Weight: " + cylinder.getWeight());
            } else if (container instanceof Pyramid pyramid) {
                System.out.println("Pyramid - Length: " + pyramid.getLength() + ", Side Length: " + pyramid.getSideLength() +
                        ", Weight: " + pyramid.getWeight());
            }
        }
    }
}
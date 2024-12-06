class Cylinder implements IMeasurableContainer {

    private final double height;
    private final double diameter;
    private final double weight;

    public Cylinder(double height, double diameter, double weight) {
        this.height = height;
        this.diameter = diameter;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public double rectangularVolume() {
        double radius = diameter / 2;
        double volume = Math.PI * Math.pow(radius, 2) * height;
        // Rectangular volume is the bounding box volume for the cylinder
        double boundingBoxVolume = diameter * diameter * height;
        return boundingBoxVolume;
    }

    public double getHeight() {
        return height;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getWeight() {
        return weight;
    }
}
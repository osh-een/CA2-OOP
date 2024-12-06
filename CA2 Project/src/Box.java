class Box implements IMeasurableContainer {

    private final double length;
    private final double width;
    private final double depth;
    private final double weight;

    public Box(double length, double width, double depth, double weight) {
        this.length = length;
        this.width = width;
        this.depth = depth;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public double rectangularVolume() {
        return length * width * depth;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getDepth() {
        return depth;
    }

    public double getWeight() {
        return weight;
    }
}

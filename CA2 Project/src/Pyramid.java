class Pyramid implements IMeasurableContainer {

    private final double length;
    private final double sideLength;
    private final double weight;

    public Pyramid(double length, double sideLength, double weight) {
        this.length = length;
        this.sideLength = sideLength;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public double rectangularVolume() {
        return length * length * sideLength; // Assuming it's a square base pyramid
    }

    public double getLength() {
        return length;
    }

    public double getSideLength() {
        return sideLength;
    }

    public double getWeight() {
        return weight;
    }
}
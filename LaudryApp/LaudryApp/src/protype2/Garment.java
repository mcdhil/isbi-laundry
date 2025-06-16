package protype2;

public class Garment {
	private String garmentId;
    private String name; // e.g., "Shirt", "Trousers", "Dress", "Bedsheet"
    private String material; // e.g., "Cotton", "Silk", "Polyester"
    private String color;
    private LaundryCareInstruction defaultCareInstruction;
    private double baseProcessingCost; // A hypothetical base cost for processing this type of garment

    /**
     * Constructor for Garment.
     * @param garmentId A unique identifier for the garment type.
     * @param name The name of the garment.
     * @param material The material of the garment.
     * @param baseProcessingCost The base cost associated with processing this garment type.
     */
    public Garment(String garmentId, String name, String material, double baseProcessingCost) {
        this.garmentId = garmentId;
        this.name = name;
        this.material = material;
        this.color = "Various"; // Default color
        this.baseProcessingCost = baseProcessingCost;
        // Default care instructions can be set here or later
        this.defaultCareInstruction = new LaundryCareInstruction("Standard Wash", "Tumble Dry Low", "Medium Iron", false);
    }

    // Getters
    public String getGarmentId() {
        return garmentId;
    }

    public String getName() {
        return name;
    }

    public String getMaterial() {
        return material;
    }

    public String getColor() {
        return color;
    }

    public LaundryCareInstruction getDefaultCareInstruction() {
        return defaultCareInstruction;
    }

    public double getBaseProcessingCost() {
        return baseProcessingCost;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDefaultCareInstruction(LaundryCareInstruction defaultCareInstruction) {
        this.defaultCareInstruction = defaultCareInstruction;
    }

    public void setBaseProcessingCost(double baseProcessingCost) {
        this.baseProcessingCost = baseProcessingCost;
    }

    /**
     * Provides details about the garment.
     * @return A string containing garment details.
     */
    public String getGarmentDetails() {
        return "Garment: " + name + " (ID: " + garmentId + "), Material: " + material +
               ", Color: " + color +
               (defaultCareInstruction != null ? "\nCare: " + defaultCareInstruction.getInstructions() : "");
    }

    @Override
    public String toString() {
        return name + " (" + material + ")";
    }
}

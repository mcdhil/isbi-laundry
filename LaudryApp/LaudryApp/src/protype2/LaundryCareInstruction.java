package protype2;

import java.util.UUID;

public class LaundryCareInstruction {
	private String instructionId;
    private String washingTemp; // e.g., "Cold", "Warm (30C)", "Hot (60C)"
    private String dryingMethod; // e.g., "Tumble Dry Low", "Hang Dry", "Lay Flat to Dry"
    private String ironingTemp; // e.g., "Low", "Medium", "High", "No Iron"
    private boolean bleachAllowed;

    /**
     * Constructor for LaundryCareInstruction.
     * @param washingTemp Washing temperature instructions.
     * @param dryingMethod Drying method instructions.
     * @param ironingTemp Ironing temperature instructions.
     * @param bleachAllowed Whether bleach is allowed or not.
     */
    public LaundryCareInstruction(String washingTemp, String dryingMethod, String ironingTemp, boolean bleachAllowed) {
        this.instructionId = UUID.randomUUID().toString();
        this.washingTemp = washingTemp;
        this.dryingMethod = dryingMethod;
        this.ironingTemp = ironingTemp;
        this.bleachAllowed = bleachAllowed;
    }

    // Getters
    public String getInstructionId() {
        return instructionId;
    }

    public String getWashingTemp() {
        return washingTemp;
    }

    public String getDryingMethod() {
        return dryingMethod;
    }

    public String getIroningTemp() {
        return ironingTemp;
    }

    public boolean isBleachAllowed() {
        return bleachAllowed;
    }

    // Setters
    public void setWashingTemp(String washingTemp) {
        this.washingTemp = washingTemp;
    }

    public void setDryingMethod(String dryingMethod) {
        this.dryingMethod = dryingMethod;
    }

    public void setIroningTemp(String ironingTemp) {
        this.ironingTemp = ironingTemp;
    }

    public void setBleachAllowed(boolean bleachAllowed) {
        this.bleachAllowed = bleachAllowed;
    }

    /**
     * Returns a formatted string of all care instructions.
     * @return A string summarizing the care instructions.
     */
    public String getInstructions() {
        return "Wash: " + washingTemp +
               ", Dry: " + dryingMethod +
               ", Iron: " + ironingTemp +
               ", Bleach: " + (bleachAllowed ? "Allowed" : "Not Allowed");
    }

    @Override
    public String toString() {
        return getInstructions();
    }
}

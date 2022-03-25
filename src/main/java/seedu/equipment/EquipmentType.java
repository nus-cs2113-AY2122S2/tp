package seedu.equipment;

public enum EquipmentType {
    MICROPHONE, SPEAKER, STAND, CABLE;

    public static String getAllTypes() {
        StringBuilder sb = new StringBuilder();
        for (EquipmentType type : EquipmentType.values()) {
            sb.append(type.name() + ", ");
        }
        return sb.toString();
    }
}

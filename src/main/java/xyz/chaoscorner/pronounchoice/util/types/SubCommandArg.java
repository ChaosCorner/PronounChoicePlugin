package xyz.chaoscorner.pronounchoice.util.types;

public class SubCommandArg {
    private String name;
    private boolean required;
    private String requiredPermission;

    public SubCommandArg(String name, boolean required, String requiredPermission) {
        this.name = name;
        this.required = required;
        this.requiredPermission = requiredPermission;
    }

    public String getName() {
        return name;
    }

    public boolean isRequired() {
        return required;
    }

    @Override
    public String toString() {
        String beginningChar = required ? "<" : "[";
        String endingChar = required ? ">" : "]";
        return String.format("%s%s%s", beginningChar, name, endingChar);
    }
}

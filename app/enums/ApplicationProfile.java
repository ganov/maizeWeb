package enums;


import be.objectify.deadbolt.java.models.Role;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * application Profiles.
 */
public enum ApplicationProfile implements Role {
    /**
     * User default / R1 profile.
     */
    R1("User"),

    /**
     * R2 profile.
     */
    R2("Vendor"),

    /**
     * Admin user profile.
     */
    R3("Admin");

    /**
     * Profile's label.
     */
    private final String label;

    /**
     * constructor.
     *
     */
    ApplicationProfile(String s) {
        label = s;
    }

    @Override
    public String getName() {
        return label;
    }

    @Override
    public String toString() {
        return name();
    }

    /**
     * Map for @select in templates.
     *
     * @return app profiles
     */
    public static Map<String, String> options() {
        Map<String, String> options = new LinkedHashMap<>();

        List<ApplicationProfile> profiles = Arrays.asList(ApplicationProfile.values());
        for (final ApplicationProfile profile : profiles) {
            options.put(profile.name(), profile.getName());
        }

        return options;
    }
}

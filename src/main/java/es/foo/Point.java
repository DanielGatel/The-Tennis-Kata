package es.foo;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public enum Point {

    P_LOVE("0"), P_15("15"), P_30("30"), P_40("40"), ADVANTAGE("A");


    String value;

    public String getValue() {
        return value;
    }

    Point(final String value) {
        this.value = value;
    }

    public static Point forValue(final String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        return Arrays.stream(Point.values())
                .filter(p -> p.getValue().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Point %s not valid", name)));
    }
}

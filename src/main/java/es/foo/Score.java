package es.foo;

import org.apache.commons.lang3.tuple.Pair;

public interface Score {

    Point getPoint(PlayerRole playerRole);
    Pair<Point, Point> getPoints();
}

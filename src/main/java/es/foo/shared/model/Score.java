package es.foo.shared.model;


public interface Score {

    Point getPoint(PlayerRole playerRole);
    void setPoint(PlayerRole playerRole, Point point);
}

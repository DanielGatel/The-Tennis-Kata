package es.foo.game;


public interface Score {

    Point getPoint(PlayerRole playerRole);
    void setPoint(PlayerRole playerRole, Point point);
}

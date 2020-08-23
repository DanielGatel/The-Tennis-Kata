package es.foo.game.impl;

import es.foo.game.PlayerRole;
import es.foo.game.Point;
import es.foo.game.Score;
import lombok.Getter;
import lombok.Setter;

public class ScoreImpl implements Score {


    @Getter
    @Setter
    private Point server;
    @Getter
    @Setter
    private Point receiver;

    public ScoreImpl() {
        server = Point.P_LOVE;
        receiver = Point.P_LOVE;
    }

    public ScoreImpl(Point server, Point receiver) {
this.server = server;
this.receiver = receiver;
    }

    public ScoreImpl(Score score) {
        server = score.getPoint(PlayerRole.SERVER);
        receiver = score.getPoint(PlayerRole.RECEIVER);
    }

    @Override
    public Point getPoint(final PlayerRole playerRole) {
        return PlayerRole.SERVER.equals(playerRole) ? server : receiver;
    }

    @Override
    public void setPoint(final PlayerRole playerRole, final Point point) {
        if (playerRole == PlayerRole.SERVER) {
            server = point;
        } else if (playerRole == PlayerRole.RECEIVER) {
            receiver = point;
        }
    }


}

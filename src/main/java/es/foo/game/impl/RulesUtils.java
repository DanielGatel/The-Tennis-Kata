package es.foo.game.impl;

import es.foo.game.PlayerRole;
import es.foo.game.Point;
import es.foo.game.Score;
import io.cucumber.messages.internal.com.google.common.collect.Sets;

import java.util.Set;

public class RulesUtils {

    protected static final Set<Point> DEUCE = Sets.immutableEnumSet(Point.P_40, Point.ADVANTAGE);

    private RulesUtils() {
    }

    public static Score resolveNormalPoint(final Score score, final PlayerRole pointWinner) {
        final Score scoreNew = new ScoreImpl(score);
        switch (score.getPoint(pointWinner)){
            case P_LOVE:
                scoreNew.setPoint(pointWinner, Point.P_15);
                break;
            case P_15:
                scoreNew.setPoint(pointWinner, Point.P_30);
                break;
            case P_30:
                scoreNew.setPoint(pointWinner, Point.P_40);
                break;
            case P_40:
                scoreNew.setPoint(pointWinner, Point.WIN);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + score.getPoint(pointWinner));
        }
        return scoreNew;
    }

    public static Score resolveDeuce(final Score score, final PlayerRole pointWinner) {
        final Score scoreNew = new ScoreImpl(score);
        if(Point.ADVANTAGE.equals(score.getPoint(pointWinner))){
            scoreNew.setPoint(pointWinner, Point.WIN);
        } else if (Point.P_40.equals(score.getPoint(PlayerRole.SERVER)) && Point.P_40.equals(score.getPoint(PlayerRole.RECEIVER))) {
            scoreNew.setPoint(pointWinner, Point.ADVANTAGE);
        } else {
            scoreNew.setPoint(PlayerRole.SERVER, Point.P_40);
            scoreNew.setPoint(PlayerRole.RECEIVER, Point.P_40);
        }
        return scoreNew;
    }

    public static boolean isDeuce(Score score) {

        return DEUCE.contains(score.getPoint(PlayerRole.SERVER)) && DEUCE.contains(score.getPoint(PlayerRole.RECEIVER));

    }
}

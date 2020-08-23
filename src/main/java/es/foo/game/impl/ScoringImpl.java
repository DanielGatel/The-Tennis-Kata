package es.foo.game.impl;

import es.foo.game.PlayerRole;
import es.foo.game.Point;
import es.foo.game.Score;
import es.foo.game.Scoring;
import io.cucumber.messages.internal.com.google.common.collect.Sets;

import java.util.Optional;
import java.util.Set;

public class ScoringImpl implements Scoring {

    protected static final Set<Point> NOT_IN_DEUCE = Sets.immutableEnumSet(Point.P_LOVE, Point.P_15, Point.P_30);

    protected Score score;

    public ScoringImpl(Score score) {
        this.score = score;
    }

    @Override
    public void update(final PlayerRole pointWinner) {

        if(isDeuce(score)){
            resolveDeuce(pointWinner);
        } else {
            switch (score.getPoint(pointWinner)){

                case P_LOVE:
                    score.setPoint(pointWinner, Point.P_15);
                    break;
                case P_15:
                    score.setPoint(pointWinner, Point.P_30);
                    break;
                case P_30:
                    score.setPoint(pointWinner, Point.P_40);
                    break;
                case P_40:
                    score.setPoint(pointWinner, Point.WIN);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + score.getPoint(pointWinner));
            }
        }


    }

    private void resolveDeuce(final PlayerRole pointWinner) {
        if(Point.ADVANTAGE.equals(score.getPoint(pointWinner))){
            score.setPoint(pointWinner, Point.WIN);
        } else if (Point.P_40.equals(score.getPoint(PlayerRole.SERVER)) && Point.P_40.equals(score.getPoint(PlayerRole.RECEIVER))) {
            score.setPoint(pointWinner, Point.ADVANTAGE);
        } else {
            score.setPoint(PlayerRole.SERVER, Point.P_40);
            score.setPoint(PlayerRole.RECEIVER, Point.P_40);
        }
    }

    @Override
    public Score getScore() {
        return score;
    }

    @Override
    public Optional<PlayerRole> getGameWinner() {
        if(Point.WIN.equals(score.getPoint(PlayerRole.SERVER))){
            return Optional.of(PlayerRole.SERVER);
        } else if(Point.WIN.equals(score.getPoint(PlayerRole.RECEIVER))){
            return Optional.of(PlayerRole.RECEIVER);
        }
        return Optional.empty();
    }

    public boolean isDeuce(Score score) {

        return !(NOT_IN_DEUCE.contains(score.getPoint(PlayerRole.SERVER)) || NOT_IN_DEUCE.contains(score.getPoint(PlayerRole.RECEIVER)));

    }


}

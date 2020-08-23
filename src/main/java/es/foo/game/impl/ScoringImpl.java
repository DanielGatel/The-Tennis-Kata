package es.foo.game.impl;

import es.foo.game.PlayerRole;
import es.foo.game.Point;
import es.foo.game.Score;
import es.foo.game.Scoring;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

public class ScoringImpl implements Scoring {

    protected State state;

    @Getter
    @Setter
    protected Collection<Consumer<State>> rules;

    protected static final Collection<Consumer<State>> RULES_DEFAULT = Set.of( Rules.deuce, Rules.score);

    public ScoringImpl(Score score) {
        state = new State();
        state.setScoreOld(score);
        rules = RULES_DEFAULT;
    }

    @Override
    public void update(final PlayerRole pointWinner) {

        startStep(pointWinner);

        rules.forEach(rule -> rule.accept(state));

        endStep();

    }

    private void startStep(final PlayerRole pointWinner) {
        state.setWinner(pointWinner);
    }

    private void endStep() {
        state.setScoreOld(state.getScoreNew());
        state.setScoreNew(null);
        state.setWinner(null);
    }

    @Override
    public Score getScore() {
        return state.getScoreOld();
    }

    @Override
    public Optional<PlayerRole> getGameWinner() {
        final Score score = state.getScoreOld();
        if(Point.WIN.equals(score.getPoint(PlayerRole.SERVER))){
            return Optional.of(PlayerRole.SERVER);
        } else if(Point.WIN.equals(score.getPoint(PlayerRole.RECEIVER))){
            return Optional.of(PlayerRole.RECEIVER);
        }
        return Optional.empty();
    }




}

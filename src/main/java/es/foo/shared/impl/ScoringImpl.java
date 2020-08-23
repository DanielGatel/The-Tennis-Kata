package es.foo.shared.impl;

import es.foo.shared.model.PlayerRole;
import es.foo.shared.model.Point;
import es.foo.shared.model.Score;
import es.foo.shared.Scoring;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

/**
 * The type Scoring.
 */
@Slf4j
public class ScoringImpl implements Scoring {

    /**
     * Default rules.
     */
    protected static final Collection<Consumer<State>> RULES_DEFAULT = Set.of(Rules.DEUCE, Rules.SCORE);

    /**
     * The state of the game.
     */
    protected State state;

    /**
     * The Rules to apply.
     */
    @Getter
    @Setter
    protected Collection<Consumer<State>> rules;


    /**
     * Instantiates a new Scoring.
     *
     */
    public ScoringImpl() {
        state = new State();
        state.setScoreOld(new ScoreImpl());
        log.info("Score set to default");
        rules = RULES_DEFAULT;
        log.info("Rules set to default");
    }

    /**
     * Instantiates a new Scoring.
     *
     * @param score
     *         the score
     */
    public ScoringImpl(Score score) {
        state = new State();
        state.setScoreOld(score);
        rules = RULES_DEFAULT;
        log.info("Rules set to default");
    }

    /**
     * Instantiates a new Scoring with custom rules.
     *
     * @param score
     *         the score
     * @param rules
     *         the rules
     */
    public ScoringImpl(Score score, Collection<Consumer<State>> rules) {
        state = new State();
        state.setScoreOld(score);
        this.rules = rules;
    }

    @Override
    public void update(final PlayerRole pointWinner) {

        log.info("Updateing state (+point {})", pointWinner);
        log.trace("State to update:\n{}", state);

        startStep(pointWinner);

        rules.forEach(rule -> rule.accept(state));

        endStep();

        log.trace("Point ends with state:\n{}", state);

    }

    private void startStep(final PlayerRole pointWinner) {
        state.setWinner(pointWinner);
        log.info("Start step");
    }

    private void endStep() {
        state.setScoreOld(state.getScoreNew());
        state.setScoreNew(null);
        state.setWinner(null);
        log.debug("End  step");
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

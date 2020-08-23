package es.foo.shared.impl;

import es.foo.shared.model.Score;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

/**
 * Main Rules.
 */
@Slf4j
public class Rules {

    /**
     * Deuce and Advantage Score.
     */
    public static final Consumer<State> DEUCE = state -> {
        if (RulesUtils.isDeuce(state.getScoreOld()) && state.getScoreNew() == null) {
            log.debug("Applying deuce rule");
            final Score scoreNew = RulesUtils.resolveDeuce(state.getScoreOld(), state.getWinner());
            state.setScoreNew(scoreNew);
        }
    };
    /**
     * Winning a Point Increases Score.
     */
    public static final Consumer<State> SCORE = state -> {
        if (state.getScoreNew() == null) {
            log.debug("Applying score rule");
            final Score scoreNew = RulesUtils.resolveNormalPoint(state.getScoreOld(), state.getWinner());
            state.setScoreNew(scoreNew);
        }
    };


    private Rules() {
    }

}

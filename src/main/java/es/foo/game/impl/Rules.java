package es.foo.game.impl;

import es.foo.game.Score;

import java.util.function.Consumer;

public class Rules {

    private Rules() {
    }

    public static final Consumer<State> deuce = (state) ->
        {
            if(RulesUtils.isDeuce(state.getScoreOld()) && state.getScoreNew() == null){
                final Score scoreNew = RulesUtils.resolveDeuce(state.getScoreOld(), state.getWinner());
                state.setScoreNew(scoreNew);
            }
        };


    public static final Consumer<State> score = (state) ->
{
            if(state.getScoreNew() == null){
                final Score scoreNew = RulesUtils.resolveNormalPoint(state.getScoreOld(), state.getWinner());
                state.setScoreNew(scoreNew);
            }
        };

}

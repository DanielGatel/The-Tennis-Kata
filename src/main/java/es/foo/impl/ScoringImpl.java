package es.foo.impl;

import es.foo.PlayerRole;
import es.foo.Score;
import es.foo.Scoring;

import java.util.Optional;

public class ScoringImpl implements Scoring {
    @Override
    public void update(final PlayerRole pointWinner) {

    }

    @Override
    public Score getScore() {
        return null;
    }

    @Override
    public Optional<PlayerRole> getGameWinner() {
        return Optional.empty();
    }
}

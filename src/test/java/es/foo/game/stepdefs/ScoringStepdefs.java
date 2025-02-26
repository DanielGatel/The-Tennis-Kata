package es.foo.game.stepdefs;

import es.foo.game.PlayerRole;
import es.foo.game.Point;
import es.foo.game.Score;
import es.foo.game.Scoring;
import es.foo.game.impl.ScoreImpl;
import es.foo.game.impl.ScoringImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScoringStepdefs {

    protected Scoring scoring;


    protected static final Pattern SCORE_PATTERN = Pattern.compile("(?<p1>0|15|30|40|A):(?<p2>0|15|30|40|A)");


    @Given("the score is {string}")
    public void theScoreIs(String score) {
        final Matcher matcher = SCORE_PATTERN.matcher(score);
        Assert.assertTrue("Score format error", matcher.matches());
        scoring = new ScoringImpl(new ScoreImpl(Point.forValue(matcher.group("p1")), Point.forValue(matcher.group("p2"))));
    }

    @Then("the score should be {string}")
    public void theScoreShouldBe(String score) {

        final Score scoreActual = scoring.getScore();
        String scoreString = String.join(":", scoreActual.getPoint(PlayerRole.SERVER).getValue(),
                                         scoreActual.getPoint(PlayerRole.RECEIVER).getValue());
        Assert.assertEquals(score, scoreString);

    }

    @Then("the {string} should win")
    public void theShouldWin(String winner) {

        final Optional<PlayerRole> winnerOptionalActual = scoring.getGameWinner();

        String winnerActual = winnerOptionalActual.map(Enum::name).orElse("");

        Assert.assertEquals("Winner is not the same", winner.toUpperCase(), winnerActual);

    }

    @When("the {string} wins a point")
    public void theWinsAPoint(String player) {

        final PlayerRole pointWinner = PlayerRole.valueOf(player.toUpperCase());
        scoring.update(pointWinner);
    }
}

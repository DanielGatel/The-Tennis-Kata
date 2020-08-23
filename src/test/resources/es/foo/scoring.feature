Feature: Scoring a Game in Real Time

  Scenario Outline: Winning a Point Increases Score Correctly
    Given the score is "<score>"
    When the "<player>" wins a point
    Then the score should be "<result>"
    Examples:
      | score | player | result |
      | 0:0   | server | 15:0   |
      | 15:15 | server | 15:30  |
      | 30:30 | server | 40:30  |

  Scenario Outline: Deuce and Advantage are Scored Correctly
    Given the score is "<score>"
    When the "<player>" wins a point
    Then the score should be "<result>"
    Examples:
      | score | player   | result |
      | 40:40 | receiver | 40:A   |
      | A:40  | receiver | 40:40  |
      | 30:30 | receiver | 40:30  |

  Scenario Outline: Winning Points are Scored Correctly
    Given the score is "<score>"
    When the "<player>" wins a point
    Then the "<winner>" should win
    Examples:
      | score | player   | winner   |
      | 40:30 | server   | server   |
      | 40:A  | receiver | receiver |


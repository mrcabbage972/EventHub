package com.codecademy.eventhub.storage.visitor;

import com.codecademy.eventhub.model.User;
import com.codecademy.eventhub.storage.filter.ExactMatch;
import com.codecademy.eventhub.storage.filter.Regex;

public class UserFilterVisitor implements Visitor {
  private final User user;

  public UserFilterVisitor(User user) {
    this.user = user;
  }

  @Override
  public boolean visit(ExactMatch exactMatch) {
    return exactMatch instanceof ExactMatch && user.get(exactMatch.getKey()).equals(exactMatch.getValue());
  }

  @Override
  public boolean visit(Regex regex) {
    return regex instanceof Regex && user.get(regex.getKey()).matches(regex.getPattern());
  }
}
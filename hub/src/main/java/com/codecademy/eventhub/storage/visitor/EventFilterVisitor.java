package com.codecademy.eventhub.storage.visitor;

import com.codecademy.eventhub.model.Event;
import com.codecademy.eventhub.storage.filter.ExactMatch;
import com.codecademy.eventhub.storage.filter.Regex;

public class EventFilterVisitor implements Visitor {
  private final Event event;

  public EventFilterVisitor(Event event) {
    this.event = event;
  }

  @Override
  public boolean visit(ExactMatch exactMatch) {
    String property = event.get(exactMatch.getKey());
    if (exactMatch instanceof ExactMatch) {
      if (property == null) {
        return false;
      }
      return property.equals(exactMatch.getValue());
    }
    return false;
  }

  @Override
  public boolean visit(Regex regex) {
    return regex.getPattern().matcher(event.get(regex.getKey())).matches();
  }
}
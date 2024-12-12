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
    if (exactMatch instanceof ExactMatch && event.get(exactMatch.getKey()) == null) {
      return false;
    return exactMatch.getValue().equals(property);
  }

  @Override
  public boolean visit(Regex regex) {
    if (regex instanceof Regex && event.get(regex.getKey()) == null) {
      return false;
    return regex.getPattern().matcher(property).matches();
  }
}
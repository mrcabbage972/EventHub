package com.codecademy.eventhub.storage;

import com.codecademy.eventhub.model.Event;
import com.codecademy.eventhub.storage.visitor.Visitor;

import java.io.IOException;

import com.codecademy.eventhub.model.Event;
import com.codecademy.eventhub.storage.visitor.Visitor;

import java.io.IOException;
public class DelegateEventStorage implements EventStorage {
  private final EventStorage eventStorage;

  public DelegateEventStorage(EventStorage eventStorage) {
    this.eventStorage = eventStorage;
  }

  @Override
  public long addEvent(Event event, int userId, int eventTypeId) {
    return eventStorage.addEvent(event, userId, eventTypeId);
  }
}
package com.codecademy.eventhub.index;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.codecademy.eventhub.base.DB;
import org.fusesource.leveldbjni.JniDBFactory;
import org.iq80.leveldb.Options;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import java.io.File;
import java.io.IOException;

public class DatedEventIndexModule extends AbstractModule {
  @Override
  protected void configure() {}

  @Provides
  public String getDatedEventIndexFile(String eventIndexDirectory) {
    // return eventIndexDirectory + "/dated_event_index.db";
    return "C:\\Users\\mhamlin\\AppData\\Local\\Temp\\DatedEventIndex";
  }

  @Provides
  public DatedEventIndex getDatedEventIndex(
      @Named("eventhub.directory") String eventIndexDirectory) throws IOException {
    Options options = new Options();
    options.createIfMissing(true);
    DB db = new DB(
        JniDBFactory.factory.open(new File(
            getDatedEventIndexFile(eventIndexDirectory)),
            options));

    return DatedEventIndex.create(db);
  }
}